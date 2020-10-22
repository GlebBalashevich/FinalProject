package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;
import by.balashevich.finalproject.controller.command.impl.pagecommand.PageName;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Order;
import by.balashevich.finalproject.model.service.OrderService;
import by.balashevich.finalproject.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class FindCheckOrdersCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        OrderService<Order> orderService = new OrderServiceImpl();
        Map<String, String> orderParameters = new HashMap<>();
        orderParameters.put(MODEL, request.getParameter(MODEL));
        orderParameters.put(EMAIL, request.getParameter(EMAIL));
        orderParameters.put(ORDER_STATUS, request.getParameter(ORDER_STATUS));
        HttpSession session = request.getSession();
        String page;

        try {
            List<Order> orderList = orderService.findOrdersByParameters(orderParameters);
            session.setAttribute(AttributeKey.ORDER_LIST, orderList);
            if (orderList == null || orderList.isEmpty()) {
                request.setAttribute(AttributeKey.ORDERS_FOUND, false);
            }
            page = PageName.ORDERS.getPath();
        } catch (ServiceProjectException e) {
            logger.log(Level.ERROR, "error while searching orders", e);
            page = PageName.ERROR.getPath();
        }

        return page;
    }
}
