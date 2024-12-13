package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeInfoTest {
    private EmployeeInfo employeeInfo;
    private PersonInfo personInfo;
    private Store store;

    @BeforeEach
    public void setUp() {
        personInfo = new PersonInfo("testName", "123456789", "testmail@mail.com", new Address("Street", "City", "District", "State"), new PassportCard("123456789"), new SocialSecurityCard("123456789"));
        store = new Store(new Address("Street", "City", "District", "State"), "testDesignation", "testemail@mail.com", "123456789");
        employeeInfo = new EmployeeInfo(personInfo,store,new Role("Agent"));
    }


    @Test
    void testEmployee() {
        EmployeeInfo employeeInfo = new EmployeeInfo(personInfo,store,new Role("Agent"));
        assertNotNull(employeeInfo);
    }

    @Test
    void testCreateEmployee() throws InvalidArguments {
        EmployeeInfo employee = EmployeeInfo.createEmployee(personInfo,store,new Role("Agent"));
        Assertions.assertNotNull(employee);
    }

    @Test
    void getStore() {
        assertEquals(store, employeeInfo.getStore());
    }

    @Test
    void getPerson() {
        assertEquals(personInfo, employeeInfo.getPerson());
    }

    @Test
    void getRole() {
        assertEquals(new Role("Agent"), employeeInfo.getRole());
    }

    @Test
    void hasRole() {
        boolean hasRole = employeeInfo.hasRole("Agent");
        assertTrue(hasRole);
        hasRole = employeeInfo.hasRole("CEO");
        assertFalse(hasRole);
    }

    @Test
    void worksInStore() {
        boolean worksInStore = employeeInfo.worksInStore("testDesignation");
        assertTrue(worksInStore);
        worksInStore = employeeInfo.worksInStore("fakeDesignation");
        assertFalse(worksInStore);
    }

    @Test
    void testEquals() {
        PersonInfo newPersonInfo = new PersonInfo("testName2", "123456790", "testmail2@mail.com", new Address("Street", "City", "District", "State"), new PassportCard("123456790"), new SocialSecurityCard("123456790"));
        EmployeeInfo employeeInfo2 = new EmployeeInfo(newPersonInfo,store,new Role("Agent"));
        EmployeeInfo employeeInfo3 = new EmployeeInfo(personInfo,store,new Role("Agent"));
        assertFalse(employeeInfo.equals(employeeInfo2));
        assertFalse(employeeInfo2.equals(employeeInfo));
        assertTrue(employeeInfo.equals(employeeInfo3));
        assertTrue(employeeInfo3.equals(employeeInfo));
    }

    @Test
    void testHashCode() {
        EmployeeInfo employeeInfo1 = employeeInfo;
        EmployeeInfo employeeInfo2 = employeeInfo;
        assertEquals(employeeInfo1.hashCode(),employeeInfo2.hashCode());
    }

    @Test
    void testToString() {
        String expectedString = "\nName: testName" +
                "\nPhone Number: 123456789" +
                "\nEmail: testmail@mail.com" +
                "\nAddress: Address: Street,City,District,State"  +
                "\nPassport Card: PassportCard: 123456789" +
                "\nSocial Security Card: SocialSecurityCard: 123456789";
        assertEquals(expectedString, employeeInfo.toString());
    }
}