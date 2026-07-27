# Chapter 01 - Java Memory Model: Stack, Heap and Object References

## Objective

Understand how Java manages memory during program execution and explore what happens internally when variables, objects, and references are created.

The goal of this chapter is to go beyond the Java syntax and understand the runtime behavior behind simple operations such as:

- Creating variables;
- Instantiating objects;
- Assigning references;
- Modifying object state.

---

## Why This Matters

Memory management is one of the foundations of programming languages and runtime environments.

In Java, developers usually interact with objects through references without needing to know how memory is managed internally. However, understanding these mechanisms is essential for:

- Writing efficient applications;
- Debugging complex problems;
- Understanding performance characteristics;
- Working with garbage collection;
- Understanding concurrency and object sharing.

Before exploring advanced Java topics such as collections, threads, and JVM internals, it is necessary to understand how data exists during program execution.

---

## Concepts

### JVM Runtime Memory

The Java Virtual Machine manages memory during application execution.

The main concepts explored in this chapter are:

- Stack memory;
- Heap memory;
- Primitive values;
- Object allocation;
- Object references;
- Reference copying;
- Object lifecycle;
- Garbage Collection basics.

---

## Stack Memory

The stack stores information related to method execution.

Each thread has its own stack.

This means local variables inside a method are isolated between threads.

Each method call creates a stack frame containing:

- Local variables;
- Method parameters;
- Execution information.

Example:

```java
public void calculate() {
    int number = 10;
}
```

### Questions explored

- Where is the primitive value stored?
- How are local variables managed?
- What happens when the method finishes execution?

---

## Heap Memory

The heap is the memory area where objects are allocated.

The heap is shared among threads.

Objects created in the heap can be accessed by multiple parts of an application through references.

Example:

```java
User user = new User();
```

This operation involves:

1.  Creating an object in the heap;
2. Creating a reference pointing to that object.

### Questions explored

- Where does the object live?
- What happens when no references point to an object?
- How does the Garbage Collector identify unused objects?

---

## Object References

Java variables that store objects do not contain the object itself.

They contain a reference that points to an object.

Example:

```java
User firstUser = new User();
User secondUser = firstUser;
```

### Questions explored

- Are there one or two objects?
- What happens when one reference changes the object?
- How does reference sharing work?

---

## Primitive Types vs Objects

Primitive types and objects behave differently in Java.

Primitive variables store values directly.

Object variables store references that point to objects managed by the JVM.

The exact physical memory location depends on JVM implementation details, but the conceptual model helps understand Java behavior.

---

## Java Primitive Data Types

Java provides **8 primitive data types**:

| Type | Size | Bits | Description |
|------|------|------|-------------|
| `byte` | 1 byte | 8 bits | Small integer values |
| `short` | 2 bytes | 16 bits | Short integer values |
| `int` | 4 bytes | 32 bits | Default integer type |
| `long` | 8 bytes | 64 bits | Large integer values |
| `float` | 4 bytes | 32 bits | Single-precision decimal values |
| `double` | 8 bytes | 64 bits | Double-precision decimal values |
| `char` | 2 bytes | 16 bits | Single UTF-16 character |
| `boolean` | JVM-dependent | JVM-dependent | Represents `true` or `false` |

> Note: The sizes above represent the Java language specification. The actual memory representation of some types, such as `boolean`, depends on the JVM implementation.

---

## Primitive example

```java
int a = 10;
int b = a;
```
In this case, the value is copied.

a and b are independent variables stored separately.
## Object example

```java
User userA = new User();
User userB = userA;
```

In this case, the reference is copied, not the object.

Both variables point to the same object in the heap.

## Key Difference:

| Primitive | Object |
|-----------|--------|
|Stores the actual value|Stores a reference|
|Copy creates an independent value|Copy creates another reference|
|Usually stored in stack frames|Objects are allocated in the heap|
|No shared state|Multiple references can share the same object|

### Questions explored

- Why does changing ```b``` not affect ```a```?
- Why can changing ```userB``` affect ```userA```?
- What is the difference between copying values and copying references?

---

## Experiments

### Location:
```
src/main/java/com/pedrovieira/javaunderhood/chapters/chapter01/memory
```

## Experiment 01 - Primitive Value Copying


### Purpose:
Observe how primitive values behave when assigned to another variable.

### Implementation:

```
PrimitiveCopyExperiment.java
```

### Questions:

- Does the second variable receive the original value?
- Are the variables independent?

---

## Experiment 02 - Reference Assignment

### Purpose:
Observe how multiple references can point to the same object.

### Implementation:
```
ReferenceAssignmentExperiment.java
```

### Questions:

- How many objects exist in memory?
- What happens when the object state changes?
- Are references copied or objects duplicated?

---

## Experiment 03 - Object Lifecycle

### Purpose:
Understand the relationship between object creation, references, and garbage collection.

### Implementation:
```
ObjectLifecycleExperiment.java
```

### Questions:

- When does an object become eligible for garbage collection?
- Does losing a reference immediately destroy the object?
- Who is responsible for memory cleanup?

---

## Findings

### Experiment 01 - Primitive Value Copying

### Results:

> The primitive value was copied, not the reference.
> 
> The variables became independent values.

---

### Experiment 02 - Reference Assignment

### Results:

> The assignment copied the object reference, not the object itself.
>
> Both variables referenced the same object instance.
>
> Changes made through one reference were visible through the other.

---

### Experiment 03 - Object Lifecycle

### Results:

> Removing the reference to an object does not immediately destroy it.
>
> The object becomes eligible for Garbage Collection when it is no longer reachable.
>
> Memory cleanup is handled automatically by the JVM through the Garbage Collector.

---

## Key Takeaways

After completing this chapter, the main concepts understood should be:

- Java variables do not all behave the same way;
- Primitive values and object references have different semantics;
- Objects are allocated and managed by the JVM.;
- References allow multiple variables to access the same object;
- Garbage Collection manages objects that are no longer reachable;
- Understanding memory behavior helps explain many Java runtime behaviors.

---

## Related Topics

This chapter prepares the foundation for:

- Java Memory Model (JMM);
- Garbage Collection;
- Object-oriented programming;
- Concurrency and synchronization;
- Performance optimization;
- JVM internals.

---

## References

- Java Virtual Machine Specification (JVMS)
- Java Language Specification (JLS)
- Java Memory Model (JMM)
- Oracle Java Documentation
- Effective Java - Joshua Bloch
- Inside the Java Virtual Machine - Bill Venners