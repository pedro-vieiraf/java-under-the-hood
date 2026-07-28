# Chapter 03 - JVM Class Loaders

## Objective

Understand how the JVM locates, loads, and makes classes available during application execution.

The goal of this chapter is to go beyond Java syntax and understand how the JVM discovers classes, delegates the loading process, and prepares classes to be linked and initialized.

---

## Why This Matters

Every Java application depends on the JVM's ability to locate and load classes correctly.

Before a class can be initialized or executed, it must first be found and loaded into memory.

Understanding the Class Loading mechanism is essential for:

- Understanding the JVM startup process;
- Understanding how Java applications locate classes;
- Understanding custom Class Loaders;
- Understanding frameworks that load classes dynamically;
- Debugging `ClassNotFoundException` and `NoClassDefFoundError`;
- Understanding plugin architectures and application servers.

This chapter builds directly on the previous one by explaining what happens after the JVM decides that a class must be loaded.

---

## Concepts

### Java Class Loading

The JVM does not know about Java classes until they are requested.

When a class is referenced for the first time, the JVM delegates the loading process to a Class Loader.

The main concepts explored in this chapter are:

- Bootstrap Class Loader;
- Platform Class Loader;
- Application Class Loader;
- Parent Delegation Model;
- Class Loading process;
- Dynamic class loading;
- Class visibility;
- Runtime class discovery.

## Class Loader Hierarchy

The JVM organizes Class Loaders in a parent-child hierarchy.

Rather than every Class Loader attempting to load classes independently, they cooperate through a delegation mechanism. Each Class Loader first delegates the loading request to its parent before attempting to load the class itself.

The default hierarchy is:

```text
Bootstrap Class Loader
          ▲
          │
Platform Class Loader
          ▲
          │
Application Class Loader
```

Each Class Loader has a specific responsibility:

| Class Loader | Responsibility |
|--------------|----------------|
| **Bootstrap Class Loader** | Loads the core Java runtime classes, such as `java.lang`, `java.util`, and other essential JDK classes. |
| **Platform Class Loader** | Loads Java platform modules that are not part of the core runtime. |
| **Application Class Loader** | Loads the classes that belong to the application itself, including classes found on the application's classpath. |

This hierarchy follows the **Parent Delegation Model**, ensuring that core Java classes are loaded only once and remain consistent throughout the execution of the application.

The hierarchy can be inspected programmatically using the `ClassLoader` API, which is the focus of the first experiment in this chapter.

---

## Bootstrap Class Loader

The Bootstrap Class Loader is responsible for loading the Java core libraries.

Examples include:

- `java.lang.String`;
- `java.lang.Object`;
- `java.util.ArrayList`.

It is implemented by the JVM itself and is not represented as a regular Java object.

### Questions explored

- Which classes are loaded by the Bootstrap Class Loader?
- Why does `String.class.getClassLoader()` return `null`?
- Why is the Bootstrap Class Loader special?

---

## Platform Class Loader

The Platform Class Loader loads Java platform modules that are not part of the core runtime.

It delegates requests to the Bootstrap Class Loader whenever necessary.

### Questions explored

- What kinds of classes does the Platform Class Loader load?
- How does it participate in the delegation hierarchy?

---

## Application Class Loader

The Application Class Loader loads the classes that belong to the application itself.

This includes the classes compiled from the project source code.

Example:

```java
MyApplication.class.getClassLoader();
```

### Questions explored

- Which Class Loader loads application classes?
- How does the Application Class Loader locate classes?

---

## Parent Delegation Model

Java Class Loaders follow a delegation model.

Instead of immediately loading a class, a Class Loader first asks its parent to load it.

Only if the parent cannot find the class does the current Class Loader attempt to load it.

This mechanism prevents duplicate loading of core Java classes and ensures a consistent runtime environment.

### Questions explored

- Why does Java use delegation?
- What happens if multiple Class Loaders attempt to load the same class?
- How does delegation improve security and consistency?

---

## Dynamic Class Loading

Classes can also be loaded dynamically during runtime.

Example:

```java
Class.forName("com.example.MyClass");
```

Dynamic loading is widely used by:

- Frameworks;
- JDBC Drivers;
- Dependency Injection containers;
- Reflection APIs;
- Plugin systems.

### Questions explored

- When is a class loaded dynamically?
- What triggers dynamic loading?
- How does `Class.forName()` interact with Class Loaders?

---

## Class Loading Sequence

When a class is requested, the JVM performs a sequence of operations.

```
Class Request
        ↓
Parent Delegation
        ↓
Bootstrap / Platform / Application Class Loader
        ↓
Loading
        ↓
Linking
        ↓
Initialization
        ↓
Class Ready for Execution
```

---

## Experiments

## Experiment 01 - Inspecting the Class Loader Hierarchy

### Location

```
src/main/java/com/pedrovieira/javaunderhood/chapters/chapter03/classloaders/hierarchy
```

### Purpose

Understand how the JVM organizes its Class Loaders and how they cooperate to locate and load classes during application startup.

This experiment explores the Class Loader hierarchy and identifies which loader is responsible for application classes and core JDK classes.


### Implementation

```
ClassLoaderHierarchyExperiment.java
```

### Questions

- Which Class Loader loads application classes?
- What is the hierarchy of Java Class Loaders?
- Which Class Loader loads JDK classes such as `String` and `ArrayList`?
- Why does the Bootstrap Class Loader appear as `null`?

---

## Experiment 02 - Loading JDK and Application Classes

### Purpose

Compare how the JVM loads JDK classes and application classes.

### Implementation

```
ClassLoaderComparisonExperiment.java
```

### Questions

- Which Class Loader loads `String`?
- Which Class Loader loads application classes?
- Why are different Class Loaders involved?

---

## Experiment 03 - Parent Delegation Model

### Purpose

Observe how Class Loaders delegate class loading requests.

### Implementation

```
ParentDelegationExperiment.java
```

### Questions

- Which Class Loader receives the request first?
- How does delegation prevent duplicate loading?
- What happens when the parent cannot locate a class?

---

## Experiment 04 - Dynamic Class Loading

### Purpose

Observe how classes can be loaded dynamically during runtime.

### Implementation

```
DynamicLoadingExperiment.java
```

### Questions

- What happens when using `Class.forName()`?
- Does loading always initialize the class?
- Which Class Loader performs the loading?

---

## Experiment 05 - Complete Class Loading Flow

### Purpose

Connect all previous concepts by observing the complete Class Loading process.

### Implementation

```
CompleteClassLoadingExperiment.java
```

### Questions

- What is the complete loading sequence?
- Which Class Loaders participate?
- How does loading connect with linking and initialization?

---

## Findings

### Experiment 01

### Results

> To be completed after implementation.

---

### Experiment 02

### Results

> To be completed after implementation.

---

### Experiment 03

### Results

> To be completed after implementation.

---

### Experiment 04

### Results

> To be completed after implementation.

---

### Experiment 05

### Results

> To be completed after implementation.

---

## Key Takeaways

After completing this chapter, the main concepts understood should be:

- Classes are not immediately available to the JVM;
- Class Loaders are responsible for locating and loading classes;
- The JVM uses multiple Class Loaders with different responsibilities;
- Java follows the Parent Delegation Model to ensure consistency and security;
- Classes can be loaded dynamically during runtime;
- Class loading is the first step before linking and initialization.

---

## Related Topics

This chapter prepares the foundation for:

- JVM Class Structure;
- Class Metadata;
- Reflection;
- Dynamic Proxies;
- Java Modules;
- JVM Internals.

---

## References

- Java Virtual Machine Specification (JVMS)
- Java Language Specification (JLS)
- Oracle Java Documentation
- Inside the Java Virtual Machine — Bill Venners