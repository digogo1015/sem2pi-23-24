package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;

import static org.junit.jupiter.api.Assertions.*;

class FiltersTest {
    private String typeOfProperty;
    private String typeOfBusiness;
    private String numberOfBedrooms;
    private String sortingCriteria;
    private Filters filters;
    @BeforeEach
    public void setUp() {
        typeOfProperty = "House";
        typeOfBusiness = "Rent";
        numberOfBedrooms = "2";
        sortingCriteria = "testCriteria";
        filters = new Filters(typeOfProperty,typeOfBusiness,numberOfBedrooms,sortingCriteria);
    }

    @Test
    void testCommission() {
        Filters filters = new Filters(typeOfProperty,typeOfBusiness,numberOfBedrooms,sortingCriteria);
        assertNotNull(filters);
    }

    @Test
    void testCreateFilters() throws InvalidArguments {
        Filters filters = Filters.createFilters(typeOfProperty,typeOfBusiness,numberOfBedrooms,sortingCriteria);
        Assertions.assertNotNull(filters);
    }

    @Test
    void getTypeOfProperty() {assertEquals(typeOfProperty, filters.getTypeOfProperty());
    }

    @Test
    void getTypeOfBusiness() {assertEquals(typeOfBusiness, filters.getTypeOfBusiness());
    }

    @Test
    void getNumberOfBedrooms() {assertEquals(numberOfBedrooms, filters.getNumberOfBedrooms());
    }

    @Test
    void getSortingCriteria() {assertEquals(sortingCriteria, filters.getSortingCriteria());
    }

    @Test
    void testToString() {
        String expectedString = "\nProperty Type: House" +
                "\nBusiness Type: Rent" +
                "\nSorting Criteria: testCriteria";
        assertEquals(expectedString, filters.toString());
    }
}