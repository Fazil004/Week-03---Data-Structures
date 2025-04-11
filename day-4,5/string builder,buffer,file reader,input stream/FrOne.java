
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class LineByLineReader {

    public void readFileLineByLine(String filePath) {
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}

public class FrOne {
    public static void main(String[] args) {
        String filePath = "example.txt";
        // Create a file named "example.txt" with some content in it
        try {
            java.io.FileWriter fileWriter = new java.io.FileWriter(filePath);
            fileWriter.write("This is the first line.\n");
            fileWriter.write("This is the second line.\n");
            fileWriter.write("This is the third line.");
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
        LineByLineReader reader = new LineByLineReader();
        reader.readFileLineByLine(filePath);
    }
}

