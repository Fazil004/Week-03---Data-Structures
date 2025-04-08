class SelectionSort {

    // Method to sort the exam scores using Selection Sort
    public void sortExamScores(int[] scores) {
        int n = scores.length;

        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in the unsorted part of the array
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (scores[j] < scores[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element of the unsorted part
            if (minIndex != i) {
                int temp = scores[i];
                scores[i] = scores[minIndex];
                scores[minIndex] = temp;
            }
        }
    }
}

public class selectSort {
    public static void main(String[] args) {
        int[] examScores = {75, 60, 85, 50, 90, 70};
        SelectionSort sorter = new SelectionSort();

        System.out.println("Original Exam Scores:");
        for (int score : examScores) {
            System.out.print(score + " ");
        }

        sorter.sortExamScores(examScores);

        System.out.println("\nSorted Exam Scores (Ascending Order):");
        for (int score : examScores) {
            System.out.print(score + " ");
        }
    }
}