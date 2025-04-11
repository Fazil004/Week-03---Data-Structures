
import java.io.*;

class ByteToCharacterConverter {

    public void convertAndPrint(String filePath, String charsetName) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, charsetName);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}

public class InpStreamRead {
    public static void main(String[] args) {
        String filePath = "binary_data.dat";
        String charsetName = "UTF-8";

        // Create a binary file with UTF-8 encoded data.
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, charsetName);
             BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {

            bufferedWriter.write("Hello, World!\nThis is a test.");
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }

        ByteToCharacterConverter converter = new ByteToCharacterConverter();
        converter.convertAndPrint(filePath, charsetName);
    }
}

