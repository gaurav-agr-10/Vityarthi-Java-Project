# Problem Statement

Small libraries and reading rooms often manage their book records manually
using registers, which makes tracking who has which book, when it was
issued, and whether a fine is due, slow and error-prone. This project
solves that problem with a simple console-based Library Management System.

## Scope of the Project
The system covers:
- Maintaining a catalog of books (add/remove/search/view)
- Maintaining a list of registered members
- Issuing books to members and returning them
- Automatically calculating late-return fines
- Saving all data to text files so records are not lost between sessions

The system does **not** cover (out of scope): user login/authentication,
a graphical interface, or online/network access — it is a single-user,
offline, console-based tool built using core Java only.

## Target Users
- A librarian or front-desk staff member who manages book lending
- Small libraries, school libraries, or personal book collections that
  don't need a full database-backed system

## High-Level Features
1. **Book Management** – add, remove, search by title, and list all books
2. **Member Management** – register new members and list all members
3. **Issue/Return Management** – issue a book to a member, return it, and
   automatically calculate a fine if it is returned after the allowed
   14-day period (Rs. 5 per late day)
4. **Data Persistence** – all books and members are saved to text files
   (`data/books.txt`, `data/members.txt`) and reloaded automatically the
   next time the program starts
