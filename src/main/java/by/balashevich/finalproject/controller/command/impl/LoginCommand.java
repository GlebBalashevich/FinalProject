package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;
import by.balashevich.finalproject.controller.command.impl.pagecommand.PageName;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.entity.User;
import by.balashevich.finalproject.model.service.UserService;
import by.balashevich.finalproject.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class LoginCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        UserService<User> userService = new UserServiceImpl();
        String userEmail = request.getParameter(EMAIL);
        String userPassword = request.getParameter(PASSWORD);
        HttpSession session = request.getSession();

        String page;

        try {
            if (userService.authorizeUser(userEmail, userPassword)) {
                User authorizedUser = userService.findUserByEmail(userEmail).get();
                if (authorizedUser.getRole() == User.Role.CLIENT){
                    Client authorizedClient = (Client) authorizedUser;
                    session.setAttribute(AttributeKey.USER, authorizedClient);
                } else{
                    session.setAttribute(AttributeKey.USER, authorizedUser);
                }
                page = PageName.HOME.getPath();
            } else {
                page = PageName.LOGIN.getPath();
                request.setAttribute(AttributeKey.SUCCESSFUL_AUTHORIZATION, false);
            }
        } catch (ServiceProjectException e) {
            page = PageName.ERROR.getPath();
            logger.log(Level.ERROR, "An error occurred during user verification", e);
        }

        return page;
    }
}
