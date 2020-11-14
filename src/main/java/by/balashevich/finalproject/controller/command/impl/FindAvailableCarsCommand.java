package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.Router;
import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;
import by.balashevich.finalproject.controller.command.PageName;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Car;
import by.balashevich.finalproject.model.service.CarService;
import by.balashevich.finalproject.model.service.OrderService;
import by.balashevich.finalproject.model.service.impl.CarServiceImpl;
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

/**
 * The Find available cars command.
 * <p>
 * Processes a request received from a client to search for cars available
 * for order according to the parameters passed (dateFrom, dateTo, type (Car),
 * price range). After processing the request, the client is forwarding to
 * the cars page (version for the client), which displays the list of
 * requested cars. In the event that no cars were found according to these
 * criteria, an appropriate message is displayed to the client.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public class FindAvailableCarsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Map<String, String> carParameters = new HashMap<>();
        CarService carService = new CarServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        carParameters.put(DATE_FROM, request.getParameter(DATE_FROM));
        carParameters.put(DATE_TO, request.getParameter(DATE_TO));
        carParameters.put(CAR_TYPE, request.getParameter(CAR_TYPE));
        carParameters.put(PRICE_RANGE, request.getParameter(PRICE_RANGE));
        HttpSession session = request.getSession();
        Router router;

        try {
            orderService.manageOrders();
            List<Car> targetCars = carService.findAvailableOrderCars(carParameters);
            session.setAttribute(AttributeKey.CAR_LIST, targetCars);
            session.setAttribute(AttributeKey.CAR_PARAMETERS, carParameters);
            session.setAttribute(AttributeKey.CARS_PAGE_NUMBER, 1);
            if (targetCars == null || targetCars.isEmpty()) {
                request.setAttribute(AttributeKey.CARS_FOUND, false);
            }
            router = new Router(PageName.CLIENT_CARS.getPath());
        } catch (ServiceProjectException e) {
            logger.log(Level.ERROR, "Number of parameters" + carParameters.size(), e);
            router = new Router(PageName.ERROR_500.getPath());
        }

        return router;
    }
}
