package library;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// MODULE 2: Issue / Return Management
// This class handles issuing a book to a member, returning it back,
// and calculating fine if the book is returned late.
public class IssueReturn {

    private Library library;

    // A book can be kept for 14 days before fine starts
    private static final int ALLOWED_DAYS = 14;

    // Fine is 5 rupees per day after the allowed days
    private static final int FINE_PER_DAY = 5;

    public IssueReturn(Library library) {
        this.library = library;
    }

    public void issueBook(int bookId, int memberId) throws LibraryException {
        Book book = library.findBookById(bookId);
        Member member = library.findMemberById(memberId);

        if (book == null) {
            throw new LibraryException("Book with ID " + bookId + " does not exist.");
        }
        if (member == null) {
            throw new LibraryException("Member with ID " + memberId + " does not exist.");
        }
        if (book.isIssued()) {
            throw new LibraryException("This book is already issued to another member.");
        }

        String today = LocalDate.now().toString(); // e.g. "2026-09-17"
        book.markAsIssued(memberId, today);
        FileHandler.saveBooks(library.getBookList());

        System.out.println("Book \"" + book.getTitle() + "\" issued to " + member.getName() + " on " + today);
    }

    public void returnBook(int bookId) throws LibraryException {
        Book book = library.findBookById(bookId);

        if (book == null) {
            throw new LibraryException("Book with ID " + bookId + " does not exist.");
        }
        if (!book.isIssued()) {
            throw new LibraryException("This book was not issued, so it cannot be returned.");
        }

        int fine = calculateFine(book.getIssueDate());

        System.out.println("Book \"" + book.getTitle() + "\" returned successfully.");
        if (fine > 0) {
            System.out.println("This book was returned late. Fine amount = Rs. " + fine);
        } else {
            System.out.println("Returned within allowed time. No fine.");
        }

        book.markAsReturned();
        FileHandler.saveBooks(library.getBookList());
    }

    // Works out how many days late the book is, and calculates the fine.
    // If it's not late, fine is 0.
    private int calculateFine(String issueDateText) {
        LocalDate issueDate = LocalDate.parse(issueDateText);
        LocalDate today = LocalDate.now();

        long daysKept = ChronoUnit.DAYS.between(issueDate, today);

        if (daysKept > ALLOWED_DAYS) {
            long lateDays = daysKept - ALLOWED_DAYS;
            return (int) (lateDays * FINE_PER_DAY);
        }
        return 0;
    }
}
