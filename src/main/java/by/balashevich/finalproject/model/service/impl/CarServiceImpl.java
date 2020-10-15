package by.balashevich.finalproject.model.service.impl;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.dao.impl.CarDaoImpl;
import by.balashevich.finalproject.model.entity.Car;
import by.balashevich.finalproject.model.service.CarService;
import by.balashevich.finalproject.validator.CarValidator;
import by.balashevich.finalproject.validator.OrderValidator;

import java.time.LocalDate;
import java.util.*;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class CarServiceImpl implements CarService<Car> {
    private static final String PRICE_DELIMITER = ";";

    public Optional<Car> findCarById(long carId) throws ServiceProjectException {
        CarDaoImpl carDao = new CarDaoImpl();
        Optional<Car> targetCar;

        try {
            targetCar = carDao.findById(carId);
        } catch (DaoProjectException e) {
            throw new ServiceProjectException("Error while searching Car by Id", e);
        }

        return targetCar;
    }

    public List<Car> findAvailableOrderCars(Map<String, String> carParametersData) throws ServiceProjectException {
        CarDaoImpl carDao = new CarDaoImpl();
        String dateFromData = carParametersData.get(DATE_FROM);
        String dateToData = carParametersData.get(DATE_TO);
        String priceRangeData = carParametersData.get(PRICE_RANGE);
        String carTypeData = carParametersData.get(CAR_TYPE);

        Map<String, Object> carParametersChecked = new HashMap<>();
        List<Car> targetCars = null;

        try {
            if (OrderValidator.validateDateData(dateFromData) && OrderValidator.validateDateData(dateToData)) {
                LocalDate dateFrom = LocalDate.parse(dateFromData);
                LocalDate dateTo = LocalDate.parse(dateToData);
                if (dateFrom.toEpochDay() < dateTo.toEpochDay()) {
                    carParametersChecked.put(DATE_FROM, dateFrom);
                    carParametersChecked.put(DATE_TO, dateTo);
                    if (CarValidator.validatePriceRangeData(priceRangeData)) {
                        String[] prices = priceRangeData.split(PRICE_DELIMITER);
                        carParametersChecked.put(PRICE_FROM, Integer.parseInt(prices[0]));
                        carParametersChecked.put(PRICE_TO, Integer.parseInt(prices[1]));
                    }
                    if (CarValidator.validateCarTypeData(carTypeData)) {
                        carParametersChecked.put(CAR_TYPE, Car.Type.valueOf(carTypeData.toUpperCase()));
                    }
                    targetCars = carDao.findAvailableOrderCars(carParametersChecked);
                }
            }
        } catch (DaoProjectException e) {
            throw new ServiceProjectException("error while searching available cars by parameters", e);
        }

        return targetCars;
    }
}
