package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.util.Objects;

import java.io.Serializable;
import java.util.Objects;

/**
 * Represents an equipment item.
 */
public class Equipment implements Serializable {

    private static final long serialVersionUID = 5991737918287346117L;

    private static final int maxLimit = 30;
    private String designation;

    /**
     * Creates an Equipment object with the specified designation.
     *
     * @param designation The designation of the equipment.
     */
    public Equipment(String designation) {
        this.designation = designation;
    }

    /**
     * Retrieves the maximum limit of equipment.
     *
     * @return The maximum limit of equipment.
     */
    public static int getMaxLimit() {
        return maxLimit;
    }

    /**
     * Checks if the equipment is equal to the specified object.
     *
     * @param o The object to compare.
     * @return true if the equipment is equal to the specified object, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return Objects.equals(designation, equipment.designation);
    }

    /**
     * Computes the hash code of the equipment.
     *
     * @return The hash code of the equipment.
     */
    @Override
    public int hashCode() {
        return Objects.hash(designation);
    }

    /**
     * Retrieves a string representation of the equipment.
     *
     * @return A string representation of the equipment.
     */
    @Override
    public String toString() {
        return "Equipment: " + designation;
    }
}
