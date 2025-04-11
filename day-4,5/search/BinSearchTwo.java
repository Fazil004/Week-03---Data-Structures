
class PeakElementFinder {

    // Method to find a peak element in an array using Binary Search
    public int findPeakElement(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        // Perform binary search
        while (left <= right) {
            int mid = left + (right - left) / 2; // To avoid overflow

            // If mid is a peak element
            if ((mid == 0 || arr[mid] >= arr[mid - 1]) && (mid == arr.length - 1 || arr[mid] >= arr[mid + 1])) {
                return mid; // Return the index of the peak element
            }
            // If the left neighbor is greater, search in the left half
            else if (mid > 0 && arr[mid - 1] > arr[mid]) {
                right = mid - 1;
            }
            // If the right neighbor is greater, search in the right half
            else {
                left = mid + 1;
            }
        }
        return -1; // Should not reach here, but added for completeness
    }
}

public class BinSearchTwo {
    public static void main(String[] args) {
        int[] numbers = {1, 3, 20, 4, 1, 0};
        PeakElementFinder finder = new PeakElementFinder();

        int peakIndex = finder.findPeakElement(numbers);
        if (peakIndex != -1)
            System.out.println("Peak element found at index: " + peakIndex); // Output: 2
        else
            System.out.println("No peak element found.");
    }
}

