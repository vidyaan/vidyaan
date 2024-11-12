public class BubbleSort {

    // Method to perform bubble sort on an array
    public static void bubbleSort(int[] arr) {
        int n = arr.length;

        // Start from the first element and do passes through the array
        for (int i = 0; i < n - 1; i++) {
            // Variable to track the last index where a swap happened
            int lastSwapIndex = -1;

            // Inner loop to compare adjacent elements
            for (int j = 0; j < n - 1 - i; j++) {

                // If the current element is greater than the next element, swap them
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j + 1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    // Update the last swap index to the current position
                    lastSwapIndex = j;
                }
            }

            // If no swaps were made during the inner loop, the array is sorted
            if (lastSwapIndex == -1) {
                break;  // No swaps, so the array is already sorted
            }
        }
    }

    // Method to print the array
    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("Original array:");
        printArray(arr);

        bubbleSort(arr);

        System.out.println("Sorted array:");
        printArray(arr);
    }
}
