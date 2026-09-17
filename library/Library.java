package library;

import java.util.ArrayList;

// MODULE 1: Book Management
// This class handles adding, removing, searching and showing books.
// It also handles adding and showing members.
public class Library {

    private ArrayList<Book> bookList;
    private final ArrayList<Member> memberList;

    public Library() {
        // When the library starts, load any previously saved data from files
        bookList = FileHandler.loadBooks();
        memberList = FileHandler.loadMembers();
    }

    // ---------- BOOK METHODS ----------

    public void addBook(int bookId, String title, String author) throws LibraryException {
        // Check if a book with the same ID already exists
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getBookId() == bookId) {
                throw new LibraryException("A book with ID " + bookId + " already exists.");
            }
        }
        Book newBook = new Book(bookId, title, author);
        bookList.add(newBook);
        FileHandler.saveBooks(bookList); // save immediately so data is not lost
        System.out.println("Book added successfully!");
    }

    public void removeBook(int bookId) throws LibraryException {
        Book book = findBookById(bookId);
        if (book == null) {
            throw new LibraryException("No book found with ID " + bookId);
        }
        if (book.isIssued()) {
            throw new LibraryException("Cannot remove book. It is currently issued to a member.");
        }
        bookList.remove(book);
        FileHandler.saveBooks(bookList);
        System.out.println("Book removed successfully!");
    }

    // Simple search: looks for the text inside the title (not case sensitive)
    public void searchBookByTitle(String keyword) {
        boolean found = false;
        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found matching: " + keyword);
        }
    }

    public void displayAllBooks() {
        if (bookList.isEmpty()) {
            System.out.println("No books in the library yet.");
            return;
        }
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println(bookList.get(i));
        }
    }

    public Book findBookById(int bookId) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getBookId() == bookId) {
                return bookList.get(i);
            }
        }
        return null; // not found
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    // ---------- MEMBER METHODS ----------

    public void addMember(int memberId, String name, String email) throws LibraryException {
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getMemberId() == memberId) {
                throw new LibraryException("A member with ID " + memberId + " already exists.");
            }
        }
        Member newMember = new Member(memberId, name, email);
        memberList.add(newMember);
        FileHandler.saveMembers(memberList);
        System.out.println("Member added successfully!");
    }

    public void displayAllMembers() {
        if (memberList.isEmpty()) {
            System.out.println("No members registered yet.");
            return;
        }
        for (int i = 0; i < memberList.size(); i++) {
            System.out.println(memberList.get(i));
        }
    }

    public Member findMemberById(int memberId) {
        for (int i = 0; i < memberList.size(); i++) {
            if (memberList.get(i).getMemberId() == memberId) {
                return memberList.get(i);
            }
        }
        return null;
    }

    public ArrayList<Member> getMemberList() {
        return memberList;
    }
}
