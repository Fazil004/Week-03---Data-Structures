
class StringConcatenationComparison {

    public void comparePerformance(int numberOfStrings) {
        // Initialize StringBuffer and StringBuilder objects
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder();
        String baseString = "hello";

        // Measure time taken by StringBuffer
        long startTimeBuffer = System.nanoTime();
        for (int i = 0; i < numberOfStrings; i++) {
            stringBuffer.append(baseString);
        }
        long endTimeBuffer = System.nanoTime();
        long durationBuffer = endTimeBuffer - startTimeBuffer;

        // Measure time taken by StringBuilder
        long startTimeBuilder = System.nanoTime();
        for (int i = 0; i < numberOfStrings; i++) {
            stringBuilder.append(baseString);
        }
        long endTimeBuilder = System.nanoTime();
        long durationBuilder = endTimeBuilder - startTimeBuilder;

        System.out.println("Number of strings: " + numberOfStrings);
        System.out.println("Time taken by StringBuffer: " + durationBuffer + " ns");
        System.out.println("Time taken by StringBuilder: " + durationBuilder + " ns");
    }
}

public class SBuffTwo {
    public static void main(String[] args) {
        int numberOfStrings = 1000000; // 1 million strings
        StringConcatenationComparison comparison = new StringConcatenationComparison();
        comparison.comparePerformance(numberOfStrings);
    }
}

