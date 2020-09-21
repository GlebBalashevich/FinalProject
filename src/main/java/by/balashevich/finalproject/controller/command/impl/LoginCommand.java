package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.PageName;
import by.balashevich.finalproject.controller.SessionRequestContent;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.entity.User;
import by.balashevich.finalproject.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class LoginCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String MESSAGE_ATTRIBUTE = "message";
    private static final String WELCOME_MESSAGE = "Hello, %s!";
    private static final String INVALID_VALUES_MESSAGE = "Invalid login or password";

    @Override
    public String execute(SessionRequestContent requestContent) {
        UserServiceImpl userService = new UserServiceImpl();
        String userEmail = requestContent.getParameter(EMAIL);
        String userPassword = requestContent.getParameter(PASSWORD);
        HttpSession session;
        String page;

        try {
            if (userService.verifyUser(userEmail, userPassword)) {
                User authorizedUser = userService.findUserByEmail(userEmail).get();
                session = requestContent.getSession();
                session.setAttribute(ROLE, authorizedUser.getRole().name());
                session.setAttribute(EMAIL, authorizedUser.getEmail());
                page = switch (authorizedUser.getRole()) {
                    case ADMIN -> PageName.ADMIN_OFFICE.getPath();
                    case CLIENT -> {
                        Client authorizedClient = (Client) authorizedUser;
                        session.setAttribute(FIRST_NAME, authorizedClient.getFirstName());
                        session.setAttribute(STATUS, authorizedClient.getStatus().name());
                        yield PageName.CLIENT_GARAGE.getPath();
                    }
                    case MANAGER -> PageName.MANAGER_OFFICE.getPath();
                };
            } else {
                page = PageName.LOGIN.getPath();
                requestContent.setAttribute(MESSAGE_ATTRIBUTE, "INVALID_VALUES_MESSAGE"); //todo message to incorrect password
            }
        } catch (ServiceProjectException e) {
            page = PageName.ERROR.getPath();
            logger.log(Level.ERROR, "An error occurred during user verification", e);
            //todo what message send on error page where put value in constant or hardcode
        }

        return page;
    }
}
