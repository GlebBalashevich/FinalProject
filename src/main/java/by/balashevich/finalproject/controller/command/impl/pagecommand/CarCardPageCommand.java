package by.balashevich.finalproject.controller.command.impl.pagecommand;

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
import by.balashevich.finalproject.validator.OrderValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.balashevich.finalproject.util.ParameterKey.*;

/**
 * The Car card page command.
 * <p>
 * The command responsible for forwarding client to the car card page
 * to view detailed information about the car and its subsequent order
 * if the car has become unavailable, the forwarding is carried out to the notification page
 * in case of an error in receiving data, it will be forwarding to the error page
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public class CarCardPageCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        CarService carService = new CarServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        long carId = Long.parseLong(request.getParameter(CAR_ID));
        Map<String, String> orderCarParameters = (HashMap) request.getSession().getAttribute(AttributeKey.CAR_PARAMETERS);
        String dateFromData = orderCarParameters.get(DATE_FROM);
        String dateToData = orderCarParameters.get(DATE_TO);
        Router router;

        try {
            Optional<Car> orderingCar = carService.findCarById(carId);
            if (orderingCar.isPresent()) {
                Car car = orderingCar.get();
                int orderAmount = orderService.calculateOrderAmount(car.getRentCost(), dateFromData, dateToData);
                if (orderAmount > 0) {
                    request.setAttribute(AttributeKey.ORDER_AMOUNT, orderAmount);
                    request.setAttribute(AttributeKey.CAR, orderingCar.get());
                    request.setAttribute(AttributeKey.DATE_FROM, dateFromData);
                    request.setAttribute(AttributeKey.DATE_TO, dateToData);
                    router = new Router(PageName.CAR_CARD.getPath());
                } else {
                    request.setAttribute(AttributeKey.NEGATIVE_AMOUNT, true);
                    router = new Router(PageName.NOTIFICATION.getPath());
                }
            } else {
                request.setAttribute(AttributeKey.CAR_FOUND, false);
                router = new Router(PageName.NOTIFICATION.getPath());
            }
        } catch (ServiceProjectException e) {
            router = new Router(PageName.ERROR_500.getPath());
            logger.log(Level.ERROR, "car Id" + carId, e);
        }

        return router;
    }
}
