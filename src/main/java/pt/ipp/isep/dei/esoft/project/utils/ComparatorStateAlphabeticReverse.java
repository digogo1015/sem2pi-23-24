package pt.ipp.isep.dei.esoft.project.utils;

import pt.ipp.isep.dei.esoft.project.domain.Advertisement;

/**
 * The ComparatorStateAlphabeticReverse class is an implementation of the SortingCriteria interface
 * that compares two Advertisement objects based on the reverse alphabetic order of their states.
 */
public class ComparatorStateAlphabeticReverse implements SortingCriteria {

    /**
     * Compares two Advertisement objects based on the reverse alphabetic order of their states.
     *
     * @param a1 The first Advertisement to compare.
     * @param a2 The second Advertisement to compare.
     * @return a negative integer if the state of a1 comes after the state of a2 in reverse alphabetic order,
     *         zero if the states are the same,
     *         a positive integer if the state of a1 comes before the state of a2 in reverse alphabetic order.
     */
    @Override
    public int compare(Advertisement a1, Advertisement a2) {
        return (-1) * a1.getAddressOfProperty().getState().compareTo(a2.getAddressOfProperty().getState());
    }
}
