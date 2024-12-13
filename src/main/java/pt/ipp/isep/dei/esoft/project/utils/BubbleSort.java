package pt.ipp.isep.dei.esoft.project.utils;

/**
 * The BubbleSort class implements the Sortable interface using the bubble sort algorithm.
 */
public class BubbleSort implements Sortable {

    /**
     * Sorts the given array using the bubble sort algorithm.
     *
     * @param arr the array to be sorted
     */
    @Override
    public void sort(int[] arr) {
        bubbleSort(arr);
    }

    /**
     * Performs the bubble sort algorithm on the array.
     *
     * @param arr the array to be sorted
     */
    private static void bubbleSort(int[] arr) {
        int len = arr.length;

        for (int i = 0; i < len - 1; i++)
            for (int j = 0; j < len - i - 1; j++)
                if (arr[j] > arr[j + 1])
                    swap(arr, j);
    }

    /**
     * Swaps two elements in the array.
     *
     * @param arr the array
     * @param i   the index of the first element to swap
     */
    private static void swap(int[] arr, int i) {
        int temp = arr[i];
        arr[i] = arr[i + 1];
        arr[i + 1] = temp;
    }
}