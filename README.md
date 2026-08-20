# Duolingo Baratero

> A desktop app to learn from one another: create your own courses and share them with the community.

![Java](https://img.shields.io/badge/Java-17-blue)
![Maven](https://img.shields.io/badge/Maven-3-red)
![Swing](https://img.shields.io/badge/UI-Swing-orange)
![JPA](https://img.shields.io/badge/JPA-3.1-green)
![SQLite](https://img.shields.io/badge/SQLite-3.49-lightgrey)
![University of Murcia](https://img.shields.io/badge/University%20of%20Murcia-E03B23?style=flat&logo=graduation-cap&logoColor=white)

![DuolingoBaratero](/duolingoBaratero/src/main/resources/logoDuolingo.png)

## Overview

**Duolingo Baratero** is a desktop application for working through courses of all kinds. Users can create their own courses in JSON or YAML extension and make them available to other users, so that they can learn from one another in a reciprocal way.

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

For the complete project documentation (requirements, use cases, user manual, domain model, architecture and testing strategy), see the [documentation index](docs/README.md).

## Academic context

- **Subject:** Software Development Processes
- **Degree:** BSc in Computer Engineering
- **University:** University of Murcia
- **Year:** 2024–2025

## Authors

- **Ibrahim Cherif Barry** - [ibracb](https://github.com/ibracb)
- **Alejandro López López** - [alexlp04](https://github.com/alexlp04)
- **Jorge Serrano Rueda** - [JorgeSR04](https://github.com/JorgeSR04)