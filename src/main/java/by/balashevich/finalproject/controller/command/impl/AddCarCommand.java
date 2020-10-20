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
import java.util.Map;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class AddCarCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        CarService<Car> carService = new CarServiceImpl();
        HttpSession session = request.getSession();
        Map<String, String> carParameters = new HashMap<>();
        carParameters.put(MODEL, request.getParameter(MODEL));
        carParameters.put(CAR_TYPE, request.getParameter(CAR_TYPE));
        carParameters.put(NUMBER_SEATS, request.getParameter(NUMBER_SEATS));
        carParameters.put(RENT_COST, request.getParameter(RENT_COST));
        carParameters.put(FUEL_TYPE, request.getParameter(FUEL_TYPE));
        carParameters.put(FUEL_CONSUMPTION, request.getParameter(FUEL_CONSUMPTION));
        carParameters.put(CAR_AVAILABLE, request.getParameter(CAR_AVAILABLE));
        carParameters.put(EXTERIOR_SMALL, (String)session.getAttribute(EXTERIOR_SMALL));
        carParameters.put(EXTERIOR, (String)session.getAttribute(EXTERIOR));
        carParameters.put(INTERIOR, (String)session.getAttribute(INTERIOR));
        String page;

        try{
            if (carService.addCar(carParameters)){
                session.removeAttribute(AttributeKey.CAR_LIST);
                session.removeAttribute(EXTERIOR_SMALL);
                session.removeAttribute(EXTERIOR);
                session.removeAttribute(INTERIOR);
                request.setAttribute(AttributeKey.CAR_SUCCESSFULLY_ADDED, true);
                page = PageName.ADMIN_CARS.getPath();
            } else{
                request.setAttribute(AttributeKey.CAR_PARAMETERS, carParameters);
                page = PageName.CREATE_CAR.getPath();
            }
        } catch(ServiceProjectException e){
            logger.log(Level.ERROR, "Error while add new car to application", e);
            page = PageName.ERROR.getPath();
        }

        return page;
    }
}
