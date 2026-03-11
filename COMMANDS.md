# Command Reference

Quick copy-paste commands for running the Banking Transaction Simulator.

## 🚀 Start Application

### Backend (Terminal 1)
```bash
cd banking-transaction-simulator/backend
mvn spring-boot:run
```

### Frontend (Terminal 2)
```bash
cd banking-transaction-simulator/frontend
npm install
npm run dev
```

## 🔧 Build Commands

### Backend Build
```bash
cd banking-transaction-simulator/backend
mvn clean install
```

### Frontend Build
```bash
cd banking-transaction-simulator/frontend
npm run build
```

## 🧪 Test API Endpoints

### Get Balance
```bash
curl http://localhost:8080/api/balance
```

### Deposit Money
```bash
curl -X POST http://localhost:8080/api/deposit -H "Content-Type: application/json" -d "{\"amount\": 500}"
```

### Withdraw Money
```bash
curl -X POST http://localhost:8080/api/withdraw -H "Content-Type: application/json" -d "{\"amount\": 200}"
```

### Get Transactions
```bash
curl http://localhost:8080/api/transactions
```

## 🔍 Verify Installation

### Check Java
```bash
java -version
```

### Check Maven
```bash
mvn -version
```

### Check Node.js
```bash
node -version
```

### Check npm
```bash
npm -version
```

## 🌐 Access URLs

- Frontend: http://localhost:3000
- Backend: http://localhost:8080
- API Base: http://localhost:8080/api

## 🛑 Stop Application

### Stop Backend
Press `Ctrl + C` in backend terminal

### Stop Frontend
Press `Ctrl + C` in frontend terminal

## 🧹 Clean Commands

### Clean Backend
```bash
cd banking-transaction-simulator/backend
mvn clean
```

### Clean Frontend
```bash
cd banking-transaction-simulator/frontend
rm -rf node_modules
rm -rf dist
```

## 📦 Package for Production

### Backend JAR
```bash
cd banking-transaction-simulator/backend
mvn clean package
java -jar target/banking-transaction-simulator-1.0.0.jar
```

### Frontend Build
```bash
cd banking-transaction-simulator/frontend
npm run build
# Output in dist/ folder
```

## 🔄 Restart Application

### Quick Restart Backend
```bash
# Stop with Ctrl+C, then:
mvn spring-boot:run
```

### Quick Restart Frontend
```bash
# Stop with Ctrl+C, then:
npm run dev
```

## 📊 View Logs

### Backend Logs
Logs appear in the terminal where you ran `mvn spring-boot:run`

### Frontend Logs
- Terminal: Where you ran `npm run dev`
- Browser: Press F12 → Console tab

## 🐛 Debug Commands

### Backend Debug Mode
```bash
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```

### Check Port Usage (Windows)
```powershell
# Check port 8080
Test-NetConnection -ComputerName localhost -Port 8080

# Check port 3000
Test-NetConnection -ComputerName localhost -Port 3000
```

### Kill Process on Port (Windows)
```powershell
# Find process on port 8080
netstat -ano | findstr :8080

# Kill process (replace PID with actual process ID)
taskkill /PID <PID> /F
```

## 📝 Git Commands

### Initialize Repository
```bash
cd banking-transaction-simulator
git init
git add .
git commit -m "Initial commit: Banking Transaction Simulator"
```

### Create .gitignore
Already created at `banking-transaction-simulator/.gitignore`

## 🔧 Configuration Changes

### Change Backend Port
Edit `backend/src/main/resources/application.properties`:
```properties
server.port=8081
```

Then update frontend API URL in `frontend/src/services/api.js`:
```javascript
const API_BASE_URL = 'http://localhost:8081/api';
```

### Change Frontend Port
Edit `frontend/vite.config.js`:
```javascript
export default defineConfig({
  plugins: [react()],
  server: {
    port: 3001
  }
})
```

## 📦 Dependency Management

### Update Backend Dependencies
```bash
cd banking-transaction-simulator/backend
mvn clean install -U
```

### Update Frontend Dependencies
```bash
cd banking-transaction-simulator/frontend
npm update
```

### Install Specific Package (Frontend)
```bash
cd banking-transaction-simulator/frontend
npm install <package-name>
```

## 🎯 Quick Test Sequence

```bash
# 1. Start backend
cd banking-transaction-simulator/backend && mvn spring-boot:run

# 2. In new terminal, test API
curl http://localhost:8080/api/balance

# 3. In new terminal, start frontend
cd banking-transaction-simulator/frontend && npm install && npm run dev

# 4. Open browser
start http://localhost:3000
```

## 📊 Performance Monitoring

### Backend Memory Usage
```bash
jps -l
jstat -gc <PID>
```

### Frontend Bundle Size
```bash
cd banking-transaction-simulator/frontend
npm run build
# Check dist/ folder size
```

## 🔐 Security Scan

### Backend Dependencies
```bash
cd banking-transaction-simulator/backend
mvn dependency:tree
```

### Frontend Dependencies
```bash
cd banking-transaction-simulator/frontend
npm audit
npm audit fix
```

## 📚 Documentation Commands

### View README
```bash
cat banking-transaction-simulator/README.md
```

### View All Documentation
```bash
ls banking-transaction-simulator/*.md
```

## 🎉 One-Line Startup (PowerShell)

### Start Both Servers
```powershell
# Start backend in background
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd banking-transaction-simulator/backend; mvn spring-boot:run"

# Wait 10 seconds for backend to start
Start-Sleep -Seconds 10

# Start frontend in background
Start-Process powershell -ArgumentList "-NoExit", "-Command", "cd banking-transaction-simulator/frontend; npm run dev"

# Open browser
Start-Process "http://localhost:3000"
```

---

**Keep this file handy for quick command reference!**
