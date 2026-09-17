package library;

import java.io.*;
import java.util.ArrayList;

// This class takes care of reading and writing data to text files.
// We keep all file-related code here so that other classes don't need
// to worry about how saving/loading actually works..
public class FileHandler {

    private static final String BOOKS_FILE = "data/books.txt";
    private static final String MEMBERS_FILE = "data/members.txt";

    // Saves the full list of books to books.txt
    // Every time we call this, it overwrites the old file with fresh data.
    public static void saveBooks(ArrayList<Book> books) {
        try {
            File file = new File(BOOKS_FILE);
            file.getParentFile().mkdirs(); // make sure "data" folder exists

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < books.size(); i++) {
                writer.write(books.get(i).toFileString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving books: " + e.getMessage());
        }
    }

    // Reads books.txt and rebuilds the ArrayList of Book objects
    public static ArrayList<Book> loadBooks() {
        ArrayList<Book> books = new ArrayList<Book>();
        File file = new File(BOOKS_FILE);

        if (!file.exists()) {
            return books; // no file yet means no books saved, return empty list
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\|");
                int bookId = Integer.parseInt(parts[0]);
                String title = parts[1];
                String author = parts[2];
                boolean isIssued = Boolean.parseBoolean(parts[3]);
                int issuedTo = Integer.parseInt(parts[4]);
                String issueDate = parts[5];

                Book book = new Book(bookId, title, author);
                if (isIssued) {
                    book.markAsIssued(issuedTo, issueDate);
                }
                books.add(book);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error while loading books: " + e.getMessage());
        }

        return books;
    }

    // Saves the full list of members to members.txt
    public static void saveMembers(ArrayList<Member> members) {
        try {
            File file = new File(MEMBERS_FILE);
            file.getParentFile().mkdirs();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < members.size(); i++) {
                writer.write(members.get(i).toFileString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving members: " + e.getMessage());
        }
    }

    // Reads members.txt and rebuilds the ArrayList of Member objects
    public static ArrayList<Member> loadMembers() {
        ArrayList<Member> members = new ArrayList<Member>();
        File file = new File(MEMBERS_FILE);

        if (!file.exists()) {
            return members;
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\|");
                int memberId = Integer.parseInt(parts[0]);
                String name = parts[1];
                String email = parts[2];
                members.add(new Member(memberId, name, email));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error while loading members: " + e.getMessage());
        }

        return members;
    }
}
