package by.balashevich.finalproject.builder;

import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.entity.User;

import java.util.Map;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class UserBuilder {
    private UserBuilder() {
    }

    public static User buildUser(Map<String, Object> userParameters) {
        User.Role role = (User.Role) userParameters.get(ROLE);
        User buildingUser;

        if (role == User.Role.CLIENT) {
            buildingUser = buildClient(userParameters);
        } else {
            buildingUser = new User();
        }

        if (userParameters.containsKey(USER_ID)) {
            buildingUser.setUserId((long) userParameters.get(USER_ID));
        }
        buildingUser.setEmail((String) userParameters.get(EMAIL));
        buildingUser.setRole(role);

        return buildingUser;
    }

    private static Client buildClient(Map<String, Object> clientParameters) {
        Client buildingClient = new Client();

        buildingClient.setFirstName((String) clientParameters.get(FIRST_NAME));
        buildingClient.setSecondName((String) clientParameters.get(SECOND_NAME));
        buildingClient.setDriverLicense((String) clientParameters.get(DRIVER_LICENSE));
        buildingClient.setPhoneNumber((long) clientParameters.get(PHONE_NUMBER));
        buildingClient.setStatus((Client.Status) clientParameters.get(CLIENT_STATUS));

        return buildingClient;
    }
}
