# Requirements

Specification of the functional and non-functional requirements of Duolingo Baratero. The use cases associated with each functional requirement are developed in [use cases](02-use-cases.md).

## Functional requirements

| ID  | Description                                                              | Related to |
|-----|--------------------------------------------------------------------------|-----------------|
| FR-01 | Allow a new user to register with a name, an email and a password.       | [Register a user](02-use-cases.md#1-register-a-user) |
| FR-02 | Allow a user to log in by checking their email and password.             | [Log in](02-use-cases.md#2-log-in) |
| FR-03 | Allow the user to choose a course from the library.                      | [Choose a course](02-use-cases.md#3-choose-a-course) |
| FR-04 | Allow starting a course by choosing a learning strategy.                 | [Start a course](02-use-cases.md#4-start-a-course) |
| FR-05 | Allow answering the different question types (options, images, audio and flashcard). | [Start a course](02-use-cases.md#4-start-a-course) |
| FR-06 | Save progress by content blocks and allow the course to be resumed.      | [Resume a course](02-use-cases.md#10-resume-a-course) |
| FR-07 | Show usage statistics, the best streak and other progress indicators.    | [View statistics](02-use-cases.md#5-view-statistics) |
| FR-08 | Allow a course to be imported from a file.                               | [Import a course](02-use-cases.md#6-import-a-course) |
| FR-09 | Allow a course to be shared or exported to a file.                       | [Share a course](02-use-cases.md#7-share-a-course) |
| FR-10 | Allow a course to be installed from a file.                              | [Install a course from a file](02-use-cases.md#8-install-a-course-from-a-file) |
| FR-11 | Allow new questions to be added to a created course.                     | [Add new questions](02-use-cases.md#9-add-new-questions) |
| FR-12 | Lives management: mistakes that cost lives, regeneration over time and loss of progress when they run out. | [User manual](03-user-manual.md#9-lives-system) |

## Non-functional requirements

| ID     | Category          | Description |
|--------|-------------------|-------------|
| NFR-01 | Compatibility     | The application runs on Java 17+ and is built with Maven 3+. |
| NFR-02 | Persistence       | Data is stored in SQLite through JPA/Hibernate. |
| NFR-03 | Interoperability  | Courses are imported and exported in JSON or YAML format. |
| NFR-04 | Graphical interface | The user interface is developed with Swing. |
| NFR-05 | Usability         | The application is easy to use and intuitive for the end user. |
| NFR-06 | Portability       | The application runs locally without relying on external services. |