# Chapter 02 - JVM Startup and Class Loading

## Objective

Understand what happens between executing a Java application and reaching the `main()` method.

The goal of this chapter is to explore the startup lifecycle of a Java application, including how the JVM loads, links, initializes classes, and finally starts program execution.

Rather than focusing on Java syntax, this chapter focuses on what happens behind the scenes before your own code begins to run.

---

## Why This Matters

Every Java application follows the same startup sequence.

When we execute:

```bash
java MyApplication
```

the JVM performs a series of operations before invoking the application's entry point.

Understanding this startup process is essential for:

- Understanding how Java applications execute;
- Understanding bytecode;
- Understanding class loading;
- Understanding static initialization;
- Understanding reflection;
- Building a solid foundation for JVM internals.

Many advanced Java topics become much easier once the startup lifecycle is understood.

---

## The Journey

Throughout this chapter, we will follow the complete execution path of a Java application.

```text
Java Source (.java)
        │
        ▼
Compilation (javac)
        │
        ▼
Bytecode (.class)
        │
        ▼
Java Launcher (java)
        │
        ▼
JVM Startup
        │
        ▼
Class Loading
        │
        ▼
Linking
        │
        ▼
Initialization
        │
        ▼
main()
```

Each section of this chapter explores one step of this journey.

---

# Concepts

## Java Compilation

Before a Java program can execute, the source code must be compiled.

The Java compiler (`javac`) translates source code into platform-independent bytecode stored inside `.class` files.

### Questions explored

- Why does Java require compilation?
- What is bytecode?
- Why doesn't the JVM execute `.java` files?

---

## Bytecode

The JVM does not execute Java source code.

Instead, it executes bytecode instructions contained in compiled class files.

These instructions are platform-independent and represent the input consumed by the JVM.

### Questions explored

- What does bytecode look like?
- How can it be inspected?
- Why is Java considered platform-independent?

---

## JVM Startup

Executing the `java` command creates a Java Virtual Machine.

Before executing user code, the JVM prepares its runtime environment.

This includes creating the main thread, loading essential classes, preparing runtime structures, and locating the application's entry point.

### Questions explored

- What happens before `main()`?
- Who starts the JVM?
- What components are created first?

---

## Classpath

The Classpath defines where the JVM searches for compiled classes.

Without a correct Classpath, the JVM cannot locate application classes.

### Questions explored

- What is the Classpath?
- How are classes discovered?
- What happens if a class cannot be found?

---

## Class Loading

Before a class can be used, it must be loaded into the JVM.

This responsibility belongs to the Class Loader subsystem.

Each loaded class becomes part of the JVM runtime.

### Questions explored

- When is a class loaded?
- Who loads classes?
- Is a class loaded more than once?

---

## Linking

Once loaded, classes pass through the linking process.

Linking prepares the class for execution by performing:

- Verification;
- Preparation;
- Resolution.

### Questions explored

- Why is bytecode verified?
- What happens during preparation?
- What is symbolic reference resolution?

---

## Class Initialization

After linking, the JVM initializes the class.

During this phase, static fields receive their initial values and static initialization blocks are executed.

Initialization occurs only once for each loaded class.

Example:

```java
class User {

    static {
        System.out.println("Initializing User...");
    }

}
```

### Questions explored

- When is a class initialized?
- What triggers initialization?
- Can initialization happen more than once?

---

## The `main()` Method

Only after all previous phases have completed does the JVM invoke the application's entry point.

```java
public static void main(String[] args) {
    System.out.println("Application started.");
}
```

### Questions explored

- Why must `main()` have this exact signature?
- Can Java execute code before `main()`?
- Where does the execution of an application actually begin?

---

# Experiments

## Experiment 01 - Java Compilation

### Location
```
src/main/java/com/pedrovieira/javaunderhood/chapters/chapter02/startup/compilation
```

### Purpose

Understand how Java source code is compiled into a JVM class file and executed by the JVM.

### Implementation

```
SimpleApplication.java
```

### Execution

From the `src/main/java` directory, compile the Java source file:

```bash
javac com/pedrovieira/javaunderhood/chapters/chapter02/startup/compilation/SimpleApplication
```
After compilation, a new `.class` file should be generated:

```
SimpleApplication.class
```
Execute the compiled Java application:
```bash
java com/pedrovieira/javaunderhood/chapters/chapter02/startup/compilation/SimpleApplication
```

Expected output:
```
Hello from Java bytecode!
```


### Questions

- What files are generated by the compiler?
- What is the relationship between .java and .class files?
- Does the JVM execute .java files directly?

---

## Experiment 02 - Bytecode Inspection

### Location
```
src/main/java/com/pedrovieira/javaunderhood/chapters/chapter02/startup/bytecode
```
### Purpose

Understand how compiled Java code is represented as JVM bytecode instructions.

The goal is to observe how Java operations are translated into instructions executed by the JVM.

### Implementation

```
BytecodeExample.java
```

### Execution

From the `src/main/java` directory, compile the Java source file:

```bash
javac javac com/pedrovieira/javaunderhood/chapters/chapter02/startup/bytecode/BytecodeExample.java
```

Inspect the generated bytecode:
```bash
javap -c com.pedrovieira.javaunderhood.chapters.chapter02.startup.bytecode.BytecodeExample
```

This command disassembles the generated .class file and displays the JVM bytecode instructions generated by the Java compiler.

The output is a human-readable representation of the instructions that the JVM executes.

### Questions

- Which JVM instructions are generated from Java code?
- How are variables represented?
- How does the JVM operand stack work?

---

## Experiment 03 - Static Initialization

### Location
```
src/main/java/com/pedrovieira/javaunderhood/chapters/chapter02/startup/initialization
```

### Purpose

Observe when class initialization occurs and understand how the JVM initializes static fields and executes static initialization blocks.

### Implementation

```
StaticInitializationExperiment.java
```

### Execution

From the `src/main/java` directory, compile the Java source file:

```bash
javac com/pedrovieira/javaunderhood/chapters/chapter02/startup/initialization/StaticInitializationExperiment.java
```

Execute the compiled class:
```bash
java com.pedrovieira.javaunderhood.chapters.chapter02.startup.initialization.StaticInitializationExperiment
```

Expected output:
```
Starting program

Accessing static field:

Static field initialized
Static block executed
10

Accessing static field again:

10
```
### Questions

- When does the static block execute?
- Does it execute more than once?
- What triggers initialization?
- Does the order of static declarations affect initialization?

---

## Experiment 04 - Class Loading

### Purpose

Observe how the JVM loads classes during execution.

### Implementation

```
ClassLoadingExperiment.java
SampleClass.java
```
### Execution

From the `src/main/java` directory, compile the Java source files:

```
javac com/pedrovieira/javaunderhood/chapters/chapter02/startup/classloading/*.java
```

Execute the compiled class:
```
java com.pedrovieira.javaunderhood.chapters.chapter02.startup.classloading.ClassLoadingExperiment
```

Expected output:
```
Program started

Loading class without initialization:

Class loaded: com.pedrovieira.javaunderhood.chapters.chapter02.startup.classloading.SampleClass

Initializing class:

SampleClass static block executed
```

### Questions

- Can a class be loaded without being initialized?
- What is the difference between class loading and class initialization?
- When does the JVM execute static fields and static blocks?
- How does `Class.forName()` trigger class initialization?

---

## Experiment 05 - JVM Startup Sequence

### Location

```
src/main/java/com/pedrovieira/javaunderhood/chapters/chapter02/startup/sequence
```

### Purpose

Connect all previous concepts by observing the complete startup sequence of a Java application.

### Implementation

```
StartupExperiment.java
```


### Execution

From the `src/main/java` directory, compile the Java source file:

```bash
javac com/pedrovieira/javaunderhood/chapters/chapter02/startup/sequence/StartupExperiment.java
```

Execute the compiled class:
```
java com.pedrovieira.javaunderhood.chapters.chapter02.startup.sequence.StartupExperiment
```

Expected output:
```
1 - Static initialization block
2 - Static field initialization
4 - main() method execution
5 - Object initialization block
6 - Constructor execution
7 - Application running
Application initialized
```
### Questions

- What happens before main() starts executing?
- In what order are static blocks and static fields initialized?
- What is the relationship between JVM startup, class loading, and class initialization?
- Which steps happen before object creation?

---

# Findings

## Experiment 01 - Java Compilation

### Results

> The Java compiler transformed the `.java` source file into a `.class` file containing the compiled representation required by the JVM.
>
> The JVM does not execute Java source code directly. It loads the generated `.class` file and executes the bytecode instructions contained inside it.
>
> The compilation process creates a separation between the Java language and the JVM execution environment.
>
> The `.class` file is the result of the compilation process and acts as the bridge between Java source code and JVM execution.

---

## Experiment 02 - Bytecode Inspection

### Results

> The Java compiler translated high-level Java operations into JVM bytecode instructions.
>
> The generated bytecode represents operations using JVM instructions, such as loading values, storing variables, performing calculations, and invoking methods.
>
> The JVM uses runtime structures such as local variables and the operand stack to execute these instructions.
>
> The `javap -c` tool provides a human-readable representation of the bytecode contained inside `.class` files.
>
> The `.class` file does not store the original Java source code. Instead, it stores a platform-independent representation that can be interpreted or compiled by the JVM.
---

## Experiment 03 - Static Initialization

### Results

> Class initialization is performed once by the JVM before the class is first actively used.
>
> Static fields and static blocks are executed only once, during class initialization.
>
> Accessing the same static field multiple times does not trigger initialization again.
>
> The execution order of static initialization follows the order in which static fields and blocks appear in the class declaration.

---

## Experiment 04 - Class Loading

### Results

> The JVM can load a class definition without executing its initialization logic.
>
> Using `Class.forName()` with initialization disabled (`false`) loads the class into the JVM but does not execute static fields or static blocks.
>
> Class initialization happens separately from class loading.
>
> When the JVM initializes a class, static fields are assigned and static initialization blocks are executed.
>
> Using `Class.forName()` with the default behavior triggers class initialization.
>
> The experiment demonstrated that loading a class and initializing a class are different phases in the JVM lifecycle.



---

## Experiment 05 - JVM Startup Sequence

### Results

> The JVM initializes classes before executing the `main()` method.
>
> Static initialization blocks and static fields are executed during class initialization.
>
> The `main()` method is not the first code executed by the JVM. Class loading and initialization happen before the application entry point starts.
>
> The application startup consists of multiple JVM-managed phases that occur before any user-defined object is created.
>
> The experiment connects compilation, class loading, initialization, and execution into a single JVM startup lifecycle.

---

## Key Takeaways

After completing this chapter, the main concepts understood should be:

- Java applications execute bytecode, not source code;
- The JVM performs class loading, linking and initialization before invoking `main()`;
- Classes must be loaded, linked, and initialized before execution;
- Static initialization occurs only once for each loaded class;
- The startup sequence provides the foundation for understanding JVM internals.

---

## Related Topics

This chapter prepares the foundation for:

- JVM Execution Engine;
- Bytecode interpretation;
- Just-In-Time (JIT) compilation;
- Reflection;
- Custom Class Loaders;
- JVM internals.

---

## References

- Java Language Specification (JLS)
- Java Virtual Machine Specification (JVMS)
- Oracle Java Documentation
- Inside the Java Virtual Machine — Bill Venners
- The Java® Virtual Machine Specification — Oracle