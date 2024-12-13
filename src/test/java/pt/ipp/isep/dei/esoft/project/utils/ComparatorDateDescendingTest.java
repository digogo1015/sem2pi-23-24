package pt.ipp.isep.dei.esoft.project.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.utils.ComparatorDateDescending;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ComparatorDateDescendingTest {

    @Test
    @DisplayName("Verify above zero")
    void compare() {
        ComparatorDateDescending cmp = new ComparatorDateDescending();

        Request request1 = new Request(new House(new PropertyInfo(new Address("Street","AAAA","District",
                "State"),"25","25", new ArrayList<>()),
                new ResidenceInfo("1","1","1",new ArrayList<>())
                ,true,true,"north"),new Client(new PersonInfo("Name",
                "123", "email@email.pt",new Address("Street","City",
                "District", "State"),new PassportCard("123213123"),
                new SocialSecurityCard("123-12-1231"))),new Agent(new EmployeeInfo(new PersonInfo("Name",
                "123","email@email.pt", new Address("Street","City",
                "District", "State"),new PassportCard("123123123"),new
                SocialSecurityCard("123-12-1231")),new Store(new Address("Street",
                "City","District", "State"),"Designation","loja@email.pt",
                "123-123-1231"), new Role("AGENT"))),"12/12/2023, 12:12:12",
                "sale", "10022");

        Request request2 = new Request(new House(new PropertyInfo(new Address("Street","AAAA","District",
                "State"),"25","25", new ArrayList<>()),
                new ResidenceInfo("1","1","1",new ArrayList<>())
                ,true,true,"north"),new Client(new PersonInfo("Name",
                "123", "email@email.pt",new Address("Street","City",
                "District", "State"),new PassportCard("123213123"),
                new SocialSecurityCard("123-12-1231"))),new Agent(new EmployeeInfo(new PersonInfo("Name",
                "123","email@email.pt", new Address("Street","City",
                "District", "State"),new PassportCard("123123123"),new
                SocialSecurityCard("123-12-1231")),new Store(new Address("Street",
                "City","District", "State"),"Designation","loja@email.pt",
                "123-123-1231"), new Role("AGENT"))),"13/12/2023, 12:12:12",
                "sale", "10022");

        int i = cmp.compare(request1, request2);

        boolean valid = i > 0;

        assertTrue(valid);
    }

    @Test
    @DisplayName("Verify equal zero")
    void compare2() {
        ComparatorDateDescending cmp = new ComparatorDateDescending();

        Request request1 = new Request(new House(new PropertyInfo(new Address("Street","AAAA","District",
                "State"),"25","25", new ArrayList<>()),
                new ResidenceInfo("1","1","1",new ArrayList<>())
                ,true,true,"north"),new Client(new PersonInfo("Name",
                "123", "email@email.pt",new Address("Street","City",
                "District", "State"),new PassportCard("123213123"),
                new SocialSecurityCard("123-12-1231"))),new Agent(new EmployeeInfo(new PersonInfo("Name",
                "123","email@email.pt", new Address("Street","City",
                "District", "State"),new PassportCard("123123123"),new
                SocialSecurityCard("123-12-1231")),new Store(new Address("Street",
                "City","District", "State"),"Designation","loja@email.pt",
                "123-123-1231"), new Role("AGENT"))),"12/12/2023, 12:12:12",
                "sale", "10022");

        Request request2 = new Request(new House(new PropertyInfo(new Address("Street","AAAA","District",
                "State"),"25","25", new ArrayList<>()),
                new ResidenceInfo("1","1","1",new ArrayList<>())
                ,true,true,"north"),new Client(new PersonInfo("Name",
                "123", "email@email.pt",new Address("Street","City",
                "District", "State"),new PassportCard("123213123"),
                new SocialSecurityCard("123-12-1231"))),new Agent(new EmployeeInfo(new PersonInfo("Name",
                "123","email@email.pt", new Address("Street","City",
                "District", "State"),new PassportCard("123123123"),new
                SocialSecurityCard("123-12-1231")),new Store(new Address("Street",
                "City","District", "State"),"Designation","loja@email.pt",
                "123-123-1231"), new Role("AGENT"))),"12/12/2023, 12:12:12",
                "sale", "10022");

        int i = cmp.compare(request1, request2);

        boolean valid = i == 0;

        assertTrue(valid);
    }
    @Test
    @DisplayName("Verify below zero")
    void compare3() {
        ComparatorDateDescending cmp = new ComparatorDateDescending();

        Request request1 = new Request(new House(new PropertyInfo(new Address("Street","AAAA","District",
                "State"),"25","25", new ArrayList<>()),
                new ResidenceInfo("1","1","1",new ArrayList<>())
                ,true,true,"north"),new Client(new PersonInfo("Name",
                "123", "email@email.pt",new Address("Street","City",
                "District", "State"),new PassportCard("123213123"),
                new SocialSecurityCard("123-12-1231"))),new Agent(new EmployeeInfo(new PersonInfo("Name",
                "123","email@email.pt", new Address("Street","City",
                "District", "State"),new PassportCard("123123123"),new
                SocialSecurityCard("123-12-1231")),new Store(new Address("Street",
                "City","District", "State"),"Designation","loja@email.pt",
                "123-123-1231"), new Role("AGENT"))),"13/12/2023, 12:12:12",
                "sale", "10022");

        Request request2 = new Request(new House(new PropertyInfo(new Address("Street","AAAA","District",
                "State"),"25","25", new ArrayList<>()),
                new ResidenceInfo("1","1","1",new ArrayList<>())
                ,true,true,"north"),new Client(new PersonInfo("Name",
                "123", "email@email.pt",new Address("Street","City",
                "District", "State"),new PassportCard("123213123"),
                new SocialSecurityCard("123-12-1231"))),new Agent(new EmployeeInfo(new PersonInfo("Name",
                "123","email@email.pt", new Address("Street","City",
                "District", "State"),new PassportCard("123123123"),new
                SocialSecurityCard("123-12-1231")),new Store(new Address("Street",
                "City","District", "State"),"Designation","loja@email.pt",
                "123-123-1231"), new Role("AGENT"))),"11/12/2023, 12:12:12",
                "sale", "10022");

        int i = cmp.compare(request1, request2);

        boolean valid = i < 0;

        assertTrue(valid);
    }

}