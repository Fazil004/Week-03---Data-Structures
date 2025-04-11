
import java.io.*;

class InputToFileWriter {

    public void readAndWriteToFile(String filePath) {
        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
             FileWriter fileWriter = new FileWriter(filePath);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            String userInput;
            System.out.println("Enter text to write to the file (enter 'exit' to stop):");
            // Read user input line by line until "exit" is entered
            while (!(userInput = bufferedReader.readLine()).equalsIgnoreCase("exit")) {
                // Write the user input to the file as a new line
                bufferedWriter.write(userInput);
                bufferedWriter.newLine();
            }
            System.out.println("Input finished. Writing to file complete.");

        } catch (IOException e) {
            System.err.println("Error reading from console or writing to file: " + e.getMessage());
        }
    }
}

public class InpStreamReadTwo {
    public static void main(String[] args) {
        String filePath = "user_input.txt";
        InputToFileWriter writer = new InputToFileWriter();
        writer.readAndWriteToFile(filePath);
    }
}

