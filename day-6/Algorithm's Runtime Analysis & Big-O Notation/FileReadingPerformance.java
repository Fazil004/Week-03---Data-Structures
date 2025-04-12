
import java.io.*;

public class FileReadingPerformance {

    private static final String FILE_NAME = "largefile.txt";
    private static final long FILE_SIZE_MB = 500; // in MB
    private static final long FILE_SIZE_BYTES = FILE_SIZE_MB * 1024 * 1024;

    public static void main(String[] args) {
        // Create a large file (if it doesn't exist)
        createLargeFile();

        // Read using FileReader
        long startTime = System.nanoTime();
        try (FileReader fileReader = new FileReader(FILE_NAME)) {
            while (fileReader.read() != -1) {
                // Read character by character
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        long fileReaderTime = endTime - startTime;
        System.out.println("FileReader Time: " + fileReaderTime + " ns");

        // Read using InputStreamReader
        startTime = System.nanoTime();
        try (FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream)) {
            while (inputStreamReader.read() != -1) {
                // Read character by character
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        endTime = System.nanoTime();
        long inputStreamReaderTime = endTime - startTime;
        System.out.println("InputStreamReader Time: " + inputStreamReaderTime + " ns");

        System.out.println("\nInputStreamReader is " + (double) fileReaderTime / inputStreamReaderTime + " times faster than FileReader for this large file.");
    }

    // Method to create a large file
    private static void createLargeFile() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            System.out.println("Large file already exists.");
            return;
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            long written = 0;
            byte[] buffer = new byte[1024 * 1024]; // 1MB buffer
            while (written < FILE_SIZE_BYTES) {
                int toWrite = (int) Math.min(FILE_SIZE_BYTES - written, buffer.length);
                fileOutputStream.write(buffer, 0, toWrite);
                written += toWrite;
            }
            System.out.println("Large file created successfully: " + FILE_NAME + " (" + FILE_SIZE_MB + " MB)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

