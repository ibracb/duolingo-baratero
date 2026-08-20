# Use cases

Detail of the use cases of the Duolingo Baratero application.

## 1. Register a user

**Primary actor:** Unregistered user.

**Goal:** Allow a new user to register on the platform.

**Preconditions:**

1. The user must not be registered previously.

**Steps:**

1. The user requests to start the registration process.
2. The system requests the following data from the user: name, email address and password (and its confirmation).
3. The user provides the requested information.
4. The system checks whether the email address is already registered.
5. The system checks that the passwords match.
6. The system registers the user in the database.
7. The system logs in for the new user.
8. The system returns to the user an initial state with no courses assigned.

**Alternative flows:**

**Alternative flow 1 – Registration after interest in courses:**

1. The user requests information about one or more courses.
2. The system provides details of the courses.
3. The user decides to register and includes up to 3 courses of interest during the process.
4. (Resumes from step 4 of the main scenario).
5. The system registers the user with the list of selected courses.
6. The system returns to the user an initial state with the selected courses.

**Alternative flow 2 – Email already registered:**

4. The system detects that the email address is already registered.
5. The system reports the error and discards the entered data.
6. The user must restart the process from the beginning.

**Alternative flow 3 – Passwords do not match:**

5. The system detects that the passwords do not match.
6. The system reports the error and discards the entered data.
7. The user must restart the process from the beginning.

**Postconditions:**

1. The user is registered.
2. The user can use the application instantly.

## 2. Log in

**Primary actor:** Registered user (without an active session).

**Goal:** Allow the user to access the application with their credentials.

**Preconditions:**

1. The user must be registered.

**Steps:**

1. The user accesses the start window.
2. The user clicks the "Log in" button.
3. The system shows the login window and requests the email and the password.
4. The user enters their credentials.
5. The system checks that the email belongs to a registered user and that the password matches.
6. The system starts the session and shows the main window.

**Alternative flows:**

**Alternative flow 1 – Empty fields:**

4. The system detects that the email or the password are empty.
5. The system shows an error message and does not start the session.

**Alternative flow 2 – User not found:**

5. The system does not find any user with that email.
6. The system shows "Login failed" and does not start the session.

**Alternative flow 3 – Incorrect password:**

5. The system detects that the password does not match the user's one.
6. The system shows "Login failed" and does not start the session.

**Postconditions:**

1. The user is authenticated in the main window.

## 3. Choose a course

**Primary actor:** Registered user.

**Goal:** Allow the user to select a course from the library.

**Preconditions:**

1. The user must be registered and logged in.

**Steps:**

1. The user decides to start a new course.
2. The system provides the courses for the user to choose from.
3. The user selects the course they want to do, applying the filters they need.
4. The user clicks on the new course to [start it](#4-start-a-course).

**Alternative flows:**

**Alternative flow 1 – The user does not choose any course:**

3. The user goes back because they are not interested in any course.

**Postconditions:**

1. The user has the course available in their profile.

## 4. Start a course

**Primary actor:** Registered user.

**Goal:** Present questions or learning cards to complete the course.

**Preconditions:**

1. The user must be registered and logged in.

**Steps:**

1. The user selects the course they want to start.
2. The system shows the types of strategies for taking the course.
3. The user selects the strategy.
4. The system generates the questions of the first content block of the course.
5. The user answers the questions or studies the cards.
6. The system checks the answers.
7. The user passes the block.
8. The system saves the progress.
9. The system prepares the questions of the next block (returns to step 5).

**Alternative flows:**

**Alternative flow 1 – The user leaves the programme at the end of a block:**

7. The user leaves the course questions.
8. The system saves the progress.

**Alternative flow 2 – The user leaves the programme in the middle of a block:**

7. The user leaves the programme before finishing the block.
8. The system does not save the progress.
9. The user will have to start it from the beginning.

**Alternative flow 3 – The user fails the block:**

8. The system does not save the progress.
9. The system shows the questions of the same block again.

**Alternative flow 4 – The user finishes the course:**

8. The system sends them to their profile.
9. The system asks whether the user wants to delete the course or restart it.

**Alternative flow 5 – The user already had the course started:**

4. The system generates the questions of the last finished content block of the course.
5. (Returns to step 5 of the main flow).

**Postconditions:**

1. The system saves the course in the user's in-progress courses.
2. The system saves the user's progress.

## 5. View statistics

**Primary actor:** Registered user.

**Goal:** Show usage time, the best streak and other progress indicators.

**Preconditions:**

1. The user must be registered and logged in.

**Steps:**

1. The user requests to view their statistics.
2. The system calculates their statistics.
3. The system shows their statistics.

**Postconditions:**

1. The user views their statistics and can make decisions based on them.

## 6. Import a course

**Primary actor:** Registered user.

**Goal:** Allow users to create their own courses.

**Preconditions:**

1. The user must be registered and logged in.
2. The user must have obtained or created a course.

**Steps:**

1. The user requests to import a course.
2. The user chooses the file extension.
3. The user looks for the course file in their files.
4. The system processes the file and gives no errors.
5. The system returns the user to their profile with the new course available.
6. The user [starts the course](#4-start-a-course).

**Alternative flows:**

**Alternative flow 1 – The file does not have the extension the user stated or contains errors:**

4. The system processes the file and gives errors.
5. The system sends an error message indicating that the file is not valid.

**Postconditions:**

1. The course remains available in the database in case the user wants to take it again.

## 7. Share a course

**Primary actor:** Registered user.

**Goal:** Allow other users to access courses created by the community.

**Preconditions:**

1. The user must be registered and logged in.
2. The user must have the course they want to share in their database.

**Steps:**

1. The user requests to export a course.
2. The system provides the courses for the user to choose from.
3. The user selects the course they want to do, applying the filters they need.
4. The user chooses the extension in which they want to export it.
5. The system generates the file.
6. The user chooses the folder in which they want to save the file.

**Alternative flows:**

**Alternative flow 1 – The system finds an error when generating the file:**

5. The system reports an error in the serialisation of the course.

**Postconditions:**

1. The user can share the file so that other users [import the course](#6-import-a-course).

## 8. Install a course from a file

**Primary actor:** Creator user.

**Goal:** Allow new courses to be loaded from files.

**Preconditions:**

1. The user must be registered in the application.

**Steps:**

1. The user accesses the main window.
2. The user goes into the *My Courses* section.
3. The user selects the option to load a course from a file.
4. The user uploads a file with the structure of the course.
5. The user confirms that they want to install the course from the confirmation window.
6. The system validates the file and installs it in the internal library.
7. The system reports that the course has been installed correctly through the confirmation window.

**Alternative flows:**

**Alternative flow 1 – The user does not want to install the course:**

5. The user reports that they do not want to install the selected course from the confirmation window.
6. The user returns to the main window.

**Alternative flow 2 – The system does not validate the course file:**

6. The system informs the user of the error produced from the installation error window.
7. The user returns to the main window.

**Postconditions:**

1. The course remains available on the platform.

## 9. Add new questions

**Primary actor:** Creator user.

**Goal:** Allow new questions to be incorporated into an already created course.

**Preconditions:**

1. The user must be registered and logged in.
2. The user must have at least one created course.

**Steps:**

1. The user accesses the main window.
2. The user selects the course they want to modify in the main window.
3. The user accesses the *modify course* window.
4. The user presses the "Add question" button.
5. The user chooses the question type in the *Choose question* window.
6. The user accesses the *Create question with options* window.
7. The user completes the required fields.
8. The user presses the "Save" button, returning to the *Modify course* window.

**Alternative flows:**

**Alternative flow 1 – Image question:**

4. The user accesses the *Create question with images* window.
5. Returns to step 5.

**Alternative flow 2 – Audio question:**

4. The user accesses the *Create question with audio* window.
5. Returns to step 5.

**Alternative flow 3 – Flashcard question:**

4. The user accesses the *Create flashcard question* window.
5. Returns to step 5.

**Postconditions:**

1. The user adds a question to one of their courses.

## 10. Resume a course

**Primary actor:** Student user of a course that has already started.

**Goal:** Allow a user to resume a course from the point where they left it.

**Preconditions:**

1. The user must be registered and logged in.
2. The user must have started the course.

**Steps:**

1. The user selects the course they want to continue.
2. The system generates the questions of the last finished content block of the course.
3. The user answers the questions or studies the cards.
4. The system checks the answers.
5. The user passes the block.
6. The system saves the progress.
7. The system prepares the questions of the next block (returns to step 3).

**Alternative flows:**

The alternative flows are the same as those of [Start a course](#4-start-a-course).

**Postconditions:**

1. The questions answered in the last access are not saved when closing.
2. The progress saves of a course are generated when each content block is finished.