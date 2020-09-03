package by.balashevich.finalproject.model.connection;

import by.balashevich.finalproject.exception.ConnectionDatabaseException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/final_project?serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Java1234";
    private static final int POOL_SIZE = 32;
    private static ConnectionPool connectionPool = new ConnectionPool();
    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> givenConnections;

    public static ConnectionPool getInstance(){
        return connectionPool;
    }

    private ConnectionPool(){
        try {
            Class.forName(DRIVER_NAME);
            freeConnections = new LinkedBlockingQueue<>(POOL_SIZE);
            givenConnections = new ArrayDeque<>(POOL_SIZE);

            for(int i = 0; i < POOL_SIZE; i++){
                Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                freeConnections.offer(new ProxyConnection(connection));
            }

        } catch (ClassNotFoundException | SQLException e) {
            logger.log(Level.ERROR, "Error during connection pool creating", e);
            //todo may be need throw runtime exception?
        }
    }

    public Connection getConnection() throws ConnectionDatabaseException {
        ProxyConnection connection;

        try {
            connection = freeConnections.take();
            givenConnections.offer(connection);
        } catch (InterruptedException e) {
            throw new ConnectionDatabaseException("error getting the connection to database", e);
        }

        return connection;
    }

    public void releaseConnection(Connection connection){
        if (connection instanceof ProxyConnection) {
            givenConnections.remove(connection);
            freeConnections.offer((ProxyConnection) connection);
        } else {
            logger.log(Level.WARN, "Invalid connection to realizing");
        }
    }

    public void destroyPool() throws ConnectionDatabaseException {
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                freeConnections.take().reallyClose();
            }
            deregisterDrivers();
        } catch (SQLException | InterruptedException e) {
            throw new ConnectionDatabaseException("Error during connection pool destroying", e);
        }
    }

    private void deregisterDrivers() throws SQLException {
        while (DriverManager.getDrivers().hasMoreElements()) {
            DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
        }
    }
}
