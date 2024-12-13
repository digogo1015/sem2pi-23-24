package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;

import static org.junit.jupiter.api.Assertions.*;

public class AgentTest {
    private Agent agent;
    private EmployeeInfo employeeInfo;
    private PersonInfo personInfo;
    private Store store;

    @BeforeEach
    public void setUp() {
        personInfo = new PersonInfo("testName", "123456789", "testmail@mail.com", new Address("Street", "City", "District", "State"), new PassportCard("123456789"), new SocialSecurityCard("123456789"));
        store = new Store(new Address("Street", "City", "District", "State"), "testDesignation", "testemail@mail.com", "123456789");
        employeeInfo = new EmployeeInfo(personInfo,store,new Role("Agent"));
        agent=new Agent(employeeInfo);
    }
    @Test
    void testEmployee() {
        Agent agent = new Agent(employeeInfo);
        assertNotNull(agent);
    }

    @Test
    public void testCreateEmployee() throws InvalidArguments {
        Agent agent = (Agent) Agent.createEmployee(employeeInfo);
        Assertions.assertNotNull(agent);
    }

    @Test
    void hasRole() {
        boolean hasRole = agent.hasRole("Agent");
        assertTrue(hasRole);
        hasRole = agent.hasRole("CEO");
        assertFalse(hasRole);
    }

    @Test
    void worksInStore() {
        boolean worksInStore = agent.worksInStore("testDesignation");
        assertTrue(worksInStore);
        worksInStore = agent.worksInStore("fakeDesignation");
        assertFalse(worksInStore);
    }

    @Test
    void getPerson() {
        assertEquals(personInfo, agent.getPerson());
    }

    @Test
    void getStore() {
        assertEquals(store, agent.getStore());
    }

    @Test
    void hasEmail() {
        boolean hasEmail = agent.hasEmail("testmail@mail.com");
        assertTrue(hasEmail);
        hasEmail = agent.hasEmail("fakemail@mail.com");
        assertFalse(hasEmail);
    }

    @Test
    void testToString() {
        String expectedString = "Agent: \n" + "Name: testName\n" +
                "Phone Number: 123456789\n" +
                "Email: testmail@mail.com\n" + "Address: Address: Street,City,District,State\n" +
                "Passport Card: PassportCard: 123456789\n" +
                "Social Security Card: SocialSecurityCard: 123456789";
        assertEquals(expectedString, agent.toString());
    }

    @Test
    void getRole() {
        assertEquals("Agent", agent.getRole());
    }

    @Test
    void testEquals() {
        PersonInfo newPersonInfo = new PersonInfo("testName2", "123456790", "testmail2@mail.com", new Address("Street", "City", "District", "State"), new PassportCard("123456790"), new SocialSecurityCard("123456790"));
        EmployeeInfo newEmployeeInfo = new EmployeeInfo(newPersonInfo,store,new Role("Agent"));
        Agent agent2 = new Agent(newEmployeeInfo);
        Agent agent3 = new Agent(employeeInfo);
        assertFalse(agent.equals(agent2));
        assertFalse(agent2.equals(agent));
        assertTrue(agent.equals(agent3));
        assertTrue(agent3.equals(agent));
    }

    @Test
    void testToStringV2() {
        String expectedString = "\n" +
                "Agent: \n" + "Name: testName\n" +
                "Phone Number: 123456789\n" +
                "Email: testmail@mail.com\n" + "Address: Address: Street,City,District,State\n" +
                "Passport Card: PassportCard: 123456789\n" +
                "Social Security Card: SocialSecurityCard: 123456789";
        assertEquals(expectedString, agent.toStringV2());
    }

    @Test
    void testHashCode() {
        Agent agent1 = new Agent(employeeInfo);
        Agent agent2 = new Agent(employeeInfo);
        assertEquals(agent1.hashCode(),agent2.hashCode());
    }
}
