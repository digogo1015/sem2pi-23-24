package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The TypesOfProperty class represents the available types of properties.
 */
public class TypesOfProperty {
    private final ArrayList<String> typeOfProperty = new ArrayList<>(Arrays.asList("House", "Apartment", "Land"));

    /**
     * Creates a copy of the ArrayList of property types.
     *
     * @return A new ArrayList containing the property types, or null if the ArrayList is null.
     */
    public ArrayList<String> copy() {
        if (typeOfProperty != null)
            return new ArrayList<>(typeOfProperty);
        return null;
    }

    @Override
    public String toString() {
        return "Property Type: " + typeOfProperty;
    }
}
