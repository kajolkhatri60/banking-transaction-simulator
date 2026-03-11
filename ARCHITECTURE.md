# System Architecture

## 🏗️ High-Level Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                         FRONTEND                             │
│                    (React + Vite)                            │
│                   Port: 3000                                 │
│                                                              │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐     │
│  │  Dashboard   │  │ Transaction  │  │ Transaction  │     │
│  │  Component   │  │    Form      │  │    Table     │     │
│  └──────────────┘  └──────────────┘  └──────────────┘     │
│         │                  │                  │             │
│         └──────────────────┴──────────────────┘             │
│                           │                                  │
│                    ┌──────▼──────┐                          │
│                    │   API.js    │                          │
│                    │   (Axios)   │                          │
│                    └──────┬──────┘                          │
└───────────────────────────┼──────────────────────────────────┘
                            │
                    HTTP REST API
                            │
┌───────────────────────────▼──────────────────────────────────┐
│                         BACKEND                              │
│                  (Spring Boot + Java)                        │
│                      Port: 8080                              │
│                                                              │
│  ┌────────────────────────────────────────────────────┐    │
│  │         TransactionController                       │    │
│  │  ┌──────────┐ ┌──────────┐ ┌──────────┐          │    │
│  │  │ /balance │ │ /deposit │ │/withdraw │ /trans   │    │
│  │  └────┬─────┘ └────┬─────┘ └────┬─────┘          │    │
│  └───────┼────────────┼────────────┼─────────────────┘    │
│          │            │            │                        │
│  ┌───────▼────────────▼────────────▼─────────────────┐    │
│  │           TransactionService                       │    │
│  │                                                     │    │
│  │  ┌─────────────────────────────────────────────┐  │    │
│  │  │      ExecutorService (Thread Pool)          │  │    │
│  │  │                                              │  │    │
│  │  │  [Thread-1] [Thread-2] [Thread-3]          │  │    │
│  │  │  [Thread-4] [Thread-5]                      │  │    │
│  │  └─────────────────────────────────────────────┘  │    │
│  │                      │                             │    │
│  └──────────────────────┼─────────────────────────────┘    │
│                         │                                   │
│  ┌──────────────────────▼─────────────────────────────┐    │
│  │              Account (Model)                        │    │
│  │  ┌──────────────────────────────────────────────┐  │    │
│  │  │  synchronized deposit()                      │  │    │
│  │  │  synchronized withdraw()                     │  │    │
│  │  │  synchronized getBalance()                   │  │    │
│  │  └──────────────────────────────────────────────┘  │    │
│  └─────────────────────────────────────────────────────┘    │
│                                                              │
│  ┌─────────────────────────────────────────────────────┐    │
│  │        TransactionRepository                        │    │
│  │  CopyOnWriteArrayList<Transaction>                 │    │
│  │  AtomicLong (ID Generator)                         │    │
│  └─────────────────────────────────────────────────────┘    │
└──────────────────────────────────────────────────────────────┘
```

## 🔄 Request Flow

### Deposit Transaction Flow

```
User clicks "Deposit" button
         │
         ▼
TransactionForm.jsx
         │
         ▼
api.deposit(amount)
         │
         ▼
POST /api/deposit
         │
         ▼
TransactionController.deposit()
         │
         ▼
TransactionService.deposit()
         │
         ▼
ExecutorService.submit(() -> {
    │
    ▼
    Account.deposit() [SYNCHRONIZED]
    │
    ▼
    TransactionRepository.save()
    │
    ▼
    Return new balance
})
         │
         ▼
Response to Frontend
         │
         ▼
Update UI (Balance + Table)
```

## 🧵 Thread Pool Architecture

```
┌─────────────────────────────────────────────────────┐
│           ExecutorService Thread Pool                │
│                  (Fixed Size: 5)                     │
│                                                      │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐         │
│  │ Thread-1 │  │ Thread-2 │  │ Thread-3 │         │
│  │  IDLE    │  │  BUSY    │  │  IDLE    │         │
│  └──────────┘  └──────────┘  └──────────┘         │
│                                                      │
│  ┌──────────┐  ┌──────────┐                        │
│  │ Thread-4 │  │ Thread-5 │                        │
│  │  BUSY    │  │  IDLE    │                        │
│  └──────────┘  └──────────┘                        │
│                                                      │
│  Task Queue: [Task1] [Task2] [Task3] ...           │
└─────────────────────────────────────────────────────┘
```

## 🔐 Thread Safety Mechanisms

### 1. Synchronized Methods
```java
public synchronized void deposit(double amount) {
    // Only one thread can execute this at a time
    balance += amount;
}
```

### 2. Thread-Safe Collections
```java
// Multiple threads can read/write safely
CopyOnWriteArrayList<Transaction> transactions;
AtomicLong idCounter;
```

### 3. Managed Thread Pool
```java
// Prevents thread explosion
ExecutorService = Executors.newFixedThreadPool(5);
```

## 📊 Data Flow

```
┌──────────────┐
│   Account    │
│  balance:    │
│   5000.0     │
└──────┬───────┘
       │
       │ Thread-1: deposit(500)
       ▼
┌──────────────┐
│   Account    │
│  balance:    │
│   5500.0     │
└──────┬───────┘
       │
       │ Thread-3: withdraw(200)
       ▼
┌──────────────┐
│   Account    │
│  balance:    │
│   5300.0     │
└──────────────┘

Transaction History:
┌────┬─────────┬────────┬──────────┬───────────┐
│ ID │  Type   │ Amount │  Thread  │ Timestamp │
├────┼─────────┼────────┼──────────┼───────────┤
│ 1  │ DEPOSIT │  500   │ Thread-1 │ 10:30:15  │
│ 2  │WITHDRAW │  200   │ Thread-3 │ 10:30:18  │
└────┴─────────┴────────┴──────────┴───────────┘
```

## 🎯 Component Interaction

### Frontend Components

```
App.jsx
  │
  └── Dashboard.jsx
        │
        ├── TransactionForm.jsx (Deposit)
        │     │
        │     └── api.deposit()
        │
        ├── TransactionForm.jsx (Withdraw)
        │     │
        │     └── api.withdraw()
        │
        └── TransactionTable.jsx
              │
              └── api.getTransactions()
```

### Backend Layers

```
Controller Layer
    │
    ├── @GetMapping("/balance")
    ├── @PostMapping("/deposit")
    ├── @PostMapping("/withdraw")
    └── @GetMapping("/transactions")
    │
    ▼
Service Layer
    │
    ├── deposit(amount) → Future<Double>
    ├── withdraw(amount) → Future<String>
    ├── getBalance() → double
    └── getTransactions() → List<Transaction>
    │
    ▼
Model Layer
    │
    ├── Account (synchronized methods)
    └── Transaction (immutable data)
    │
    ▼
Repository Layer
    │
    └── TransactionRepository (thread-safe storage)
```

## 🔄 Concurrency Model

### Race Condition Prevention

```
WITHOUT Synchronization (WRONG):
Thread-1: Read balance (5000)
Thread-2: Read balance (5000)
Thread-1: Add 500 → Write 5500
Thread-2: Add 300 → Write 5300  ❌ Lost Thread-1's update!

WITH Synchronization (CORRECT):
Thread-1: Lock → Read (5000) → Add 500 → Write 5500 → Unlock
Thread-2: Wait...
Thread-2: Lock → Read (5500) → Add 300 → Write 5800 → Unlock ✅
```

## 📡 API Contract

### Request/Response Format

```
Deposit Request:
POST /api/deposit
{
  "amount": 500.0
}

Deposit Response:
{
  "balance": 5500.0,
  "message": "Deposit successful"
}

Withdraw Request:
POST /api/withdraw
{
  "amount": 200.0
}

Withdraw Success:
{
  "balance": 5300.0,
  "message": "Withdrawal successful"
}

Withdraw Failure:
{
  "error": "Insufficient balance"
}

Transaction List:
[
  {
    "id": 1,
    "type": "DEPOSIT",
    "amount": 500.0,
    "threadName": "pool-1-thread-1",
    "timestamp": "2026-03-11T10:30:15"
  }
]
```

## 🎨 UI Architecture

```
┌─────────────────────────────────────────────────┐
│              Dashboard Header                    │
│     "Banking Transaction Simulator"              │
└─────────────────────────────────────────────────┘
┌─────────────────────────────────────────────────┐
│           Balance Card                           │
│                                                  │
│            Balance                               │
│            ₹5000.00                              │
└─────────────────────────────────────────────────┘
┌────────────────────┐  ┌────────────────────────┐
│   Deposit Form     │  │   Withdraw Form        │
│                    │  │                        │
│  Amount: [____]    │  │  Amount: [____]        │
│  [Deposit Button]  │  │  [Withdraw Button]     │
└────────────────────┘  └────────────────────────┘
┌─────────────────────────────────────────────────┐
│         Transaction History Table                │
│                                                  │
│ ID | Type | Amount | Thread | Timestamp         │
│ ───┼──────┼────────┼────────┼──────────         │
│ 1  | DEP  | 500    | Thr-1  | 10:30:15          │
│ 2  | WITH | 200    | Thr-3  | 10:30:18          │
└─────────────────────────────────────────────────┘
```

## 🔧 Technology Stack

```
Frontend Stack:
├── React 18.2.0 (UI Framework)
├── Vite 5.0.8 (Build Tool)
├── Axios 1.6.2 (HTTP Client)
└── CSS3 (Styling)

Backend Stack:
├── Spring Boot 3.2.0 (Framework)
├── Java 17 (Language)
├── Maven (Build Tool)
└── ExecutorService (Concurrency)
```

## 📈 Scalability Considerations

### Current Configuration
- Thread Pool: 5 threads
- Storage: In-memory (CopyOnWriteArrayList)
- Polling: 2-second intervals

### Production Enhancements
- Increase thread pool size based on load
- Add database persistence (PostgreSQL/MySQL)
- Implement WebSocket for real-time updates
- Add Redis for caching
- Implement load balancing
- Add authentication/authorization
- Implement rate limiting

## 🎯 Design Patterns Used

1. **Repository Pattern**: TransactionRepository
2. **Service Layer Pattern**: TransactionService
3. **MVC Pattern**: Controller-Service-Model
4. **Thread Pool Pattern**: ExecutorService
5. **Future Pattern**: Async result handling
6. **Component Pattern**: React components

---

**This architecture ensures thread safety, scalability, and maintainability.**
