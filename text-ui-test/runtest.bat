@ECHO OFF

REM Create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM Delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM Compile all Java files into the bin folder
REM Note: -cp points to the directory containing source files
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM Run the program
REM Note: -classpath points to the 'bin' folder where compiled .class files are
java -classpath ..\bin Gabriel < input.txt > ACTUAL.TXT

REM Compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT