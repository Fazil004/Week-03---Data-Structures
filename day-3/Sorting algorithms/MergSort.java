class MergeSort {

    // Method to sort the book prices using Merge Sort
    public void sortBookPrices(int[] prices) {
        if (prices.length > 1) {
            int mid = prices.length / 2;

            // Split the array into two halves
            int[] leftHalf = new int[mid];
            System.arraycopy(prices, 0, leftHalf, 0, mid);

            int[] rightHalf = new int[prices.length - mid];
            System.arraycopy(prices, mid, rightHalf, 0, prices.length - mid);

            // Recursively sort both halves
            sortBookPrices(leftHalf);
            sortBookPrices(rightHalf);

            // Merge the sorted halves
            merge(prices, leftHalf, rightHalf);
        }
    }

    // Method to merge two sorted arrays
    private void merge(int[] prices, int[] leftHalf, int[] rightHalf) {
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;
        int i = 0, j = 0, k = 0;

        // Compare elements from both halves and merge them in sorted order
        while (i < leftSize && j < rightSize) {
            if (leftHalf[i] <= rightHalf[j]) {
                prices[k] = leftHalf[i];
                i++;
            } else {
                prices[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        // Copy the remaining elements of left half, if any
        while (i < leftSize) {
            prices[k] = leftHalf[i];
            i++;
            k++;
        }

        // Copy the remaining elements of right half, if any
        while (j < rightSize) {
            prices[k] = rightHalf[j];
            j++;
            k++;
        }
    }
}

public class MergSort {
    public static void main(String[] args) {
        int[] bookPrices = {25, 15, 30, 10, 35, 20};
        MergeSort sorter = new MergeSort();

        System.out.println("Original Book Prices:");
        for (int price : bookPrices) {
            System.out.print(price + " ");
        }

        sorter.sortBookPrices(bookPrices);

        System.out.println("\nSorted Book Prices (Ascending Order):");
        for (int price : bookPrices) {
            System.out.print(price + " ");
        }
    }
}