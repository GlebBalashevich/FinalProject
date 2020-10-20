package by.balashevich.finalproject.controller.command.impl;

import by.balashevich.finalproject.controller.command.ActionCommand;
import by.balashevich.finalproject.controller.command.AttributeKey;
import by.balashevich.finalproject.controller.command.impl.pagecommand.PageName;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Car;
import by.balashevich.finalproject.model.service.CarService;
import by.balashevich.finalproject.model.service.impl.CarServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class FindCheckCarsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        Map<String, String> carParameters = new HashMap<>();
        CarService<Car> carService = new CarServiceImpl();
        carParameters.put(CAR_TYPE, request.getParameter(CAR_TYPE));
        carParameters.put(CAR_AVAILABLE, request.getParameter(CAR_AVAILABLE));
        HttpSession session = request.getSession();
        String page;

        try {
            List<Car> targetCars = carService.findCheckCars(carParameters);
            session.setAttribute(AttributeKey.CAR_LIST, targetCars);
            if (targetCars == null || targetCars.isEmpty()) {
                request.setAttribute(AttributeKey.CARS_FOUND, false);
            }
            page = PageName.ADMIN_CARS.getPath();
        } catch (ServiceProjectException e) {
            page = PageName.ERROR.getPath();
            logger.log(Level.ERROR, "An error occurred during searching cars for check", e);
        }

        return page;
    }
}
