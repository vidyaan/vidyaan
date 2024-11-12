public class InsertionSort {

    // Function to perform insertion sort on an array
    public static void insertionSort(int[] arr) {
        // Traverse through all elements in the array
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];  // The current element to be inserted
            int j = i - 1;     // The last index of the sorted portion

            // Move elements of arr[0..i-1], that are greater than key,
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];  // Shift element to the right
                j = j - 1;  // Move the index one position to the left
            }
            arr[j + 1] = key;  // Insert the key at the correct position
        }
    }

    // Utility function to print the array
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Main function to test the insertion sort
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6};
        System.out.println("Original array:");
        printArray(arr);

        // Perform insertion sort
        insertionSort(arr);

        System.out.println("Sorted array:");
        printArray(arr);
    }
}
