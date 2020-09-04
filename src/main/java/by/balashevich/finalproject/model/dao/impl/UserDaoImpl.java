package by.balashevich.finalproject.model.dao.impl;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.model.entity.UserRole;
import by.balashevich.finalproject.model.pool.ConnectionPool;
import by.balashevich.finalproject.model.dao.BaseDao;
import by.balashevich.finalproject.model.dao.TableColumnName;
import by.balashevich.finalproject.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDaoImpl implements BaseDao<User> {
    private static final String FIND_ALL_QUERY_SQL = "SELECT userid, login, password, role FROM user";
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

    public Optional<User> findByLogin(String targetLogin) throws DaoProjectException {
        Optional<User> targetUser = Optional.empty();
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_QUERY_SQL)) {
            statement.setString(1, targetLogin);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long userId = resultSet.getLong(TableColumnName.USERID);
                String login = resultSet.getString(TableColumnName.LOGIN);
                String password = resultSet.getString(TableColumnName.PASSWORD);
                UserRole role = UserRole.valueOf(resultSet.getString(TableColumnName.ROLE));
                targetUser = Optional.of(new User(userId, login, password, role));
            }
        } catch (SQLException e) {
            throw new DaoProjectException("Error during searching user by login", e);
        }

        return targetUser;
    }
}
