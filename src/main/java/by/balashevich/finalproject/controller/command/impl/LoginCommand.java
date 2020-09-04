package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.PageName;
import by.balashevich.finalproject.controller.SessionRequestContent;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.User;
import by.balashevich.finalproject.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.ResourceBundle;

public class LoginCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String MESSAGE_ATTRIBUTE = "message";
    private static final String WELCOME_MESSAGE = "Hello, %s!";
    private static final String INVALID_VALUES_MESSAGE = "Invalid login or password";

    @Override
    public String execute(SessionRequestContent requestContent) {
        UserServiceImpl userService = new UserServiceImpl();
        String userLogin = requestContent.getParameter(LOGIN_PARAMETER);
        String userPassword = requestContent.getParameter(PASSWORD_PARAMETER);
        String page;

        try {
            Optional<User> verifyingUser = userService.verifyUser(userLogin, userPassword);
            if (verifyingUser.isPresent()){
                page = PageName.WELCOME.getPath();
                String userName = verifyingUser.toString();
                requestContent.setAttribute(MESSAGE_ATTRIBUTE, String.format(WELCOME_MESSAGE, userName));
            } else{
                page = PageName.LOGIN.getPath();
                requestContent.setAttribute(MESSAGE_ATTRIBUTE, INVALID_VALUES_MESSAGE);
            }
        } catch (ServiceProjectException e){
            page = PageName.ERROR.getPath();
            logger.log(Level.ERROR, "An error occurred during user verification", e);
            //todo what message send on error page where put value in constant or hardcode
        }

        return page;
    }
}
