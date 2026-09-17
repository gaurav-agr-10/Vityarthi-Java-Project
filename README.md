# Library Management System (Pure Java)

## Overview
A console-based Library Management System built in **pure Java** (no external
frameworks or database). It allows a librarian to manage books and members,
and to issue/return books with automatic fine calculation for late returns.
All data is saved to simple text files so it persists between runs.

## Features
- Add, remove, search, and display books
- Add and display members
- Issue a book to a member
- Return a book with automatic fine calculation (Rs. 5/day after 14 days)
- Data is saved to text files (`data/books.txt`, `data/members.txt`) so
  nothing is lost when the program closes
- Input validation and custom exception handling (`LibraryException`)

## Technologies / Tools Used
- Java (core Java only — Collections, File I/O, java.time)
- No external libraries or database required

## Project Structure
```
LibraryManagementSystem/
├── src/library/
│   ├── Book.java            (Book model)
│   ├── Member.java          (Member model)
│   ├── LibraryException.java(Custom exception)
│   ├── FileHandler.java     (Reads/writes text files)
│   ├── Library.java         (Module 1: Book & member management)
│   ├── IssueReturn.java     (Module 2: Issue/Return + fine logic)
│   └── Main.java            (Module 3: Menu-driven user interaction)
├── data/                    (auto-created; stores books.txt, members.txt)
├── README.md
└── statement.md
```

## Steps to Install & Run
1. Make sure Java (JDK 8 or above) is installed: `java -version`
2. Open a terminal in the project's root folder.
3. Compile the source files:
   ```
   javac -d out src/library/*.java
   ```
4. Run the program:
   ```
   java -cp out library.Main
   ```
5. Follow the on-screen menu to add books, add members, issue/return books.

## Instructions for Testing
- **Add Book test:** Choose option 1, enter a Book ID/Title/Author, then
  choose option 4 to confirm it appears in the list.
- **Duplicate ID test:** Try adding a book with an ID that already exists —
  the program should show an error instead of crashing.
- **Issue/Return test:** Add a member (option 5), issue a book to them
  (option 7), then return it (option 8) to see the fine calculation logic
  run (fine will be 0 if returned the same day).
- **Invalid input test:** Enter a letter instead of a number at the menu —
  the program should show "Invalid input!" instead of crashing.
- **Persistence test:** Add a book, close the program, and run it again —
  the book should still be there (loaded from `data/books.txt`).

## Screenshots
_(Add screenshots of the running console menu here after you run it.)_
