package by.balashevich.finalproject.model.service;

import by.balashevich.finalproject.exception.ServiceProjectException;
import by.balashevich.finalproject.model.entity.Car;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface Car service.
 *
 * @author Balashevich Gleb
 * @version 1.0
 */
public interface CarService {

    /**
     * Add car boolean.
     *
     * @param carParameters the car parameters
     * @return the boolean
     * @throws ServiceProjectException the service project exception
     */
    boolean addCar(Map<String, String> carParameters) throws ServiceProjectException;

    /**
     * Find car by id optional.
     *
     * @param carId the car id
     * @return the optional
     * @throws ServiceProjectException the service project exception
     */
    Optional<Car> findCarById(long carId) throws ServiceProjectException;

    /**
     * Find available order cars list.
     *
     * @param carParametersData the car parameters data
     * @return the list
     * @throws ServiceProjectException the service project exception
     */
    List<Car> findAvailableOrderCars(Map<String, String> carParametersData) throws ServiceProjectException;

    /**
     * Find cars by parameters list.
     *
     * @param carParametersData the car parameters data
     * @return the list
     * @throws ServiceProjectException the service project exception
     */
    List<Car> findCarsByParameters(Map<String, String> carParametersData) throws ServiceProjectException;

    /**
     * Update car boolean.
     *
     * @param updatingCar   the updating car
     * @param carParameters the car parameters
     * @return the boolean
     * @throws ServiceProjectException the service project exception
     */
    boolean updateCar(Car updatingCar, Map<String, String> carParameters) throws ServiceProjectException;
}
