# 🚀 Run Without Installing Maven

Since Maven is not installed on your system, here are your options to run the backend:

## ✅ Option 1: Use Maven Wrapper (EASIEST - No Installation!)

I've created Maven Wrapper files for you. This downloads Maven automatically on first run.

### Run Backend:
```powershell
cd banking-transaction-simulator\backend
.\mvnw.cmd spring-boot:run
```

**First time**: It will download Maven (takes 1-2 minutes)  
**After that**: Runs instantly!

## ✅ Option 2: Use IntelliJ IDEA (Recommended for Development)

### Step 1: Download IntelliJ IDEA Community (Free)
- Download: https://www.jetbrains.com/idea/download/
- Install the Community Edition (free)

### Step 2: Open Project
1. Open IntelliJ IDEA
2. Click "Open"
3. Navigate to: `banking-transaction-simulator\backend`
4. Click OK

### Step 3: Wait for Sync
- IntelliJ will automatically download Maven dependencies
- Wait for the progress bar at the bottom to complete

### Step 4: Run Application
1. Find `BankingApplication.java` in the project explorer
2. Right-click on it
3. Select "Run 'BankingApplication'"

✅ **Done!** Backend will start on port 8080

## ✅ Option 3: Use VS Code with Java Extension

### Step 1: Install Extensions
1. Open VS Code
2. Install "Extension Pack for Java" by Microsoft

### Step 2: Open Project
1. File → Open Folder
2. Select `banking-transaction-simulator\backend`

### Step 3: Run
1. Open `BankingApplication.java`
2. Click "Run" button above the main method
3. Or press F5

## ✅ Option 4: Install Maven Globally

### Using Chocolatey (if you have it):
```powershell
choco install maven
```

### Manual Installation:
1. Download: https://maven.apache.org/download.cgi
2. Extract to: `C:\Program Files\Apache\maven`
3. Add to PATH:
   - System Properties → Environment Variables
   - Edit "Path" → Add: `C:\Program Files\Apache\maven\bin`
4. Restart PowerShell
5. Verify: `mvn --version`

## ✅ Option 5: Run Pre-Compiled JAR

Let me compile it for you and create a JAR file you can run directly.

After I create it, you can run:
```powershell
java -jar banking-transaction-simulator.jar
```

## 🎯 Recommended Approach

**For Quick Testing**: Use Option 1 (Maven Wrapper)
```powershell
cd banking-transaction-simulator\backend
.\mvnw.cmd spring-boot:run
```

**For Development**: Use Option 2 (IntelliJ IDEA)
- Better debugging
- Code completion
- Easier to modify code

## ⚠️ Before Running: Check Java

Make sure Java is installed:
```powershell
java -version
```

**Expected output**: Java version 17 or higher

**If not installed**:
1. Download: https://adoptium.net/
2. Download "Temurin 17 (LTS)" for Windows
3. Install it
4. Restart PowerShell
5. Verify: `java -version`

## 🎉 Quick Start (Recommended)

### Step 1: Check Java
```powershell
java -version
```

### Step 2: Run Backend with Wrapper
```powershell
cd C:\Users\Dell\Desktop\Multithreading_project\banking-transaction-simulator\backend
.\mvnw.cmd spring-boot:run
```

### Step 3: Wait for "Banking Transaction Simulator Started!"

### Step 4: In NEW PowerShell, Run Frontend
```powershell
cd C:\Users\Dell\Desktop\Multithreading_project\banking-transaction-simulator\frontend
npm install
npm run dev
```

### Step 5: Open Browser
http://localhost:3000

## 🐛 Troubleshooting

### "mvnw.cmd is not recognized"
Make sure you're in the backend directory:
```powershell
cd banking-transaction-simulator\backend
```

### "JAVA_HOME not found"
Set JAVA_HOME:
```powershell
# Find Java location
where java

# Set JAVA_HOME (adjust path if needed)
$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-17.0.x"
```

### Maven Wrapper download fails
Check internet connection or use Option 2 (IntelliJ IDEA)

## 📞 Which Option Should I Use?

**Tell me which option you prefer, and I'll guide you through it!**

1. Maven Wrapper (no installation)
2. IntelliJ IDEA (best for development)
3. VS Code (if you already use it)
4. Install Maven globally
5. Pre-compiled JAR (I'll create it for you)

**Or just try Option 1 right now:**
```powershell
cd banking-transaction-simulator\backend
.\mvnw.cmd spring-boot:run
```
