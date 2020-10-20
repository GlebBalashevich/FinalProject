package by.balashevich.finalproject.model.dao.impl;

import by.balashevich.finalproject.builder.CarBuilder;
import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.model.dao.CarDao;
import by.balashevich.finalproject.model.entity.Car;
import by.balashevich.finalproject.model.pool.ConnectionPool;
import by.balashevich.finalproject.util.DateConverter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class CarDaoImpl implements CarDao {
    private static final Logger logger = LogManager.getLogger();

    private static final String ADD_CAR = "INSERT INTO cars (model, car_type, number_seats, rent_cost," +
            "fuel_type, fuel_consumption, is_available) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String ADD_CAR_VIEW = "INSERT INTO car_views(exterior_small, exterior, interior, cars_id)" +
            " VALUES (?, ?, ?, LAST_INSERT_ID())";
    private static final String FIND_ALL = "SELECT car_id, model, car_type, number_seats, rent_cost, " +
            "fuel_type, fuel_consumption, car_views.exterior, car_views.exterior_small, car_views.interior," +
            " is_available FROM cars LEFT OUTER JOIN car_views ON car_id = car_views.cars_id";
    private static final String FIND_BY_ID = FIND_ALL + " WHERE car_id = ?";
    private static final String CHECK_CAR_TYPE = " car_type=?";
    private static final String CHECK_CAR_AVAILABLE = " is_available=?";
    private static final String CHECK_PRICE_RANGE = " (rent_cost BETWEEN ? AND ?)";
    private static final String CHECK_ORDERS_DATE_RANGE = " NOT EXISTS (SELECT date_from, date_to, " +
            "cars_id FROM orders WHERE cars.car_id = orders.car_id AND " +
            "((? BETWEEN date_from AND date_to) OR (? BETWEEN date_from AND date_to)))";
    private static final String FIND_AVAILABLE_ORDER_CAR = FIND_ALL + " WHERE is_available=true";
    private static final String UPDATE_CAR = "UPDATE cars SET model = ?, car_type = ?, number_seats = ?," +
            "rent_cost = ?, fuel_type = ?, fuel_consumption = ?, is_available = ? WHERE car_id = ?";
    private static final String DOT = ".";
    private static final String WHERE_KEYWORD = " WHERE";
    private static final String AND_KEYWORD = " AND";

    @Override
    public boolean add(Map<String, Object> parameters) throws DaoProjectException {
        boolean isCarAdded = false;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection = connectionPool.getConnection();
        PreparedStatement carStatement = null;
        PreparedStatement carViewStatement = null;

        try {
            connection.setAutoCommit(false);
            carStatement = connection.prepareStatement(ADD_CAR);
            carViewStatement = connection.prepareStatement(ADD_CAR_VIEW);

            carStatement.setString(1, (String) parameters.get(MODEL));
            carStatement.setInt(2, ((Car.Type) parameters.get(CAR_TYPE)).ordinal());
            carStatement.setInt(3, (int) parameters.get(NUMBER_SEATS));
            carStatement.setInt(4, (int) parameters.get(RENT_COST));
            carStatement.setInt(5, ((Car.FuelType) parameters.get(FUEL_TYPE)).ordinal());
            carStatement.setInt(6, (int) parameters.get(FUEL_CONSUMPTION));
            carStatement.setBoolean(7, (boolean) parameters.get(CAR_AVAILABLE));
            isCarAdded = carStatement.executeUpdate() > 0;
            if (isCarAdded) {
                carViewStatement.setString(1, (String) parameters.get(EXTERIOR_SMALL));
                carViewStatement.setString(2, (String) parameters.get(EXTERIOR));
                carViewStatement.setString(3, (String) parameters.get(INTERIOR));
                isCarAdded = carViewStatement.executeUpdate() > 0;
            }
            if (isCarAdded) {
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                logger.log(Level.ERROR, "error while rollback committing car data", throwables);
            }
            throw new DaoProjectException("Error during add new car", e);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
                if (carStatement != null) {
                    carStatement.close();
                }
                if (carViewStatement != null) {
                    carViewStatement.close();
                }
            } catch (SQLException throwables) {
                logger.log(Level.ERROR, "error while rollback committing car data", throwables); // TODO: 20.10.2020 fatal?
            }
        }

        return isCarAdded;
    }

    @Override
    public boolean remove(Car car) throws DaoProjectException {
        return false;
    }

    @Override
    public boolean update(Car updatingCar) throws DaoProjectException {
        boolean isCarUpdated;
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CAR)) {
            statement.setString(1, updatingCar.getModel());
            statement.setInt(2, updatingCar.getType().ordinal());
            statement.setInt(3, updatingCar.getNumberSeats());
            statement.setInt(4, updatingCar.getRentCost());
            statement.setInt(5, updatingCar.getFuelType().ordinal());
            statement.setInt(6, updatingCar.getFuelConsumption());
            statement.setBoolean(7, updatingCar.isAvailable());
            statement.setLong(8, updatingCar.getCarId());
            isCarUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoProjectException("Error during searching all cars", e);
        }
        return isCarUpdated;
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

    @Override
    public List<Car> findAvailableOrderCars(Map<String, Object> carParameters) throws DaoProjectException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<Car> carList = new ArrayList<>();
        StringBuilder filteringAvailableCarsQuery = new StringBuilder();
        filteringAvailableCarsQuery.append(FIND_AVAILABLE_ORDER_CAR);
        if (carParameters.containsKey(PRICE_FROM) && carParameters.containsKey(PRICE_TO)) {
            filteringAvailableCarsQuery.append(AND_KEYWORD).append(CHECK_PRICE_RANGE);
        }
        if (carParameters.containsKey(CAR_TYPE)) {
            filteringAvailableCarsQuery.append(AND_KEYWORD).append(CHECK_CAR_TYPE);
        }
        filteringAvailableCarsQuery.append(AND_KEYWORD).append(CHECK_ORDERS_DATE_RANGE);

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

    @Override
    public List<Car> findCheckCars(Map<String, Object> carParameters) throws DaoProjectException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        List<Car> targetCars = new ArrayList<>();
        StringBuilder findCheckCarsQuery = new StringBuilder(FIND_ALL);
        if (carParameters.containsKey(CAR_TYPE) || carParameters.containsKey(CAR_AVAILABLE)) {
            findCheckCarsQuery.append(WHERE_KEYWORD);
            if (carParameters.containsKey(CAR_TYPE)) {
                findCheckCarsQuery.append(CHECK_CAR_TYPE);
            }
            if (carParameters.containsKey(CAR_AVAILABLE)) {
                if (carParameters.containsKey(CAR_TYPE)) {
                    findCheckCarsQuery.append(AND_KEYWORD);
                }
                findCheckCarsQuery.append(CHECK_CAR_AVAILABLE);
            }
        }

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(findCheckCarsQuery.toString())) {
            int parameterIndex = 0;
            if (carParameters.containsKey(CAR_TYPE)) {
                statement.setInt(++parameterIndex, ((Car.Type) carParameters.get(CAR_TYPE)).ordinal());
            }
            if (carParameters.containsKey(CAR_AVAILABLE)) {
                statement.setBoolean(++parameterIndex, (boolean) carParameters.get(CAR_AVAILABLE));
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetCars.add(createCar(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoProjectException("Error during searching cars for checking", e);
        }

        return targetCars;
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
