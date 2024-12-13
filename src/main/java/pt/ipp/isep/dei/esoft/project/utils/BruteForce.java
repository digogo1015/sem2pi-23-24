package pt.ipp.isep.dei.esoft.project.utils;

/**
 * The BruteForce class implements a brute force algorithm to sort an array and divide it into two lists with minimal difference in their sums.
 */
public class BruteForce {

    /**
     * Sorts the given array and divides it into two lists with minimal difference.
     *
     * @param arr the array to be sorted and divided
     * @return a string representation of the sorted array, two lists, difference, and execution time
     */
    public String sort(int[] arr) {
        return bruteForce(arr);
    }

    /**
     * Main method for the brute force algorithm.
     *
     * @param arrNum the array to be sorted and divided
     * @return a string representation of the sorted array, two lists, difference, and execution time
     */
    public static String bruteForce(int[] arrNum) {
        int len = arrNum.length;

        int[] list1 = new int[len];
        int[] list2 = new int[len];

        long startTime = System.nanoTime();

        int index = divide(arrNum);
        int diff = getLists(arrNum, index, list1, list2);

        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1_000_000_000.0;

        return displayInfo(arrNum, diff, list1, list2, executionTime);
    }

    /**
     * Divides the array into two lists with minimal difference.
     *
     * @param arr    the array to be divided
     * @param num    the binary representation used for division
     * @param list1  the first list to store elements
     * @param list2  the second list to store elements
     * @return the difference between the sums of the two lists
     */
    private static int getLists(int[] arr, int num, int[] list1, int[] list2) {
        int len = arr.length;

        for (int i = 0; i < len; i++) {
            if (((num >> i) & 1) == 1) {
                list1[i] = arr[i];
                list2[i] = 0;
            } else {
                list1[i] = 0;
                list2[i] = arr[i];
            }
        }
        return abs(sum(list1) - sum(list2));
    }

    /**
     * Calculates the power of a number.
     *
     * @param x the base
     * @param y the exponent
     * @return the result of x raised to the power of y
     */
    private static int pow(int x, int y) {
        int res = 1;
        while (y-- != 0)
            res *= x;
        return res;
    }

    /**
     * Finds the index to divide the array into two lists.
     *
     * @param arr the array to be divided
     * @return the index for dividing the array
     */
    private static int divide(int[] arr) {
        int len = arr.length;
        int comb = pow(2, len - 1) - 1;
        int index = -1;
        int dif = -1;
        int temp;

        for (int i = 0; i < comb; i++) {
            temp = getDiff(arr, i + 1);
            if (dif > temp || dif < 0) {
                index = i + 1;
                dif = temp;
            }
        }
        return index;
    }

    /**
     * Calculates the difference between two lists.
     *
     * @param arr the array to be divided
     * @param num the binary representation used for division
     * @return the difference between the sums of the two lists
     */
    private static int getDiff(int[] arr, int num) {
        int[] tmp1 = new int[arr.length];
        int[] tmp0 = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            if (((num >> i) & 1) == 1) {
                tmp1[i] = arr[i];
                tmp0[i] = 0;
            } else {
                tmp1[i] = 0;
                tmp0[i] = arr[i];
            }
        }
        return abs(sum(tmp1) - sum(tmp0));
    }

    /**
     * Calculates the absolute value of a number.
     *
     * @param x the number
     * @return the absolute value of x
     */
    private static int abs(int x) {
        return x > 0 ? x : -x;
    }

    /**
     * Calculates the sum of an array.
     *
     * @param sum the array
     * @return the sum of the elements in the array
     */
    private static int sum(int[] sum) {
        int res = 0;

        for (int i : sum)
            res += i;
        return res;
    }

    /**
     * Formats an array as a string.
     *
     * @param arr the array to be formatted
     * @return a string representation of the array
     */
    private static String pl(int[] arr) {
        String str = "";
        for (int i = 0; i < arr.length - 1; i++)
            str += arr[i] + ",";
        str += arr[arr.length - 1] + "";
        return str;
    }

    /**
     * Formats and returns a string containing information about the sorted array, two lists, difference, and execution time.
     *
     * @param arrNum        the original array
     * @param diff          the difference between the sums of the two lists
     * @param list1         the first list
     * @param list2         the second list
     * @param executionTime the execution time of the algorithm
     * @return a string representation of the sorted array, two lists, difference, and execution time
     */
    private static String displayInfo(int[] arrNum, int diff, int[] list1, int[] list2, double executionTime) {
        return (pl(arrNum) + ";" + pl(list1) + ";" + pl(list2) + ";" + diff + ";" + executionTime);
    }
}