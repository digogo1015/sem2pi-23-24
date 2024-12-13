package pt.ipp.isep.dei.esoft.project.ui;

import com.google.gson.Gson;
import pt.ipp.isep.dei.esoft.project.database.Database;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.utils.Files;

import static pt.ipp.isep.dei.esoft.project.utils.Files.save;

public class SaveSER implements Runnable {
    Repositories repositories;
    StoreRepository storeRepository;
    ClientRepository clientRepository;
    EmployeeRepository employeeRepository;
    RequestRepository requestRepository;
    AdvertisementRepository advertisementRepository;

    public SaveSER() {
        this.repositories = Repositories.getInstance();
        this.storeRepository = repositories.getStoreRepository();
        this.clientRepository = repositories.getClientRepository();
        this.employeeRepository = repositories.getEmployeeRepository();
        this.requestRepository = repositories.getRequestRepository();
        this.advertisementRepository = repositories.getAdvertisementRepository();
    }

    @Override
    public void run() {
        saveSER();
        saveJSON();
    }

    private void saveJSON() {
        saveStoresJSON();
        saveClientsJSON();
        saveEmployeesJSON();
        saveRequestsJSON();
        saveAdvertisementsJSON();
    }

    private void saveStoresJSON() {
        Object object = storeRepository.copy();
        Files.writeToFile(new Gson().toJson(object), Database.storesJSON);
    }

    private void saveClientsJSON() {
        Object object = clientRepository.copy();
        Files.writeToFile(new Gson().toJson(object), Database.clientsJSON);
    }

    private void saveEmployeesJSON() {
        Object object = employeeRepository.copy();
        Files.writeToFile(new Gson().toJson(object), Database.employeesJSON);
    }

    private void saveRequestsJSON() {
        Object object = requestRepository.copy();
        Files.writeToFile(new Gson().toJson(object), Database.requestsJSON);
    }

    private void saveAdvertisementsJSON() {
        Object object = advertisementRepository.copy();
        Files.writeToFile(new Gson().toJson(object), Database.advertisementsJSON);
    }

    private void saveSER() {
        saveStoresSER();
        saveClientsSER();
        saveEmployeesSER();
        saveRequestsSER();
        saveAdvertisementsSER();
    }

    private void saveStoresSER() {
        Object object = storeRepository.copy();
        save(object, Database.storesSER);
    }

    private void saveClientsSER() {
        Object object = clientRepository.copy();
        save(object, Database.clientsSER);
    }

    private void saveEmployeesSER() {
        Object object = employeeRepository.copy();
        save(object, Database.employeesSER);
    }

    private void saveRequestsSER() {
        Object object = requestRepository.copy();
        save(object, Database.requestsSER);
    }

    private void saveAdvertisementsSER() {
        Object object = advertisementRepository.copy();
        save(object, Database.advertisementsSER);
    }
}