import java.util.HashSet;

class DuplicateRemover {

    // Method to remove duplicate characters from a string using StringBuilder
    public String removeDuplicates(String str) {
        // Initialize an empty StringBuilder
        StringBuilder sb = new StringBuilder();
        // Initialize a HashSet to keep track of characters
        HashSet<Character> charSet = new HashSet<>();

        // Iterate over each character in the string
        for (char c : str.toCharArray()) {
            // If the character is not in the HashSet, append it to the StringBuilder and add it to the HashSet
            if (!charSet.contains(c)) {
                sb.append(c);
                charSet.add(c);
            }
        }
        // Return the StringBuilder as a string without duplicates
        return sb.toString();
    }
}

public class sbTwo {
    public static void main(String[] args) {
        String input = "programming";
        DuplicateRemover remover = new DuplicateRemover();
        String result = remover.removeDuplicates(input);
        System.out.println("Original string: " + input);
        System.out.println("String without duplicates: " + result); // Output: progam
    }
}