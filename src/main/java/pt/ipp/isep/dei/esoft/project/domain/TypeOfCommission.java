package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The TypeOfCommission class represents the types of commission available.
 */
public class TypeOfCommission {
    private final ArrayList<String> typeofCommission = new ArrayList<>(Arrays.asList("Percentage", "Fixed Value"));

    /**
     * Creates a copy of the ArrayList of commission types.
     *
     * @return A new ArrayList containing the commission types, or null if the ArrayList is null.
     */
    public ArrayList<String> copy() {
        if (typeofCommission != null)
            return new ArrayList<>(typeofCommission);
        return null;
    }

    @Override
    public String toString() {
        return "Commission Type: " + typeofCommission;
    }
}
