import java.util.Deque;
import java.util.LinkedList;

class SlidingWindowMaximum {

    // Function to find the maximum element in each sliding window
    public int[] findSlidingWindowMaximum(int[] array, int k) {
        int n = array.length;
        if (n == 0 || k <= 0) {
            return new int[0]; // Handle edge cases
        }
        if (k > n)
        {
            k = n;
        }

        int[] maxValues = new int[n - k + 1]; // Array to store the maximum values
        Deque<Integer> deque = new LinkedList<>(); // Deque to store indices

        // Process the first k elements (first window)
        for (int i = 0; i < k; i++) {
            // 1. Remove elements from the deque that are smaller than the current element
            while (!deque.isEmpty() && array[i] >= array[deque.peekLast()]) {
                deque.removeLast();
            }
            // 2. Add the current element's index to the deque
            deque.addLast(i);
        }

        // The maximum element of the first window is at the front of the deque
        maxValues[0] = array[deque.peekFirst()];

        // Process the remaining elements
        for (int i = k; i < n; i++) {
            // 1. Remove the elements that are out of the current window
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
                deque.removeFirst();
            }
            // 2. Remove the elements from the deque that are smaller than the current element
            while (!deque.isEmpty() && array[i] >= array[deque.peekLast()]) {
                deque.removeLast();
            }
            // 3. Add the current element's index to the deque
            deque.addLast(i);

            // 4. The maximum element of the current window is at the front of the deque
            maxValues[i - k + 1] = array[deque.peekFirst()];
        }
        return maxValues;
    }
}

public class SlidingWindowMax {
    public static void main(String[] args) {
        int[] array = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        SlidingWindowMaximum slidingWindowMaximum = new SlidingWindowMaximum();
        int[] maxValues = slidingWindowMaximum.findSlidingWindowMaximum(array, k);

        System.out.print("Array: ");
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.print("Maximum Values: ");
        for (int max : maxValues) {
            System.out.print(max + " ");
        }
        System.out.println(); // 3 3 5 5 6 7
    }
}