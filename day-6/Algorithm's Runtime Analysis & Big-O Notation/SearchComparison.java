
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class SearchComparison {

    public static void main(String[] args) {
        // Define dataset sizes
        int[] dataSizes = {1000, 100000, 1000000};
        int target = -1; // Target value to search for

        for (int size : dataSizes) {
            // Generate random array of given size
            int[] arrayData = generateRandomArray(size);

            // Create HashSet and TreeSet
            Set<Integer> hashSetData = new HashSet<>();
            Set<Integer> treeSetData = new TreeSet<>();
            for (int value : arrayData) {
                hashSetData.add(value);
                treeSetData.add(value);
            }

            // Perform Array Search
            long startTime = System.nanoTime();
            arraySearch(arrayData, target);
            long endTime = System.nanoTime();
            long arraySearchTime = endTime - startTime;

            // Perform HashSet Search
            startTime = System.nanoTime();
            hashSetData.contains(target);
            endTime = System.nanoTime();
            long hashSetSearchTime = endTime - startTime;

            // Perform TreeSet Search
            startTime = System.nanoTime();
            treeSetData.contains(target);
            endTime = System.nanoTime();
            long treeSetSearchTime = endTime - startTime;

            System.out.println("Dataset Size: " + size);
            System.out.println("Array Search Time: " + arraySearchTime + " ns");
            System.out.println("HashSet Search Time: " + hashSetSearchTime + " ns");
            System.out.println("TreeSet Search Time: " + treeSetSearchTime + " ns");
            System.out.println("--------------------------");
        }
    }

     // Method to perform Linear Search on an array
    public static int arraySearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i; // Return the index if found
            }
        }
        return -1; // Return -1 if not found
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

