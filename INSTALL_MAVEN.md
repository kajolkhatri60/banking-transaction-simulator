# Maven Installation Guide for Windows

Maven is not installed on your system. Here are your options:

## Option 1: Install Maven (Recommended)

### Using Chocolatey (Easiest)
If you have Chocolatey installed:
```powershell
choco install maven
```

### Manual Installation
1. **Download Maven**
   - Go to: https://maven.apache.org/download.cgi
   - Download: `apache-maven-3.9.6-bin.zip` (or latest version)

2. **Extract Maven**
   - Extract to: `C:\Program Files\Apache\maven`

3. **Set Environment Variables**
   - Open System Properties → Environment Variables
   - Add to System Variables:
     - Variable: `MAVEN_HOME`
     - Value: `C:\Program Files\Apache\maven`
   
   - Edit `Path` variable, add:
     - `%MAVEN_HOME%\bin`

4. **Verify Installation**
   ```powershell
   mvn --version
   ```

5. **Restart PowerShell** and try again:
   ```powershell
   cd banking-transaction-simulator/backend
   mvn spring-boot:run
   ```

## Option 2: Use Maven Wrapper (No Installation Required)

I'll create Maven Wrapper files for you. This allows running Maven without installing it globally.

### After I create the wrapper files, run:
```powershell
cd banking-transaction-simulator/backend
.\mvnw.cmd spring-boot:run
```

## Option 3: Use IDE (IntelliJ IDEA / Eclipse)

### IntelliJ IDEA
1. Open IntelliJ IDEA
2. File → Open → Select `banking-transaction-simulator/backend`
3. Wait for Maven to sync
4. Right-click `BankingApplication.java` → Run

### Eclipse
1. Open Eclipse
2. File → Import → Maven → Existing Maven Projects
3. Select `banking-transaction-simulator/backend`
4. Right-click project → Run As → Spring Boot App

## Option 4: Run Pre-compiled JAR (Coming Soon)

I can create a pre-compiled JAR file that you can run directly with:
```powershell
java -jar banking-transaction-simulator.jar
```

## Quick Check: Do you have Java?

```powershell
java -version
```

If Java is not installed:
1. Download from: https://adoptium.net/
2. Install JDK 17 or higher
3. Restart PowerShell

## Which Option Do You Prefer?

Let me know and I'll help you proceed!
