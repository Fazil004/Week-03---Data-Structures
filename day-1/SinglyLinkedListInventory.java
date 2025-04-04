import java.util.Scanner;

// Class to represent an item
class Item {
    int itemId;
    String itemName;
    int quantity;
    double price;

    public Item(int itemId, String itemName, int quantity, double price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item ID: " + itemId + ", Item Name: " + itemName + ", Quantity: " + quantity + ", Price: $" + price;
    }
}

// Class to represent a node in the linked list
class Node {
    Item item;
    Node next;

    public Node(Item item) {
        this.item = item;
        this.next = null;
    }
}

// Class to represent the singly linked list
class InventoryList {
    private Node head;

    public InventoryList() {
        this.head = null;
    }

    // 1. Add an item at the beginning of the list
    public void addItemAtBeginning(Item item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        System.out.println("Item added at the beginning.");
    }

    // 2. Add an item at the end of the list
    public void addItemAtEnd(Item item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Item added at the end.");
    }

    // 3. Add an item at a specific position in the list
    public void addItemAtPosition(int position, Item item) {
        if (position <= 0) {
            System.out.println("Invalid position. Please enter a positive number.");
            return;
        }
        if (position == 1) {
            addItemAtBeginning(item);
            return;
        }
        Node newNode = new Node(item);
        Node current = head;
        int count = 1;
        while (current != null && count < position - 1) {
            current = current.next;
            count++;
        }
        if (current == null) {
            System.out.println("Position is out of bounds. Item not added.");
            return;
        }
        newNode.next = current.next;
        current.next = newNode;
        System.out.println("Item added at position " + position);
    }

    // 4. Remove an item based on Item ID
    public void removeItemById(int itemId) {
        if (head == null) {
            System.out.println("List is empty. No item to remove.");
            return;
        }
        if (head.item.itemId == itemId) {
            head = head.next;
            System.out.println("Item with ID " + itemId + " removed.");
            return;
        }
        Node current = head;
        Node previous = null;
        while (current != null && current.item.itemId != itemId) {
            previous = current;
            current = current.next;
        }
        if (current == null) {
            System.out.println("Item with ID " + itemId + " not found.");
            return;
        }
        previous.next = current.next;
        System.out.println("Item with ID " + itemId + " removed.");
    }

    // 5. Update the quantity of an item by Item ID
    public void updateQuantityById(int itemId, int newQuantity) {
        if (head == null) {
            System.out.println("List is empty. No item to update.");
            return;
        }
        Node current = head;
        while (current != null && current.item.itemId != itemId) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("Item with ID " + itemId + " not found.");
            return;
        }
        current.item.quantity = newQuantity;
        System.out.println("Quantity updated for item with ID " + itemId + ".");
    }

    // 6. Search for an item based on Item ID or Item Name
    public void searchItem(int itemId, String itemName) {
        if (head == null) {
            System.out.println("List is empty. No item to search.");
            return;
        }
        Node current = head;
        boolean found = false;
        while (current != null) {
            if (current.item.itemId == itemId || current.item.itemName.equalsIgnoreCase(itemName)) {
                System.out.println("Item found: " + current.item);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("Item not found with given ID or Name.");
        }
    }

    // 7. Calculate and display the total value of inventory
    public void displayTotalInventoryValue() {
        if (head == null) {
            System.out.println("List is empty. Inventory value is 0.");
            return;
        }
        double totalValue = 0;
        Node current = head;
        while (current != null) {
            totalValue += current.item.quantity * current.item.price;
            current = current.next;
        }
        System.out.println("Total Inventory Value: $" + totalValue);
    }

    // Helper method to get the node at a specific position
    private Node getNodeAtPosition(int position) {
        if (position <= 0 || head == null) {
            return null;
        }
        Node current = head;
        int count = 1;
        while (current != null && count < position) {
            current = current.next;
            count++;
        }
        return current;
    }
    // 8. Sort the inventory based on Item Name or Price
    public void sortInventory(String sortBy, String order) {
        if (head == null || head.next == null) {
            return; // Already sorted or empty
        }

        Node sortedHead = mergeSort(head, sortBy, order);
        this.head = sortedHead; // Update the head of the list
    }

    // Merge sort implementation
    private Node mergeSort(Node head, String sortBy, String order) {
        if (head == null || head.next == null) {
            return head;
        }

        // Split the list into two halves
        Node middle = getMiddle(head);
        Node nextOfMiddle = middle.next;
        middle.next = null; // Separate the two halves

        // Recursively sort the two halves
        Node left = mergeSort(head, sortBy, order);
        Node right = mergeSort(nextOfMiddle, sortBy, order);

        // Merge the sorted halves
        Node sortedList = merge(left, right, sortBy, order);
        return sortedList;
    }

    // Helper method to find the middle of the linked list
    private Node getMiddle(Node head) {
        if (head == null) {
            return null;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Helper method to merge two sorted linked lists
private Node merge(Node left, Node right, String sortBy, String order) {
    Node result = null;
    Node tail = null;

    while (left != null && right != null) {
        boolean shouldPickLeft;
        if (sortBy.equalsIgnoreCase("itemName")) {
            int comparison = left.item.itemName.compareToIgnoreCase(right.item.itemName);
            if (order.equalsIgnoreCase("asc")) {
                shouldPickLeft = comparison <= 0;
            } else {
                shouldPickLeft = comparison > 0;
            }
        } else { // sortBy price
            if (order.equalsIgnoreCase("asc")) {
                shouldPickLeft = left.item.price <= right.item.price;
            } else {
                shouldPickLeft = left.item.price > right.item.price;
            }
        }

        if (shouldPickLeft) {
            if (result == null) {
                result = left;
                tail = left;
            } else {
                tail.next = left;
                tail = left;
            }
            left = left.next;
        } else {
            if (result == null) {
                result = right;
                tail = right;
            } else {
                tail.next = right;
                tail = right;
            }
            right = right.next;
        }
    }

    // Add the remaining nodes from either list
    if (left != null) {
        tail.next = left;
    } else {
        tail.next = right;
    }
    return result;
}
}

// Main class
public class SinglyLinkedListInventory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryList inventoryList = new InventoryList();
        int choice;

        do {
            System.out.println("\nInventory Management System Menu:");
            System.out.println("1. Add an item at the beginning");
            System.out.println("2. Add an item at the end");
            System.out.println("3. Add an item at a specific position");
            System.out.println("4. Remove an item by Item ID");
            System.out.println("5. Update the quantity of an item by Item ID");
            System.out.println("6. Search for an item by Item ID or Name");
            System.out.println("7. Display the total value of inventory");
            System.out.println("8. Sort the inventory");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Item ID: ");
                    int itemId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Item Name: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Enter Quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    Item item = new Item(itemId, itemName, quantity, price);
                    inventoryList.addItemAtBeginning(item);
                    break;
                case 2:
                    System.out.print("Enter Item ID: ");
                    itemId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Item Name: ");
                    itemName = scanner.nextLine();
                    System.out.print("Enter Quantity: ");
                    quantity = scanner.nextInt();
                    System.out.print("Enter Price: ");
                    price = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    item = new Item(itemId, itemName, quantity, price);
                    inventoryList.addItemAtEnd(item);
                    break;
                case 3:
                    System.out.print("Enter position to add item: ");
                    int position = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Item ID: ");
                    itemId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Item Name: ");
                    itemName = scanner.nextLine();
                    System.out.print("Enter Quantity: ");
                    quantity = scanner.nextInt();
                    System.out.print("Enter Price: ");
                    price = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    item = new Item(itemId, itemName, quantity, price);
                    inventoryList.addItemAtPosition(position, item);
                    break;
                case 4:
                    System.out.print("Enter Item ID to remove: ");
                    itemId = scanner.nextInt();
                    inventoryList.removeItemById(itemId);
                    break;
                case 5:
                    System.out.print("Enter Item ID to update quantity: ");
                    itemId = scanner.nextInt();
                    System.out.print("Enter new Quantity: ");
                    quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    inventoryList.updateQuantityById(itemId, quantity);
                    break;
                case 6:
                    System.out.print("Enter Item ID to search: ");
                    itemId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Item Name to search: ");
                    itemName = scanner.nextLine();
                    inventoryList.searchItem(itemId, itemName);
                    break;
                case 7:
                    inventoryList.displayTotalInventoryValue();
                    break;
                case 8:
                    System.out.print("Sort by (name/price): ");
                    String sortBy = scanner.nextLine();
                    System.out.print("Order (asc/desc): ");
                    String order = scanner.nextLine();
                    inventoryList.sortInventory(sortBy, order);
                    System.out.println("Inventory sorted.");
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