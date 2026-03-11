# Quick Reference Card

## 🚀 Start Commands

### Backend
```bash
cd banking-transaction-simulator/backend
mvn spring-boot:run
```

### Frontend
```bash
cd banking-transaction-simulator/frontend
npm install
npm run dev
```

## 🔗 URLs

- **Frontend**: http://localhost:3000
- **Backend**: http://localhost:8080
- **API Base**: http://localhost:8080/api

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/balance | Get current balance |
| POST | /api/deposit | Deposit money |
| POST | /api/withdraw | Withdraw money |
| GET | /api/transactions | Get transaction history |

## 💰 Example API Calls

### Get Balance
```bash
curl http://localhost:8080/api/balance
```

### Deposit
```bash
curl -X POST http://localhost:8080/api/deposit \
  -H "Content-Type: application/json" \
  -d '{"amount": 500}'
```

### Withdraw
```bash
curl -X POST http://localhost:8080/api/withdraw \
  -H "Content-Type: application/json" \
  -d '{"amount": 200}'
```

### Get Transactions
```bash
curl http://localhost:8080/api/transactions
```

## 🧵 Thread Pool Configuration

- **Pool Size**: 5 threads
- **Type**: Fixed thread pool
- **Location**: `TransactionService.java`
- **Thread Names**: pool-1-thread-1 to pool-1-thread-5

## 🎨 UI Components

| Component | Purpose |
|-----------|---------|
| Dashboard.jsx | Main layout and orchestration |
| TransactionForm.jsx | Deposit/Withdraw forms |
| TransactionTable.jsx | Transaction history display |

## 📁 Key Files

### Backend
- `BankingApplication.java` - Main entry point
- `Account.java` - Thread-safe account model
- `TransactionService.java` - Business logic with thread pool
- `TransactionController.java` - REST API endpoints

### Frontend
- `main.jsx` - React entry point
- `App.jsx` - Root component
- `api.js` - API service layer
- `Dashboard.jsx` - Main UI component

## 🔧 Configuration Files

- `pom.xml` - Maven dependencies
- `package.json` - npm dependencies
- `application.properties` - Spring Boot config
- `vite.config.js` - Vite configuration

## 🎯 Initial State

- **Starting Balance**: ₹5000
- **Transaction Count**: 0
- **Thread Pool**: 5 threads ready

## 🐛 Common Issues

| Issue | Solution |
|-------|----------|
| Port 8080 in use | Change in application.properties |
| Port 3000 in use | Accept alternate port when prompted |
| Maven not found | Install Maven |
| npm not found | Install Node.js |
| CORS error | Ensure backend is running |

## 📊 Monitoring

### Backend Console
Watch for logs like:
```
pool-1-thread-2 deposited 500.0 | Balance: 5500.0
```

### Frontend UI
- Balance updates in real-time
- Transaction table shows thread names
- Success/error messages appear

## 🎨 Color Scheme

- Background: `#000000` (Black)
- Text: `#ffffff` (White)
- Cards: `#1a1a1a` (Dark Gray)
- Borders: `#ffffff` (White)

## 📦 Dependencies

### Backend
- Spring Boot 3.2.0
- Java 17

### Frontend
- React 18.2.0
- Vite 5.0.8
- Axios 1.6.2

## 🔐 Thread Safety

- ✅ Synchronized deposit/withdraw methods
- ✅ CopyOnWriteArrayList for transactions
- ✅ AtomicLong for ID generation
- ✅ ExecutorService for thread management

## 📝 Quick Test Sequence

1. Start backend → See "Banking Transaction Simulator Started!"
2. Start frontend → Open http://localhost:3000
3. Check balance → Should show ₹5000.00
4. Deposit ₹1000 → Balance becomes ₹6000.00
5. Withdraw ₹500 → Balance becomes ₹5500.00
6. Check table → See 2 transactions with thread names

---

**Keep this card handy for quick reference!**
