# Rule-Based Language Interpreter

## Group Members

- Jaime Cotes Nuñez
- Simon Andres Banda David
- Catalina Zuluaga Echavarria

## Description

This project is a rule-based language interpreter developed for the Formal Languages course.

The program allows rules to be written using the following structure:

```text
rule <id>:
if <condition> then <action>

The implementation includes lexical analysis, syntactic analysis, Abstract Syntax Tree construction, interpretation of the rules, and basic static analysis.

Versions Used
Operating System: Windows
Programming Language: Java
JDK Version: Java 24
IDE: IntelliJ IDEA
Project type: Plain Java project
How to Run
Using IntelliJ IDEA
Open IntelliJ IDEA.
Select Open and choose the project folder.
Make sure the project is configured with JDK 24.
Open the file:
src/Main.java
Run the main method.

The test cases are already included in Main.java.
To choose a different test case, modify the method called inside main.

Example:

public static void main(String[] args) {
    runCase1();
}

Available test cases include:

runCase1();
runCase2();
runCase3();
runCase4();
runCase5();
runCase6();
runCase7();
runCase8();
runAdditionalCase();
Using the Terminal

From the project folder, compile the source files with:

javac -d out src/Main.java src/lexer/*.java src/parser/*.java src/ast/*.java src/interpreter/*.java src/analisys/*.java

Then run the program with:

java -cp out Main
Input and Output

The program receives rules and an initial state inside the test case methods in Main.java.

Example rule:

rule r1:
if temp > 30 then alert

Example initial state:

temp = 35

Expected output:

alert
Notes
The parser was implemented manually.
No parser generators or external parsing libraries were used.
The project does not use Maven or Gradle.
To test new inputs, modify or add a test case in Main.java.

```bash
src/analysis/*.java
