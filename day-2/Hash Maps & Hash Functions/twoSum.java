
import java.util.HashMap;
import java.util.Map;

class TwoSum {

    // Function to find two indices that add up to the target sum
    public int[] findTwoSumIndices(int[] arr, int targetSum) {
        Map<Integer, Integer> numMap = new HashMap<>(); // Map to store number and its index

        for (int i = 0; i < arr.length; i++) {
            int complement = targetSum - arr[i];
            if (numMap.containsKey(complement)) {
                return new int[]{numMap.get(complement), i}; // Found the pair
            }
            numMap.put(arr[i], i); // Store the number and its index
        }
        return new int[]{-1, -1}; // No pair found
    }
}

public class twoSum {
    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int targetSum = 9;
        TwoSum twoSum = new TwoSum();
        int[] result = twoSum.findTwoSumIndices(arr, targetSum);
        if (result[0] != -1 && result[1] != -1) {
            System.out.println("Indices: " + result[0] + ", " + result[1]); // Output: 0, 1
        } else {
            System.out.println("No pair found");
        }

        int[] arr2 = {2, 3, 4, 5};
        int targetSum2 = 8;
        int[] result2 = twoSum.findTwoSumIndices(arr2, targetSum2);
        if (result2[0] != -1 && result2[1] != -1) {
            System.out.println("Indices: " + result2[0] + ", " + result2[1]);
        }
        else {
            System.out.println("No pair found");
        }
    }
}

