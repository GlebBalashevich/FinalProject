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
import by.balashevich.finalproject.model.service.impl.ClientNotificationServiceImpl;
import by.balashevich.finalproject.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static by.balashevich.finalproject.util.ParameterKey.*;

/**
 * The Order car command.
 * <p>
 * Processes a customer's request to create a new order. The data required for the order
 * (dateFrom, dateTo, carId, userId, amount) is extracted from the request and sent
 * to the service for processing. After the order is processed, the customer is forwarding
 * to a notification page with a message about the successful or unsuccessful
 * placing of the order.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public class OrderCarCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        OrderService orderService = new OrderServiceImpl();
        ClientNotificationService clientNotificationService = new ClientNotificationServiceImpl();
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
                clientNotificationService.createOrderNotification(user.getEmail());
                session.removeAttribute(AttributeKey.CAR_LIST);
                session.removeAttribute(AttributeKey.CAR_PARAMETERS);
                session.removeAttribute(AttributeKey.CARS_PAGE_NUMBER);
                request.setAttribute(AttributeKey.SUCCESSFUL_ORDERING, true);
            } else {
                request.setAttribute(AttributeKey.SUCCESSFUL_ORDERING, false);
            }
            router = new Router(PageName.NOTIFICATION.getPath());
        } catch (ServiceProjectException e) {
            logger.log(Level.ERROR, "User Id" + orderParameters.get(USER_ID), e);
            router = new Router(PageName.ERROR_500.getPath());
        }

        return router;
    }
}
