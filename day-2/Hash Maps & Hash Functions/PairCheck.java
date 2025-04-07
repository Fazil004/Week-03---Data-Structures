import java.util.HashMap;
import java.util.Map;

class PairSumChecker {

    // Function to check if there exists a pair with the given sum
    public boolean hasPairWithSum(int[] arr, int targetSum) {
        Map<Integer, Boolean> visitedNumbers = new HashMap<>();

        for (int number : arr) {
            int complement = targetSum - number;
            if (visitedNumbers.containsKey(complement)) {
                return true; // Found a pair
            }
            visitedNumbers.put(number, true); // Mark the current number as visited
        }
        return false; // No pair found
    }
}

public class PairCheck {
    public static void main(String[] args) {
        int[] arr = {1, 4, 45, 6, 10, 8};
        int targetSum = 16;
        PairSumChecker checker = new PairSumChecker();
        boolean result = checker.hasPairWithSum(arr, targetSum);
        System.out.println("Array: {1, 4, 45, 6, 10, 8}, Target Sum: 16");
        System.out.println("Has pair with sum 16: " + result); // Output: true

        int[] arr2 = {1, 2, 3, 4, 5};
        int targetSum2 = 10;
        boolean result2 = checker.hasPairWithSum(arr2, targetSum2);
        System.out.println("Array: {1, 2, 3, 4, 5}, Target Sum: 10");
        System.out.println("Has pair with sum 10: " + result2); // Output: false
    }
}