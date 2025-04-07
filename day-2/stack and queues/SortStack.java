import java.util.Stack;

class StackSorter {

    // Recursive method to sort the stack
    public void sortStack(Stack<Integer> stack) {
        // Base case: If the stack is empty, it's already sorted
        if (stack.isEmpty()) {
            return;
        }

        // 1. Pop an element from the stack
        int temp = stack.pop();

        // 2. Recursively sort the remaining stack
        sortStack(stack);

        // 3. Insert the popped element back into the sorted stack in its correct position
        insertSorted(stack, temp);
    }

    // Method to insert an element into a sorted stack
    private void insertSorted(Stack<Integer> stack, int value) {
        // Base case: If the stack is empty or the top element is greater than the value,
        // just push the value onto the stack
        if (stack.isEmpty() || stack.peek() > value) {
            stack.push(value);
            return;
        }

        // 1. Pop the top element from the stack
        int temp = stack.pop();

        // 2. Recursively insert the value into the sorted stack
        insertSorted(stack, value);

        // 3. Push the popped element back onto the stack
        stack.push(temp);
    }
}

public class SortStack {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(2);
        stack.push(8);
        stack.push(1);
        stack.push(7);

        System.out.println("Original Stack: " + stack); // [5, 2, 8, 1, 7]

        StackSorter sorter = new StackSorter();
        sorter.sortStack(stack);

        System.out.println("Sorted Stack: " + stack); // [1, 2, 5, 7, 8]
    }
}