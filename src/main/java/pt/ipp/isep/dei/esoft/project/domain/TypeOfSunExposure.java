package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The TypeOfSunExposure class represents the available types of sun exposure.
 */
public class TypeOfSunExposure {

    private final ArrayList<String> typeofSunExposure = new ArrayList<>(Arrays.asList("N", "S", "E", "W"));

    /**
     * Creates a copy of the ArrayList of sun exposure types.
     *
     * @return A new ArrayList containing the sun exposure types, or null if the ArrayList is null.
     */
    public ArrayList<String> copy() {
        if (typeofSunExposure != null)
            return new ArrayList<>(typeofSunExposure);
        return null;
    }

    @Override
    public String toString() {
        return "Sun Exposure: " + typeofSunExposure;
    }
}
