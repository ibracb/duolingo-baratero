# Duolingo Baratero

<p align="center">
  <img src="duolingoBaratero/src/main/resources/logoDuolingo.png" alt="Duolingo Baratero logo" width="512">
</p>

> A desktop app to learn from one another: create your own courses and share them with the community.

![Java](https://img.shields.io/badge/Java-17-blue)
![Maven](https://img.shields.io/badge/Maven-3-red)
![Swing](https://img.shields.io/badge/UI-Swing-orange)
![JPA](https://img.shields.io/badge/JPA-3.1-green)
![SQLite](https://img.shields.io/badge/SQLite-3.49-lightgrey)
![JUnit](https://img.shields.io/badge/JUnit-5-green)
![Mockito](https://img.shields.io/badge/Mockito-5-green)
![University of Murcia](https://img.shields.io/badge/University%20of%20Murcia-E03B23?style=flat&logo=graduation-cap&logoColor=white)

## Overview

**Duolingo Baratero** is a desktop application for working through courses of all kinds. Users can learn with our default courses, and also create their own courses in JSON/YAML extension and make them available to other users, so that they can learn from one another in a reciprocal way.

## Demo

<p align="center">
  <video src="https://github.com/user-attachments/assets/c7e6a624-831a-4416-92a6-9e7a63612a9b"
    controls width="800"></video>
</p>

## Project structure

```
duolingo-baratero/
├── docs/                       	# Complete documentation
├── duolingoBaratero/           	# Maven project
├── .gitignore                  	# Files and folders ignored by Git
└── README.md                   	# Main documentation
```

## Requirements

- **Java (JDK) 17+** — check it with `java --version`
- **Maven 3+** — check it with `mvn --version`

## Installation

```bash
# Clone the repository
git clone https://github.com/ibracb/duolingoBaratero
cd duolingoBaratero
```

## Compilation and execution

```bash
# Compile the project
mvn compile

# Run the application
mvn exec:java
```

## Running the tests

Tests are written with **JUnit 5 (Jupiter)** and **Mockito**.

```
# Run all the tests
mvn test
```

```
# Run a specific test class
mvn test -Dtest=TestClass
```

```
# Run a specific test method
mvn test -Dtest=TestClass#testMethod
```

## Documentation

For the complete project documentation (requirements, use cases, user manual, domain model, architecture and testing strategy), see the [documentation index](docs/).

## Academic context

- **Subject:** Software Development Processes
- **Degree:** BSc in Computer Engineering
- **University:** University of Murcia
- **Year:** 2024–2025

## Authors

- **Ibrahim Cherif Barry** - [ibracb](https://github.com/ibracb)
- **Alejandro López López** - [alexlp04](https://github.com/alexlp04)
- **Jorge Serrano Rueda** - [JorgeSR04](https://github.com/JorgeSR04)
