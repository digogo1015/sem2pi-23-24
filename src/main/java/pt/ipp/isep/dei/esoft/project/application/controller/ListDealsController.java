package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Advertisement;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import pt.ipp.isep.dei.esoft.project.utils.BubbleSort;
import pt.ipp.isep.dei.esoft.project.utils.SelectionSort;
import pt.ipp.isep.dei.esoft.project.utils.Utils;

import java.util.ArrayList;

/**
 * The ListDealsController class is a controller responsible for handling deal-related operations
 * in a divide stores application.
 */
public class ListDealsController {
    Repositories repositories;
    AdvertisementRepository advertisementRepository;

    /**
     * Constructs a new instance of ListDealsController.
     * Initializes the repositories and sets the advertisementRepository
     * based on the repositories instance.
     */
    public ListDealsController() {
        this.repositories = Repositories.getInstance();
        this.advertisementRepository = repositories.getAdvertisementRepository();
    }

    /**
     * Returns a list of all deals.
     *
     * @return An ArrayList of Advertisement objects representing the deals.
     */
    public ArrayList<Advertisement> getDeals() {
        return advertisementRepository.getAllDeals();
    }

    /**
     * Filters the list of deals based on the specified area list and the original deal list.
     *
     * @param areaList An array of integers representing the areas for filtering.
     * @param dealList An ArrayList of Advertisement objects representing the original deal list.
     * @return An ArrayList of Advertisement objects representing the filtered deals.
     */
    public ArrayList<Advertisement> getDeals(int[] areaList, ArrayList<Advertisement> dealList) {
        return advertisementRepository.dealsAscending(areaList, dealList);
    }

    /**
     * Returns an array of areas extracted from the provided list of advertisements.
     *
     * @param list                  An ArrayList of Advertisement objects to extract areas from.
     * @param sortMethod            The sorting method to use for the area list. (0 for Selection Sort, 1 for Bubble Sort)
     * @param ascendingOrDescending Specifies whether the area list should be sorted in ascending (0) or descending (1) order.
     * @return An array of integers representing the areas extracted from the advertisement list.
     */
    public int[] getAreaList(ArrayList<Advertisement> list, int sortMethod, int ascendingOrDescending) {
        int[] areaList = new int[list.size()];

        for (int i = 0; i < list.size(); i++)
            areaList[i] = Integer.parseInt(list.get(i).getRequest().getProperty().getArea());

        if (sortMethod == 0)
            (new SelectionSort()).sort(areaList);
        else
            (new BubbleSort()).sort(areaList);
        if (ascendingOrDescending == 1)
            Utils.revertList(areaList);
        return areaList;
    }
}