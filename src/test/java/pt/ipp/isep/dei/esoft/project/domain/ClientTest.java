package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private PersonInfo personInfo;
    private Client client;
    @BeforeEach
    public void setUp() {
        personInfo = new PersonInfo("testName", "123456789", "testmail@mail.com", new Address("Street", "City", "District", "State"), new PassportCard("123456789"), new SocialSecurityCard("123456789"));
        client = new Client(personInfo);
    }

    @Test
    void testClient() {
        Client client = new Client(personInfo);
        assertNotNull(client);
    }

    @Test
    void testCreateClient() throws InvalidArguments {
        Client client = Client.createClient(personInfo);
        Assertions.assertNotNull(client);
    }

    @Test
    void getPerson() {
        assertEquals(personInfo, client.getPerson());
    }

    @Test
    void getRole() {
        assertEquals("CLIENT",client.getRole());
    }

    @Test
    void testEquals() {
        PersonInfo newPersonInfo = new PersonInfo("testName2", "123456790", "testmail2@mail.com", new Address("Street", "City", "District", "State"), new PassportCard("123456790"), new SocialSecurityCard("123456790"));
        Client client2 = new Client(newPersonInfo);
        Client client3 = new Client(personInfo);
        assertFalse(client.equals(client2));
        assertFalse(client2.equals(client));
        assertTrue(client.equals(client3));
        assertTrue(client3.equals(client));
    }

    @Test
    void hasEmail() {
        boolean hasEmail = client.hasEmail("testmail@mail.com");
        assertTrue(hasEmail);
        hasEmail = client.hasEmail("fakemail@mail.com");
        assertFalse(hasEmail);
    }

    @Test
    void testHashCode() {
        Client client1 = new Client(personInfo);
        Client client2 = new Client(personInfo);
        assertEquals(client1.hashCode(),client2.hashCode());
    }

    @Test
    void testToString() {
        String expectedString = "Client: testName ;123456789 ;testmail@mail.com";
        assertEquals(expectedString, client.toString());
    }
}