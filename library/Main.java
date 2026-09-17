package library;

import java.util.Scanner;

// MODULE 3: Main Menu / User Interaction
// This is the entry point of the program. It shows a menu to the user
// and calls the right method depending on what the user chooses.
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        IssueReturn issueReturn = new IssueReturn(library);

        int choice = -1;

        System.out.println("=====================================");
        System.out.println(" WELCOME TO LIBRARY MANAGEMENT SYSTEM ");
        System.out.println("=====================================");

        while (choice != 0) {
            printMenu();
            System.out.print("Enter your choice: ");

            // Validate that the input is actually a number
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.\n");
                sc.next(); // clear the bad input
                continue;
            }

            choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Book ID: ");
                        int bookId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter Author: ");
                        String author = sc.nextLine();
                        library.addBook(bookId, title, author);
                        break;

                    case 2:
                        System.out.print("Enter Book ID to remove: ");
                        int removeId = sc.nextInt();
                        library.removeBook(removeId);
                        break;

                    case 3:
                        System.out.print("Enter title or keyword to search: ");
                        sc.nextLine();
                        String keyword = sc.nextLine();
                        library.searchBookByTitle(keyword);
                        break;

                    case 4:
                        System.out.println("\n--- All Books ---");
                        library.displayAllBooks();
                        break;

                    case 5:
                        System.out.print("Enter Member ID: ");
                        int memberId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();
                        library.addMember(memberId, name, email);
                        break;

                    case 6:
                        System.out.println("\n--- All Members ---");
                        library.displayAllMembers();
                        break;

                    case 7:
                        System.out.print("Enter Book ID to issue: ");
                        int issueBookId = sc.nextInt();
                        System.out.print("Enter Member ID: ");
                        int issueMemberId = sc.nextInt();
                        issueReturn.issueBook(issueBookId, issueMemberId);
                        break;

                    case 8:
                        System.out.print("Enter Book ID to return: ");
                        int returnBookId = sc.nextInt();
                        issueReturn.returnBook(returnBookId);
                        break;

                    case 0:
                        System.out.println("Thank you for using the Library Management System. Goodbye!");
                        break;

                    default:
                        System.out.println("Please choose a valid option from the menu.");
                }
            } catch (LibraryException e) {
                // This catches OUR custom errors, like "book not found"
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                // This catches any other unexpected error, like wrong input type
                System.out.println("Something went wrong: " + e.getMessage());
                sc.nextLine(); // clear leftover input so program doesn't get stuck
            }

            System.out.println(); // blank line for readability
        }

        sc.close();
    }

    private static void printMenu() {
        System.out.println("---------------------------------------");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Search Book by Title");
        System.out.println("4. Display All Books");
        System.out.println("5. Add Member");
        System.out.println("6. Display All Members");
        System.out.println("7. Issue Book");
        System.out.println("8. Return Book");
        System.out.println("0. Exit");
        System.out.println("---------------------------------------");
    }
}
