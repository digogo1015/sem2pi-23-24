package pt.ipp.isep.dei.esoft.project.utils;

import pt.ipp.isep.dei.esoft.project.domain.VisitRequest;

import java.util.Comparator;

/**
 * The ComparatorDateAscendingVisitRequest class is an implementation of the Comparator interface
 * that compares two VisitRequest objects based on the ascending order of their visit dates.
 */
public class ComparatorDateAscendingVisistRequest implements Comparator<VisitRequest> {

    /**
     * Compares two VisitRequest objects based on the ascending order of their visit dates.
     *
     * @param v1 The first VisitRequest to compare.
     * @param v2 The second VisitRequest to compare.
     * @return a negative integer if the visit date of v1 is later than the visit date of v2,
     *         zero if the visit dates are the same,
     *         a positive integer if the visit date of v1 is earlier than the visit date of v2.
     */
    @Override
    public int compare(VisitRequest v1, VisitRequest v2) {
        return (-1) * (Utils.transformStringToDate(v1.getDateOfVisit()).compareTo(Utils.transformStringToDate(v2.getDateOfVisit())));
    }
}
