
public class FibonacciComparison {

    // Recursive method to calculate Fibonacci number
    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // Iterative method to calculate Fibonacci number
    public static int fibonacciIterative(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1, sum;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    public static void main(String[] args) {
        // Define Fibonacci numbers to calculate
        int[] fibNumbers = {10, 30, 40};

        for (int n : fibNumbers) {
            System.out.println("Fibonacci Number: " + n);

            // Recursive Calculation
            long startTime = System.nanoTime();
            int recursiveResult = fibonacciRecursive(n);
            long endTime = System.nanoTime();
            long recursiveTime = endTime - startTime;
            System.out.println("Recursive Result: " + recursiveResult);
            System.out.println("Recursive Time: " + recursiveTime + " ns");

            // Iterative Calculation
            startTime = System.nanoTime();
            int iterativeResult = fibonacciIterative(n);
            endTime = System.nanoTime();
            long iterativeTime = endTime - startTime;
            System.out.println("Iterative Result: " + iterativeResult);
            System.out.println("Iterative Time: " + iterativeTime + " ns");

            System.out.println("--------------------------");
        }
    }
}

