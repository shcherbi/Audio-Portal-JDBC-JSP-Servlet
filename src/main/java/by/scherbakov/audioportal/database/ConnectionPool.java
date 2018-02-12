package by.scherbakov.audioportal.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * {@code ConnectionPool} class represents an ability to connect with
 * database.
 *
 * @author ScherbakovIlia
 */

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool connectionPool;
    private BlockingQueue<Connection> connectionQueue;
    private static AtomicBoolean instanceExist = new AtomicBoolean(false);
    private static ReentrantLock threadLock = new ReentrantLock();
    private static final DatabaseConfiguration DB_CONFIG = new DatabaseConfiguration();

    /**
     * Creates an entity of {@code ConnectionPool}.
     * Fills the BlockingQueue with connections.
     */
    private ConnectionPool() {
        connectionQueue = new ArrayBlockingQueue<>(DB_CONFIG.getPoolSize());
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < DB_CONFIG.getPoolSize(); i++) {
            addConnection();
        }
    }

    /**
     * Creates connection pool or take it if it is already exist.
     *
     * @return ConnectionPool object.
     */
    public static ConnectionPool getInstance() {
        if (!instanceExist.get()) {
            threadLock.lock();
            try {
                if (connectionPool == null) {
                    connectionPool = new ConnectionPool();
                    instanceExist.getAndSet(true);
                }
            } finally {
                threadLock.unlock();
            }
        }
        return connectionPool;
    }

    /**
     * Adds Connection to the BlockingQueue of connections.
     */
    private void addConnection() {
        try {
            Connection connection = DriverManager.getConnection(DB_CONFIG.getURL(), DB_CONFIG.getUser(),
                    DB_CONFIG.getPassword());
            this.connectionQueue.put(connection);
        } catch (SQLException | InterruptedException e) {
            LOGGER.error("Connection wasn't add into connection pool", e);
        }
    }

    /**
     * Takes Connection from the pool
     *
     * @return Connection object
     */
    public Connection takeConnection() {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            LOGGER.error("Couldn't take connection from connection pool", e);
        }
        return connection;
    }

    /**
     * Returns Connection to the pool
     *
     * @param connection is Connection object
     */
    public void closeConnection(Connection connection) {
        try {
            connectionQueue.put(connection);
        } catch (InterruptedException e) {
            LOGGER.error("Couldn't close connection", e);
        }
    }

    /**
     * Closes pool by closing all connections in a pool
     */
    public void closePool() {
        try {
            for (int i = 0; i < DB_CONFIG.getPoolSize(); i++) {
                connectionQueue.take().close();
            }
        } catch (SQLException | InterruptedException e) {
            LOGGER.error("Couldn't close connection pool", e);
        }
    }
}
