
import java.util.Arrays;

public class MergeSort {

    // Function to perform Merge Sort
    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return; // Base case: an array of size 1 is already sorted
        }

        // Split the array into two halves
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);

        // Recursively sort each half
        mergeSort(left);
        mergeSort(right);

        // Merge the sorted halves back into the original array
        merge(arr, left, right);
    }

    // Merge two sorted arrays into one sorted array
    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0; // Pointer for left subarray
        int j = 0; // Pointer for right subarray
        int k = 0; // Pointer for the merged array

        // Merge the two subarrays while there are elements in both left and right
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        // If there are any remaining elements in the left subarray, copy them
        while (i < left.length) {
            arr[k] = left[i];
            i++;
            k++;
        }

        // If there are any remaining elements in the right subarray, copy them
        while (j < right.length) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    // Main function to test Merge Sort
    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7};

        // Print the original array
        System.out.println("Original array: " + Arrays.toString(arr));

        // Call mergeSort
        mergeSort(arr);

        // Print the sorted array
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}
