package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;
import by.balashevich.finalproject.controller.command.PageName;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.User;
import by.balashevich.finalproject.model.service.ClientOperationService;
import by.balashevich.finalproject.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class RegisterClientCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        UserServiceImpl userService = new UserServiceImpl();
        ClientOperationService clientOperationService;
        Map<String, String> clientParameters = new HashMap();
        clientParameters.put(EMAIL, request.getParameter(EMAIL));
        clientParameters.put(PASSWORD, request.getParameter(PASSWORD));
        clientParameters.put(CONFIRM_PASSWORD, request.getParameter(CONFIRM_PASSWORD));
        clientParameters.put(FIRST_NAME, request.getParameter(FIRST_NAME));
        clientParameters.put(SECOND_NAME, request.getParameter(SECOND_NAME));
        clientParameters.put(DRIVER_LICENSE, request.getParameter(DRIVER_LICENSE));
        clientParameters.put(PHONE_NUMBER, request.getParameter(PHONE_NUMBER));
        HttpSession session = request.getSession();
        String page;

        try {
            if (!userService.existUser(clientParameters.get(EMAIL))) {
                if (userService.add(clientParameters)) {
                    session.setAttribute(ROLE, User.Role.CLIENT.name());
                    clientOperationService = new ClientOperationService();
                    clientOperationService.registerMailNotification(clientParameters.get(EMAIL),
                            (String) session.getAttribute(AttributeKey.LOCALE),
                            clientParameters.get(FIRST_NAME), request.getRequestURL().toString());
                    page = PageName.HOME.getPath();
                } else {
                    request.setAttribute(AttributeKey.REGISTER_PARAMETERS, clientParameters);
                    page = PageName.REGISTER.getPath();
                    logger.log(Level.INFO, "form not pass validation");
                }
            } else {
                request.setAttribute("message", null); // TODO: 21.09.2020  define message
                page = PageName.REGISTER.getPath();
                logger.log(Level.INFO, "user exist");
            }
        } catch (ServiceProjectException e) {
            page = PageName.ERROR.getPath();
            logger.log(Level.ERROR, "An error occurred during client adding", e);
        }

        return page;
    }
}
