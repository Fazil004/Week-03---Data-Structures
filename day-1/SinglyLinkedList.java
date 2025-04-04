import java.util.Scanner;

// Class to represent a student
class Student {
    int rollNumber;
    String name;
    int age;
    String grade;

    public Student(int rollNumber, String name, int age, String grade) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Roll Number: " + rollNumber + ", Name: " + name + ", Age: " + age + ", Grade: " + grade;
    }
}

// Class to represent a node in the linked list
class Node {
    Student student;
    Node next;

    public Node(Student student) {
        this.student = student;
        this.next = null;
    }
}

// Class to represent the singly linked list
class StudentLinkedList {
    private Node head;

    public StudentLinkedList() {
        this.head = null;
    }

    // 1. Add a new student record at the beginning of the list
    public void addAtBeginning(Student student) {
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        System.out.println("Student added at the beginning.");
    }

    // 2. Add a new student record at the end of the list
    public void addAtEnd(Student student) {
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Student added at the end.");
    }

    // 3. Add a new student record at a specific position in the list
    public void addAtPosition(int position, Student student) {
        if (position <= 0) {
            System.out.println("Invalid position. Please enter a positive number.");
            return;
        }
        if (position == 1) {
            addAtBeginning(student);
            return;
        }
        Node newNode = new Node(student);
        Node current = head;
        int count = 1;
        while (current != null && count < position - 1) {
            current = current.next;
            count++;
        }
        if (current == null) {
            System.out.println("Position is out of bounds.  Student not added.");
            return;
        }
        newNode.next = current.next;
        current.next = newNode;
        System.out.println("Student added at position " + position);
    }

    // 4. Delete a student record by Roll Number
    public void deleteByRollNumber(int rollNumber) {
        if (head == null) {
            System.out.println("List is empty. No student to delete.");
            return;
        }
        if (head.student.rollNumber == rollNumber) {
            head = head.next;
            System.out.println("Student with Roll Number " + rollNumber + " deleted.");
            return;
        }
        Node current = head;
        Node previous = null;
        while (current != null && current.student.rollNumber != rollNumber) {
            previous = current;
            current = current.next;
        }
        if (current == null) {
            System.out.println("Student with Roll Number " + rollNumber + " not found.");
            return;
        }
        previous.next = current.next;
        System.out.println("Student with Roll Number " + rollNumber + " deleted.");
    }

    // 5. Search for a student record by Roll Number
    public void searchByRollNumber(int rollNumber) {
        if (head == null) {
            System.out.println("List is empty. No student to search.");
            return;
        }
        Node current = head;
        while (current != null && current.student.rollNumber != rollNumber) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("Student with Roll Number " + rollNumber + " not found.");
            return;
        }
        System.out.println("Student found: " + current.student);
    }

    // 6. Display all student records
    public void displayAllStudents() {
        if (head == null) {
            System.out.println("List is empty. No students to display.");
            return;
        }
        System.out.println("Student Records:");
        Node current = head;
        while (current != null) {
            System.out.println(current.student);
            current = current.next;
        }
    }

    // 7. Update a student's grade based on their Roll Number
    public void updateGradeByRollNumber(int rollNumber, String newGrade) {
        if (head == null) {
            System.out.println("List is empty. No student to update.");
            return;
        }
        Node current = head;
        while (current != null && current.student.rollNumber != rollNumber) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("Student with Roll Number " + rollNumber + " not found.");
            return;
        }
        current.student.grade = newGrade;
        System.out.println("Grade updated for student with Roll Number " + rollNumber + ".");
    }
}

// Main class
public class SinglyLinkedList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentLinkedList studentList = new StudentLinkedList();
        int choice;

        do {
            System.out.println("\nStudent Record Management System Menu:");
            System.out.println("1. Add a new student at the beginning");
            System.out.println("2. Add a new student at the end");
            System.out.println("3. Add a new student at a specific position");
            System.out.println("4. Delete a student by Roll Number");
            System.out.println("5. Search for a student by Roll Number");
            System.out.println("6. Display all students");
            System.out.println("7. Update a student's grade");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll Number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Grade: ");
                    String grade = scanner.nextLine();
                    Student student = new Student(rollNumber, name, age, grade);
                    studentList.addAtBeginning(student);
                    break;
                case 2:
                    System.out.print("Enter Roll Number: ");
                    rollNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Grade: ");
                    grade = scanner.nextLine();
                    student = new Student(rollNumber, name, age, grade);
                    studentList.addAtEnd(student);
                    break;
                case 3:
                    System.out.print("Enter position to add student: ");
                    int position = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Roll Number: ");
                    rollNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    age = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Grade: ");
                    grade = scanner.nextLine();
                    student = new Student(rollNumber, name, age, grade);
                    studentList.addAtPosition(position, student);
                    break;
                case 4:
                    System.out.print("Enter Roll Number of student to delete: ");
                    rollNumber = scanner.nextInt();
                    studentList.deleteByRollNumber(rollNumber);
                    break;
                case 5:
                    System.out.print("Enter Roll Number of student to search: ");
                    rollNumber = scanner.nextInt();
                    studentList.searchByRollNumber(rollNumber);
                    break;
                case 6:
                    studentList.displayAllStudents();
                    break;
                case 7:
                    System.out.print("Enter Roll Number of student to update grade: ");
                    rollNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new Grade: ");
                    grade = scanner.nextLine();
                    studentList.updateGradeByRollNumber(rollNumber, grade);
                    Break;
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
