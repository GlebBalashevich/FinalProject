package by.balashevich.finalproject.model.service;

import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Car;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CarService<T extends Car> {

    boolean addCar(Map<String, String> carParameters) throws ServiceProjectException;

    Optional<Car> findCarById(long carId) throws ServiceProjectException;

    List<Car> findAvailableOrderCars(Map<String, String> carParametersData) throws ServiceProjectException;

    List<Car> findCheckCars(Map<String, String> carParametersData) throws ServiceProjectException;

    boolean updateCar(Car updatingCar, Map<String, String> carParameters) throws ServiceProjectException;
}
