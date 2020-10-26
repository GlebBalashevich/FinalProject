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
import java.util.*;

import static by.balashevich.finalproject.util.ParameterKey.*;

public class UserDaoImpl implements UserDao {
    private static final String ADD_CLIENT = "INSERT INTO users(email, password, user_role, first_name, " +
            "second_name, driver_license, phone_number, client_status)VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_ALL = "SELECT user_id, email, user_role, first_name, second_name, " +
            "driver_license, phone_number, client_status FROM users";
    private static final String FIND_ALL_CLIENTS = FIND_ALL + " WHERE user_role = 1";
    private static final String FIND_CLIENTS_BY_STATUS = FIND_ALL + " WHERE client_status = ?";
    private static final String FIND_BY_EMAIL = FIND_ALL + " WHERE email = ?";
    private static final String FIND_PASSWORD_BY_EMAIL = "SELECT password FROM users WHERE email = ?";
    private static final String FIND_STATUS_BY_EMAIL = "SELECT client_status FROM users WHERE email = ?";
    private static final String FIND_EMAIL = "SELECT email FROM users WHERE email = ?";
    private static final String CHANGE_STATUS = "UPDATE users SET client_status = (?) where email=(?)";
    private static final String EMPTY_VALUE = "";

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
            statement.setInt(8, ((Client.Status) userParameters.get(CLIENT_STATUS)).ordinal());
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
    public boolean update(User user) throws DaoProjectException {
        return false;
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
    public List<Client> findAllClients() throws DaoProjectException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        List<Client> targetClients = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_CLIENTS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetClients.add((Client) createUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoProjectException("Error during changing password in database", e);
        }

        return targetClients;
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
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Client.Status targetStatus = null;

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
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Optional<User> targetUser;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_EMAIL)) {
            statement.setString(1, targetEmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                targetUser = Optional.of(createUser(resultSet));
            } else {
                targetUser = Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoProjectException("Error during searching user by email", e);
        }

        return targetUser;
    }

    @Override
    public String findPasswordByEmail(String targetEmail) throws DaoProjectException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        String userPassword = EMPTY_VALUE;

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
    public List<Client> findClientsByStatus(Client.Status clientStatus) throws DaoProjectException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        List<Client> targetClients = new ArrayList<>();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_CLIENTS_BY_STATUS)) {
            statement.setInt(1, clientStatus.ordinal());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                targetClients.add((Client) createUser(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoProjectException("Error during searching clients by status", e);
        }

        return targetClients;
    }

    @Override
    public String existEmail(String targetEmail) throws DaoProjectException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        String email = EMPTY_VALUE;

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

    private User createUser(ResultSet resultSet) throws SQLException {
        Map<String, Object> userParameters = new HashMap<>();
        userParameters.put(USER_ID, resultSet.getLong(USER_ID));
        userParameters.put(EMAIL, resultSet.getString(EMAIL));
        userParameters.put(ROLE, User.Role.getUserRole(resultSet.getInt(ROLE)));
        userParameters.put(FIRST_NAME, resultSet.getString(FIRST_NAME));
        userParameters.put(SECOND_NAME, resultSet.getString(SECOND_NAME));
        userParameters.put(DRIVER_LICENSE, resultSet.getString(DRIVER_LICENSE));
        userParameters.put(PHONE_NUMBER, resultSet.getLong(PHONE_NUMBER));
        userParameters.put(CLIENT_STATUS, Client.Status.getClientStatus(resultSet.getInt(CLIENT_STATUS)));

        return UserBuilder.buildUser(userParameters);
    }
}
