
class MatrixSearch {

    // Method to search for a target value in a 2D sorted matrix using Binary Search
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = 0;
        int right = rows * cols - 1;

        // Perform binary search
        while (left <= right) {
            int mid = left + (right - left) / 2; // To avoid overflow
            int row = mid / cols;
            int col = mid % cols;
            int midElement = matrix[row][col];

            if (midElement == target) {
                return true; // Target found
            } else if (target < midElement) {
                right = mid - 1; // Search left half
            } else {
                left = mid + 1; // Search right half
            }
        }
        return false; // Target not found
    }
}

public class BinSearchThree {
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        };
        int target = 16;
        MatrixSearch searcher = new MatrixSearch();

        boolean found = searcher.searchMatrix(matrix, target);
        System.out.println("Target " + target + " found: " + found); // Output: true

        target = 25;
        found = searcher.searchMatrix(matrix, target);
        System.out.println("Target " + target + " found: " + found); // Output: false
    }
}

