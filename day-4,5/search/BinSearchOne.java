
class RotationPointFinder {

    // Method to find the index of the smallest element (rotation point) in a rotated sorted array
    public int findRotationPoint(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        // Perform binary search
        while (left < right) {
            int mid = left + (right - left) / 2; // To avoid overflow
            // If arr[mid] > arr[right], then the smallest element is in the right half
            if (arr[mid] > arr[right]) {
                left = mid + 1; // Update left to search in the right half
            } else {
                // If arr[mid] < arr[right], the smallest element is in the left half
                right = mid; // Update right to search in the left half
            }
        }
        // Continue until left equals right, and then return left/right
        return left;
    }
}

public class BinSearchOne {
    public static void main(String[] args) {
        int[] rotatedArray = {4, 5, 6, 7, 0, 1, 2};
        RotationPointFinder finder = new RotationPointFinder();

        int rotationPointIndex = finder.findRotationPoint(rotatedArray);
        System.out.println("Rotation point index: " + rotationPointIndex); // Output: 4
    }
}

