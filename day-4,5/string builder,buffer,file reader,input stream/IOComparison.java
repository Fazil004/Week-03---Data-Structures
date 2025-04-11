
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOComparison {

    private static final int STRING_CONCAT_COUNT = 1000000;
    private static final String LARGE_FILE_PATH = "large_file.txt";
    private static final long FILE_SIZE_MB = 100;

    public static void main(String[] args) {
        // String Concatenation Comparison
        stringConcatenationComparison();

        // File Reading and Word Count Comparison
        fileReadingComparison();
    }

    private static void stringConcatenationComparison() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("hello");
        }

        // StringBuilder
        long startTime = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < STRING_CONCAT_COUNT; i++) {
            sb.append(strings.get(i % strings.size())); // Use modulus to cycle through the list
        }
        long endTime = System.nanoTime();
        long durationStringBuilder = endTime - startTime;
        System.out.println("StringBuilder: " + durationStringBuilder + " ns");

        // StringBuffer
        startTime = System.nanoTime();
        StringBuffer sbuffer = new StringBuffer();
        for (int i = 0; i < STRING_CONCAT_COUNT; i++) {
            sbuffer.append(strings.get(i % strings.size()));
        }
        endTime = System.nanoTime();
        long durationStringBuffer = endTime - startTime;
        System.out.println("StringBuffer: " + durationStringBuffer + " ns");

        System.out.println("StringBuilder is " + (double) durationStringBuffer / durationStringBuilder + " times faster than StringBuffer for string concatenation.");
    }

    private static void fileReadingComparison() {
        // Create a large file (100MB)
        createLargeFile();

        // FileReader
        long startTime = System.nanoTime();
        int wordCountFileReader = countWordsFileReader();
        long endTime = System.nanoTime();
        long durationFileReader = endTime - startTime;
        System.out.println("FileReader Word Count: " + wordCountFileReader);
        System.out.println("FileReader: " + durationFileReader + " ns");

        // InputStreamReader
        startTime = System.nanoTime();
        int wordCountInputStreamReader = countWordsInputStreamReader();
        endTime = System.nanoTime();
        long durationInputStreamReader = endTime - startTime;
        System.out.println("InputStreamReader Word Count: " + wordCountInputStreamReader);
        System.out.println("InputStreamReader: " + durationInputStreamReader + " ns");

        System.out.println("FileReader is " + (double) durationInputStreamReader / durationFileReader + " times faster than InputStreamReader for reading the file and counting words.");
    }

    private static void createLargeFile() {
        File file = new File(LARGE_FILE_PATH);
        long targetSize = FILE_SIZE_MB * 1024 * 1024; // in bytes
        try (FileOutputStream fos = new FileOutputStream(file);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(osw)) {

            long written = 0;
            while (written < targetSize) {
                String data = "This is a line of text.  The quick brown fox jumps over the lazy dog.  1234567890\n";
                writer.write(data);
                written += data.getBytes(StandardCharsets.UTF_8).length;
            }
        } catch (IOException e) {
            System.err.println("Error creating large file: " + e.getMessage());
        }
    }

    private static int countWordsFileReader() {
        int wordCount = 0;
        try (FileReader fileReader = new FileReader(LARGE_FILE_PATH);
             BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            Pattern pattern = Pattern.compile("\\b\\w+\\b"); // improved word matching
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    wordCount++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file with FileReader: " + e.getMessage());
        }
        return wordCount;
    }

    private static int countWordsInputStreamReader() {
        int wordCount = 0;
        try (FileInputStream fis = new FileInputStream(LARGE_FILE_PATH);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {
            String line;
            Pattern pattern = Pattern.compile("\\b\\w+\\b");
            while ((line = reader.readLine()) != null) {
                 Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    wordCount++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file with InputStreamReader: " + e.getMessage());
        }
        return wordCount;
    }
}