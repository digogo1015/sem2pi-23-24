package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The `NumberOfBedrooms` class represents a list of available number of bedrooms for a property.
 * It provides methods to create a copy of the list and retrieve the string representation.
 */
public class NumberOfBedrooms {
    private final ArrayList<String> numberOfBedrooms = new ArrayList<>(Arrays.asList("T0", "T1", "T2",
            "T3", "T4", "T5", "More"));

    /**
     * Creates a copy of the 'numberOfBedrooms' list.
     *
     * @return A new ArrayList<String> containing the same elements as 'numberOfBedrooms'.
     */
    public ArrayList<String> copy() {
        return new ArrayList<>(numberOfBedrooms);
    }

    /**
     * Returns the string representation of the `NumberOfBedrooms` object.
     *
     * @return The string representation.
     */
    @Override
    public String toString() {
        return "Number of Bedrooms: " + numberOfBedrooms;
    }
}
