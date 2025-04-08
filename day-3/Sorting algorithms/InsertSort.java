
class InsertionSort {

    // Method to sort the employee IDs using Insertion Sort
    public void sortEmployeeIds(int[] employeeIds) {
        int n = employeeIds.length;

        for (int i = 1; i < n; i++) {
            int key = employeeIds[i];
            int j = i - 1;

            // Move elements of arr[0..i-1], that are greater than key, to one position ahead
            // of their current position
            while (j >= 0 && employeeIds[j] > key) {
                employeeIds[j + 1] = employeeIds[j];
                j = j - 1;
            }
            employeeIds[j + 1] = key;
        }
    }
}

public class InsertSort {
    public static void main(String[] args) {
        int[] employeeIds = {105, 101, 103, 102, 104, 100};
        InsertionSort sorter = new InsertionSort();

        System.out.println("Original Employee IDs:");
        for (int id : employeeIds) {
            System.out.print(id + " ");
        }

        sorter.sortEmployeeIds(employeeIds);

        System.out.println("\nSorted Employee IDs (Ascending Order):");
        for (int id : employeeIds) {
            System.out.print(id + " ");
        }
    }
}

