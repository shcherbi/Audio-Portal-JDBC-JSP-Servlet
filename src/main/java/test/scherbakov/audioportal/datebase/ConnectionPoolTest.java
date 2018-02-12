package test.scherbakov.audioportal.datebase;

import by.scherbakov.audioportal.database.ConnectionPool;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Class {@code ConnectionPoolTest} is used to test
 * taking and putting connections from and into pool
 */
public class ConnectionPoolTest {
    private static ConnectionPool pool;
    private static ArrayList<Connection> connections;

    @BeforeClass
    public static void initConnectionPool() {
        pool = ConnectionPool.getInstance();
    }

    @BeforeMethod
    public void initConnections() {
        connections = new ArrayList<>();
    }

    @AfterMethod
    public void destroyConnections() {
        connections.clear();
    }

    @AfterClass
    public static void closeConnectionPool() {
        pool.closePool();
    }

    @Test
    public void checkTakeConnection() {
        connections.add(pool.takeConnection());
        Assert.assertNotNull(connections.get(0));
        pool.closeConnection(connections.get(0));
    }

    @Test
    public void checkPutConnection() {
        Connection firstConnection = pool.takeConnection();
        pool.closeConnection(firstConnection);
        Connection secondConnection;
        for (int i = 0; i < 9; i++) {
            secondConnection = pool.takeConnection();
            pool.closeConnection(secondConnection);
        }
        secondConnection = pool.takeConnection();
        pool.closeConnection(secondConnection);
        Assert.assertEquals(firstConnection, secondConnection);
    }
}
