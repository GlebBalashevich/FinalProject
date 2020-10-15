package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;
import by.balashevich.finalproject.controller.command.PageName;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Car;
import by.balashevich.finalproject.model.service.impl.CarServiceImpl;
import by.balashevich.finalproject.model.service.impl.OrderServiceImpl;
import by.balashevich.finalproject.validator.OrderValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.Optional;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class OrderPageCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        CarServiceImpl carService = new CarServiceImpl();
        OrderServiceImpl orderService = new OrderServiceImpl();
        long carId = Long.parseLong(request.getParameter(CAR_ID));
        String dateFromData = request.getParameter(DATE_FROM);
        String dateToData = request.getParameter(DATE_TO);
        String page;

        try {
            Optional<Car> orderingCar = carService.findCarById(carId);
            if (orderingCar.isPresent() && OrderValidator.validateDateData(dateFromData)
                    && OrderValidator.validateDateData(dateToData)) {
                LocalDate dateFrom = LocalDate.parse(dateFromData);
                LocalDate dateTo = LocalDate.parse(dateToData);
                Car car = orderingCar.get();
                int orderAmount = orderService.calculateOrderAmount(car.getRentCost(), dateFrom, dateTo);
                request.setAttribute(AttributeKey.ORDER_AMOUNT, orderAmount);
                request.setAttribute(AttributeKey.CAR, orderingCar.get());
                request.setAttribute(AttributeKey.DATE_FROM, dateFrom);
                request.setAttribute(AttributeKey.DATE_TO, dateTo);
                page = PageName.ORDER.getPath();
            } else {
                page = PageName.NOTIFICATION.getPath();
                // FIXME: 14.10.2020 add message about mistake
            }
        } catch (ServiceProjectException e) {
            page = PageName.ERROR.getPath();
            logger.log(Level.ERROR, "An error occurred during client adding", e);
        }

        return page;
    }
}
