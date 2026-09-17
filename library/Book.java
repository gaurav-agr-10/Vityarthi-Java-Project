package library;

// This class represents one Book in the library.
// It just stores the details of a book and gives methods to read/change them.
public class Book {

    private int bookId;
    private String title;
    private String author;
    private boolean isIssued;   // true if book is currently issued to someone
    private int issuedToMemberId; // -1 means not issued to anyone
    private String issueDate;     // simple date as text, e.g. "2026-09-01"

    // Constructor - used when we add a NEW book (not issued yet)
    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = false;
        this.issuedToMemberId = -1;
        this.issueDate = "";
    }

    // Getters and setters
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public int getIssuedToMemberId() {
        return issuedToMemberId;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void markAsIssued(int memberId, String issueDate) {
        this.isIssued = true;
        this.issuedToMemberId = memberId;
        this.issueDate = issueDate;
    }

    public void markAsReturned() {
        this.isIssued = false;
        this.issuedToMemberId = -1;
        this.issueDate = "";
    }

    // This is used to print book details nicely on the screen
    public String toString() {
        String status;
        if (isIssued) {
            status = "Issued (Member ID: " + issuedToMemberId + ", Issue Date: " + issueDate + ")";
        } else {
            status = "Available";
        }
        return "Book ID: " + bookId + " | Title: " + title + " | Author: " + author + " | Status: " + status;
    }

    // This is used when we save the book into a text file (data/books.txt)
    // We separate fields using "|" so it is easy to split later
    public String toFileString() {
        return bookId + "|" + title + "|" + author + "|" + isIssued + "|" + issuedToMemberId + "|" + issueDate;
    }
}
