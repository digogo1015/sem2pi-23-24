package pt.ipp.isep.dei.esoft.project.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @Test
    void testSendEmailWithMessage() {
        String emailMsg = "Test email message";
        assertTrue(Database.sendEmail(emailMsg));
    }

    @Test
    void testSendEmailWithAddressAndPassword() {
        String emailAddress = "test@example.com";
        String password = "password123";
        assertTrue(Database.sendEmail(emailAddress, password));
    }

    @Test
    void saveUsers() {
        String email = "test@example.com";
        String password = "password123";
        assertTrue(Database.saveUsers(email, password));
    }
}