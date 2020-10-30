package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.Router;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;
import by.balashevich.finalproject.controller.command.PageName;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.entity.User;
import by.balashevich.finalproject.model.service.ClientNotificationService;
import by.balashevich.finalproject.model.service.OrderService;
import by.balashevich.finalproject.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class OrderCarCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        OrderService orderService = new OrderServiceImpl();
        ClientNotificationService clientNotificationService = new ClientNotificationService();
        Map<String, String> orderParameters = new HashMap<>();
        orderParameters.put(CAR_ID, request.getParameter(CAR_ID));
        orderParameters.put(USER_ID, request.getParameter(USER_ID));
        orderParameters.put(DATE_FROM, request.getParameter(DATE_FROM));
        orderParameters.put(DATE_TO, request.getParameter(DATE_TO));
        orderParameters.put(AMOUNT, request.getParameter(AMOUNT));
        HttpSession session = request.getSession();
        Router router;

        try {
            if (orderService.add(orderParameters)) {
                User user = (Client) session.getAttribute(AttributeKey.USER);
                String locale = (String) session.getAttribute(AttributeKey.LOCALE);
                clientNotificationService.createOrderNotification(user.getEmail(), locale);
                session.removeAttribute(AttributeKey.CAR_LIST);
                session.removeAttribute(AttributeKey.CAR_PARAMETERS);
                request.setAttribute(AttributeKey.SUCCESSFUL_ORDERING, true);
            } else {
                request.setAttribute(AttributeKey.SUCCESSFUL_ORDERING, false);
                logger.log(Level.WARN, ""); // TODO: 15.10.2020 message 
            }
            router = new Router(PageName.NOTIFICATION.getPath());
        } catch (ServiceProjectException e) {
            logger.log(Level.ERROR, "error while creating new order", e);
            router = new Router(PageName.ERROR_500.getPath());
        }

        return router;
    }
}
