package pt.ipp.isep.dei.esoft.project.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.utils.ComparatorCityAlphabeticReverse;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ComparatorStateAlphabeticTest {

    @Test
    @DisplayName("Verify above zero")
    void compare() {
        ComparatorCityAlphabeticReverse cmp = new ComparatorCityAlphabeticReverse();

        Advertisement advertisement1 = new Advertisement("12/04/2023",
                new Request(new House(new PropertyInfo(new Address("Street","AAAA","District",
                        "bbbbb"),"25","25", new ArrayList<>()),
                        new ResidenceInfo("1","1","1",new ArrayList<>())
                        ,true,true,"north"),new Client(new PersonInfo("Name",
                        "123", "email@email.pt",new Address("Street","City",
                        "District", "State"),new PassportCard("123213123"),
                        new SocialSecurityCard("123-12-1231"))),new Agent(new EmployeeInfo(new PersonInfo("Name",
                        "123","email@email.pt", new Address("Street","City",
                        "District", "State"),new PassportCard("123123123"),new
                        SocialSecurityCard("123-12-1231")),new Store(new Address("Street",
                        "City","District", "State"),"Designation","loja@email.pt",
                        "123-123-1231"), new Role("AGENT"))),"12/03/2023",
                        "sale", "10020"),new
                Commission("typeOfcommission","value"));

        Advertisement advertisement2 = new Advertisement("12/04/2023",
                new Request(new House(new PropertyInfo(new Address("Street","BBBB","District",
                        "aaaaa"),"25","25", new ArrayList<>()),
                        new ResidenceInfo("1","1","1",new ArrayList<>())
                        ,true,true,"north"),new Client(new PersonInfo("Name",
                        "123", "email@email.pt",new Address("Street","City",
                        "District", "State"),new PassportCard("123213123"),
                        new SocialSecurityCard("123-12-1231"))),new Agent(new EmployeeInfo(new PersonInfo("Name",
                        "123","email@email.pt", new Address("Street","City",
                        "District", "State"),new PassportCard("123123123"),new
                        SocialSecurityCard("123-12-1231")),new Store(new Address("Street",
                        "City","District", "State"),"Designation","loja@email.pt",
                        "123-123-1231"), new Role("AGENT"))),"12/03/2023",
                        "sale", "10019"),new
                Commission("typeOfcommission","value"));

        int i = cmp.compare(advertisement1, advertisement2);

        boolean valid = i > 0;

        assertTrue(valid);
    }

    @Test
    @DisplayName("Verify equal zero")
    void compare2() {
        ComparatorCityAlphabeticReverse cmp = new ComparatorCityAlphabeticReverse();

        Advertisement advertisement1 = new Advertisement("12/04/2023",
                new Request(new House(new PropertyInfo(new Address("Street","AAAA","District",
                        "aaaaa"),"25","25", new ArrayList<>()),
                        new ResidenceInfo("1","1","1",new ArrayList<>())
                        ,true,true,"north"),new Client(new PersonInfo("Name",
                        "123", "email@email.pt",new Address("Street","City",
                        "District", "State"),new PassportCard("123213123"),
                        new SocialSecurityCard("123-12-1231"))),new Agent(new EmployeeInfo(new PersonInfo("Name",
                        "123","email@email.pt", new Address("Street","City",
                        "District", "State"),new PassportCard("123123123"),new
                        SocialSecurityCard("123-12-1231")),new Store(new Address("Street",
                        "City","District", "State"),"Designation","loja@email.pt",
                        "123-123-1231"), new Role("AGENT"))),"12/03/2023",
                        "sale", "10022"),new
                Commission("typeOfcommission","value"));

        Advertisement advertisement2 = new Advertisement("12/04/2023",
                new Request(new House(new PropertyInfo(new Address("Street","AAAA","District",
                        "aaaaa"),"25","25", new ArrayList<>()),
                        new ResidenceInfo("1","1","1",new ArrayList<>())
                        ,true,true,"north"),new Client(new PersonInfo("Name",
                        "123", "email@email.pt",new Address("Street","City",
                        "District", "State"),new PassportCard("123213123"),
                        new SocialSecurityCard("123-12-1231"))),new Agent(new EmployeeInfo(new PersonInfo("Name",
                        "123","email@email.pt", new Address("Street","City",
                        "District", "State"),new PassportCard("123123123"),new
                        SocialSecurityCard("123-12-1231")),new Store(new Address("Street",
                        "City","District", "State"),"Designation","loja@email.pt",
                        "123-123-1231"), new Role("AGENT"))),"12/03/2023",
                        "sale", "10022"),new
                Commission("typeOfcommission","value"));

        int i = cmp.compare(advertisement1, advertisement2);

        boolean valid = i == 0;

        assertTrue(valid);
    }
    @Test
    @DisplayName("Verify below zero")
    void compare3() {
        ComparatorCityAlphabeticReverse cmp = new ComparatorCityAlphabeticReverse();

        Advertisement advertisement1 = new Advertisement("12/04/2023",
                new Request(new House(new PropertyInfo(new Address("Street","BBBB","District",
                        "aaaa"),"25","25", new ArrayList<>()),
                        new ResidenceInfo("1","1","1",new ArrayList<>())
                        ,true,true,"north"),new Client(new PersonInfo("Name",
                        "123", "email@email.pt",new Address("Street","City",
                        "District", "State"),new PassportCard("123213123"),
                        new SocialSecurityCard("123-12-1231"))),new Agent(new EmployeeInfo(new PersonInfo("Name",
                        "123","email@email.pt", new Address("Street","City",
                        "District", "State"),new PassportCard("123123123"),new
                        SocialSecurityCard("123-12-1231")),new Store(new Address("Street",
                        "City","District", "State"),"Designation","loja@email.pt",
                        "123-123-1231"), new Role("AGENT"))),"12/03/2023",
                        "sale", "10020"),new
                Commission("typeOfcommission","value"));

        Advertisement advertisement2 = new Advertisement("12/04/2023",
                new Request(new House(new PropertyInfo(new Address("Street","AAAA","District",
                        "bbbb"),"25","25", new ArrayList<>()),
                        new ResidenceInfo("1","1","1",new ArrayList<>())
                        ,true,true,"north"),new Client(new PersonInfo("Name",
                        "123", "email@email.pt",new Address("Street","City",
                        "District", "State"),new PassportCard("123213123"),
                        new SocialSecurityCard("123-12-1231"))),new Agent(new EmployeeInfo(new PersonInfo("Name",
                        "123","email@email.pt", new Address("Street","City",
                        "District", "State"),new PassportCard("123123123"),new
                        SocialSecurityCard("123-12-1231")),new Store(new Address("Street",
                        "City","District", "State"),"Designation","loja@email.pt",
                        "123-123-1231"), new Role("AGENT"))),"12/03/2023",
                        "sale", "10040"),new
                Commission("typeOfcommission","value"));

        int i = cmp.compare(advertisement1, advertisement2);

        boolean valid = i < 0;

        assertTrue(valid);
    }
}