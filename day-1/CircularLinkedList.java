import java.util.Scanner;

// Class to represent a task
class Task {
    int taskId;
    String taskName;
    int priority;
    String dueDate;

    public Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Task ID: " + taskId + ", Task Name: " + taskName + ", Priority: " + priority + ", Due Date: " + dueDate;
    }
}

// Class to represent a node in the circular linked list
class Node {
    Task task;
    Node next;

    public Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

// Class to represent the circular linked list
class TaskScheduler {
    private Node head;
    private Node current; // Keep track of the current task

    public TaskScheduler() {
        this.head = null;
        this.current = null;
    }

    // 1. Add a task at the beginning of the circular list
    public void addTaskAtBeginning(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
            head.next = head; // Point to itself for circularity
            current = head;
        } else {
            Node temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            newNode.next = head;
            temp.next = newNode;
            head = newNode;
        }
        System.out.println("Task added at the beginning.");
    }

    // 2. Add a task at the end of the circular list
    public void addTaskAtEnd(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
            head.next = head;
            current = head;
        } else {
            Node temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = head;
        }
        System.out.println("Task added at the end.");
    }

    // 3. Add a task at a specific position in the circular list
    public void addTaskAtPosition(int position, Task task) {
        if (position <= 0) {
            System.out.println("Invalid position. Please enter a positive number.");
            return;
        }
        if (position == 1) {
            addTaskAtBeginning(task);
            return;
        }
        Node newNode = new Node(task);
        Node temp = head;
        int count = 1;
        while (temp != null && count < position - 1) {
            temp = temp.next;
            count++;
        }
        if (temp == null || temp.next == head) {
            System.out.println("Position is out of bounds. Task not added.");
            return;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        System.out.println("Task added at position " + position);
    }

    // 4. Remove a task by Task ID
    public void removeTaskById(int taskId) {
        if (head == null) {
            System.out.println("List is empty. No task to remove.");
            return;
        }
        if (head.task.taskId == taskId) {
            if (head.next == head) { // Only one node in the list
                head = null;
                current = null;
            } else {
                Node temp = head;
                while (temp.next != head) {
                    temp = temp.next;
                }
                temp.next = head.next;
                head = head.next;
                if (current.task.taskId == taskId) {
                    current = head; // Update current if the current task is deleted
                }
            }
            System.out.println("Task with ID " + taskId + " removed.");
            return;
        }
        Node current = head;
        Node prev = null;
        while (current != null && current.next != head && current.task.taskId != taskId) {
            prev = current;
            current = current.next;
        }
        if (current == null || current.next == head) {
            System.out.println("Task with ID " + taskId + " not found.");
            return;
        }
        prev.next = current.next;
        if (current.task.taskId == taskId) {
            current = prev.next; // Update current if the current task is deleted
        }
        System.out.println("Task with ID " + taskId + " removed.");
    }

    // 5. View the current task and move to the next task in the circular list
    public void viewCurrentAndNextTask() {
        if (head == null) {
            System.out.println("List is empty. No tasks to display.");
            return;
        }
        System.out.println("Current Task: " + current.task);
        current = current.next; // Move to the next task
        System.out.println("Next Task: " + current.task);

    }

    // 6. Display all tasks in the list starting from the head node
    public void displayAllTasks() {
        if (head == null) {
            System.out.println("List is empty. No tasks to display.");
            return;
        }
        System.out.println("All Tasks:");
        Node temp = head;
        do {
            System.out.println(temp.task);
            temp = temp.next;
        } while (temp != head);
    }

    // 7. Search for a task by Priority
    public void searchTaskByPriority(int priority) {
        if (head == null) {
            System.out.println("List is empty. No tasks to search.");
            return;
        }
        Node temp = head;
        boolean found = false;
        do {
            if (temp.task.priority == priority) {
                System.out.println("Task found: " + temp.task);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) {
            System.out.println("No tasks found with priority " + priority + ".");
        }
    }
}

// Main class
public class CircularLinkedList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskScheduler scheduler = new TaskScheduler();
        int choice;

        do {
            System.out.println("\nTask Scheduler Menu:");
            System.out.println("1. Add a task at the beginning");
            System.out.println("2. Add a task at the end");
            System.out.println("3. Add a task at a specific position");
            System.out.println("4. Remove a task by Task ID");
            System.out.println("5. View current and next task");
            System.out.println("6. Display all tasks");
            System.out.println("7. Search for a task by Priority");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Task ID: ");
                    int taskId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Task Name: ");
                    String taskName = scanner.nextLine();
                    System.out.print("Enter Priority: ");
                    int priority = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Due Date: ");
                    String dueDate = scanner.nextLine();
                    Task task = new Task(taskId, taskName, priority, dueDate);
                    scheduler.addTaskAtBeginning(task);
                    break;
                case 2:
                    System.out.print("Enter Task ID: ");
                    taskId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Task Name: ");
                    taskName = scanner.nextLine();
                    System.out.print("Enter Priority: ");
                    priority = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Due Date: ");
                    dueDate = scanner.nextLine();
                    task = new Task(taskId, taskName, priority, dueDate);
                    scheduler.addTaskAtEnd(task);
                    break;
                case 3:
                    System.out.print("Enter position to add task: ");
                    int position = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Task ID: ");
                    taskId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Task Name: ");
                    taskName = scanner.nextLine();
                    System.out.print("Enter Priority: ");
                    priority = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Due Date: ");
                    dueDate = scanner.nextLine();
                    task = new Task(taskId, taskName, priority, dueDate);
                    scheduler.addTaskAtPosition(position, task);
                    break;
                case 4:
                    System.out.print("Enter Task ID to remove: ");
                    taskId = scanner.nextInt();
                    scheduler.removeTaskById(taskId);
                    break;
                case 5:
                    scheduler.viewCurrentAndNextTask();
                    break;
                case 6:
                    scheduler.displayAllTasks();
                    break;
                case 7:
                    System.out.print("Enter Priority to search: ");
                    priority = scanner.nextInt();
                    scheduler.searchTaskByPriority(priority);
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