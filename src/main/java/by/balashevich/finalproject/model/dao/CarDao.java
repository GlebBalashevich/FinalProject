package by.balashevich.finalproject.model.dao;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.model.entity.Car;

import java.util.List;
import java.util.Map;

public interface CarDao extends BaseDao<Car> {

    List<Car> findAvailableOrderCars(Map<String, Object> carParameters) throws DaoProjectException;

    List<Car> findCheckCars(Map<String, Object> carParameters) throws DaoProjectException;
}
