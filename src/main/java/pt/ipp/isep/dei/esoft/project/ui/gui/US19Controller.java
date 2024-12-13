package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.StringUtils;
import pt.ipp.isep.dei.esoft.project.application.controller.DivideStoresController;
import pt.ipp.isep.dei.esoft.project.domain.Store;
import pt.ipp.isep.dei.esoft.project.utils.BruteForce;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;

import java.util.ArrayList;

public class US19Controller {
    @FXML
    private Button button;
    @FXML
    private TextArea label;
    @FXML
    private TextField number;
    private DivideStoresController ctrl;

    public US19Controller() {
        this.ctrl = new DivideStoresController();
    }

    /**
     * Executes the logic for dividing stores based on the provided number.
     */
    public void run() {
        ArrayList<Store> storeList = ctrl.getStores();

        divideStores(storeList);
    }

    /**
     * Retrieves the property numbers from the user input and validates them.
     *
     * @param storeList the list of stores
     * @return an array of property numbers if valid, otherwise null
     */
    private int[] getPropertyNumbers(ArrayList<Store> storeList) {
        try {
            String str = number.getText();

            boolean verifyNumber = (!(str.isBlank()) && !(StringUtils.isAlphaSpace(str)) && StringUtils.isNumeric(str));

            if (verifyNumber) {
                int num = Integer.parseInt(str);

                if ((num >= 3 && num <= 30)) {
                    int[] arr = new int[num];

                    for (int i = 0; i < num; i++)
                        arr[i] = ctrl.getNumPropertiesByStoreDesignation(storeList.get(i).getDesignation());
                    return arr;
                }
            }
            throw new InvalidArguments("Invalid Number");
        } catch (InvalidArguments ia) {
            label.setText(ia.getMessage());
        } catch (IndexOutOfBoundsException index) {
            label.setText("Not enough stores");
            label.setText("Current Stores: " + storeList.size());
        }
        return null;
    }

    /**
     * Divides the stores based on the property numbers and displays the result.
     *
     * @param storeList the list of stores
     */
    private void divideStores(ArrayList<Store> storeList) {
        int[] arr = getPropertyNumbers(storeList);

        if (arr != null) {
            BruteForce bruteForce = new BruteForce();
            String str = bruteForce.sort(arr);

            String v2 = formatBruteForce(str, storeList);
            label.setText(v2);
        }
    }

    /**
     * Formats the result of the brute force algorithm for display.
     *
     * @param str       the result string from the brute force algorithm
     * @param storeList the list of stores
     * @return the formatted result string
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
     * @param num the numbers array
     * @param arr the designations array
     * @return the formatted string combining the numbers and designations
     */
    private String comb(String[] num, String[] arr) {
        String str = "";

        for (int i = 0; i < num.length - 1; i++)
            str += "(" + num[i] + ", " + arr[i] + "), ";
        str += "(" + num[num.length - 1] + ", " + arr[num.length - 1] + ")";

        return str;
    }
}
