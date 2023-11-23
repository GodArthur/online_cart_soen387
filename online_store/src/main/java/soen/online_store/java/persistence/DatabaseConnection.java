package soen.online_store.java.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class manages the database connection.
 * For SQLite, only the URL is needed to establish the connection.
 * No username or password is required.
 * Ensure the SQLite JDBC driver JAR is included in your project dependencies.
 *
 * @author Korjon Chang-Jones
 */
public class DatabaseConnection {

    private final String url;

    public DatabaseConnection(String url) {
        this.url = url;
        
        // Attempt to load the SQLite JDBC driver
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            // Handle error that the driver class could not be found
            System.out.println("SQLite JDBC Driver not found.");
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        // DriverManager will recognize the JDBC URL for SQLite and provide the correct connection
        return DriverManager.getConnection(url);
    }
}
