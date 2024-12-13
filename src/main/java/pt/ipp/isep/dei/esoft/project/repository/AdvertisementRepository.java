package pt.ipp.isep.dei.esoft.project.repository;

import kotlin.Pair;
import org.apache.commons.math.stat.regression.SimpleRegression;
import pt.ipp.isep.dei.esoft.project.database.Database;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.utils.SortingCriteria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static pt.ipp.isep.dei.esoft.project.utils.Utils.formatNBedrooms;

/**
 * The AdvertisementRepository class represents a repository for managing advertisements.
 */
public class AdvertisementRepository {
    private List<Advertisement> advertisementList = new ArrayList<>();

    /**
     * Creates a copy of the advertisement list.
     *
     * @return A new ArrayList containing the advertisements, or null if the list is null.
     */
    public ArrayList<Advertisement> copy() {
        if (advertisementList != null)
            return new ArrayList<>(advertisementList);
        return null;
    }

    /**
     * Adds an advertisement to the repository.
     *
     * @param advertisement The advertisement to add.
     * @return true if the advertisement is successfully added, false otherwise.
     */
    public boolean addAdvertisement(Advertisement advertisement) {
        return validateAdvertisement(advertisement) && advertisementList.add(advertisement);
    }

    private boolean validateAdvertisement(Advertisement advertisement) {
        return !copy().contains(advertisement);
    }

    /**
     * Retrieves a list of advertisements filtered by the given filters.
     *
     * @param filters The filters to apply.
     * @return A list of advertisements matching the filters, or null if an error occurs.
     */
    public ArrayList<Advertisement> getListOfAdvertisementsByFilter(Filters filters) {
        ArrayList<Advertisement> list = new ArrayList<>();

        for (Advertisement a : this.copy()) {
            if (a.hasPropertyType(filters.getTypeOfProperty()) && a.hasBusinessType(filters.getTypeOfBusiness()) && a.getStatus().equals("ToBeValidated"))
                if (!(a.getRequest().getProperty() instanceof Residence) || a.hasNumberOfBedrooms(formatNBedrooms(filters.getNumberOfBedrooms())))
                    list.add(a);
        }

        try {
            return sorted(list, Class.forName(Database.utilsPath +
                    (new TypeOfSortingCriteria()).getComparatorSortingCriteria(filters.getSortingCriteria())));
        } catch (Exception ignored) {}
        return null;
    }

    private <T> ArrayList<Advertisement> sorted(ArrayList<Advertisement> list, Class<T> type) {
        try {
            Collections.sort(list, (SortingCriteria) type.getConstructor().newInstance());
        } catch (Exception ignored) {}
        return list;
    }

    /**
     * Retrieves a list of all validated deals.
     *
     * @return A list of validated deals.
     */
    public ArrayList<Advertisement> getAllDeals() {
        ArrayList<Advertisement> list = new ArrayList<>();

        for (Advertisement a : this.copy())
            if (a.getStatus().equals("Validated"))
                list.add(a);
        return list;
    }

    /**
     * Retrieves a list of advertisements that are not sold.
     *
     * @return A list of advertisements that are not sold.
     */
    public ArrayList<Advertisement> getAdvertisementsNotSold() {
        ArrayList<Advertisement> list = new ArrayList<>();

        for (Advertisement a : this.copy())
            if (!a.getStatus().equals("Validated"))
                list.add(a);
        return list;
    }

    /**
     * Sorts the dealListOriginal based on the given areaList.
     *
     * @param areaList           The list of areas to sort by.
     * @param dealListOriginal   The original list of deals.
     * @return The sorted list of deals.
     */
    public ArrayList<Advertisement> dealsAscending(int[] areaList, ArrayList<Advertisement> dealListOriginal) {
        ArrayList<Advertisement> dealListInOrder = new ArrayList<>();
        ArrayList<Advertisement> dealList = new ArrayList<>(dealListOriginal);

        for (int j : areaList)
            for (Advertisement a : dealList)
                if (j == Integer.parseInt(a.getRequest().getProperty().getArea())) {
                    dealListInOrder.add(a);
                    dealList.remove(a);
                    break;
                }
        return dealListInOrder;
    }

    /**
     * Retrieves a SimpleRegression object for the specified choice.
     *
     * @param choice The choice to determine the regression variable.
     * @return The SimpleRegression object.
     */
    public SimpleRegression getSimpleRegression(int choice) {
        SimpleRegression simpleRegression = new SimpleRegression();
        ArrayList<Advertisement> list = getAllDeals();

        for (Advertisement a : list)
            if (a.getRequest().getProperty() instanceof Residence && a.hasBusinessType("Sale"))
                for (PurchaseOrder p : a.getPurchaseOrderList())
                    if (p.getStatus().equals("Validated"))
                        switch (choice) {
                            case 0:
                                simpleRegression.addData(Double.parseDouble(a.getRequest().getProperty().getArea()), Double.parseDouble(p.getPrice()));
                                break;
                            case 1:
                                simpleRegression.addData(Double.parseDouble(a.getRequest().getProperty().getCityCenter()), Double.parseDouble(p.getPrice()));
                                break;
                            case 2:
                                simpleRegression.addData(Double.parseDouble((a.getRequest().getNumOfBedrooms())), Double.parseDouble(p.getPrice()));
                                break;
                            case 3:
                                simpleRegression.addData(Double.parseDouble((a.getRequest().getNumOfBathrooms())), Double.parseDouble(p.getPrice()));
                                break;
                            case 4:
                                simpleRegression.addData(Double.parseDouble((a.getRequest().getNumOfParkingSpaces())), Double.parseDouble(p.getPrice()));
                                break;
                            default:
                                break;
                        }
        return simpleRegression;
    }

    /**
     * Retrieves the data for multiple regression analysis.
     *
     * @return A Pair object containing the dependent and independent variables.
     */
    public Pair<double[], double[][]> getMultipleRegression() {
        ArrayList<Advertisement> residenceDeals = new ArrayList<>();

        for (Advertisement a : getAllDeals())
            if (a.getRequest().getProperty() instanceof Residence && a.hasBusinessType("Sale"))
                for (PurchaseOrder p : a.getPurchaseOrderList())
                    if (p.getStatus().equals("Validated"))
                        residenceDeals.add(a);

        double[] y = new double[residenceDeals.size()];
        double[][] x = new double[residenceDeals.size()][5];
        int i = 0;

        for (Advertisement a : residenceDeals)
            for (PurchaseOrder p : a.getPurchaseOrderList())
                if (p.getStatus().equals("Validated")) {
                    y[i] = Double.parseDouble(p.getPrice());
                    x[i][0] = Double.parseDouble(a.getRequest().getProperty().getArea());
                    x[i][1] = Double.parseDouble(a.getRequest().getProperty().getCityCenter());
                    x[i][2] = Double.parseDouble(a.getRequest().getNumOfBedrooms());
                    x[i][3] = Double.parseDouble(a.getRequest().getNumOfBathrooms());
                    x[i][4] = Double.parseDouble(a.getRequest().getNumOfParkingSpaces());
                    i++;
                }
        return new Pair<>(y, x);
    }

    /**
     * Retrieves a list of advertisements associated with the specified agent's email.
     *
     * @param email The email of the agent.
     * @return A list of advertisements associated with the agent's email.
     */
    public ArrayList<Advertisement> getAgentAdvertisements(String email) {
        ArrayList<Advertisement> agentAdvertisements = new ArrayList<>();

        for (Advertisement a : this.copy())
            if (a.getRequest().getAgent().hasEmail(email) && a.getStatus().equals("ToBeValidated"))
                agentAdvertisements.add(a);
        return agentAdvertisements;
    }

    /**
     * Retrieves a list of visit requests within the specified date range.
     *
     * @param list    The list of advertisements.
     * @param dStart  The start date.
     * @param dEnd    The end date.
     * @return A list of visit requests within the specified date range.
     */
    public ArrayList<VisitRequest> getVisitRequestList(ArrayList<Advertisement> list, LocalDate dStart, LocalDate dEnd) {
        ArrayList<VisitRequest> visitRequestsList = new ArrayList<>();

        for (Advertisement a : list)
            for (VisitRequest v : a.getVisitRequestList()) {
                LocalDate visitDate = LocalDate.parse(v.getDateOfVisit());
                if (visitDate.isBefore(dEnd) && visitDate.isAfter(dStart) && v.getStatus().equals("ToBeValidated"))
                    visitRequestsList.add(v);
            }
        return visitRequestsList;
    }

    /**
     * Adds a purchase order to an advertisement.
     *
     * @param purchaseOrder The purchase order to add.
     * @param advertisement The advertisement to associate the purchase order with.
     * @return true if the purchase order is successfully added, false otherwise.
     */
    public boolean addPurchaseOrder(PurchaseOrder purchaseOrder, Advertisement advertisement) {
        return advertisement.addPurchaseOrder(purchaseOrder);
    }

    /**
     * Retrieves the number of properties with the specified designation.
     *
     * @param designation The designation to count.
     * @return The number of properties with the specified designation.
     */
    public int getNumPropertiesByDesignation(String designation) {
        int i = 0;

        for (Advertisement a : this.copy())
            if (a.getStore().hasSameDesignation(designation))
                i++;
        return i;
    }

    /**
     * Retrieves the advertisement associated with the specified visit request.
     *
     * @param visitRequest The visit request to match.
     * @return The advertisement associated with the visit request, or null if not found.
     */
    public Advertisement getVisitRequestAdvertisement(VisitRequest visitRequest) {
        ArrayList<Advertisement> advertisements = copy();
        for (Advertisement a : advertisements) {
            ArrayList<VisitRequest> visitRequests = a.getVisitRequestList();
            for (VisitRequest v : visitRequests)
                if (v.equals(visitRequest))
                    return a;
        }
        return null;
    }
}