package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;
import by.balashevich.finalproject.controller.command.impl.pagecommand.PageName;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Order;
import by.balashevich.finalproject.model.service.OrderService;
import by.balashevich.finalproject.model.service.impl.OrderServiceImpl;
import by.balashevich.finalproject.util.ParameterKey;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class UpdateOrderStatusCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        OrderService orderService = new OrderServiceImpl();
        HttpSession session = request.getSession();
        String updatingStatusData = request.getParameter(ParameterKey.ORDER_STATUS);
        int orderIndex = Integer.parseInt(request.getParameter(ParameterKey.ORDER_INDEX));
        Order order = ((ArrayList<Order>) session.getAttribute(AttributeKey.ORDER_LIST)).get(orderIndex);
        String page;

        try {
            if (orderService.updateOrderStatus(order, updatingStatusData)) {
                request.setAttribute(AttributeKey.ORDER_STATUS_UPDATED, true);
            } else {
                request.setAttribute(AttributeKey.ORDER_STATUS_UPDATED, false);
            }
            page = (String) session.getAttribute(AttributeKey.CURRENT_PAGE);
        } catch (ServiceProjectException e) {
            logger.log(Level.ERROR, "error while changing order status", e);
            page = PageName.ERROR_500.getPath();
        }

        return page;
    }
}
