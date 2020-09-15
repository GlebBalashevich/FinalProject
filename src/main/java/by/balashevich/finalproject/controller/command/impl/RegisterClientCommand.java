package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.SessionRequestContent;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.PageName;
import by.balashevich.finalproject.creator.ClientCreator;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.service.impl.ClientServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegisterClientCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String CONFIRM_PASSWORD_PARAMETER = "confirm_password";
    private static final String FIRST_NAME_PARAMETER = "first_name";
    private static final String SECOND_NAME_PARAMETER = "second_name";
    private static final String DRIVER_LICENSE_PARAMETER = "driver_license";
    private static final String EMAIL_PARAMETER = "email";
    private static final String PHONE_NUMBER_PARAMETER = "phone_number";

    @Override
    public String execute(SessionRequestContent requestContent) {
        ClientCreator clientCreator = new ClientCreator();
        ClientServiceImpl clientService = new ClientServiceImpl();
        String login = requestContent.getParameter(LOGIN_PARAMETER);
        String password = requestContent.getParameter(PASSWORD_PARAMETER);
        String confirmPassword = requestContent.getParameter(CONFIRM_PASSWORD_PARAMETER);
        String firstName = requestContent.getParameter(FIRST_NAME_PARAMETER);
        String secondName = requestContent.getParameter(SECOND_NAME_PARAMETER);
        String driverLicense = requestContent.getParameter(DRIVER_LICENSE_PARAMETER);
        String email = requestContent.getParameter(EMAIL_PARAMETER);
        int phoneNumber = Integer.parseInt(requestContent.getParameter(PHONE_NUMBER_PARAMETER));
        String page;

        Client client = clientCreator.createClient(login, firstName, secondName, driverLicense, email, phoneNumber);
        try {
            if (clientService.add(client, password)){
                page = PageName.HOME.getPath();
            } else{
                page = PageName.REGISTER.getPath();
            }
        } catch (ServiceProjectException e) {
            page = PageName.ERROR.getPath();
            logger.log(Level.ERROR, "An error occurred during client adding", e);
        }

        return page;
    }
}
