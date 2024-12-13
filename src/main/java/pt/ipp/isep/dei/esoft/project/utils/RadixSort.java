package pt.ipp.isep.dei.esoft.project.utils;

public class RadixSort implements Sortable {
    @Override
    public void sort(int[] arr) {
        radixSort(arr);
    }

    private static void radixSort(int[] arr) {
        int max = getMax(arr);

        for (int exp = 1; max / exp > 0; exp *= 10)
            countSort(arr, exp);
    }

    private static void countSort(int[] arr, int exp) {
        int len = arr.length;
        int[] temp = new int[len];
        int[] count = new int[10];

        for (int j : arr) count[(j / exp) % 10]++;

        count[0]--;
        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (int i = len - 1; i >= 0; i--) {
            int index = (arr[i] / exp) % 10;

            temp[count[index]] = arr[i];
            count[index]--;
        }

        for (int i = 0; i < len; i++)
            arr[i] = temp[i];
    }

    private static int getMax(int[] arr) {
        int max = arr[0];

        for (int i = 1; i < arr.length; i++)
            if (max < arr[i])
                max = arr[i];
        return max;
    }
}