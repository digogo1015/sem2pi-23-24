package pt.ipp.isep.dei.esoft.project.utils;

public class SelectionSort implements Sortable {
    @Override
    public void sort(int[] arr) {
        selectionSort(arr);
    }

    private static void selectionSort(int[] arr) {
        int len = arr.length;

        for (int i = 0; i < len - 1; i++) {
            int index = i;

            for (int j = i + 1; j < len; j++)
                if (arr[j] < arr[index])
                    index = j;

            swap(arr, index, i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}