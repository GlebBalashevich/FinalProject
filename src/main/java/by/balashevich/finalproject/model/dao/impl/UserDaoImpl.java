package by.balashevich.finalproject.model.dao.impl;

import by.balashevich.finalproject.exception.ConnectionDatabaseException;
import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.model.connection.ConnectionPool;
import by.balashevich.finalproject.model.dao.BaseDao;
import by.balashevich.finalproject.model.dao.UserTableColumn;
import by.balashevich.finalproject.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements BaseDao<User> {
    private static final String FIND_ALL_QUERY_SQL = "SELECT userid, login, password, name FROM user";
    private static final String FIND_BY_LOGIN_QUERY_SQL = FIND_ALL_QUERY_SQL + " WHERE login = ?";

    @Override
    public boolean add(User user) throws DaoProjectException {
        return false;
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
    public User findById(long id) throws DaoProjectException {
        return null;
    }

    public User findByLogin(String targetLogin) throws DaoProjectException {
        User targetUser = null; //todo may be optional
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_QUERY_SQL)) {
            statement.setString(1, targetLogin);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long userId = resultSet.getLong(UserTableColumn.USERID.getColumnName());
                String login = resultSet.getString(UserTableColumn.LOGIN.getColumnName());
                String password = resultSet.getString(UserTableColumn.PASSWORD.getColumnName());
                String name = resultSet.getString(UserTableColumn.NAME.getColumnName());
                targetUser = new User(userId, login, password, name);
            }
        } catch (SQLException | ConnectionDatabaseException e) {
            throw new DaoProjectException("Error during searching user by login", e);
        }

        return targetUser;
    }
}
