package pt.ipp.isep.dei.esoft.project.utils;

import pt.ipp.isep.dei.esoft.project.domain.Advertisement;

/**
 * The ComparatorCityAlphabetic class is an implementation of the SortingCriteria interface
 * that compares two advertisements based on the alphabetical order of their cities.
 */
public class ComparatorCityAlphabetic implements SortingCriteria {

    /**
     * Compares two Advertisement objects based on the alphabetical order of their cities.
     *
     * @param a1 The first Advertisement to compare.
     * @param a2 The second Advertisement to compare.
     * @return a negative integer if the city of a1 comes before the city of a2 in alphabetical order,
     *         zero if the cities are the same,
     *         a positive integer if the city of a1 comes after the city of a2 in alphabetical order.
     */
    @Override
    public int compare(Advertisement a1, Advertisement a2) {
        return a1.getAddressOfProperty().getCity().compareTo(a2.getAddressOfProperty().getCity());
    }
}
