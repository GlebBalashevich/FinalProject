package by.balashevich.finalproject.model.dao.impl;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.model.dao.OrderDao;
import by.balashevich.finalproject.model.entity.Order;
import by.balashevich.finalproject.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class OrderDaoImpl implements OrderDao {
    private static final String ADD_ORDER = "INSERT INTO orders(date_from, date_to, amount, status, car_id, " +
            "client_id)VALUES (?, ?, ?, ?, ?, ?)";

    @Override
    public boolean add(Map<String, Object> orderParameters) throws DaoProjectException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        boolean isOrderAdded;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_ORDER)) {
            statement.setLong(1, (long) orderParameters.get(DATE_FROM));
            statement.setLong(2, (long) orderParameters.get(DATE_TO));
            statement.setInt(3, (int) orderParameters.get(AMOUNT));
            statement.setInt(4, ((Order.Status) orderParameters.get(ORDER_STATUS)).ordinal());
            statement.setLong(5, (long) orderParameters.get(CAR_ID));
            statement.setLong(6, (long) orderParameters.get(USER_ID));
            isOrderAdded = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoProjectException("Error while adding order to dataBase", e);
        }

        return isOrderAdded;
    }

    @Override
    public boolean remove(Order order) throws DaoProjectException {
        return false;
    }

    @Override
    public boolean update(Order order) throws DaoProjectException {
        return false;
    }

    @Override
    public Optional<Order> findById(long id) throws DaoProjectException {
        return Optional.empty();
    }

    @Override
    public List<Order> findAll() throws DaoProjectException {
        return null;
    }
}
