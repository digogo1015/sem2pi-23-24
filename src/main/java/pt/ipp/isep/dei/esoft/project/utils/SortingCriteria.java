package pt.ipp.isep.dei.esoft.project.utils;

import pt.ipp.isep.dei.esoft.project.domain.Advertisement;

import java.util.Comparator;

public interface SortingCriteria extends Comparator<Advertisement> {
    @Override
    int compare(Advertisement o1, Advertisement o2);
}
