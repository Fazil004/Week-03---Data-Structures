
import java.util.Arrays;

class SearchChallenge {

    // Method to find the first missing positive integer using Linear Search
    public int findFirstMissingPositive(int[] nums) {
        int n = nums.length;

        // Mark visited numbers by negating them.  We use the array indices
        // to represent the positive numbers.  For example, if nums[0] is 3,
        // we'll negate nums[2] (since 2 is the index corresponding to the number 3).
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num > 0 && num <= n) {
                int index = num - 1;
                if (nums[index] > 0) {
                    nums[index] *= -1; // Mark as visited
                }
            }
        }

        // Find the first positive number (which indicates a missing number)
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        // If all numbers from 1 to n are present, then the missing number is n+1
        return n + 1;
    }

    // Method to find the index of a target number using Binary Search
    public int findTargetIndex(int[] nums, int target) {
        // Sort the array first to use Binary Search
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}

public class ChallengeProb {
    public static void main(String[] args) {
        SearchChallenge searcher = new SearchChallenge();

        int[] numbers = {3, 4, -1, 1, 8};
        int missingPositive = searcher.findFirstMissingPositive(numbers);
        System.out.println("First missing positive integer: " + missingPositive); // Output: 2

        int target = 8;
        int targetIndex = searcher.findTargetIndex(numbers, target);
        System.out.println("Index of " + target + ": " + targetIndex); // Output: 4 (after sorting)

        int[] numbers2 = { 7, 8, 9, 11, 12};
        int missingPositive2 = searcher.findFirstMissingPositive(numbers2);
        System.out.println("First missing positive integer: " + missingPositive2);

        int target2 = 10;
        int targetIndex2 = searcher.findTargetIndex(numbers2, target2);
        System.out.println("Index of " + target2 + ": " + targetIndex2);
    }
}

