
import java.util.HashMap;
import java.util.Map;

class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Boolean> numMap = new HashMap<>();
        for (int num : nums) {
            numMap.put(num, true); // Mark all numbers as unvisited
        }

        int longestStreak = 0;

        for (int num : nums) {
            if (numMap.get(num)) { //if it is unvisited
                int currentStreak = 1;
                int currentNum = num;
                numMap.put(num, false); // Mark the current number as visited

                // Check for consecutive elements in the decreasing direction
                while (numMap.containsKey(currentNum - 1)) {
                    currentNum--;
                    currentStreak++;
                    numMap.put(currentNum, false); // Mark the consecutive number as visited
                }

                currentNum = num;
                // Check for consecutive elements in the increasing direction
                while (numMap.containsKey(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                    numMap.put(currentNum, false); // Mark the consecutive number as visited
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }
}

public class longConsqSum {
    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        LongestConsecutiveSequence solution = new LongestConsecutiveSequence();
        int longestSequence = solution.longestConsecutive(nums);
        System.out.println("Longest Consecutive Sequence: " + longestSequence); // Output: 4

        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int longestSequence2 = solution.longestConsecutive(nums2);
        System.out.println("Longest Consecutive Sequence: " + longestSequence2); // Output: 9
    }
}

