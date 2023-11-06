package soen.online_store.java.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Korjon Chang-Jones
 */
public class DatabaseConnection {

    private final String url;
    private final String user;
    private final String password;
    private final String driver;

    public DatabaseConnection(String url, String user, String password, String driver) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.driver = driver;
    }

    public Connection getConnection() throws SQLException {
        // Ensure the JDBC driver is loaded (may be unnecessary with newer JDBC versions)
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found", e);
        }
        return DriverManager.getConnection(url, user, password);
    }
}
