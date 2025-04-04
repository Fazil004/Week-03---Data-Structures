import java.util.Scanner;

// Class to represent a state of the text
class TextState {
    String text;

    public TextState(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Text: " + text;
    }
}

// Class to represent a node in the doubly linked list
class Node {
    TextState state;
    Node next;
    Node prev;

    public Node(TextState state) {
        this.state = state;
        this.next = null;
        this.prev = null;
    }
}

// Class to represent the doubly linked list for Undo/Redo
class TextEditor {
    private Node head;
    private Node current; // Points to the current state
    private int historySize; // Maximum history size

    public TextEditor(int historySize) {
        this.head = null;
        this.current = null;
        this.historySize = historySize;
    }

    // 1. Add a new text state at the end of the list
    public void addTextState(String text) {
        TextState newState = new TextState(text);
        Node newNode = new Node(newState);

        if (head == null) {
            head = newNode;
            current = newNode;
        } else {
            current.next = newNode;
            newNode.prev = current;
            current = newNode;
        }
        trimHistory(); // Keep history within the limit
        System.out.println("Text state added.");
    }

    // Method to trim the history if it exceeds the maximum size
    private void trimHistory() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        if (count > historySize) {
            head = head.next;
            head.prev = null;
            System.out.println("History trimmed. Oldest state removed.");
        }
    }

    // 2. Implement the undo functionality
    public void undo() {
        if (current == null || current.prev == null) {
            System.out.println("Cannot undo. At the beginning of history.");
            return;
        }
        current = current.prev;
        System.out.println("Undoing to previous state.");
    }

    // 3. Implement the redo functionality
    public void redo() {
        if (current == null || current.next == null) {
            System.out.println("Cannot redo. At the end of history.");
            return;
        }
        current = current.next;
        System.out.println("Redoing to next state.");
    }

    // 4. Display the current state of the text
    public void displayCurrentState() {
        if (current == null) {
            System.out.println("No text state available.");
            return;
        }
        System.out.println("Current State: " + current.state);
    }
}

// Main class
public class DoublyListRedoUndo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the maximum history size: ");
        int historySize = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        TextEditor editor = new TextEditor(historySize);
        int choice;

        do {
            System.out.println("\nText Editor Menu:");
            System.out.println("1. Enter new text");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Display current state");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter text: ");
                    String text = scanner.nextLine();
                    editor.addTextState(text);
                    break;
                case 2:
                    editor.undo();
                    break;
                case 3:
                    editor.redo();
                    break;
                case 4:
                    editor.displayCurrentState();
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