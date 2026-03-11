# 🚀 Run Checklist

Use this checklist to ensure everything is set up correctly before running the application.

## ✅ Pre-Flight Checklist

### System Requirements
- [ ] Java 17 or higher installed
  ```bash
  java -version
  ```
  Expected: java version "17" or higher

- [ ] Maven 3.6+ installed
  ```bash
  mvn -version
  ```
  Expected: Apache Maven 3.6.x or higher

- [ ] Node.js 16+ installed
  ```bash
  node -version
  ```
  Expected: v16.x.x or higher

- [ ] npm installed
  ```bash
  npm -version
  ```
  Expected: 8.x.x or higher

### Port Availability
- [ ] Port 8080 is available (Backend)
  ```bash
  # Windows PowerShell
  Test-NetConnection -ComputerName localhost -Port 8080
  ```

- [ ] Port 3000 is available (Frontend)
  ```bash
  # Windows PowerShell
  Test-NetConnection -ComputerName localhost -Port 3000
  ```

## 🎯 Step-by-Step Launch

### Step 1: Backend Setup
- [ ] Navigate to backend directory
  ```bash
  cd banking-transaction-simulator/backend
  ```

- [ ] Build the project (first time only)
  ```bash
  mvn clean install
  ```
  Expected: "BUILD SUCCESS"

- [ ] Start the backend server
  ```bash
  mvn spring-boot:run
  ```
  Expected: "Banking Transaction Simulator Started!"

- [ ] Verify backend is running
  ```bash
  curl http://localhost:8080/api/balance
  ```
  Expected: `{"balance":5000.0}`

### Step 2: Frontend Setup
- [ ] Open a NEW terminal window

- [ ] Navigate to frontend directory
  ```bash
  cd banking-transaction-simulator/frontend
  ```

- [ ] Install dependencies (first time only)
  ```bash
  npm install
  ```
  Expected: No errors, packages installed

- [ ] Start the frontend server
  ```bash
  npm run dev
  ```
  Expected: "Local: http://localhost:3000"

- [ ] Open browser to http://localhost:3000

### Step 3: Verify Application
- [ ] Frontend loads without errors
- [ ] Balance displays ₹5000.00
- [ ] Deposit form is visible
- [ ] Withdraw form is visible
- [ ] Transaction table is visible (empty initially)

## 🧪 Functional Testing

### Test 1: Deposit Transaction
- [ ] Enter amount: 1000
- [ ] Click "Deposit" button
- [ ] Success message appears
- [ ] Balance updates to ₹6000.00
- [ ] Transaction appears in table
- [ ] Thread name is visible in table

### Test 2: Withdraw Transaction
- [ ] Enter amount: 500
- [ ] Click "Withdraw" button
- [ ] Success message appears
- [ ] Balance updates to ₹5500.00
- [ ] Transaction appears in table
- [ ] Thread name is visible in table

### Test 3: Insufficient Balance
- [ ] Enter amount: 10000 (more than balance)
- [ ] Click "Withdraw" button
- [ ] Error message: "Insufficient balance"
- [ ] Balance remains unchanged

### Test 4: Multithreading Verification
- [ ] Open backend console
- [ ] Perform multiple rapid transactions
- [ ] Observe different thread names in console
  ```
  pool-1-thread-1 deposited 500.0 | Balance: 6000.0
  pool-1-thread-3 withdrew 200.0 | Balance: 5800.0
  ```
- [ ] Verify thread names in UI table match console

### Test 5: Real-time Updates
- [ ] Note current balance
- [ ] Wait 2 seconds
- [ ] Verify balance refreshes automatically
- [ ] Transaction table updates automatically

## 🔍 API Testing (Optional)

### Test Balance Endpoint
```bash
curl http://localhost:8080/api/balance
```
- [ ] Returns JSON with balance

### Test Deposit Endpoint
```bash
curl -X POST http://localhost:8080/api/deposit \
  -H "Content-Type: application/json" \
  -d '{"amount": 500}'
```
- [ ] Returns success response with new balance

### Test Withdraw Endpoint
```bash
curl -X POST http://localhost:8080/api/withdraw \
  -H "Content-Type: application/json" \
  -d '{"amount": 200}'
```
- [ ] Returns success response with new balance

### Test Transactions Endpoint
```bash
curl http://localhost:8080/api/transactions
```
- [ ] Returns array of transactions

## 🎨 UI Verification

### Visual Checks
- [ ] Background is black
- [ ] Text is white
- [ ] Cards have white borders
- [ ] Buttons have hover effects
- [ ] Forms are side-by-side
- [ ] Table is readable and styled
- [ ] Typography is professional

### Responsive Design
- [ ] Resize browser window
- [ ] Forms stack on mobile view
- [ ] Table scrolls horizontally if needed
- [ ] All elements remain visible

## 🐛 Troubleshooting

### Backend Won't Start
- [ ] Check Java version (must be 17+)
- [ ] Check port 8080 availability
- [ ] Review console for error messages
- [ ] Try `mvn clean install` again

### Frontend Won't Start
- [ ] Check Node.js version (must be 16+)
- [ ] Delete node_modules and reinstall
  ```bash
  rm -rf node_modules
  npm install
  ```
- [ ] Check port 3000 availability

### Cannot Connect to Backend
- [ ] Verify backend is running on port 8080
- [ ] Check CORS configuration in TransactionController
- [ ] Verify API_BASE_URL in frontend/src/services/api.js

### Transactions Not Appearing
- [ ] Check browser console for errors (F12)
- [ ] Verify backend console shows transaction logs
- [ ] Check network tab for API responses

## 📊 Performance Checks

- [ ] Transactions complete in < 1 second
- [ ] UI updates smoothly
- [ ] No console errors in browser
- [ ] No exceptions in backend console
- [ ] Multiple rapid transactions work correctly

## 🎉 Success Criteria

All of the following should be true:
- [ ] Backend running on port 8080
- [ ] Frontend running on port 3000
- [ ] Balance displays correctly
- [ ] Deposits work
- [ ] Withdrawals work
- [ ] Insufficient balance validation works
- [ ] Transaction history displays
- [ ] Thread names visible
- [ ] Real-time updates working
- [ ] UI styled correctly (black & white)
- [ ] No errors in any console

## 📝 Notes

- Initial balance: ₹5000
- Thread pool size: 5
- Update interval: 2 seconds
- All data is in-memory (resets on restart)

## 🎓 Demo Script

For demonstrating the application:

1. **Show Initial State**
   - Point out balance: ₹5000
   - Empty transaction table

2. **Perform Deposit**
   - Deposit ₹1000
   - Show balance update
   - Show transaction in table with thread name

3. **Perform Withdrawal**
   - Withdraw ₹500
   - Show balance update
   - Show new transaction

4. **Show Multithreading**
   - Open backend console
   - Perform multiple rapid transactions
   - Point out different thread names

5. **Test Validation**
   - Try to withdraw ₹10000
   - Show error message

6. **Show Real-time Updates**
   - Explain 2-second polling
   - Show automatic refresh

## ✅ Final Verification

If all items are checked, your application is running perfectly! 🎉

---

**Ready to demonstrate concurrent banking transactions!**
