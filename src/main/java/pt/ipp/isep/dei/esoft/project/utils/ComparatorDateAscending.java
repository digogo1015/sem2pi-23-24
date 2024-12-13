package pt.ipp.isep.dei.esoft.project.utils;

import pt.ipp.isep.dei.esoft.project.domain.Request;


import java.util.Comparator;

/**
 * The ComparatorDateAscending class is an implementation of the Comparator interface
 * that compares two Request objects based on the ascending order of their dates.
 */
public class ComparatorDateAscending implements Comparator<Request> {

    /**
     * Compares two Request objects based on the ascending order of their dates.
     *
     * @param r1 The first Request to compare.
     * @param r2 The second Request to compare.
     * @return a negative integer if the date of r1 is later than the date of r2,
     *         zero if the dates are the same,
     *         a positive integer if the date of r1 is earlier than the date of r2.
     */
    @Override
    public int compare(Request r1, Request r2) {
        return (-1) * (Utils.transformStringToDate(r1.getDateOfRequest()).compareTo(Utils.transformStringToDate(r2.getDateOfRequest())));
    }
}
