
class LinearSearch {

    // Method to find the index of the first negative number in the array
    public int findFirstNegative(int[] arr) {
        int n = arr.length;

        // Iterate through the array from the start
        for (int i = 0; i < n; i++) {
            // Check if the current element is negative
            if (arr[i] < 0) {
                // If a negative number is found, return its index
                return i;
            }
        }

        // If the loop completes without finding a negative number, return -1
        return -1;
    }
}

public class LinSearchOne {
    public static void main(String[] args) {
        int[] numbers = {5, 2, -8, 9, -3, 7};
        LinearSearch searcher = new LinearSearch();

        int index = searcher.findFirstNegative(numbers);

        if (index != -1) {
            System.out.println("The first negative number is found at index: " + index);
        } else {
            System.out.println("No negative number found in the array.");
        }
    }
}

