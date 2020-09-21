package by.balashevich.finalproject.factory;

import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.entity.User;

import java.util.Map;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class UserFactory {
    public static User createUser(Map<String, Object> userParameters) {
        User.Role role = (User.Role) userParameters.get(ROLE);
        User createdUser = switch (role){
            case ADMIN -> createAdmin(userParameters);
            case CLIENT -> createClient(userParameters);
            case MANAGER -> createManager(userParameters);
        };

        return createdUser;
    }

    private UserFactory(){}

    private static User createAdmin(Map<String, Object> adminParameters){
        long adminId = (long)adminParameters.get(USER_ID);
        String email = (String)adminParameters.get(EMAIL);
        User.Role role = User.Role.CLIENT;

        return new User(adminId, email, role);
    }

    private static Client createClient(Map<String, Object> clientParameters){
        long clientId = (long)clientParameters.get(USER_ID);
        String email = (String)clientParameters.get(EMAIL);
        User.Role role = User.Role.CLIENT;
        String firstName = (String)clientParameters.get(FIRST_NAME);
        String secondName = (String)clientParameters.get(SECOND_NAME);
        String driverLicense = (String)clientParameters.get(DRIVER_LICENSE);
        long phoneNumber = (long)clientParameters.get(PHONE_NUMBER);
        Client.Status status = (Client.Status)clientParameters.get(STATUS);

        return new Client(clientId, email, firstName, secondName, driverLicense, phoneNumber, status);
    }

    private static User createManager(Map<String, Object> managerParameters){
        return null;
    }
}
