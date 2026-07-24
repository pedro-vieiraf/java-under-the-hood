# Java Under the Hood

> Exploring Java beyond the APIs.

**Java Under the Hood** is my personal engineering notebook for studying how Java works internally.

Instead of focusing on frameworks or CRUD applications, this repository explores the core mechanisms behind the Java language and the JVM through implementations, experiments, benchmarks, and technical notes.

The goal is not simply to learn how to use Java APIs, but to understand **why they exist, how they are implemented, and what engineering problems they solve.**

---

# Philosophy

Most Java learning resources focus on **how to use** the language.

This repository focuses on **how Java works**.

Every topic starts with a problem that Java needs to solve, investigates the underlying mechanisms, explores a simplified implementation whenever possible, and finishes with practical engineering takeaways.

The objective is to build a deeper understanding of the platform rather than simply memorizing APIs.

---

# Repository Structure

Each chapter follows the same structure:

- 📖 Problem overview
- 🧠 Why this feature exists
- ⚙️ Internal implementation
- 🧪 Hands-on experiments
- ✅ Unit tests
- 📝 Engineering notes
- 📚 References

---

# Topics

## Core Java

- [ ] Java Memory Model
- [ ] Primitive Types
- [ ] References
- [ ] Objects
- [ ] Strings
- [ ] Immutability
- [ ] Exceptions

## Collections

- [ ] ArrayList
- [ ] LinkedList
- [ ] HashMap
- [ ] HashSet
- [ ] TreeMap
- [ ] TreeSet
- [ ] Queue
- [ ] Stack

## Language Features

- [ ] Generics
- [ ] Annotations
- [ ] Reflection
- [ ] Streams
- [ ] Lambdas
- [ ] Functional Interfaces
- [ ] Optional

## Concurrency

- [ ] Threads
- [ ] ExecutorService
- [ ] CompletableFuture
- [ ] Locks
- [ ] Synchronization
- [ ] Virtual Threads

## JVM

- [ ] Class Loading
- [ ] Bytecode
- [ ] Garbage Collector
- [ ] JIT Compiler
- [ ] JVM Memory Areas

## Performance

- [ ] Big-O Analysis
- [ ] Benchmarking
- [ ] Profiling
- [ ] Memory Optimization

---

# Technologies

- Java 21
- Maven
- JUnit 5
- GitHub Actions
- JaCoCo (planned)

---

# Project Status

This repository is actively maintained as part of my long-term journey to deepen my understanding of Java and software engineering.

New chapters will be added as new topics are explored.

---

# References

Whenever possible, the experiments and notes are based on official documentation, OpenJDK source code, technical books, and engineering articles.

---

## Disclaimer

The implementations in this repository are intentionally simplified for educational purposes.

Their goal is to explain concepts and engineering decisions rather than reproduce the full complexity of the OpenJDK implementation.