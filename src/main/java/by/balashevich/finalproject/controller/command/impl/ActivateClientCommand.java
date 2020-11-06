package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.Router;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;
import by.balashevich.finalproject.controller.command.PageName;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.service.UserService;
import by.balashevich.finalproject.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.balashevich.finalproject.util.ParameterKey.*;

/**
 * The type Activate client command.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public class ActivateClientCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        UserService userService = new UserServiceImpl();
        String clientEmail = request.getParameter(EMAIL);
        Router router;

        try {
            if (userService.updateClientStatus(clientEmail, Client.Status.ACTIVE)) {
                request.setAttribute(AttributeKey.SUCCESSFUL_ACTIVATION, true);
            } else {
                request.setAttribute(AttributeKey.SUCCESSFUL_ACTIVATION, false);
            }
            router = new Router(PageName.NOTIFICATION.getPath());
        } catch (ServiceProjectException e) {
            logger.log(Level.ERROR, "Client Email " + clientEmail, e);
            router = new Router(PageName.ERROR_500.getPath());
        }

        return router;
    }
}
