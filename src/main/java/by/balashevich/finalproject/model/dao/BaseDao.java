package by.balashevich.finalproject.model.dao;

import by.balashevich.finalproject.exception.DaoProjectException;
import by.balashevich.finalproject.model.entity.Entity;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BaseDao<T extends Entity> {
    Logger logger = LogManager.getLogger();

    boolean add(Map<String, Object> parameters) throws DaoProjectException;

    boolean remove(T t) throws DaoProjectException;

    boolean update(T t) throws DaoProjectException;

    Optional<T> findById(long id) throws DaoProjectException;

    List<T> findAll() throws DaoProjectException;

    default void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Error occurred while closing the statement", e);
            }
        }
    }

    default void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Error occurred while closing the connection", e);
            }
        }
    }
}
