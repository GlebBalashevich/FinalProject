package by.balashevich.finalproject.creator;

import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.entity.ClientStatus;

public class ClientCreator {
//todo this with that class
    public Client createClient(long userId, String login, String firstName, String secondName,
                               String driverLicense, String email, int phoneNumber, ClientStatus status){
        Client client = new Client(userId, login, firstName, secondName, driverLicense, email, phoneNumber, status);

        return client;
    }

    public Client createClient(String login, String firstName, String secondName,
                               String driverLicense, String email, int phoneNumber){
        Client client = new Client(login, firstName, secondName, driverLicense, email, phoneNumber, ClientStatus.PENDING);

        return client;
    }
}
