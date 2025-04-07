import java.util.Stack;

class stockSpan {

    // Function to calculate the stock span for each day
    public int[] calculateSpan(int[] prices) {
        int n = prices.length;
        int[] span = new int[n]; // Array to store the span values
        Stack<Integer> stack = new Stack<>(); // Stack to store indices of prices

        // The span for the first day is always 1
        span[0] = 1;
        stack.push(0); // Push the index of the first day onto the stack

        // Iterate over the remaining days
        for (int i = 1; i < n; i++) {
            // 1. Pop elements from the stack while the price of the current day is greater
            // than or equal to the price of the day at the top of the stack
            while (!stack.isEmpty() && prices[i] >= prices[stack.peek()]) {
                stack.pop();
            }

            // 2. If the stack is empty, the current day's price is greater than all the previous days' prices
            // so the span is i + 1.  Otherwise, the span is the difference between the current day's index
            // and the index of the day at the top of the stack
            span[i] = stack.isEmpty() ? i + 1 : i - stack.peek();

            // 3. Push the index of the current day onto the stack
            stack.push(i);
        }
        return span;
    }
}

public class StockSpan {
    public static void main(String[] args) {
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        StockSpan stockSpan = new StockSpan();
        int[] span = stockSpan.calculateSpan(prices);

        System.out.print("Prices: ");
        for (int price : prices) {
            System.out.print(price + " ");
        }
        System.out.println();

        System.out.print("Span: ");
        for (int s : span) {
            System.out.print(s + " ");
        }
        System.out.println(); // 1 1 1 2 1 4 6
    }
}