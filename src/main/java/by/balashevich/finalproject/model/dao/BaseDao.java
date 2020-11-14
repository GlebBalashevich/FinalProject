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

/**
 * The interface Base dao.
 *
 * @param <T> the type parameter
 * @author Balashevich Gleb
 * @version 1.0
 */
public interface BaseDao<T extends Entity> {
    /**
     * The constant logger.
     */
    Logger logger = LogManager.getLogger();

    /**
     * Add boolean.
     *
     * @param parameters the parameters
     * @return the boolean
     * @throws DaoProjectException the dao project exception
     */
    boolean add(Map<String, Object> parameters) throws DaoProjectException;

    /**
     * Remove boolean.
     *
     * @param t the t
     * @return the boolean
     * @throws DaoProjectException the dao project exception
     */
    boolean remove(T t) throws DaoProjectException;

    /**
     * Update boolean.
     *
     * @param t the t
     * @return the boolean
     * @throws DaoProjectException the dao project exception
     */
    boolean update(T t) throws DaoProjectException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoProjectException the dao project exception
     */
    Optional<T> findById(long id) throws DaoProjectException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoProjectException the dao project exception
     */
    List<T> findAll() throws DaoProjectException;

    /**
     * autocommit.
     *
     * @param connection the connection
     */
    default void autocommit(Connection connection, boolean type) {
        if (connection != null) {
            try {
                connection.setAutoCommit(type);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Error occurred while changing autocommit to " + type, e);
            }
        }
    }

    /**
     * rollback.
     *
     * @param connection the connection
     */
    default void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Error while rollback committing car data", e);
            }
        }
    }

    /**
     * Close.
     *
     * @param statement the statement
     */
    default void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Error occurred while closing the statement", e);
            }
        }
    }

    /**
     * Close.
     *
     * @param connection the connection
     */
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
