class HeapSort {

    // Method to sort the salary demands using Heap Sort
    public void sortSalaryDemands(int[] salaries) {
        int n = salaries.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(salaries, n, i);
        }

        // Extract elements from the heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            int temp = salaries[0];
            salaries[0] = salaries[i];
            salaries[i] = temp;

            // call max heapify on the reduced heap
            heapify(salaries, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is an index in arr[]
    // n is size of heap
    void heapify(int[] salaries, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // left = 2*i + 1
        int right = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (left < n && salaries[left] > salaries[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && salaries[right] > salaries[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            int swap = salaries[i];
            salaries[i] = salaries[largest];
            salaries[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(salaries, n, largest);
        }
    }
}

public class HpSort {
    public static void main(String[] args) {
        int[] salaryDemands = {55000, 48000, 60000, 42000, 52000, 58000};
        HeapSort sorter = new HeapSort();

        System.out.println("Original Salary Demands:");
        for (int salary : salaryDemands) {
            System.out.print(salary + " ");
        }

        sorter.sortSalaryDemands(salaryDemands);

        System.out.println("\nSorted Salary Demands (Ascending Order):");
        for (int salary : salaryDemands) {
            System.out.print(salary + " ");
        }
    }
}