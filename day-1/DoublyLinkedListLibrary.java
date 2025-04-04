import java.util.Scanner;

// Class to represent a book
class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean availabilityStatus;

    public Book(String title, String author, String genre, int bookId, boolean availabilityStatus) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.availabilityStatus = availabilityStatus;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Genre: " + genre + ", Availability: " + (availabilityStatus ? "Available" : "Unavailable");
    }
}

// Class to represent a node in the doubly linked list
class Node {
    Book book;
    Node next;
    Node prev;

    public Node(Book book) {
        this.book = book;
        this.next = null;
        this.prev = null;
    }
}

// Class to represent the doubly linked list
class LibraryManagementSystem {
    private Node head;
    private Node tail;
    private int count; // To store the total number of books

    public LibraryManagementSystem() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    // 1. Add a new book at the beginning of the list
    public void addBookAtBeginning(Book book) {
        Node newNode = new Node(book);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        count++;
        System.out.println("Book added at the beginning.");
    }

    // 2. Add a new book at the end of the list
    public void addBookAtEnd(Book book) {
        Node newNode = new Node(book);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        count++;
        System.out.println("Book added at the end.");
    }

    // 3. Add a new book at a specific position in the list
    public void addBookAtPosition(int position, Book book) {
        if (position <= 0) {
            System.out.println("Invalid position. Please enter a positive number.");
            return;
        }
        if (position == 1) {
            addBookAtBeginning(book);
            return;
        }
        Node newNode = new Node(book);
        Node current = head;
        int currentPosition = 1;
        while (current != null && currentPosition < position - 1) {
            current = current.next;
            currentPosition++;
        }
        if (current == null) {
            System.out.println("Position is out of bounds. Book not added.");
            return;
        }
        newNode.next = current.next;
        newNode.prev = current;
        if (current.next != null) {
            current.next.prev = newNode;
        } else {
            tail = newNode;
        }
        current.next = newNode;
        count++;
        System.out.println("Book added at position " + position);
    }

    // 4. Remove a book by Book ID
    public void removeBookByBookId(int bookId) {
        if (head == null) {
            System.out.println("List is empty. No book to remove.");
            return;
        }
        if (head.book.bookId == bookId) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null; // List becomes empty
            }
            count--;
            System.out.println("Book with ID " + bookId + " removed.");
            return;
        }
        Node current = head;
        while (current != null && current.book.bookId != bookId) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("Book with ID " + bookId + " not found.");
            return;
        }
        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            tail = current.prev;
        }
        current.prev.next = current.next;
        count--;
        System.out.println("Book with ID " + bookId + " removed.");
    }

    // 5. Search for a book by Book Title or Author
    public void searchBook(String title, String author) {
        if (head == null) {
            System.out.println("List is empty. No books to search.");
            return;
        }
        Node current = head;
        boolean found = false;
        while (current != null) {
            if (current.book.title.equalsIgnoreCase(title) || current.book.author.equalsIgnoreCase(author)) {
                System.out.println("Book found: " + current.book);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("Book not found with given title or author.");
        }
    }

    // 6. Update a book's Availability Status
    public void updateBookAvailabilityStatus(int bookId, boolean newStatus) {
        if (head == null) {
            System.out.println("List is empty. No book to update.");
            return;
        }
        Node current = head;
        while (current != null && current.book.bookId != bookId) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("Book with ID " + bookId + " not found.");
            return;
        }
        current.book.availabilityStatus = newStatus;
        System.out.println("Availability status updated for book with ID " + bookId + ".");
    }

    // 7. Display all books in forward order
    public void displayAllBooksForward() {
        if (head == null) {
            System.out.println("List is empty. No books to display.");
            return;
        }
        System.out.println("All Books (Forward Order):");
        Node current = head;
        while (current != null) {
            System.out.println(current.book);
            current = current.next;
        }
    }

    // 8. Display all books in reverse order
    public void displayAllBooksReverse() {
        if (tail == null) {
            System.out.println("List is empty. No books to display.");
            return;
        }
        System.out.println("All Books (Reverse Order):");
        Node current = tail;
        while (current != null) {
            System.out.println(current.book);
            current = current.prev;
        }
    }

    // 9. Count the total number of books in the library
    public int getTotalNumberOfBooks() {
        return count;
    }
}

// Main class
public class DoublyLinkedListLibrary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryManagementSystem library = new LibraryManagementSystem();
        int choice;

        do {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add a book at the beginning");
            System.out.println("2. Add a book at the end");
            System.out.println("3. Add a book at a specific position");
            System.out.println("4. Remove a book by Book ID");
            System.out.println("5. Search for a book by Title or Author");
            System.out.println("6. Update a book's availability status");
            System.out.println("7. Display all books (forward)");
            System.out.println("8. Display all books (reverse)");
            System.out.println("9. Count the total number of books");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter Book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Availability Status (true/false): ");
                    boolean availabilityStatus = scanner.nextBoolean();
                    scanner.nextLine(); // Consume newline
                    Book book = new Book(title, author, genre, bookId, availabilityStatus);
                    library.addBookAtBeginning(book);
                    break;
                case 2:
                    System.out.print("Enter Book Title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    author = scanner.nextLine();
                    System.out.print("Enter Genre: ");
                    genre = scanner.nextLine();
                    System.out.print("Enter Book ID: ");
                    bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Availability Status (true/false): ");
                    availabilityStatus = scanner.nextBoolean();
                    scanner.nextLine(); // Consume newline
                    book = new Book(title, author, genre, bookId, availabilityStatus);
                    library.addBookAtEnd(book);
                    break;
                case 3:
                    System.out.print("Enter position to add book: ");
                    int position = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Book Title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    author = scanner.nextLine();
                    System.out.print("Enter Genre: ");
                    genre = scanner.nextLine();
                    System.out.print("Enter Book ID: ");
                    bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Availability Status (true/false): ");
                    availabilityStatus = scanner.nextBoolean();
                    scanner.nextLine(); // Consume newline
                    book = new Book(title, author, genre, bookId, availabilityStatus);
                    library.addBookAtPosition(position, book);
                    break;
                case 4:
                    System.out.print("Enter Book ID to remove: ");
                    bookId = scanner.nextInt();
                    library.removeBookByBookId(bookId);
                    break;
                case 5:
                    System.out.print("Enter Book Title to search: ");
                    String searchTitle = scanner.nextLine();
                    System.out.print("Enter Author to search: ");
                    String searchAuthor = scanner.nextLine();
                    library.searchBook(searchTitle, searchAuthor);
                    break;
                case 6:
                    System.out.print("Enter Book ID to update availability: ");
                    bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new Availability Status (true/false): ");
                    availabilityStatus = scanner.nextBoolean();
                    scanner.nextLine(); // Consume newline
                    library.updateBookAvailabilityStatus(bookId, availabilityStatus);
                    break;
                case 7:
                    library.displayAllBooksForward();
                    break;
                case 8:
                    library.displayAllBooksReverse();
                    break;
                case 9:
                    System.out.println("Total number of books: " + library.getTotalNumberOfBooks());
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
        scanner.close();
    }
}