package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.database.Database;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AdvertisementRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.StoreRepository;

import pt.ipp.isep.dei.esoft.project.utils.ExceptionHandler;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;
import pt.ipp.isep.dei.esoft.project.utils.Print;

import java.util.ArrayList;
import java.util.HashMap;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static pt.ipp.isep.dei.esoft.project.utils.Files.readCSVFile;

/**
 * The ImportFileController class is a controller responsible for handling file import operations
 * in a divide stores application.
 */
public class ImportFileController {

    private Repositories repositories;
    private AdvertisementRepository advertisementRepository;
    private StoreRepository storeRepository;

    /**
     * Constructs a new instance of ImportFileController.
     * Initializes the repositories and sets the advertisementRepository and storeRepository
     * based on the repositories instance.
     */
    public ImportFileController() {
        this.repositories = Repositories.getInstance();
        this.advertisementRepository = repositories.getAdvertisementRepository();
        this.storeRepository = repositories.getStoreRepository();
    }

    /**
     * Retrieves data from a CSV file and sets up the data accordingly.
     *
     * @param name The name of the CSV file to retrieve data from.
     */
    public void getDataFromFile(String name) {
        try {
            ArrayList<String[]> data = readCSVFile(Database.legacyPath + name);

            try {
                setupData(data);
                Print.text("All went Well");
            } catch (Exception e) {
                Print.text("Invalid data");
            }
        } catch (Exception e) {
            ExceptionHandler.ioException(e);
        }
    }

    /**
     * Sets up data based on the provided ArrayList of CSV data.
     *
     * @param data The ArrayList containing the CSV data.
     */
    public void setupData(ArrayList<String[]> data) {
        String[] csvLabels = data.remove(0);

        String[] labels = {"sid", "owner_name", "owner_passportNum", "owner_TIN(SSN)", "owner_email", "owner_phone", "property_type",
                "property_area(square feet)", "property_location", "property_distanceFromCenter (miles)",
                "property_numberBedrooms", "property_numberBathrooms", "property_pnumParking", "property_centralHeating",
                "property_airconditioned", "property_basement", "property_loft", "property_sunExposure",
                "property_requested_sale_rent_price", "property_sale_rent_price (USD)", "commission(%)",
                "contract_duration(months)", "property_dateAnnounceRequest", "property_dateofSale", "type_business",
                "store_ID", "store_name", "store_location", "store_phonenumber", "store_emailAddress"};

        HashMap<String, Integer> csvLabelsMap = new HashMap<>();

        for (int i = 0; i < csvLabels.length; i++)
            csvLabelsMap.put(csvLabels[i], i);

        for (String[] line : data) {
            try {
                // sid ignored (0)
                String OwnerName = line[csvLabelsMap.get(labels[1])];
                String OwnerPPCard = line[csvLabelsMap.get(labels[2])];
                String OwnerSSCard = line[csvLabelsMap.get(labels[3])];
                String OwnerEmail = line[csvLabelsMap.get(labels[4])];
                String OwnerPhoneNum = line[csvLabelsMap.get(labels[5])];
                String TypeOfProperty = line[csvLabelsMap.get(labels[6])];
                String Area = line[csvLabelsMap.get(labels[7])];
                String PropertyAddress = line[csvLabelsMap.get(labels[8])];
                String DistCityCenter = line[csvLabelsMap.get(labels[9])];
                String NumberOfRooms = line[csvLabelsMap.get(labels[10])];
                String NumberOfBaths = line[csvLabelsMap.get(labels[11])];
                String NumOFParkingSpaces = line[csvLabelsMap.get(labels[12])];
                // central heating ignored (13)
                // air conditioning ignored (14)
                String Basement = line[csvLabelsMap.get(labels[15])];
                String Loft = line[csvLabelsMap.get(labels[16])];
                String SunExposure = line[csvLabelsMap.get(labels[17])];
                String Price = line[csvLabelsMap.get(labels[18])];
                String PriceBuyer = line[csvLabelsMap.get(labels[19])];
                String CommissionValue = line[csvLabelsMap.get(labels[20])];
                // Contract duration (21)
                String DateOfRequest = line[csvLabelsMap.get(labels[22])];
                String DateOfSale = line[csvLabelsMap.get(labels[23])];
                String TypeOfBusiness = capitalize(line[csvLabelsMap.get(labels[24])]);
                // store id (25)
                String StoreDesignation = line[csvLabelsMap.get(labels[26])];
                String StoreLocation = line[csvLabelsMap.get(labels[27])];
                String StorePhoneNum = line[csvLabelsMap.get(labels[28])];
                String StoreEmail = line[csvLabelsMap.get(labels[29])];

                Employee agent = Repositories.getInstance().getEmployeeRepository().getEmployeeByEmail("legacy@agent.app");

                Client owner = Client.createClient(PersonInfo.createPerson(OwnerName, OwnerPhoneNum, OwnerEmail,
                        Address.splitLocation(PropertyAddress), PassportCard.createCard(OwnerPPCard),
                        SocialSecurityCard.createCard(OwnerSSCard)));

                PropertyInfo propertyInfo = new PropertyInfo(Address.splitLocation(PropertyAddress), Area, DistCityCenter, new ArrayList<>());
                Property property = null;

                switch (TypeOfProperty) {
                    case "house": {
                        boolean loft = Boolean.parseBoolean(Loft);
                        boolean basement = Boolean.parseBoolean(Basement);
                        ResidenceInfo residenceInfo = new ResidenceInfo(NumberOfRooms, NumberOfBaths, NumOFParkingSpaces, new ArrayList<>());
                        property = House.createProperty(propertyInfo, residenceInfo, loft, basement, SunExposure);
                        break;
                    }
                    case "apartment": {
                        ResidenceInfo residenceInfo = new ResidenceInfo(NumberOfRooms, NumberOfBaths, NumOFParkingSpaces, new ArrayList<>());
                        property = Apartment.createProperty(propertyInfo, residenceInfo);
                        break;
                    }
                    case "land":
                        property = Land.createProperty(propertyInfo);
                        break;
                }

                Request request = Request.createRequest(property, owner, agent, DateOfRequest, TypeOfBusiness, Price);

                Commission commission = Commission.createCommission("Percentage", CommissionValue);

                Store store = Store.createStore(Address.splitLocation(StoreLocation), StoreDesignation, StoreEmail, StorePhoneNum);

                Advertisement advertisement = Advertisement.createAdvertisement(DateOfRequest, request, commission, store);

                Client buyer = Repositories.getInstance().getClientRepository().getClientByEmail("legacy@client.app");

                PurchaseOrder purchaseOrder = PurchaseOrder.createPurchaseOrder(buyer, DateOfSale, PriceBuyer);

                advertisement.addPurchaseOrder(purchaseOrder);
                advertisement.clearPurchaseOrders(purchaseOrder);

                storeRepository.addStore(store);
                advertisementRepository.addAdvertisement(advertisement);
            } catch (InvalidArguments ia) {
                Print.text(ia.getMessage());
            }
        }
    }

}
