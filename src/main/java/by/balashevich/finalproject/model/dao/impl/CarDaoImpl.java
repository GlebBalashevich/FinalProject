package by.balashevich.finalproject.model.dao.impl;

import by.balashevich.finalproject.builder.CarBuilder;
import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.model.dao.CarDao;
import by.balashevich.finalproject.model.entity.Car;
import by.balashevich.finalproject.model.pool.ConnectionPool;
import by.balashevich.finalproject.util.DateConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class CarDaoImpl implements CarDao {
    private static final String DOT = ".";
    private static final String FIND_ALL = "SELECT car_id, model, car_type, number_seats, rent_cost, " +
            "fuel_type, fuel_consumption, car_views.exterior, car_views.exterior_small, car_views.interior," +
            " is_available FROM cars LEFT OUTER JOIN car_views ON car_id = car_views.cars_id";
    private static final String FIND_BY_ID = FIND_ALL + " WHERE car_id = ?";
    private static final String CHECK_CAR_TYPE = " AND car_type=?";
    private static final String CHECK_PRICE_RANGE = " AND (rent_cost BETWEEN ? AND ?)";
    private static final String CHECK_ORDERS_DATE_RANGE = " AND NOT EXISTS (SELECT date_from, date_to, " +
            "cars_id FROM orders WHERE cars.car_id = orders.car_id AND " +
            "((? BETWEEN date_from AND date_to) OR (? BETWEEN date_from AND date_to)))";
    private static final String FIND_AVAILABLE_ORDER_CAR = FIND_ALL + " WHERE is_available=true";

    @Override
    public boolean add(Map<String, Object> parameters) throws DaoProjectException {
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

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                carList.add(createCar(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoProjectException("Error during searching all cars", e);
        }

        return carList;
    }

    @Override
    public Optional<Car> findById(long id) throws DaoProjectException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Optional<Car> targetCar = Optional.empty();

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                targetCar = Optional.of(createCar(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoProjectException("Error while searching car by ID", e);
        }

        return targetCar;
    }

    public List<Car> findAvailableOrderCars(Map<String, Object> carParameters) throws DaoProjectException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<Car> carList = new ArrayList<>();
        StringBuilder filteringAvailableCarsQuery = new StringBuilder();
        filteringAvailableCarsQuery.append(FIND_AVAILABLE_ORDER_CAR);
        if (carParameters.containsKey(PRICE_FROM) && carParameters.containsKey(PRICE_TO)) {
            filteringAvailableCarsQuery.append(CHECK_PRICE_RANGE);
        }
        if (carParameters.containsKey(CAR_TYPE)) {
            filteringAvailableCarsQuery.append(CHECK_CAR_TYPE);
        }
        filteringAvailableCarsQuery.append(CHECK_ORDERS_DATE_RANGE);

        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(filteringAvailableCarsQuery.toString())) {
            int parameterIndex = 0;
            if (carParameters.containsKey(PRICE_FROM) && carParameters.containsKey(PRICE_TO)) {
                statement.setInt(++parameterIndex, (int) carParameters.get(PRICE_FROM));
                statement.setInt(++parameterIndex, (int) carParameters.get(PRICE_TO));
            }
            if (carParameters.containsKey(CAR_TYPE)) {
                statement.setInt(++parameterIndex, ((Car.Type) carParameters.get(CAR_TYPE)).ordinal());
            }
            statement.setLong(++parameterIndex, DateConverter.convertToLong((LocalDate) carParameters.get(DATE_FROM)));
            statement.setLong(++parameterIndex, DateConverter.convertToLong((LocalDate) carParameters.get(DATE_TO)));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                carList.add(createCar(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoProjectException("Error during searching all available cars", e);
        }

        return carList;
    }

    private Car createCar(ResultSet resultSet) throws SQLException {
        Map<String, Object> carParameters = new HashMap<>();
        carParameters.put(CAR_ID, resultSet.getLong(CAR_ID));
        carParameters.put(MODEL, resultSet.getString(MODEL));
        carParameters.put(CAR_TYPE, Car.Type.getType(resultSet.getInt(CAR_TYPE)));
        carParameters.put(NUMBER_SEATS, resultSet.getInt(NUMBER_SEATS));
        carParameters.put(RENT_COST, resultSet.getInt(RENT_COST));
        carParameters.put(FUEL_TYPE, Car.FuelType.getFuelType(resultSet.getInt(FUEL_TYPE)));
        carParameters.put(FUEL_CONSUMPTION, resultSet.getInt(FUEL_CONSUMPTION));
        carParameters.put(CAR_AVAILABLE, resultSet.getBoolean(CAR_AVAILABLE));
        carParameters.put(EXTERIOR, resultSet.getString(CAR_VIEWS + DOT + EXTERIOR));
        carParameters.put(EXTERIOR_SMALL, resultSet.getString(CAR_VIEWS + DOT + EXTERIOR_SMALL));
        carParameters.put(INTERIOR, resultSet.getString(CAR_VIEWS + DOT + INTERIOR));

        return CarBuilder.buildCar(carParameters);
    }
}
