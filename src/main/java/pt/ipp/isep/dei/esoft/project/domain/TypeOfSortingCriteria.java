package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The TypeOfSortingCriteria class represents the available sorting criteria for sorting objects.
 */
public class TypeOfSortingCriteria {

    private ArrayList<String> sortingCriteria = new ArrayList<>(Arrays.asList(
            "Price Ascending",
            "Price Descending",
            "City Alphabetic",
            "City Alphabetic Reverse",
            "State Alphabetic",
            "State Alphabetic Reverse"
    ));

    private ArrayList<String> sortingCriteriaClassNames = new ArrayList<>(Arrays.asList(
            "ComparatorAscendingPrice",
            "ComparatorDescendingPrice",
            "ComparatorCityAlphabetic",
            "ComparatorCityAlphabeticReverse",
            "ComparatorStateAlphabetic",
            "ComparatorStateAlphabeticReverse"
    ));

    /**
     * Retrieves the corresponding comparator class name based on the provided sorting criteria string.
     *
     * @param sortingCriteriaString The sorting criteria string.
     * @return The class name of the corresponding comparator.
     */
    public String getComparatorSortingCriteria(String sortingCriteriaString) {
        int index = sortingCriteria.indexOf(sortingCriteriaString);
        return sortingCriteriaClassNames.get(index);
    }

    /**
     * Creates a copy of the ArrayList of sorting criteria.
     *
     * @return A new ArrayList containing the sorting criteria, or null if the ArrayList is null.
     */
    public ArrayList<String> copy() {
        if (sortingCriteria != null)
            return new ArrayList<>(sortingCriteria);
        return null;
    }

    @Override
    public String toString() {
        return "Sorting Criteria: " + sortingCriteria;
    }
}
