class BubbleSort {

    // Method to sort the student marks using Bubble Sort
    public void sortStudentMarks(int[] marks) {
        int n = marks.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                // Compare adjacent elements
                if (marks[j] > marks[j + 1]) {
                    // Swap marks[j] and marks[j+1]
                    int temp = marks[j];
                    marks[j] = marks[j + 1];
                    marks[j + 1] = temp;
                    swapped = true;
                }
            }

            // If no two elements were swapped in inner loop, the array is sorted
            if (!swapped) {
                break;
            }
        }
    }
}

public class BubSort {
    public static void main(String[] args) {
        int[] studentMarks = {65, 80, 72, 50, 95, 58};
        BubbleSort sorter = new BubbleSort();

        System.out.println("Original Student Marks:");
        for (int mark : studentMarks) {
            System.out.print(mark + " ");
        }

        sorter.sortStudentMarks(studentMarks);

        System.out.println("\nSorted Student Marks (Ascending Order):");
        for (int mark : studentMarks) {
            System.out.print(mark + " ");
        }
    }
}