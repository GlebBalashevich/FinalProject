package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.Router;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;
import by.balashevich.finalproject.controller.command.PageName;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Order;
import by.balashevich.finalproject.model.service.ClientNotificationService;
import by.balashevich.finalproject.model.service.OrderService;
import by.balashevich.finalproject.model.service.impl.ClientNotificationServiceImpl;
import by.balashevich.finalproject.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static by.balashevich.finalproject.util.ParameterKey.*;

/**
 * The Decline order command.
 * <p>
 * Processes a request from a service administrator to decline an order placed by a client.
 * After processing the request, the administrator is redirected to the orders page,
 * where he is notified about the successful or unsuccessful declining of the order.
 * In the event that the declining of the order was successful, the client is notified that his
 * order has been declined.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public class DeclineOrderCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        OrderService orderService = new OrderServiceImpl();
        ClientNotificationService clientNotificationService = new ClientNotificationServiceImpl();
        HttpSession session = request.getSession();
        int orderIndex = Integer.parseInt(request.getParameter(ORDER_INDEX));
        List<Order> orderList = ((ArrayList<Order>) session.getAttribute(AttributeKey.ORDER_LIST));
        Order decliningOrder = orderList.get(orderIndex);
        Router router;

        try {
            if (orderService.declineOrder(decliningOrder)) {
                clientNotificationService.declineOrderNotification(decliningOrder);
                orderList.remove(orderIndex);
                request.setAttribute(AttributeKey.ORDER_DECLINED, true);
            } else {
                request.setAttribute(AttributeKey.ORDER_DECLINED, false);
            }
            router = new Router(PageName.ADMIN_ORDERS.getPath());
        } catch (ServiceProjectException e) {
            logger.log(Level.ERROR, "Order Id " + decliningOrder.getOrderId(), e);
            router = new Router(PageName.ERROR_500.getPath());
        }

        return router;
    }
}
