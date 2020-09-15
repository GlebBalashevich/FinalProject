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
    private static final String CHANGE_PASSWORD_QUERY_SQL = "UPDATE users SET password = (?) where login=";
    private static final String FIND_BY_LOGIN_QUERY_SQL = "SELECT user_id, login, user_role FROM users WHERE login = ?";
    private static final String FIND_PASSWORD_BY_LOGIN_QUERY_SQL = "SELECT password FROM users WHERE login = ?";

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

    public boolean updatePassword(User user, String changingPassword) throws DaoProjectException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        String changePasswordFullQuery = CHANGE_PASSWORD_QUERY_SQL + user.getLogin();
        boolean isPasswordChanged;

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(changePasswordFullQuery)) {
            statement.setString(1, changingPassword);
            isPasswordChanged = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoProjectException("Error during changing password in database", e);
        }

        return isPasswordChanged;
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
                UserRole role = UserRole.getUserRole(resultSet.getInt(TableColumnName.ROLE));

                targetUser = Optional.of(new User(userId, login, role));
            }
        } catch (SQLException e) {
            throw new DaoProjectException("Error during searching user by login", e);
        }

        return targetUser;
    }

    public String findPasswordByLogin(String targetLogin) throws DaoProjectException {
        String userPassword = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PASSWORD_BY_LOGIN_QUERY_SQL)) {
            statement.setString(1, targetLogin);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userPassword = resultSet.getString(TableColumnName.PASSWORD);
            }
        } catch (SQLException e) {
            throw new DaoProjectException("Error during searching password by login", e);
        }

        return userPassword;
    }
}
