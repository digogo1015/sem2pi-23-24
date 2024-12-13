package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.database.Database;
import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.utils.Files;
import pt.ipp.isep.dei.esoft.project.utils.InvalidArguments;
import pt.ipp.isep.dei.esoft.project.utils.Print;

import java.util.ArrayList;

import static pt.ipp.isep.dei.esoft.project.utils.Files.*;

/**
 * The Bootstrap class is responsible for initializing and setting up the application by adding initial data,
 * retrieving data from SER (Serialized) files, setting up users, and adding roles.
 * It implements the Runnable interface to enable running the bootstrap process in a separate thread.
 */
public class Bootstrap implements Runnable {
    private Repositories repositories;
    private StoreRepository storeRepository;
    private ClientRepository clientRepository;
    private EmployeeRepository employeeRepository;
    private RequestRepository requestRepository;
    private AdvertisementRepository advertisementRepository;
    private RoleRepository roleRepository;
    private AuthenticationRepository authenticationRepository;
    private ManageUsers managerUsers;

    /**
     * Constructs a Bootstrap object and initializes the repositories and managerUsers.
     */
    public Bootstrap() {
        this.repositories = Repositories.getInstance();
        this.storeRepository = repositories.getStoreRepository();
        this.clientRepository = repositories.getClientRepository();
        this.employeeRepository = repositories.getEmployeeRepository();
        this.requestRepository = repositories.getRequestRepository();
        this.advertisementRepository = repositories.getAdvertisementRepository();
        this.roleRepository = repositories.getRoleRepository();
        this.authenticationRepository = repositories.getAuthenticationRepository();
        this.managerUsers = new ManageUsers();
    }

    /**
     * Runs the bootstrap process by calling the necessary methods.
     */
    public void run() {
        addRoles();
        getDataFromSER();
        setUpUsers();
        setUpInitial();
    }

    /**
     * Sets up the initial configuration by adding headquarters, system administrator, and legacy setup.
     */
    private void setUpInitial() {
        Store HQ = addHeadQuarters();
        addSystemAdministrator(HQ);
        addLegacySetup(HQ);
    }

    /**
     * Adds the legacy setup, including legacy clients and agents, to the headquarters.
     */
    private void addLegacySetup(Store HQ) {
        addClientLegacy();
        addAgentLegacy(HQ);
    }

    /**
     * Retrieves data from SER (Serialized) files and adds it to the corresponding repositories.
     */
    private void getDataFromSER() {
        addStoresSER();
        addClientsSER();
        addEmployeesSER();
        addRequestsSER();
        addAdvertisementsSER();
    }

    /**
     * Adds stores from the SER (Serialized) file to the store repository.
     */
    private void addStoresSER() {
        ArrayList<Store> list = (ArrayList<Store>) read(Database.storesSER);

        if (list != null) {
            for (Store store : list) {
                storeRepository.addStore(store);
            }
        }
    }

    /**
     * Adds clients from the SER (Serialized) file to the client repository.
     */
    private void addClientsSER() {
        ArrayList<Client> list = (ArrayList<Client>) read(Database.clientsSER);

        if (list != null) {
            for (Client client : list) {
                clientRepository.addClient(client);
            }
        }
    }

    /**
     * Adds employees from the SER (Serialized) file to the employee repository.
     */
    private void addEmployeesSER() {
        ArrayList<Employee> list = (ArrayList<Employee>) read(Database.employeesSER);

        if (list != null) {
            for (Employee employee : list) {
                employeeRepository.addEmployee(employee);
            }
        }
    }

    /**
     * Adds requests from the SER (Serialized) file to the request repository.
     */
    private void addRequestsSER() {
        ArrayList<Request> list = (ArrayList<Request>) read(Database.requestsSER);

        if (list != null) {
            for (Request request : list) {
                requestRepository.addRequest(request);
            }
        }
    }

    /**
     * Adds advertisements from the SER (Serialized) file to the advertisement repository.
     */
    private void addAdvertisementsSER() {
        ArrayList<Advertisement> list = (ArrayList<Advertisement>) read(Database.advertisementsSER);

        if (list != null) {
            for (Advertisement advertisement : list) {
                advertisementRepository.addAdvertisement(advertisement);
            }
        }
    }

    /**
     * Sets up users by reading user data from a CSV file and adding them to the authentication repository.
     */
    private void setUpUsers() {
        try {
            ArrayList<String[]> users = Files.readCSVFile(Database.usersCSV);

            for (String[] user : users) {
                Person person = managerUsers.getPersonByEmail(user[0]);

                if (person != null) {
                    authenticationRepository.addUserWithRole(person.getPerson().getName(), user[0], user[1], person.getRole());
                }
            }
        } catch (Exception ignored) {
        }
    }

    /**
     * Adds predefined roles to the role repository and assigns them to users in the authentication repository.
     */
    private void addRoles() {
        roleRepository.addRole(Role.createRole(AuthenticationController.ROLE_SYSTEM_ADMINISTRATOR));
        roleRepository.addRole(Role.createRole(AuthenticationController.ROLE_NETWORK_MANAGER));
        roleRepository.addRole(Role.createRole(AuthenticationController.ROLE_STORE_MANAGER));
        roleRepository.addRole(Role.createRole(AuthenticationController.ROLE_AGENT));
        roleRepository.addRole(Role.createRole(AuthenticationController.ROLE_CLIENT));

        ArrayList<Role> rolesRepository = roleRepository.copy();

        for (Role role : rolesRepository) {
            authenticationRepository.addUserRole(role.getDesignation(), role.getDesignation());
        }
    }

    /**
     * Adds a system administrator to the specified store.
     */
    private void addSystemAdministrator(Store store) {
        try {
            String name = "SysAdmin";
            String phoneNum = "400-400-4000";
            String email = "sysadmin@this.app";
            String ppCard = "999999999";
            String ssCard = "400-40-4000";
            String password = "admin";

            Employee sysAdmin = SystemAdministrator.createEmployee(EmployeeInfo.createEmployee(PersonInfo.createPerson(name, phoneNum,
                            email, Address.createAddress("404 Not Found St.", "Matosinhos", "Porto",
                                    "Portugal"), PassportCard.createCard(ppCard), SocialSecurityCard.createCard(ssCard)),
                    store, roleRepository.getRoleByDesignation("SYSTEM ADMINISTRATOR")));

            employeeRepository.addEmployee(sysAdmin);

            Database.sendEmail(email, password);
            Database.saveUsers(email, password);

            authenticationRepository.addUserWithRole(name, email, password, "SYSTEM ADMINISTRATOR");
        } catch (InvalidArguments ia) {
            Print.text(ia.getMessage());
        }
    }

    /**
     * Creates and adds the headquarters store.
     *
     * @return The headquarters store.
     */
    private Store addHeadQuarters() {
        Store store = null;

        try {
            String storeDesignation = "HQ";
            String email = "HQ@this.app";
            String phoneNumber = "100-100-1000";

            store = Store.createStore(Address.createAddress("Debaixo da Ponte", "Ponte de Lima",
                    "Viana do Castelo", "Portugal"), storeDesignation, email, phoneNumber);
        } catch (InvalidArguments ia) {
            Print.text(ia.getMessage());
        }
        return store;
    }

    /**
     * Adds a legacy agent to the specified store.
     */
    private void addAgentLegacy(Store store) {
        try {
            String name = "LegacyAgent";
            String phoneNum = "800-800-8000";
            String email = "legacy@agent.app";
            String ppCard = "000000001";
            String ssCard = "300-30-3000";
            String password = "agent";

            Employee legacyAgent = Agent.createEmployee(EmployeeInfo.createEmployee(PersonInfo.createPerson(name, phoneNum, email,
                            Address.createAddress("Ali", "Acola", "Aseguire", "Absurdabemte longe daqui"),
                            PassportCard.createCard(ppCard), SocialSecurityCard.createCard(ssCard)),
                    store, roleRepository.getRoleByDesignation("AGENT")));

            employeeRepository.addEmployee(legacyAgent);

            Database.sendEmail(email, password);
            Database.saveUsers(email, password);

            authenticationRepository.addUserWithRole(name, email, password, "AGENT");
        } catch (InvalidArguments ia) {
            Print.text(ia.getMessage());
        }
    }

    /**
     * Adds a legacy client.
     */
    private void addClientLegacy() {
        try {
            String name = "LegacyClient";
            String phoneNum = "900-900-9000";
            String email = "legacy@client.app";
            String ppCard = "000000002";
            String ssCard = "200-20-2000";
            String password = "client";

            Client legacyClient = Client.createClient(PersonInfo.createPerson(name, phoneNum, email,
                    Address.createAddress("Ali", "Acola", "Aseguire", "Absurdabemte longe daqui"),
                    PassportCard.createCard(ppCard), SocialSecurityCard.createCard(ssCard)));

            clientRepository.addClient(legacyClient);

            Database.sendEmail(email, password);
            Database.saveUsers(email, password);

            authenticationRepository.addUserWithRole(name, email, password, "CLIENT");
        } catch (InvalidArguments ia) {
            Print.text(ia.getMessage());
        }
    }
}
