
package soen.online_store.java.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Korjon Chang-Jones
 */
public class DatabaseConnection {
    
     private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/spendr";
    private static final String DATABASE_USER = "dbuser";
    private static final String DATABASE_PASSWORD = "dbpass";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
