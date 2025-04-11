
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WordCounter {

    public int countWordOccurrences(String filePath, String targetWord) {
        int count = 0;
        // The try-with-resources statement automatically closes the resources.
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            // Read the file line by line.
            while ((line = bufferedReader.readLine()) != null) {
                // Use regex to find whole word matches, case-insensitively
                Pattern pattern = Pattern.compile("\\b" + Pattern.quote(targetWord) + "\\b", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    count++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return count;
    }
}

public class FrTwo {
    public static void main(String[] args) {
        String filePath = "example.txt";
        String targetWord = "the";
        // Create the file.
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(filePath);
            fileWriter.write("The quick brown fox jumps over the lazy dog. The dog is lazy.\n");
            fileWriter.write("The cat is also lazy.");
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }

        WordCounter wordCounter = new WordCounter();
        int occurrenceCount = wordCounter.countWordOccurrences(filePath, targetWord);
        System.out.println("The word \"" + targetWord + "\" appears " + occurrenceCount + " times in the file."); // Output: 4
    }
}

