import java.util.Arrays;
import java.util.Random;

public class SearchPerformanceComparison {

    // Method to perform Linear Search
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Return the index if found
            }
        }
        return -1; // Return -1 if not found
    }

    // Method to perform Binary Search (requires sorted array)
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2; // To avoid overflow
            if (arr[mid] == target) {
                return mid; // Return the index if found
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // Return -1 if not found
    }

    public static void main(String[] args) {
        // Define dataset sizes
        int[] dataSizes = {1000, 10000, 1000000};
        int target = -1; // Target value to search for

        for (int size : dataSizes) {
            // Generate random array of given size
            int[] data = generateRandomArray(size);

            // Perform Linear Search
            long startTime = System.nanoTime();
            linearSearch(data, target);
            long endTime = System.nanoTime();
            long linearSearchTime = endTime - startTime;

            // Perform Binary Search (after sorting the array)
            int[] sortedData = Arrays.copyOf(data, data.length); // Create a copy to preserve original
            Arrays.sort(sortedData); // Sort the array
            startTime = System.nanoTime();
            binarySearch(sortedData, target);
            endTime = System.nanoTime();
            long binarySearchTime = endTime - startTime;

            System.out.println("Dataset Size: " + size);
            System.out.println("Linear Search Time: " + linearSearchTime + " ns");
            System.out.println("Binary Search Time: " + binarySearchTime + " ns");
            System.out.println("--------------------------");
        }
    }

    // Helper method to generate a random integer array of given size
      public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(2*size) - size; // Generate random numbers in the range of -size to size-1
        }
        return array;
    }
}

