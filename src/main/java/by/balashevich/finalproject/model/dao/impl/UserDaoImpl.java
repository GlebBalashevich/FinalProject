package by.balashevich.finalproject.model.dao.impl;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.builder.UserBuilder;
import by.balashevich.finalproject.model.dao.UserDao;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.pool.ConnectionPool;
import by.balashevich.finalproject.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class UserDaoImpl implements UserDao {
    private static final String EMPTY_VALUE = "";
    private static final String CHANGE_PASSWORD = "UPDATE users SET password = (?) where email=";
    private static final String CHANGE_STATUS = "UPDATE users SET status = (?) where email=(?)";
    private static final String FIND_ALL_BY_EMAIL = "SELECT user_id, email, user_role, first_name, second_name," +
            "driver_license, phone_number, status FROM users WHERE email = ?";
    private static final String FIND_PASSWORD_BY_EMAIL = "SELECT password FROM users WHERE email = ?";
    private static final String FIND_STATUS_BY_EMAIL = "SELECT status FROM users WHERE email = ?";
    private static final String FIND_EMAIL = "SELECT email FROM users WHERE email = ?";
    private static final String ADD_CLIENT = "INSERT INTO users(email, password, user_role, first_name, " +
            "second_name, driver_license, phone_number, status)VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public boolean add(Map<String, Object> userParameters) throws DaoProjectException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        boolean isClientAdded;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_CLIENT)) {
            statement.setString(1, (String) userParameters.get(EMAIL));
            statement.setString(2, (String) userParameters.get(PASSWORD));
            statement.setInt(3, ((User.Role) userParameters.get(ROLE)).ordinal());
            statement.setString(4, (String) userParameters.get(FIRST_NAME));
            statement.setString(5, (String) userParameters.get(SECOND_NAME));
            statement.setString(6, (String) userParameters.get(DRIVER_LICENSE));
            statement.setLong(7, (long) userParameters.get(PHONE_NUMBER));
            statement.setInt(8, ((Client.Status) userParameters.get(USER_STATUS)).ordinal());
            isClientAdded = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoProjectException("Error during Dao adding client into database", e);
        }

        return isClientAdded;
    }

    @Override
    public boolean remove(User user) throws DaoProjectException {
        return false;
    }

    @Override
    public User update(User user) throws DaoProjectException {
        return null;
    }

    @Override
    public Optional<User> findById(long id) throws DaoProjectException {
        return null;
    }

    @Override
    public List<User> findAll() throws DaoProjectException {
        return null;
    }

    @Override
    public boolean updateClientStatus(String email, Client.Status status) throws DaoProjectException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        boolean isParameterChanged;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(CHANGE_STATUS)) {
            statement.setInt(1, status.ordinal());
            statement.setString(2, email);
            isParameterChanged = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoProjectException("Error during changing password in database", e);
        }

        return isParameterChanged;
    }

    @Override
    public Client.Status findStatusByEmail(String targetEmail) throws DaoProjectException {
        Client.Status targetStatus = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_STATUS_BY_EMAIL)) {
            statement.setString(1, targetEmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                targetStatus = Client.Status.getClientStatus(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DaoProjectException("Error during searching client status by email", e);
        }

        return targetStatus;
    }

    @Override
    public Optional<User> findByEmail(String targetEmail) throws DaoProjectException {
        Optional<User> targetUser = Optional.empty();
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_EMAIL)) {
            statement.setString(1, targetEmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Map<String, Object> userParameters = new HashMap<>();
                userParameters.put(USER_ID, resultSet.getLong(USER_ID));
                userParameters.put(EMAIL, resultSet.getString(EMAIL));
                userParameters.put(ROLE, User.Role.getUserRole(resultSet.getInt(ROLE)));
                userParameters.put(FIRST_NAME, resultSet.getString(FIRST_NAME));
                userParameters.put(SECOND_NAME, resultSet.getString(SECOND_NAME));
                userParameters.put(DRIVER_LICENSE, resultSet.getString(DRIVER_LICENSE));
                userParameters.put(PHONE_NUMBER, resultSet.getLong(PHONE_NUMBER));
                userParameters.put(USER_STATUS, Client.Status.getClientStatus(resultSet.getInt(USER_STATUS)));

                targetUser = Optional.of(UserBuilder.buildUser(userParameters));
            }
        } catch (SQLException e) {
            throw new DaoProjectException("Error during searching user by email", e);
        }

        return targetUser;
    }

    @Override
    public String findPasswordByEmail(String targetEmail) throws DaoProjectException {
        String userPassword = EMPTY_VALUE;
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PASSWORD_BY_EMAIL)) {
            statement.setString(1, targetEmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userPassword = resultSet.getString(PASSWORD);
            }
        } catch (SQLException e) {
            throw new DaoProjectException("Error during searching password by email", e);
        }

        return userPassword;
    }

    @Override
    public String existEmail(String targetEmail) throws DaoProjectException {
        String email = EMPTY_VALUE;
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_EMAIL)) {
            statement.setString(1, targetEmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                email = resultSet.getString(EMAIL);
            }
        } catch (SQLException e) {
            throw new DaoProjectException("Error during searching email in database", e);
        }

        return email;
    }
}
