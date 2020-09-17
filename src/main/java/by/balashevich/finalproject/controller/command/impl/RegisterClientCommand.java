package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.SessionRequestContent;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.PageName;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

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
        UserServiceImpl userService = new UserServiceImpl();
        Map<String, String> clientParameters = new HashMap();
        clientParameters.put(LOGIN_PARAMETER, requestContent.getParameter(LOGIN_PARAMETER));
        clientParameters.put(PASSWORD_PARAMETER, requestContent.getParameter(PASSWORD_PARAMETER));
        clientParameters.put(CONFIRM_PASSWORD_PARAMETER, requestContent.getParameter(CONFIRM_PASSWORD_PARAMETER));
        clientParameters.put(FIRST_NAME_PARAMETER, requestContent.getParameter(FIRST_NAME_PARAMETER));
        clientParameters.put(SECOND_NAME_PARAMETER, requestContent.getParameter(SECOND_NAME_PARAMETER));
        clientParameters.put(DRIVER_LICENSE_PARAMETER, requestContent.getParameter(DRIVER_LICENSE_PARAMETER));
        clientParameters.put(EMAIL_PARAMETER, requestContent.getParameter(EMAIL_PARAMETER));
        clientParameters.put(PHONE_NUMBER_PARAMETER, requestContent.getParameter(PHONE_NUMBER_PARAMETER));
        String page;

        try {
            if (userService.addClient(clientParameters)){
                page = PageName.HOME.getPath();
            } else{
                page = PageName.REGISTER.getPath();
                requestContent.setAttribute("registerParameters", clientParameters);
            }
        } catch (ServiceProjectException e) {
            page = PageName.ERROR.getPath();
            logger.log(Level.ERROR, "An error occurred during client adding", e);
        }

        return page;
    }
}
