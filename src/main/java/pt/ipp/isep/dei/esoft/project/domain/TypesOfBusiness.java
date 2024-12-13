package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The TypesOfBusiness class represents the available types of businesses.
 */
public class TypesOfBusiness {
    private final ArrayList<String> typeOfBusiness = new ArrayList<>(Arrays.asList("Sale", "Rent"));

    /**
     * Creates a copy of the ArrayList of business types.
     *
     * @return A new ArrayList containing the business types, or null if the ArrayList is null.
     */
    public ArrayList<String> copy() {
        if (typeOfBusiness != null)
            return new ArrayList<>(typeOfBusiness);
        return null;
    }

    @Override
    public String toString() {
        return "Business Type: " + typeOfBusiness;
    }
}
