package pt.ipp.isep.dei.esoft.project.utils;

public class MergeSort implements Sortable {
    @Override
    public void sort(int[] arr) {
        mergeSort(arr);
    }

    private static void mergeSort(int[] arr) {
        int arrLen = arr.length;

        if (arrLen < 2)
            return;

        int midIndex = arrLen / 2;

        int[] leftHalf = new int[midIndex];
        int[] rightHalf = new int[arrLen - midIndex];

        for (int i = 0; i < midIndex; i++)
            leftHalf[i] = arr[i];

        for (int i = midIndex; i < arrLen; i++)
            rightHalf[i - midIndex] = arr[i];

        mergeSort(leftHalf);
        mergeSort(rightHalf);

        merge(arr, leftHalf, rightHalf);
    }

    private static void merge(int[] arr, int[] leftHalf, int[] rightHalf) {
        int i = 0, j = 0, k = 0;
        int leftHalfLen = leftHalf.length;
        int rightHalfLen = rightHalf.length;

        while (i < leftHalfLen && j < rightHalfLen) {
            if (leftHalf[i] <= rightHalf[j])
                arr[k] = leftHalf[i++];
            else
                arr[k] = rightHalf[j++];
            k++;
        }

        while (i < leftHalfLen)
            arr[k++] = leftHalf[i++];

        while (j < rightHalfLen)
            arr[k++] = rightHalf[j++];
    }
}