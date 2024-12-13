package pt.ipp.isep.dei.esoft.project.utils;

import pt.ipp.isep.dei.esoft.project.domain.Request;

import java.util.Comparator;

/**
 * The ComparatorDateDescending class is an implementation of the Comparator interface
 * that compares two Request objects based on the descending order of their request dates.
 */
public class ComparatorDateDescending implements Comparator<Request> {

    /**
     * Compares two Request objects based on the descending order of their request dates.
     *
     * @param r1 The first Request to compare.
     * @param r2 The second Request to compare.
     * @return a negative integer if the request date of r1 is earlier than the request date of r2,
     *         zero if the request dates are the same,
     *         a positive integer if the request date of r1 is later than the request date of r2.
     */
    @Override
    public int compare(Request r1, Request r2) {
        return Utils.transformStringToDate(r2.getDateOfRequest()).compareTo(Utils.transformStringToDate(r1.getDateOfRequest()));
    }
}
