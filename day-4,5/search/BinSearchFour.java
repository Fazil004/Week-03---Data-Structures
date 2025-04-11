
class FirstLastOccurrence {

    // Method to find the first occurrence of the target element using Binary Search
    public int findFirstOccurrence(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int firstIndex = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                firstIndex = mid;
                right = mid - 1; // Continue searching on the left side
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return firstIndex;
    }

    // Method to find the last occurrence of the target element using Binary Search
    public int findLastOccurrence(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int lastIndex = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                lastIndex = mid;
                left = mid + 1; // Continue searching on the right side
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return lastIndex;
    }

    // Method to find both the first and last occurrence
    public int[] findFirstAndLastOccurrence(int[] arr, int target) {
        int first = findFirstOccurrence(arr, target);
        int last = findLastOccurrence(arr, target);
        return new int[]{first, last};
    }
}

public class BinSearchFour {
    public static void main(String[] args) {
        int[] sortedArray = {2, 4, 5, 5, 5, 5, 5, 7, 9, 12};
        int targetElement = 5;
        FirstLastOccurrence finder = new FirstLastOccurrence();

        int[] result = finder.findFirstAndLastOccurrence(sortedArray, targetElement);
        System.out.println("First Occurrence: " + result[0]); // Output: 2
        System.out.println("Last Occurrence: " + result[1]);  // Output: 6

        int targetElement2 = 10;
        int[] result2 = finder.findFirstAndLastOccurrence(sortedArray, targetElement2);
        System.out.println("First Occurrence: " + result2[0]); // Output: -1
        System.out.println("Last Occurrence: " + result2[1]);  // Output: -1
    }
}

