package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;
import by.balashevich.finalproject.controller.command.impl.pagecommand.PageName;
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

public class FindAvailableCarsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        Map<String, String> carParameters = new HashMap<>();
        CarService carService = new CarServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        carParameters.put(DATE_FROM, request.getParameter(DATE_FROM));
        carParameters.put(DATE_TO, request.getParameter(DATE_TO));
        carParameters.put(CAR_TYPE, request.getParameter(CAR_TYPE));
        carParameters.put(PRICE_RANGE, request.getParameter(PRICE_RANGE));
        HttpSession session = request.getSession();
        String page;
        try {
            orderService.deleteExpiredOrders();
            List<Car> targetCars = carService.findAvailableOrderCars(carParameters);
            session.setAttribute(AttributeKey.CAR_LIST, targetCars);
            session.setAttribute(AttributeKey.CAR_PARAMETERS, carParameters);
            if (targetCars == null || targetCars.isEmpty()) {
                request.setAttribute(AttributeKey.CARS_FOUND, false);
            }
            page = PageName.CLIENT_CARS.getPath();
        } catch (ServiceProjectException e) {
            page = PageName.ERROR_500.getPath();
            logger.log(Level.ERROR, "An error occurred during searching available cars", e);
        }

        return page;
    }
}
