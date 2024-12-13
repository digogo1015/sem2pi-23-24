package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.database.Database;
import pt.ipp.isep.dei.esoft.project.domain.Client;
import pt.ipp.isep.dei.esoft.project.domain.Employee;

import java.util.ArrayList;
import java.util.List;

public class ClientRepository {
    private List<Client> clientList = new ArrayList<>();

    public boolean addClient(Client client) {
        return validate(client) && clientList.add(client);
    }

    public ArrayList<Client> copy() {
        if (clientList != null)
            return new ArrayList<>(clientList);
        return null;
    }

    public boolean validate(Client client) {
        for (Client c : this.copy())
            if (c.equals(client))
                return false;
        return true;
    }

    public Client getClientByEmail(String email) {
        for (Client c : this.copy())
            if (c.hasEmail(email))
                return c;
        return null;
    }

    public boolean registerClient(Client client, String password, AuthenticationRepository authenticationRepository) {
        return authenticationRepository.addUserWithRole(client.getPerson().getName(),
                client.getPerson().getEmail(), password, "CLIENT") && addClient(client) &&
                Database.saveUsers(client.getPerson().getEmail(), password);
    }
}