# Quick Setup Guide

## Step-by-Step Installation

### 1. Backend Setup

Open a terminal and run:

```bash
cd banking-transaction-simulator/backend
mvn clean install
mvn spring-boot:run
```

Wait for the message: "Banking Transaction Simulator Started!"

The backend will be running on: http://localhost:8080

### 2. Frontend Setup

Open a NEW terminal and run:

```bash
cd banking-transaction-simulator/frontend
npm install
npm run dev
```

The frontend will be running on: http://localhost:3000

### 3. Access the Application

Open your browser and navigate to: http://localhost:3000

## Troubleshooting

### Backend Issues

**Problem**: Port 8080 already in use  
**Solution**: Change port in `backend/src/main/resources/application.properties`
```properties
server.port=8081
```
Also update frontend API URL in `frontend/src/services/api.js`

**Problem**: Maven not found  
**Solution**: Install Maven from https://maven.apache.org/download.cgi

**Problem**: Java version error  
**Solution**: Ensure Java 17+ is installed
```bash
java -version
```

### Frontend Issues

**Problem**: npm not found  
**Solution**: Install Node.js from https://nodejs.org/

**Problem**: Port 3000 already in use  
**Solution**: The terminal will prompt you to use a different port. Press 'y' to accept.

**Problem**: Cannot connect to backend  
**Solution**: Ensure backend is running on port 8080 and check CORS settings

## Testing Multithreading

1. Open browser console (F12)
2. Open backend terminal to see thread logs
3. Perform multiple transactions quickly
4. Observe different thread names in:
   - Backend console logs
   - Transaction table "Thread" column

## Example Usage

1. **Check Initial Balance**: Should show ₹5000.00
2. **Make a Deposit**: Enter 1000, click Deposit
3. **Make a Withdrawal**: Enter 500, click Withdraw
4. **View History**: Scroll down to see all transactions with thread info

## Production Deployment

For production deployment:

### Backend
```bash
cd backend
mvn clean package
java -jar target/banking-transaction-simulator-1.0.0.jar
```

### Frontend
```bash
cd frontend
npm run build
# Deploy the 'dist' folder to your web server
```

## Support

For issues or questions, check the main README.md file for detailed documentation.
