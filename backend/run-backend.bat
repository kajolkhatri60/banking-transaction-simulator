@echo off
echo ========================================
echo Banking Transaction Simulator - Backend
echo ========================================
echo.

REM Find Java installation
for /f "tokens=*" %%i in ('where java 2^>nul') do set JAVA_EXE=%%i

if "%JAVA_EXE%"=="" (
    echo ERROR: Java not found!
    echo Please install Java 17 or higher from https://adoptium.net/
    pause
    exit /b 1
)

REM Get Java home directory
for %%i in ("%JAVA_EXE%") do set JAVA_BIN=%%~dpi
for %%i in ("%JAVA_BIN:~0,-5%") do set JAVA_HOME=%%~fi

echo Java found at: %JAVA_HOME%
echo.

REM Set JAVA_HOME for this session
set JAVA_HOME=%JAVA_HOME%

echo Starting backend server...
echo This will download Maven on first run (takes 1-2 minutes)
echo.

REM Run Maven Wrapper
call mvnw.cmd spring-boot:run

pause
