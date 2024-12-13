package pt.ipp.isep.dei.esoft.project.utils;

import pt.ipp.isep.dei.esoft.project.domain.Advertisement;

/**
 * The ComparatorAscendingPrice class is an implementation of the SortingCriteria interface
 * that compares two advertisements based on their prices in ascending order.
 */
public class ComparatorAscendingPrice implements SortingCriteria {

    /**
     * Compares two Advertisement objects based on their prices in ascending order.
     *
     * @param a1 The first Advertisement to compare.
     * @param a2 The second Advertisement to compare.
     * @return a negative integer if a1's price is less than a2's price,
     *         zero if a1's price is equal to a2's price,
     *         a positive integer if a1's price is greater than a2's price.
     */
    @Override
    public int compare(Advertisement a1, Advertisement a2) {
        return Integer.parseInt(a1.getPrice()) - Integer.parseInt(a2.getPrice());
    }
}
