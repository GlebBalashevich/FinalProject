package by.balashevich.finalproject.model.dao.impl;

import by.balashevich.finalproject.creator.ClientCreator;
import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.model.dao.BaseDao;
import by.balashevich.finalproject.model.dao.TableColumnName;
import by.balashevich.finalproject.model.entity.Client;
import by.balashevich.finalproject.model.entity.ClientStatus;
import by.balashevich.finalproject.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ClientDaoImpl implements BaseDao<Client> {
    private static final String ADD_CLIENT_QUERY_SQL = "INSERT INTO users(login, password, user_role, first_name, " +
            "second_name, driver_license, email, phone_number, status)VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_LOGIN_QUERY_SQL = "SELECT user_id, login, first_name, second_name, driver_license" +
            "email, phone_number, status FROM users WHERE login = ?";

    public boolean add(Client client, String password) throws DaoProjectException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        boolean isClientAdded;

        try(Connection connection = connectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(ADD_CLIENT_QUERY_SQL)){
            statement.setString(1, client.getLogin());
            statement.setString(2, password);
            statement.setInt(3, client.getRole().ordinal());
            statement.setString(4, client.getFirstName());
            statement.setString(5, client.getSecondName());
            statement.setString(6, client.getDriverLicense());
            statement.setString(7, client.getEmail());
            statement.setInt(8, client.getPhoneNumber());
            statement.setInt(9, client.getStatus().ordinal());
            isClientAdded = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoProjectException("Error during Dao adding client into database", e);
        }

        return isClientAdded;
    }

    @Override
    public boolean add(Client client) throws DaoProjectException {
        return false;
    }

    @Override
    public boolean remove(Client client) throws DaoProjectException {
        return false;
    }

    @Override
    public Client update(Client client) throws DaoProjectException {
        return null;
    }

    @Override
    public Client findById(long id) throws DaoProjectException {
        return null;
    }

    public Optional<Client> findByLogin(String targetLogin) throws DaoProjectException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        ClientCreator clientCreator = new ClientCreator();
        Optional<Client> targetClient = Optional.empty();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_QUERY_SQL)) {
            statement.setString(1, targetLogin);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long userId = resultSet.getLong(TableColumnName.USERID);
                String login = resultSet.getString(TableColumnName.LOGIN);
                String firstName = resultSet.getString(TableColumnName.FIRST_NAME);
                String secondName = resultSet.getString(TableColumnName.SECOND_NAME);
                String driverLicense = resultSet.getString(TableColumnName.DRIVER_LICENSE);
                String email = resultSet.getString(TableColumnName.EMAIL);
                int phoneNumber = resultSet.getInt(TableColumnName.PHONE_NUMBER);
                ClientStatus status = ClientStatus.getClientStatus(resultSet.getInt(TableColumnName.STATUS));
                Client client = clientCreator.createClient(userId, login, firstName, secondName,
                        driverLicense, email, phoneNumber, status);
                targetClient = Optional.of(client);
            }
        } catch (SQLException e) {
            throw new DaoProjectException("Error during searching client by login", e);
        }

        return targetClient;
    }
}
