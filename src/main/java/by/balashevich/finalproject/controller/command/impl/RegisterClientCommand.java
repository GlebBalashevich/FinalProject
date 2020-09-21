package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.SessionRequestContent;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.PageName;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.User;
import by.balashevich.finalproject.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class RegisterClientCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent requestContent) {
        UserServiceImpl userService = new UserServiceImpl();
        Map<String, String> clientParameters = new HashMap();
        clientParameters.put(EMAIL, requestContent.getParameter(EMAIL));
        clientParameters.put(PASSWORD, requestContent.getParameter(PASSWORD));
        clientParameters.put(CONFIRM_PASSWORD, requestContent.getParameter(CONFIRM_PASSWORD));
        clientParameters.put(FIRST_NAME, requestContent.getParameter(FIRST_NAME));
        clientParameters.put(SECOND_NAME, requestContent.getParameter(SECOND_NAME));
        clientParameters.put(DRIVER_LICENSE, requestContent.getParameter(DRIVER_LICENSE));
        clientParameters.put(PHONE_NUMBER, requestContent.getParameter(PHONE_NUMBER));
        HttpSession session;
        String page;

        try {
            if (userService.existUser(clientParameters.get(EMAIL))) {
                if (userService.add(clientParameters)) {
                    session = requestContent.getSession();
                    session.setAttribute(ROLE, User.Role.CLIENT.name());
                    page = PageName.HOME.getPath();

                } else {
                    page = PageName.REGISTER.getPath();
                    requestContent.setAttribute("registerParameters", clientParameters);
                }
            } else{
                page = PageName.REGISTER.getPath();
                requestContent.setAttribute("message", null); // TODO: 21.09.2020  define message
            }
        } catch (ServiceProjectException e) {
            page = PageName.ERROR.getPath();
            logger.log(Level.ERROR, "An error occurred during client adding", e);
        }

        return page;
    }
}
