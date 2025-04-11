
class StringReverser {

    // Method to reverse a string using StringBuilder
    public String reverseString(String str) {
        // Create a new StringBuilder object
        StringBuilder sb = new StringBuilder();

        // Append the string to the StringBuilder
        sb.append(str);

        // Use the reverse() method of StringBuilder to reverse the string
        sb.reverse();

        // Convert the StringBuilder back to a string and return it
        return sb.toString();
    }
}

public class SbOne {
    public static void main(String[] args) {
        String input = "hello";
        StringReverser reverser = new StringReverser();
        String reversed = reverser.reverseString(input);
        System.out.println("Original string: " + input);
        System.out.println("Reversed string: " + reversed); // Output: olleh

        String input2 = "Kattankulathur";
        String reversed2 = reverser.reverseString(input2);
        System.out.println("Original string: " + input2);
        System.out.println("Reversed string: " + reversed2);
    }
}

