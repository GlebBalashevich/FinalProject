package by.balashevich.finalproject.model.dao.impl;

import by.balashevich.finalproject.builder.CarBuilder;
import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.model.dao.CarDao;
import by.balashevich.finalproject.model.entity.Car;
import by.balashevich.finalproject.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class CarDaoImpl implements CarDao {
    private static final String FIND_BY_ID = "SELECT car_id, model, type, number_seats, rent_cost, " +
            "fuel_type, fuel_consumption, is_available FROM cars WHERE car_id = ?";
    private static final String FIND_ALL = "SELECT car_id, model, type, number_seats, rent_cost, " +
            "fuel_type, fuel_consumption, is_available FROM cars";

    @Override
    public boolean add(Map<String, String> parameters) throws DaoProjectException {
        return false;
    }

    @Override
    public boolean remove(Car car) throws DaoProjectException {
        return false;
    }

    @Override
    public Car update(Car car) throws DaoProjectException {
        return null;
    }

    @Override
    public List<Car> findAll() throws DaoProjectException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<Car> carList = new ArrayList<>();

        try(Connection connection = pool.getConnection();
        PreparedStatement statement = connection.prepareStatement(FIND_ALL)){
            ResultSet resultSet = statement.executeQuery();
            Optional<Car> targetCar;
            while(resultSet.next()){
                Map<String, Object> carParameters = new HashMap<>();
                carParameters.put(CAR_ID, resultSet.getLong(CAR_ID));
                carParameters.put(MODEL, resultSet.getString(MODEL));
                carParameters.put(CAR_TYPE, Car.Type.getType(resultSet.getInt(CAR_TYPE)));
                carParameters.put(NUMBER_SEATS, resultSet.getInt(NUMBER_SEATS));
                carParameters.put(RENT_COST, resultSet.getInt(RENT_COST));
                carParameters.put(FUEL_TYPE, Car.FuelType.getFuelType(resultSet.getInt(FUEL_TYPE)));
                carParameters.put(FUEL_CONSUMPTION, resultSet.getInt(FUEL_CONSUMPTION));
                carParameters.put(CAR_AVAILABLE, resultSet.getBoolean(CAR_AVAILABLE));

                targetCar = Optional.of(CarBuilder.buildCar(carParameters));
                targetCar.ifPresent(carList::add);
            }
        } catch (SQLException e) {
            throw new DaoProjectException("error during searching all cars", e);
        }

        return carList;
    }

    @Override
    public Optional<Car> findById(long id) throws DaoProjectException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Optional<Car> targetCar = Optional.empty();

        try(Connection connection = pool.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)){
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Map<String, Object> carParameters = new HashMap<>();
                carParameters.put(CAR_ID, resultSet.getLong(CAR_ID));
                carParameters.put(MODEL, resultSet.getString(MODEL));
                carParameters.put(CAR_TYPE, Car.Type.getType(resultSet.getInt(CAR_TYPE)));
                carParameters.put(NUMBER_SEATS, resultSet.getInt(NUMBER_SEATS));
                carParameters.put(RENT_COST, resultSet.getInt(RENT_COST));
                carParameters.put(FUEL_TYPE, Car.FuelType.getFuelType(resultSet.getInt(FUEL_TYPE)));
                carParameters.put(FUEL_CONSUMPTION, resultSet.getInt(FUEL_CONSUMPTION));
                carParameters.put(CAR_AVAILABLE, resultSet.getBoolean(CAR_AVAILABLE));

                targetCar = Optional.of(CarBuilder.buildCar(carParameters));
            }
        } catch (SQLException e) {
            throw new DaoProjectException("error while searching car by ID", e);
        }

        return targetCar;
    }
}
