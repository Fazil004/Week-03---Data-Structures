import java.util.Scanner;

// Class to represent a movie
class Movie {
    String title;
    String director;
    int yearOfRelease;
    double rating;

    public Movie(String title, String director, int yearOfRelease, double rating) {
        this.title = title;
        this.director = director;
        this.yearOfRelease = yearOfRelease;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Director: " + director + ", Year of Release: " + yearOfRelease + ", Rating: " + rating;
    }
}

// Class to represent a node in the doubly linked list
class Node {
    Movie movie;
    Node next;
    Node prev;

    public Node(Movie movie) {
        this.movie = movie;
        this.next = null;
        this.prev = null;
    }
}

// Class to represent the doubly linked list
class MovieLinkedList {
    private Node head;
    private Node tail;

    public MovieLinkedList() {
        this.head = null;
        this.tail = null;
    }

    // 1. Add a movie record at the beginning of the list
    public void addAtBeginning(Movie movie) {
        Node newNode = new Node(movie);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        System.out.println("Movie added at the beginning.");
    }

    // 2. Add a movie record at the end of the list
    public void addAtEnd(Movie movie) {
        Node newNode = new Node(movie);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
        System.out.println("Movie added at the end.");
    }

    // 3. Add a movie record at a specific position in the list
    public void addAtPosition(int position, Movie movie) {
        if (position <= 0) {
            System.out.println("Invalid position. Please enter a positive number.");
            return;
        }
        if (position == 1) {
            addAtBeginning(movie);
            return;
        }
        Node newNode = new Node(movie);
        Node current = head;
        int count = 1;
        while (current != null && count < position - 1) {
            current = current.next;
            count++;
        }
        if (current == null) {
            System.out.println("Position is out of bounds. Movie not added.");
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
        System.out.println("Movie added at position " + position);
    }

    // 4. Remove a movie record by Movie Title
    public void removeByTitle(String title) {
        if (head == null) {
            System.out.println("List is empty. No movie to remove.");
            return;
        }
        if (head.movie.title.equalsIgnoreCase(title)) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null; // List becomes empty
            }
            System.out.println("Movie with title '" + title + "' removed.");
            return;
        }
        Node current = head;
        while (current != null && !current.movie.title.equalsIgnoreCase(title)) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("Movie with title '" + title + "' not found.");
            return;
        }
        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            tail = current.prev;
        }
        current.prev.next = current.next;
        System.out.println("Movie with title '" + title + "' removed.");
    }

    // 5. Search for a movie record by Director or Rating
    public void searchByDirectorOrRating(String director, double rating) {
        if (head == null) {
            System.out.println("List is empty. No movies to search.");
            return;
        }
        Node current = head;
        boolean found = false;
        while (current != null) {
            if (current.movie.director.equalsIgnoreCase(director) || current.movie.rating == rating) {
                System.out.println("Movie found: " + current.movie);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No movies found with the given director or rating.");
        }
    }

    // 6. Display all movie records in forward order
    public void displayAllMoviesForward() {
        if (head == null) {
            System.out.println("List is empty. No movies to display.");
            return;
        }
        System.out.println("Movie Records (Forward Order):");
        Node current = head;
        while (current != null) {
            System.out.println(current.movie);
            current = current.next;
        }
    }

    // 7. Display all movie records in reverse order
    public void displayAllMoviesReverse() {
        if (tail == null) {
            System.out.println("List is empty. No movies to display.");
            return;
        }
        System.out.println("Movie Records (Reverse Order):");
        Node current = tail;
        while (current != null) {
            System.out.println(current.movie);
            current = current.prev;
        }
    }

    // 8. Update a movie's Rating based on the Movie Title
    public void updateRatingByTitle(String title, double newRating) {
        if (head == null) {
            System.out.println("List is empty. No movie to update.");
            return;
        }
        Node current = head;
        while (current != null && !current.movie.title.equalsIgnoreCase(title)) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("Movie with title '" + title + "' not found.");
            return;
        }
        current.movie.rating = newRating;
        System.out.println("Rating updated for movie with title '" + title + "'.");
    }
}

// Main class
public class MovieManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MovieLinkedList movieList = new MovieLinkedList();
        int choice;

        do {
            System.out.println("\nMovie Management System Menu:");
            System.out.println("1. Add a movie at the beginning");
            System.out.println("2. Add a movie at the end");
            System.out.println("3. Add a movie at a specific position");
            System.out.println("4. Remove a movie by title");
            System.out.println("5. Search for a movie by director or rating");
            System.out.println("6. Display all movies (forward)");
            System.out.println("7. Display all movies (reverse)");
            System.out.println("8. Update a movie's rating");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Movie Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Director: ");
                    String director = scanner.nextLine();
                    System.out.print("Enter Year of Release: ");
                    int year = scanner.nextInt();
                    System.out.print("Enter Rating: ");
                    double rating = scanner.nextDouble();
                    scanner.nextLine(); // Consume the remaining newline
                    Movie movie = new Movie(title, director, year, rating);
                    movieList.addAtBeginning(movie);
                    break;
                case 2:
                    System.out.print("Enter Movie Title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter Director: ");
                    director = scanner.nextLine();
                    System.out.print("Enter Year of Release: ");
                    year = scanner.nextInt();
                    System.out.print("Enter Rating: ");
                    rating = scanner.nextDouble();
                    scanner.nextLine();
                    movie = new Movie(title, director, year, rating);
                    movieList.addAtEnd(movie);
                    break;
                case 3:
                    System.out.print("Enter position to add movie: ");
                    int position = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Movie Title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter Director: ");
                    director = scanner.nextLine();
                    System.out.print("Enter Year of Release: ");
                    year = scanner.nextInt();
                    System.out.print("Enter Rating: ");
                    rating = scanner.nextDouble();
                    scanner.nextLine();
                    movie = new Movie(title, director, year, rating);
                    movieList.addAtPosition(position, movie);
                    break;
                case 4:
                    System.out.print("Enter Movie Title to remove: ");
                    title = scanner.nextLine();
                    movieList.removeByTitle(title);
                    break;
                case 5:
                    System.out.print("Enter Director to search: ");
                    String searchDirector = scanner.nextLine();
                    System.out.print("Enter Rating to search: ");
                    double searchRating = scanner.nextDouble();
                    scanner.nextLine();
                    movieList.searchByDirectorOrRating(searchDirector, searchRating);
                    break;
                case 6:
                    movieList.displayAllMoviesForward();
                    break;
                case 7:
                    movieList.displayAllMoviesReverse();
                    break;
                case 8:
                    System.out.print("Enter Movie Title to update rating: ");
                    title = scanner.nextLine();
                    System.out.print("Enter new Rating: ");
                    rating = scanner.nextDouble();
                    scanner.nextLine();
                    movieList.updateRatingByTitle(title, rating);
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