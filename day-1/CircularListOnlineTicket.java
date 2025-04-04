import java.util.Scanner;

// Class to represent a booked ticket
class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    int seatNumber;
    String bookingTime;

    public Ticket(int ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId + ", Customer Name: " + customerName + ", Movie Name: " + movieName + ", Seat Number: " + seatNumber + ", Booking Time: " + bookingTime;
    }
}

// Class to represent a node in the circular linked list
class Node {
    Ticket ticket;
    Node next;

    public Node(Ticket ticket) {
        this.ticket = ticket;
        this.next = null;
    }
}

// Class to represent the circular linked list
class TicketReservationSystem {
    private Node head;
    private int totalTickets;

    public TicketReservationSystem() {
        this.head = null;
        this.totalTickets = 0;
    }

    // 1. Add a new ticket reservation at the end of the circular list
    public void addTicket(Ticket ticket) {
        Node newNode = new Node(ticket);
        if (head == null) {
            head = newNode;
            head.next = head; // Point to itself for circularity
        } else {
            Node temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = head;
        }
        totalTickets++;
        System.out.println("Ticket added.");
    }

    // 2. Remove a ticket by Ticket ID
    public void removeTicket(int ticketId) {
        if (head == null) {
            System.out.println("No tickets to remove.");
            return;
        }
        if (head.ticket.ticketId == ticketId) {
            if (head.next == head) { // Only one node
                head = null;
            } else {
                Node temp = head;
                while (temp.next != head) {
                    temp = temp.next;
                }
                temp.next = head.next;
                head = head.next;
            }
            totalTickets--;
            System.out.println("Ticket removed.");
            return;
        }
        Node current = head;
        Node prev = null;
        while (current.next != head && current.ticket.ticketId != ticketId) {
            prev = current;
            current = current.next;
        }
        if (current.next == head) {
            System.out.println("Ticket not found.");
            return;
        }
        prev.next = current.next;
        totalTickets--;
        System.out.println("Ticket removed.");
    }

    // 3. Display the current tickets in the list
    public void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        System.out.println("Current Tickets:");
        Node temp = head;
        do {
            System.out.println(temp.ticket);
            temp = temp.next;
        } while (temp != head);
    }

    // 4. Search for a ticket by Customer Name or Movie Name
    public void searchTicket(String customerName, String movieName) {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Node current = head;
        boolean found = false;
        do {
            if (current.ticket.customerName.equalsIgnoreCase(customerName) || current.ticket.movieName.equalsIgnoreCase(movieName)) {
                System.out.println("Ticket found: " + current.ticket);
                found = true;
            }
            current = current.next;
        } while (current != head);
        if (!found) {
            System.out.println("Ticket not found.");
        }
    }

    // 5. Calculate the total number of booked tickets
    public int getTotalNumberOfBookedTickets() {
        return totalTickets;
    }
}

// Main class
public class CircularListOnlineTicket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketReservationSystem system = new TicketReservationSystem();
        int choice;

        do {
            System.out.println("\nOnline Ticket Reservation System Menu:");
            System.out.println("1. Add a ticket");
            System.out.println("2. Remove a ticket");
            System.out.println("3. Display all tickets");
            System.out.println("4. Search for a ticket");
            System.out.println("5. Get total number of booked tickets");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Ticket ID: ");
                    int ticketId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Customer Name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter Movie Name: ");
                    String movieName = scanner.nextLine();
                    System.out.print("Enter Seat Number: ");
                    int seatNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Booking Time: ");
                    String bookingTime = scanner.nextLine();
                    system.addTicket(new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime));
                    break;
                case 2:
                    System.out.print("Enter Ticket ID to remove: ");
                    ticketId = scanner.nextInt();
                    system.removeTicket(ticketId);
                    break;
                case 3:
                    system.displayTickets();
                    break;
                case 4:
                    System.out.print("Enter Customer Name to search: ");
                    String searchCustomerName = scanner.nextLine();
                    System.out.print("Enter Movie Name to search: ");
                    String searchMovieName = scanner.nextLine();
                    system.searchTicket(searchCustomerName, searchMovieName);
                    break;
                case 5:
                    System.out.println("Total number of booked tickets: " + system.getTotalNumberOfBookedTickets());
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