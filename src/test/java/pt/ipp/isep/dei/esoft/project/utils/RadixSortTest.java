package pt.ipp.isep.dei.esoft.project.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.utils.RadixSort;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RadixSortTest {
    @Test
    @DisplayName("Ensure is sorting")
    void sort() {
        int[] arr = new int[]{4, 9, 2, 1, 10, 23, 9, 2, 39, 1, 3};

        RadixSort radixSort = new RadixSort();
        radixSort.sort(arr);

        assertEquals(Arrays.toString(new int[]{1, 1, 2, 2, 3, 4, 9, 9, 10, 23, 39}), Arrays.toString(arr));
        assertNotEquals(Arrays.toString(new int[]{1, 1, 3, 2, 2, 4, 9, 9, 10, 23, 39}), Arrays.toString(arr));
    }

    @Test
    @DisplayName("Ensure is sorting with elements already sorted")
    void sort2() {
        int[] arr = new int[]{1, 1, 2, 2, 3, 4, 9, 9, 10, 23, 39};

        RadixSort radixSort = new RadixSort();
        radixSort.sort(arr);

        assertEquals(Arrays.toString(new int[]{1, 1, 2, 2, 3, 4, 9, 9, 10, 23, 39}), Arrays.toString(arr));
        assertNotEquals(Arrays.toString(new int[]{1, 1, 3, 2, 2, 4, 9, 9, 10, 23, 39}), Arrays.toString(arr));
    }

}