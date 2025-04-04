import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class to represent a user
class User {
    int userId;
    String name;
    int age;
    ArrayList<Integer> friendIds; // List of User IDs of friends

    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name + ", Age: " + age;
    }
}

// Class to represent a node in the singly linked list
class Node {
    User user;
    Node next;

    public Node(User user) {
        this.user = user;
        this.next = null;
    }
}

// Class to represent the singly linked list
class SocialNetwork {
    private Node head;

    public SocialNetwork() {
        this.head = null;
    }

    // Helper method to find a user by User ID
    private User findUserById(int userId) {
        Node current = head;
        while (current != null) {
            if (current.user.userId == userId) {
                return current.user;
            }
            current = current.next;
        }
        return null;
    }

    // 1. Add a friend connection between two users
    public void addFriendConnection(int userId1, int userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        if (user1.friendIds.contains(userId2) || user2.friendIds.contains(userId1)) {
            System.out.println("Users are already friends.");
            return;
        }

        user1.friendIds.add(userId2);
        user2.friendIds.add(userId1);
        System.out.println("Friend connection added between User " + userId1 + " and User " + userId2 + ".");
    }

    // 2. Remove a friend connection
    public void removeFriendConnection(int userId1, int userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        if (!user1.friendIds.contains(userId2) || !user2.friendIds.contains(userId1)) {
            System.out.println("Users are not friends.");
            return;
        }

        user1.friendIds.remove(Integer.valueOf(userId2)); // Use Integer.valueOf to remove by object
        user2.friendIds.remove(Integer.valueOf(userId1));
        System.out.println("Friend connection removed between User " + userId1 + " and User " + userId2 + ".");
    }

    // 3. Find mutual friends between two users
    public void findMutualFriends(int userId1, int userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        ArrayList<Integer> mutualFriends = new ArrayList<>();
        for (Integer friendId : user1.friendIds) {
            if (user2.friendIds.contains(friendId)) {
                mutualFriends.add(friendId);
            }
        }

        if (mutualFriends.isEmpty()) {
            System.out.println("Users " + userId1 + " and " + userId2 + " have no mutual friends.");
        } else {
            System.out.println("Mutual friends between User " + userId1 + " and User " + userId2 + ": " + mutualFriends);
        }
    }

    // 4. Display all friends of a specific user
    public void displayAllFriends(int userId) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        if (user.friendIds.isEmpty()) {
            System.out.println("User " + userId + " has no friends.");
        } else {
            System.out.println("Friends of User " + userId + ": " + user.friendIds);
        }
    }

    // 5. Search for a user by Name or User ID
    public void searchUser(String name, int userId) {
        Node current = head;
        boolean found = false;
        while (current != null) {
            if (current.user.name.equalsIgnoreCase(name) || current.user.userId == userId) {
                System.out.println("User found: " + current.user);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("User not found with given name or ID.");
        }
    }

    // 6. Count the number of friends for each user
    public void countFriendsOfEachUser() {
        if (head == null) {
            System.out.println("No users in the network.");
            return;
        }
        System.out.println("Number of friends for each user:");
        Node current = head;
        while (current != null) {
            System.out.println(current.user.name + ": " + current.user.friendIds.size() + " friends");
            current = current.next;
        }
    }

    // Method to add a user to the social network
    public void addUser(User user) {
        Node newNode = new Node(user);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("User added: " + user);
    }
}

// Main class
public class SNS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SocialNetwork network = new SocialNetwork();
        int choice;

        // Pre-populate the network with some users
        network.addUser(new User(1, "Alice", 25));
        network.addUser(new User(2, "Bob", 30));
        network.addUser(new User(3, "Charlie", 28));
        network.addUser(new User(4, "David", 32));

        // Add some initial friend connections
        network.addFriendConnection(1, 2); // Alice and Bob are friends
        network.addFriendConnection(1, 3); // Alice and Charlie are friends
        network.addFriendConnection(2, 3); // Bob and Charlie are friends
        network.addFriendConnection(3, 4); // Charlie and David are friends


        do {
            System.out.println("\nSocial Network Menu:");
            System.out.println("1. Add a friend connection");
            System.out.println("2. Remove a friend connection");
            System.out.println("3. Find mutual friends");
            System.out.println("4. Display all friends of a user");
            System.out.println("5. Search for a user");
            System.out.println("6. Count the number of friends for each user");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter User ID 1: ");
                    int userId1 = scanner.nextInt();
                    System.out.print("Enter User ID 2: ");
                    int userId2 = scanner.nextInt();
                    network.addFriendConnection(userId1, userId2);
                    break;
                case 2:
                    System.out.print("Enter User ID 1: ");
                    userId1 = scanner.nextInt();
                    System.out.print("Enter User ID 2: ");
                    userId2 = scanner.nextInt();
                    network.removeFriendConnection(userId1, userId2);
                    break;
                case 3:
                    System.out.print("Enter User ID 1: ");
                    userId1 = scanner.nextInt();
                    System.out.print("Enter User ID 2: ");
                    userId2 = scanner.nextInt();
                    network.findMutualFriends(userId1, userId2);
                    break;
                case 4:
                    System.out.print("Enter User ID: ");
                    int userId = scanner.nextInt();
                    network.displayAllFriends(userId);
                    break;
                case 5:
                    System.out.print("Enter User Name to search: ");
                    String name = scanner.next();
                    System.out.print("Enter User ID to search: ");
                    userId = scanner.nextInt();
                    network.searchUser(name, userId);
                    break;
                case 6:
                    network.countFriendsOfEachUser();
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