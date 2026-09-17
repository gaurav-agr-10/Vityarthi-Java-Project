package library;

// A custom exception class..
// We use this instead of normal Exception so that we can catch OUR errors
// separately (like "book not found" or "book already issued").
public class LibraryException extends Exception {

    public LibraryException(String message) {
        super(message);
    }
}
