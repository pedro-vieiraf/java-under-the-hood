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

---

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
Class Loading
        ↓
Linking
        ↓
Initialization
        ↓
Class Ready for Use
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

### What to Observe

During the execution, pay attention to:

- Which Class Loader loads the application class;
- Which Class Loader loads JDK classes such as `String` and `ArrayList`;
- The parent relationship between the Class Loaders;
- Why the Bootstrap Class Loader appears as `null`.

### Conclusion

After completing this experiment, the following observations can be made:

> The application class was loaded by the **Application Class Loader**.
>
> The Application Class Loader delegates requests to the **Platform Class Loader**, which in turn delegates to the **Bootstrap Class Loader**.
>
> The Bootstrap Class Loader appears as `null` because it is implemented inside the JVM and is not represented as a regular Java object.
>
> Core JDK classes such as `String` and `ArrayList` are loaded by the Bootstrap Class Loader.

---

## Experiment 02 - Parent Delegation Model

### Location

```text
src/main/java/com/pedrovieira/javaunderhood/chapters/chapter03/classloaders/delegation
```

### Purpose

Understand how the JVM uses the Parent Delegation Model when a Class Loader receives a request to load a class.

Instead of loading classes immediately, each Class Loader first delegates the request to its parent. Only if the parent cannot locate the class does the current Class Loader attempt to load it.

### Implementation

```text
ParentDelegationExperiment.java
```

### What to Observe

During the execution, pay attention to:

- Which Class Loader receives the initial request;
- Whether the Application Class Loader immediately loads the requested class;
- How the parent-child delegation hierarchy works;
- Why delegation prevents duplicate loading of core Java classes.

### Conclusion

After completing this experiment, the following observations can be made:

> Class Loaders do not immediately attempt to load every requested class.
>
> Each Class Loader first delegates the request to its parent before trying to load the class itself.
>
> This delegation mechanism ensures that core Java classes are loaded only once by the Bootstrap Class Loader.
>
> The Parent Delegation Model provides consistency, reliability, and security throughout the JVM.
---

## Experiment 03 - Loading JDK and Application Classes

### Location

```text
src/main/java/com/pedrovieira/javaunderhood/chapters/chapter03/classloaders/loading
```

### Purpose

Observe how different types of classes are ultimately loaded by different Class Loaders after the Parent Delegation Model has been applied.

This experiment compares application classes and JDK classes to identify which Class Loader is responsible for each one.

### Implementation

```text
ClassLoadingComparisonExperiment.java
```

### What to Observe

During the execution, pay attention to:

- Which Class Loader loaded the application class;
- Which Class Loader loaded core JDK classes such as `String` and `ArrayList`;
- How the final Class Loader differs depending on the origin of the class;
- How the observed results relate to the Parent Delegation Model.

### Conclusion

After completing this experiment, the following observations can be made:

> Application classes are ultimately loaded by the **Application Class Loader**.
>
> Core JDK classes are ultimately loaded by the **Bootstrap Class Loader**.
>
> Although every request starts from the Application Class Loader, the Parent Delegation Model determines which Class Loader is actually responsible for loading each class.
>
> The JVM uses different Class Loaders to separate application code from platform code, improving consistency, isolation, and security.
---

## Experiment 04 - Dynamic Class Loading

### Location

```text
src/main/java/com/pedrovieira/javaunderhood/chapters/chapter03/classloaders/dynamic
```

### Purpose

Understand how classes can be loaded dynamically during application execution.

Unlike previous experiments, where classes were loaded automatically by the JVM, this experiment demonstrates how a class can be requested explicitly at runtime using the Reflection API.

### Implementation

```text
DynamicLoadingExperiment.java
Plugin.java
```

### What to Observe

During the execution, pay attention to:

- When the requested class is loaded;
- Whether loading also initializes the class;
- Which Class Loader performs the loading;
- How `Class.forName()` differs from ordinary class references.

### Conclusion

After completing this experiment, the following observations can be made:

> Classes can be requested dynamically during runtime using `Class.forName()`.
>
> By default, `Class.forName()` loads and initializes the requested class.
>
> Dynamic loading still follows the Parent Delegation Model, allowing the appropriate Class Loader to perform the loading.
>
> Dynamic class loading is one of the mechanisms that enables reflection, plugin systems, dependency injection frameworks, and many other advanced Java technologies.

---

## Experiment 05 - Complete Class Loading Flow

### Location

```text
src/main/java/com/pedrovieira/javaunderhood/chapters/chapter03/classloaders/flow
```

### Purpose

Connect all previous concepts by observing the complete lifecycle of a class inside the JVM.

This experiment demonstrates how a class progresses from being requested by the application to becoming fully initialized and ready for use.

### Implementation

```text
CompleteClassLoadingExperiment.java
DemoClass.java
```

### What to Observe

During the execution, pay attention to:

- When the class is requested by the application;
- Where the linking phase fits into the overall loading lifecycle;
- When the class is linked;
- When static initialization occurs;
- When the class becomes available for normal use.

### Conclusion

After completing this experiment, the following observations can be made:

> Class loading is a multi-stage process rather than a single operation.
>
> Before a class can be used, the JVM loads, links, and initializes it.
> 
> The linking phase happens internally inside the JVM and is not directly observable through Java code.
>
> Static fields and static initialization blocks execute during the initialization phase.
>
> Once initialization is complete, the class is ready for normal execution by the application.

---

## Key Takeaways

After completing this chapter, the main concepts understood should be:

- Classes are not immediately available to the JVM;
- Class Loaders are responsible for locating and loading classes;
- The JVM uses multiple Class Loaders with different responsibilities;
- Java follows the Parent Delegation Model to ensure consistency and security;
- Classes can be loaded dynamically during runtime;
- Class loading is the first step before linking and initialization.
- Loading, linking, and initialization together prepare classes for execution by the JVM.

---

## Related Topics

This chapter prepares the foundation for:

- JVM Class File Structure;
- Constant Pool;
- Class Metadata;
- Reflection;
- Dynamic Proxies;
- Java Modules;
- JVM Internals.
