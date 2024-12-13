package pt.ipp.isep.dei.esoft.project.utils;

import pt.ipp.isep.dei.esoft.project.domain.Advertisement;

/**
 * The ComparatorDescendingPrice class is an implementation of the SortingCriteria interface
 * that compares two Advertisement objects based on the descending order of their prices.
 */
public class ComparatorDescendingPrice implements SortingCriteria {

    /**
     * Compares two Advertisement objects based on the descending order of their prices.
     *
     * @param a1 The first Advertisement to compare.
     * @param a2 The second Advertisement to compare.
     * @return a negative integer if the price of a1 is greater than the price of a2,
     *         zero if the prices are the same,
     *         a positive integer if the price of a1 is less than the price of a2.
     */
    @Override
    public int compare(Advertisement a1, Advertisement a2) {
        return (-1) * (Integer.parseInt(a1.getPrice()) - Integer.parseInt(a2.getPrice()));
    }
}
