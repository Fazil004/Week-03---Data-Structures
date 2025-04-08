
import java.util.Random;

class QuickSort {

    // Method to sort the product prices using Quick Sort
    public void sortProductPrices(int[] prices) {
        quickSort(prices, 0, prices.length - 1);
    }

    private void quickSort(int[] prices, int low, int high) {
        if (low < high) {
            // Partition the array
            int pi = partition(prices, low, high);

            // Recursively sort the left and right partitions
            quickSort(prices, low, pi - 1);
            quickSort(prices, pi + 1, high);
        }
    }

    private int partition(int[] prices, int low, int high) {
        // Choose the pivot (here, we choose a random element as pivot to avoid worst case)
        Random random = new Random();
        int pivotIndex = low + random.nextInt(high - low + 1);
        int temp = prices[pivotIndex];
        prices[pivotIndex] = prices[high];
        prices[high] = temp;
        int pivot = prices[high];


        int i = (low - 1); // Index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (prices[j] <= pivot) {
                i++;

                // Swap prices[i] and prices[j]
                int temp2 = prices[i];
                prices[i] = prices[j];
                prices[j] = temp2;
            }
        }

        // Swap prices[i+1] and prices[high] (pivot)
        int temp3 = prices[i + 1];
        prices[i + 1] = prices[high];
        prices[high] = temp3;

        return i + 1;
    }
}

public class QkSort {
    public static void main(String[] args) {
        int[] productPrices = {85, 60, 95, 45, 70, 55};
        QuickSort sorter = new QuickSort();

        System.out.println("Original Product Prices:");
        for (int price : productPrices) {
            System.out.print(price + " ");
        }

        sorter.sortProductPrices(productPrices);

        System.out.println("\nSorted Product Prices (Ascending Order):");
        for (int price : productPrices) {
            System.out.print(price + " ");
        }
    }
}

