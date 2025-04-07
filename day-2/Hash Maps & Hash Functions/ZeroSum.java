
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ZeroSumSubarrays {

    // Function to find all subarrays with a zero sum
    public List<List<Integer>> findZeroSumSubarrays(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> sumMap = new HashMap<>();
        // Initialize the map with a sum of 0 at index -1.  This is because a prefix of the array
        // having a sum of 0 constitutes a valid subarray.
        sumMap.put(0, new ArrayList<>());
        sumMap.get(0).add(-1);

        int currentSum = 0;
        // Iterate through the array, calculating the cumulative sum
        for (int i = 0; i < arr.length; i++) {
            currentSum += arr[i];
            // If the current sum has been seen before, it means there is a subarray
            // between the previous occurrence of this sum and the current index that sums to zero
            if (sumMap.containsKey(currentSum)) {
                List<Integer> startIndexList = sumMap.get(currentSum);
                for (int startIndex : startIndexList) {
                    // Add the subarray to the result list
                    List<Integer> subArray = new ArrayList<>();
                    for (int j = startIndex + 1; j <= i; j++) {
                        subArray.add(arr[j]);
                    }
                    result.add(subArray);
                }
            }
            // Add the current sum and its index to the map
            if (!sumMap.containsKey(currentSum)) {
                sumMap.put(currentSum, new ArrayList<>());
            }
            sumMap.get(currentSum).add(i);
        }
        return result;
    }
}

public class ZeroSum {
    public static void main(String[] args) {
        int[] arr = {6, 3, -1, -3, 4, -2, 2, 4, -2, -7, 0};
        ZeroSumSubarrays zeroSumSubarrays = new ZeroSumSubarrays();
        List<List<Integer>> result = zeroSumSubarrays.findZeroSumSubarrays(arr);

        if (result.isEmpty()) {
            System.out.println("No zero-sum subarrays found.");
        } else {
            System.out.println("Zero-sum subarrays:");
            for (List<Integer> subArray : result) {
                System.out.println(subArray);
            }
        }
    }
}

