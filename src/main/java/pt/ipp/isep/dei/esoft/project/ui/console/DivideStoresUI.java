package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.DivideStoresController;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.utils.BruteForce;
import pt.ipp.isep.dei.esoft.project.utils.Input;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;
import pt.ipp.isep.dei.esoft.project.utils.Print;

import java.util.ArrayList;

/**
 * The DivideStoresUI class represents a user interface for dividing stores based on certain properties.
 * It implements the Runnable interface to support execution in a separate thread.
 */
public class DivideStoresUI implements Runnable {
    private DivideStoresController ctrl = new DivideStoresController();

    /**
     * Runs the store division process.
     * Retrieves the store list from the controller and divides the stores based on their properties.
     */
    @Override
    public void run() {
        ArrayList<Store> storeList = ctrl.getStores();

        divideStores(storeList);
    }

    /**
     * Obtains the property numbers for the store list and validates them.
     * Uses the BruteForce algorithm to divide the stores based on the properties.
     *
     * @param storeList The list of stores to be divided.
     */
    private void divideStores(ArrayList<Store> storeList) {
        int[] arr = getPropertyNumbers(storeList);

        BruteForce bruteForce = new BruteForce();
        String str = bruteForce.sort(arr);

        String v2 = formatBruteForce(str, storeList);
        Print.text(v2);
    }

    /**
     * Formats the result of the BruteForce algorithm.
     * Builds strings representing the full list, sublist 1, sublist 2, difference, and time.
     *
     * @param str       The result string from the BruteForce algorithm.
     * @param storeList The list of stores.
     * @return The formatted result string.
     */
    private String formatBruteForce(String str, ArrayList<Store> storeList) {
        String[] arr = str.split(";");

        int len = arr[0].split(",").length;

        String[] designations = new String[len];

        for (int i = 0; i < len; i++)
            designations[i] = storeList.get(i).getDesignation();

        String[] fullListNum = arr[0].split(",");
        String[] list1Num = arr[1].split(",");
        String[] list2Num = arr[2].split(",");

        String[] designations1 = new String[len];
        String[] designations2 = new String[len];

        for (int i = 0; i < len; i++) {
            if (ctrl.getNumPropertiesByStoreDesignation(storeList.get(i).getDesignation()) == Integer.parseInt(list1Num[i])) {
                designations1[i] = storeList.get(i).getDesignation();
                designations2[i] = "";
            } else {
                designations1[i] = "";
                designations2[i] = storeList.get(i).getDesignation();
            }
        }

        String fullList = "[" + comb(fullListNum, designations) + "]";
        String list1 = "[" + comb(list1Num, designations1) + "]";
        String list2 = "[" + comb(list2Num, designations2) + "]";

        return ("Full List: " + fullList + "\nSublist 1: " + list1 + "\nSublist 2: " + list2 +
                "\nDifference: " + arr[3] + "\nTime: " + arr[4] + " secs\n");
    }

    /**
     * Combines the numbers and designations into a formatted string.
     *
     * @param num The numbers array.
     * @param arr The designations array.
     * @return The formatted string combining numbers and designations.
     */
    private String comb(String[] num, String[] arr) {
        String str = "";

        for (int i = 0; i < num.length - 1; i++)
            str += "(" + num[i] + ", " + arr[i] + "), ";
        str += "(" + num[num.length - 1] + ", " + arr[num.length - 1] + ")";

        return str;
    }

    /**
     * Obtains the property numbers for the store list from the user and validates them.
     *
     * @param storeList The list of stores.
     * @return An array of property numbers.
     */
    private int[] getPropertyNumbers(ArrayList<Store> storeList) {
        do {
            try {
                String str = Input.getStringWithLabel("Algorithm Input (3-30):");

                if (str.isBlank())
                    throw new InvalidArguments("InvalidNumber");

                int num = Integer.parseInt(str);

                if (num >= 3 && num <= 30) {
                    int[] arr = new int[num];

                    for (int i = 0; i < num; i++)
                        arr[i] = ctrl.getNumPropertiesByStoreDesignation(storeList.get(i).getDesignation());
                    return arr;
                }
                throw new InvalidArguments("Invalid Number");
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            } catch (IndexOutOfBoundsException index) {
                Print.text("Not enough stores");
                Print.text("Current Stores: " + storeList.size());
            }
        } while (true);
    }
}
