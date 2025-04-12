
import java.util.Arrays;
import java.util.Random;

public class SortingPerformanceComparison {

    // Method to perform Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Method to perform Merge Sort
    public static void mergeSort(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;
            int[] leftArray = Arrays.copyOfRange(arr, 0, mid);
            int[] rightArray = Arrays.copyOfRange(arr, mid, arr.length);

            mergeSort(leftArray);
            mergeSort(rightArray);

            merge(arr, leftArray, rightArray);
        }
    }

    private static void merge(int[] arr, int[] leftArray, int[] rightArray) {
        int leftSize = leftArray.length;
        int rightSize = rightArray.length;
        int i = 0, j = 0, k = 0;

        while (i < leftSize && j < rightSize) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k++] = leftArray[i++];
            } else {
                arr[k++] = rightArray[j++];
            }
        }

        while (i < leftSize) {
            arr[k++] = leftArray[i++];
        }

        while (j < rightSize) {
            arr[k++] = rightArray[j++];
        }
    }

    // Method to perform Quick Sort
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // Index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i+1] and arr[high] (pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return (i + 1);
    }

    public static void main(String[] args) {
        // Define dataset sizes
        int[] dataSizes = {1000, 10000, 1000000};

        for (int size : dataSizes) {
            // Generate random array of given size
            int[] data = generateRandomArray(size);
            int[] bubbleSortData = Arrays.copyOf(data, data.length); //copy of data for bubble sort
            int[] mergeSortData = Arrays.copyOf(data, data.length);  //copy of data for merge sort
            int[] quickSortData = Arrays.copyOf(data, data.length);  //copy of data for quick sort

            // Perform Bubble Sort
            long startTime = System.nanoTime();
            bubbleSort(bubbleSortData);
            long endTime = System.nanoTime();
            long bubbleSortTime = endTime - startTime;

            // Perform Merge Sort
            startTime = System.nanoTime();
            mergeSort(mergeSortData);
            endTime = System.nanoTime();
            long mergeSortTime = endTime - startTime;

            // Perform Quick Sort
            startTime = System.nanoTime();
            quickSort(quickSortData);
            endTime = System.nanoTime();
            long quickSortTime = endTime - startTime;

            System.out.println("Dataset Size: " + size);
            System.out.println("Bubble Sort Time: " + bubbleSortTime + " ns");
            System.out.println("Merge Sort Time: " + mergeSortTime + " ns");
            System.out.println("Quick Sort Time: " + quickSortTime + " ns");
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

