
class StringConcatenator {

    // Method to concatenate strings efficiently using StringBuffer
    public String concatenateStrings(String[] strArray) {
        // Create a new StringBuffer object
        StringBuffer sb = new StringBuffer();

        // Iterate through each string in the array and append it to the StringBuffer
        for (String str : strArray) {
            sb.append(str);
        }

        // Return the concatenated string after the loop finishes
        return sb.toString();
    }
}

public class SBuffOne {
    public static void main(String[] args) {
        String[] strings = {"Hello", ", ", "World", "!", " Welcome", " to", " Java"};
        StringConcatenator concatenator = new StringConcatenator();
        String result = concatenator.concatenateStrings(strings);
        System.out.println("Concatenated string: " + result); // Output: Hello, World! Welcome to Java
    }
}

