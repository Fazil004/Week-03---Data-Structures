
import java.util.ArrayList;
import java.util.List;

public class StringConcatenationPerformance {

    private static final int STRING_COUNT = 1000000;

    public static void main(String[] args) {
        // Prepare a list of strings to concatenate
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stringList.add("test");
        }

        // String Concatenation
        long startTime = System.nanoTime();
        String resultString = "";
        for (int i = 0; i < STRING_COUNT; i++) {
            resultString += stringList.get(i % stringList.size());
        }
        long endTime = System.nanoTime();
        long stringTime = endTime - startTime;
        System.out.println("String Concatenation Time: " + stringTime + " ns");

        // StringBuilder Concatenation
        startTime = System.nanoTime();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < STRING_COUNT; i++) {
            stringBuilder.append(stringList.get(i % stringList.size()));
        }
        endTime = System.nanoTime();
        long stringBuilderTime = endTime - startTime;
        System.out.println("StringBuilder Concatenation Time: " + stringBuilderTime + " ns");

        // StringBuffer Concatenation
        startTime = System.nanoTime();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < STRING_COUNT; i++) {
            stringBuffer.append(stringList.get(i % stringList.size()));
        }
        endTime = System.nanoTime();
        long stringBufferTime = endTime - startTime;
        System.out.println("StringBuffer Concatenation Time: " + stringBufferTime + " ns");

        System.out.println("\nStringBuilder is " + (double) stringTime / stringBuilderTime + " times faster than String for concatenation.");
        System.out.println("StringBuffer is " + (double) stringTime / stringBufferTime + " times faster than String for concatenation.");
        System.out.println("StringBuilder is " + (double) stringBufferTime / stringBuilderTime + " times faster than StringBuffer for concatenation.");
    }
}

