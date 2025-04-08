class CountingSort {

    // Method to sort the student ages using Counting Sort
    public void sortStudentAges(int[] ages) {
        int n = ages.length;
        int minAge = 10;
        int maxAge = 18;
        int range = maxAge - minAge + 1;

        int[] count = new int[range];
        int[] output = new int[n];

        // Store the frequency of each age
        for (int age : ages) {
            count[age - minAge]++;
        }

        // Compute cumulative frequencies
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        // Place the ages in their correct positions in the output array
        for (int i = n - 1; i >= 0; i--) {
            output[count[ages[i] - minAge] - 1] = ages[i];
            count[ages[i] - minAge]--;
        }

        // Copy the sorted ages back into the original array
        System.arraycopy(output, 0, ages, 0, n);
    }
}

public class CountSort {
    public static void main(String[] args) {
        int[] studentAges = {15, 12, 18, 10, 14, 16, 11, 13, 17};
        CountingSort sorter = new CountingSort();

        System.out.println("Original Student Ages:");
        for (int age : studentAges) {
            System.out.print(age + " ");
        }

        sorter.sortStudentAges(studentAges);

        System.out.println("\nSorted Student Ages (Ascending Order):");
        for (int age : studentAges) {
            System.out.print(age + " ");
        }
    }
}