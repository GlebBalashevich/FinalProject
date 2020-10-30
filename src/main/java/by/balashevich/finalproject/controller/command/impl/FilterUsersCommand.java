package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.Router;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;
import by.balashevich.finalproject.controller.command.PageName;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.service.UserService;
import by.balashevich.finalproject.model.service.impl.UserServiceImpl;
import by.balashevich.finalproject.util.ParameterKey;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class FilterUsersCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        UserService userService = new UserServiceImpl();
        String clientStatusData = request.getParameter(ParameterKey.CLIENT_STATUS);
        HttpSession session = request.getSession();
        Router router;

        try{
            List<Client> clientsList = userService.findClientsByStatus(clientStatusData);
            session.setAttribute(AttributeKey.CLIENT_LIST, clientsList);
            session.setAttribute(AttributeKey.CLIENTS_PAGE_NUMBER, 1);
            if (clientsList == null || clientsList.isEmpty()) {
                request.setAttribute(AttributeKey.CLIENTS_FOUND, false);
            }
            router = new Router(PageName.ADMIN_USERS.getPath());
        } catch (ServiceProjectException e){
            logger.log(Level.ERROR, "error while searching clients", e);
            router = new Router(PageName.ERROR_500.getPath());
        }

        return router;
    }
}
