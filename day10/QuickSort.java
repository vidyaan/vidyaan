public class QuickSort {

    // Main QuickSort method
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Find the pivot element such that elements smaller than the pivot
            // are on the left, and elements greater than the pivot are on the right
            int pivotIndex = partition(arr, low, high);

            // Recursively sort the two subarrays
            quickSort(arr, low, pivotIndex - 1);  // Left subarray
            quickSort(arr, pivotIndex + 1, high); // Right subarray
        }
    }

    // Partition the array around the pivot
    private static int partition(int[] arr, int low, int high) {
        // We can choose the last element as the pivot (or any element)
        int pivot = arr[high];
        int i = (low - 1);  // Index of the smaller element

        for (int j = low; j <= high - 1; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap arr[i + 1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1; // Return the pivot index
    }

    // Utility method to print the array
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Main method to test QuickSort
    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        int n = arr.length;

        System.out.println("Original array:");
        printArray(arr);

        // Perform QuickSort
        quickSort(arr, 0, n - 1);

        System.out.println("Sorted array:");
        printArray(arr);
    }
}
