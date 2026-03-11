# ✅ Simplest Way to Run Backend

## Option 1: Use the Batch File (EASIEST!)

I've created a batch file that handles everything for you.

### Just double-click this file:
```
banking-transaction-simulator\backend\run-backend.bat
```

Or run from PowerShell:
```powershell
cd banking-transaction-simulator\backend
.\run-backend.bat
```

This will:
1. Find your Java installation automatically
2. Set JAVA_HOME correctly
3. Download Maven (first time only)
4. Start the backend server

## Option 2: Use IntelliJ IDEA (RECOMMENDED)

This is the easiest way for development:

### Step 1: Download IntelliJ IDEA
- Go to: https://www.jetbrains.com/idea/download/
- Download "Community Edition" (FREE)
- Install it

### Step 2: Open Project
1. Launch IntelliJ IDEA
2. Click "Open"
3. Navigate to: `C:\Users\Dell\Desktop\Multithreading_project\banking-transaction-simulator\backend`
4. Click "OK"

### Step 3: Wait for Setup
- IntelliJ will automatically detect it's a Maven project
- It will download all dependencies (takes 2-3 minutes first time)
- Wait for the progress bar at the bottom to finish

### Step 4: Run
1. In the Project panel (left side), expand: `src` → `main` → `java` → `com.bank`
2. Right-click on `BankingApplication.java`
3. Select "Run 'BankingApplication'"

✅ **Done!** Backend will start on port 8080

## Option 3: Manual JAVA_HOME Setup

If the batch file doesn't work, set JAVA_HOME manually:

### Find Java Location:
```powershell
where java
```

This will show something like:
```
C:\Program Files\Java\jdk-21\bin\java.exe
```

### Set JAVA_HOME (remove \bin\java.exe):
```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21"
```

### Then run:
```powershell
cd banking-transaction-simulator\backend
.\mvnw.cmd spring-boot:run
```

## 🎯 My Recommendation

**Use IntelliJ IDEA** - It's the easiest and best for development:
1. Download IntelliJ IDEA Community (free)
2. Open the backend folder
3. Wait for sync
4. Click Run

No Maven installation needed, no JAVA_HOME issues, and you get a great development environment!

## 📞 Which Option Do You Want to Try?

1. **Batch file** - Double-click `run-backend.bat`
2. **IntelliJ IDEA** - Best option, I'll guide you
3. **Manual setup** - I'll help you set JAVA_HOME

Let me know and I'll help you get it running!
