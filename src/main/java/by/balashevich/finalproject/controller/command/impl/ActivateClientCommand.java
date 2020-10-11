package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.PageName;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.balashevich.finalproject.controller.command.AttributeKey.SUCCESSFUL_ACTIVATION;
import static by.balashevich.finalproject.util.ParameterKey.*;

public class ActivateClientCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        UserServiceImpl userService = new UserServiceImpl();
        String clientEmail = request.getParameter(EMAIL);
        String page;

        try {
            if (userService.updateClientStatus(clientEmail, Client.Status.ACTIVE)){
                page = PageName.LOGIN.getPath();
                request.setAttribute(SUCCESSFUL_ACTIVATION, true);
            }
            else{
                page = PageName.LOGIN.getPath();
                request.setAttribute(SUCCESSFUL_ACTIVATION, false);
            }
        } catch (ServiceProjectException e){
            page = PageName.ERROR.getPath();
            logger.log(Level.ERROR, "An error occurred during client activating", e);
        }

        return page;
    }
}