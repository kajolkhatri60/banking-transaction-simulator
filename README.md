# Multithreaded Banking Transaction Simulator

A production-quality full-stack banking application demonstrating concurrent transaction processing using Java multithreading and a modern React frontend.

## 📋 Project Overview

This project simulates a real-world banking system where multiple users can perform deposits and withdrawals concurrently. The backend uses Java's ExecutorService with a thread pool to handle concurrent transactions safely, while the frontend provides a sleek, professional dashboard for real-time transaction monitoring.

## 🏗️ Architecture

### Backend Architecture
- **Framework**: Spring Boot 3.2.0
- **Language**: Java 17
- **Concurrency**: ExecutorService with fixed thread pool (5 threads)
- **Thread Safety**: Synchronized methods on Account model
- **API**: RESTful endpoints with CORS enabled
- **Build Tool**: Maven

### Frontend Architecture
- **Framework**: React 18
- **Build Tool**: Vite
- **HTTP Client**: Axios
- **Styling**: Custom CSS (Black & White theme)
- **Real-time Updates**: Polling every 2 seconds

## 🧵 How Multithreading Works

### Thread Pool Configuration
```java
ExecutorService executorService = Executors.newFixedThreadPool(5);
```

The system uses a fixed thread pool with 5 worker threads. When a transaction request arrives:

1. The request is submitted to the ExecutorService as a Callable task
2. One of the 5 available threads picks up the task
3. The thread executes the transaction (deposit/withdraw)
4. Thread-safe operations are ensured using synchronized methods
5. Transaction details (including thread name) are logged and stored

### Thread Safety Mechanisms

**Synchronized Account Methods:**
```java
public synchronized void deposit(double amount) {
    balance += amount;
    System.out.println(Thread.currentThread().getName() + 
        " deposited " + amount + " | Balance: " + balance);
}

public synchronized boolean withdraw(double amount) {
    if (balance >= amount) {
        balance -= amount;
        System.out.println(Thread.currentThread().getName() + 
            " withdrew " + amount + " | Balance: " + balance);
        return true;
    }
    return false;
}
```

**Thread-Safe Repository:**
- Uses `CopyOnWriteArrayList` for concurrent read/write operations
- Uses `AtomicLong` for thread-safe ID generation

### Console Output Example
```
pool-1-thread-1 deposited 500.0 | Balance: 5500.0
pool-1-thread-3 withdrew 200.0 | Balance: 5300.0
pool-1-thread-2 deposited 1000.0 | Balance: 6300.0
pool-1-thread-4 withdrew 150.0 | Balance: 6150.0
```

## 📁 Project Structure

```
banking-transaction-simulator/
├── backend/
│   ├── src/main/java/com/bank/
│   │   ├── controller/
│   │   │   └── TransactionController.java
│   │   ├── service/
│   │   │   └── TransactionService.java
│   │   ├── model/
│   │   │   ├── Account.java
│   │   │   └── Transaction.java
│   │   ├── repository/
│   │   │   └── TransactionRepository.java
│   │   └── BankingApplication.java
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
│
└── frontend/
    ├── src/
    │   ├── components/
    │   │   ├── Dashboard.jsx
    │   │   ├── Dashboard.css
    │   │   ├── TransactionForm.jsx
    │   │   ├── TransactionForm.css
    │   │   ├── TransactionTable.jsx
    │   │   └── TransactionTable.css
    │   ├── services/
    │   │   └── api.js
    │   ├── App.jsx
    │   ├── App.css
    │   ├── main.jsx
    │   └── index.css
    ├── index.html
    ├── vite.config.js
    └── package.json
```

## 🚀 Setup Instructions

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Node.js 16+ and npm
- Git

### Backend Setup

1. Navigate to backend directory:
```bash
cd banking-transaction-simulator/backend
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The backend server will start on `http://localhost:8080`

### Frontend Setup

1. Navigate to frontend directory:
```bash
cd banking-transaction-simulator/frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm run dev
```

The frontend will start on `http://localhost:3000`

## 🔌 API Endpoints

### GET /api/balance
Get current account balance.

**Response:**
```json
{
  "balance": 5000.0
}
```

### POST /api/deposit
Deposit money into account.

**Request:**
```json
{
  "amount": 500.0
}
```

**Response:**
```json
{
  "balance": 5500.0,
  "message": "Deposit successful"
}
```

### POST /api/withdraw
Withdraw money from account.

**Request:**
```json
{
  "amount": 200.0
}
```

**Success Response:**
```json
{
  "balance": 5300.0,
  "message": "Withdrawal successful"
}
```

**Error Response (Insufficient Balance):**
```json
{
  "error": "Insufficient balance"
}
```

### GET /api/transactions
Get all transaction history.

**Response:**
```json
[
  {
    "id": 1,
    "type": "DEPOSIT",
    "amount": 500.0,
    "threadName": "pool-1-thread-1",
    "timestamp": "2026-03-11T05:07:30.123"
  },
  {
    "id": 2,
    "type": "WITHDRAW",
    "amount": 200.0,
    "threadName": "pool-1-thread-3",
    "timestamp": "2026-03-11T05:08:15.456"
  }
]
```

## 🎨 UI Features

### Design Theme
- **Color Scheme**: Minimalist Black & White
- **Background**: Pure black (#000000)
- **Text**: White (#ffffff)
- **Cards**: Dark gray (#1a1a1a)
- **Borders**: White with 2px solid
- **Typography**: Large, professional financial dashboard aesthetic

### Dashboard Components

1. **Balance Display**
   - Large, prominent balance amount
   - Real-time updates every 2 seconds
   - Currency formatted (₹)

2. **Transaction Forms**
   - Separate deposit and withdraw sections
   - Input validation
   - Loading states during processing
   - Success/error messages

3. **Transaction History Table**
   - Transaction ID
   - Type (DEPOSIT/WITHDRAW) with badges
   - Amount with currency formatting
   - Thread name (shows which thread processed it)
   - Timestamp
   - Hover effects for better UX

## 🧪 Testing the Multithreading

To see multithreading in action:

1. Open the backend console to see thread logs
2. Open multiple browser tabs with the frontend
3. Perform multiple deposits/withdrawals simultaneously from different tabs
4. Observe in the console that different threads handle different requests
5. Check the transaction table to see which thread processed each transaction

## 🔒 Thread Safety Features

- **Synchronized Methods**: Prevent race conditions on balance updates
- **CopyOnWriteArrayList**: Thread-safe transaction storage
- **AtomicLong**: Thread-safe ID generation
- **ExecutorService**: Managed thread pool prevents thread explosion
- **Future Objects**: Proper handling of asynchronous results

## 📊 Key Features

✅ Concurrent transaction processing with 5-thread pool  
✅ Thread-safe account operations  
✅ Real-time balance updates  
✅ Complete transaction history with thread tracking  
✅ Professional black & white UI  
✅ Error handling for insufficient balance  
✅ RESTful API design  
✅ Responsive design  
✅ Loading states and user feedback  

## 🛠️ Technologies Used

### Backend
- Spring Boot 3.2.0
- Java 17
- Maven
- ExecutorService (java.util.concurrent)
- Spring Web
- Spring Validation

### Frontend
- React 18.2.0
- Vite 5.0.8
- Axios 1.6.2
- Modern CSS3

## 📝 Notes

- Initial account balance is set to ₹5000
- Thread pool size is configured to 5 threads
- Frontend polls for updates every 2 seconds
- All transactions are stored in-memory (resets on server restart)
- CORS is enabled for local development

## 🎯 Learning Outcomes

This project demonstrates:
- Java multithreading with ExecutorService
- Thread synchronization and safety
- Concurrent programming patterns
- RESTful API design
- React state management
- Real-time UI updates
- Full-stack integration
- Professional UI/UX design

## 📸 Screenshots

[Add screenshots of your running application here]

## 🤝 Contributing

This is a demonstration project. Feel free to fork and enhance!

## 📄 License

MIT License - Feel free to use this project for learning purposes.

---

**Built with ❤️ for learning concurrent programming and full-stack development**
