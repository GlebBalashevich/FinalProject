package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.SessionRequestContent;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.PageName;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class ActivateClientCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(SessionRequestContent requestContent) {
        UserServiceImpl userService = new UserServiceImpl();
        String clientEmail = requestContent.getParameter(EMAIL);
        String page;

        try {
            userService.updateClientStatus(clientEmail, Client.Status.ACTIVE);
            page = PageName.CLIENT_GARAGE.getPath();
        } catch (ServiceProjectException e){
            page = PageName.ERROR.getPath();
            logger.log(Level.ERROR, "An error occurred during client adding", e); //todo make correct log
        }

        return page;
    }
}
