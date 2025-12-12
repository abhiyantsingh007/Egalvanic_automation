@echo off
cd /d "c:\Users\Abhiyant\Downloads\sculptsoft\Egalvanic_automation-main\Egalvanic_automation-main"
javac src/main/java/Egalvanic.java
if %ERRORLEVEL% EQU 0 (
    echo Compilation successful
    java -cp src/main/java Egalvanic
) else (
    echo Compilation failed
)
pause