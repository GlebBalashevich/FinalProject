package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;
import by.balashevich.finalproject.controller.command.impl.pagecommand.PageName;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.entity.User;
import by.balashevich.finalproject.model.service.ClientOperationService;
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
    public String execute(HttpServletRequest request) {
        OrderService orderService = new OrderServiceImpl();
        ClientOperationService clientOperationService = new ClientOperationService();
        Map<String, String> orderParameters = new HashMap<>();
        orderParameters.put(CAR_ID, request.getParameter(CAR_ID));
        orderParameters.put(USER_ID, request.getParameter(USER_ID));
        orderParameters.put(DATE_FROM, request.getParameter(DATE_FROM));
        orderParameters.put(DATE_TO, request.getParameter(DATE_TO));
        orderParameters.put(AMOUNT, request.getParameter(AMOUNT));
        HttpSession session = request.getSession();
        String page;

        try {
            if (orderService.add(orderParameters)) {
                User user = (Client) session.getAttribute(AttributeKey.USER);
                String locale = (String) session.getAttribute(AttributeKey.LOCALE);
                clientOperationService.createOrderNotification(user.getEmail(), locale);
                session.removeAttribute(AttributeKey.CAR_LIST);
                session.removeAttribute(AttributeKey.CAR_PARAMETERS);
            } else {
                logger.log(Level.WARN, ""); // TODO: 15.10.2020 message 
            }
            page = PageName.NOTIFICATION.getPath();
        } catch (ServiceProjectException e) {
            logger.log(Level.ERROR, "error while creating new order", e);
            page = PageName.ERROR.getPath();
        }

        return page;
    }
}
