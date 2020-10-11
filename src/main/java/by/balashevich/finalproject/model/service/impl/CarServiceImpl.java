package by.balashevich.finalproject.model.service.impl;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.dao.impl.CarDaoImpl;
import by.balashevich.finalproject.model.entity.Car;
import by.balashevich.finalproject.model.service.CarService;
import by.balashevich.finalproject.parser.DateParser;
import by.balashevich.finalproject.validator.CarValidator;
import by.balashevich.finalproject.validator.OrderValidator;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class CarServiceImpl implements CarService<Car> {
    private static final String PRICE_DELIMITER = ";";

    public List<Car> findAvailableOrderCars(Map<String, String> carParametersData) throws ServiceProjectException {
        CarDaoImpl carDao = new CarDaoImpl();
        OrderValidator orderValidator = new OrderValidator();
        CarValidator carValidator = new CarValidator();
        DateParser dateParser = new DateParser();
        String dateFromData = carParametersData.get(DATE_FROM);
        String dateToData = carParametersData.get(DATE_TO);
        String priceRangeData = carParametersData.get(PRICE_RANGE);
        String carTypeData =  carParametersData.get(CAR_TYPE);

        Map<String, Object> carParametersChecked = new HashMap<>();
        List<Car> targetCars = null;

        try {
            if (orderValidator.validateDateData(dateFromData) && orderValidator.validateDateData(dateToData)) {
                Date dateFrom = dateParser.parseDate(dateFromData);
                Date dateTo = dateParser.parseDate(dateToData);
                if (dateFrom.getTime() < dateTo.getTime()){
                    carParametersChecked.put(DATE_FROM, dateFrom);
                    carParametersChecked.put(DATE_TO, dateTo);
                    if (carValidator.validatePriceRangeData(priceRangeData)){
                        String[] prices = priceRangeData.split(PRICE_DELIMITER);
                        carParametersChecked.put(PRICE_FROM, Integer.parseInt(prices[0]));
                        carParametersChecked.put(PRICE_TO, Integer.parseInt(prices[1]));
                    }
                    if (carValidator.validateCarTypeData(carTypeData)){
                        carParametersChecked.put(CAR_TYPE, Car.Type.valueOf(carTypeData.toUpperCase()));
                    }
                    targetCars = carDao.findAvailableOrderCars(carParametersChecked);
                }
            }
        } catch (ParseException | DaoProjectException e) {
            throw new ServiceProjectException("error while searching available cars by parameters", e);
        }

        return targetCars;
    }
}
