package soen.online_store.java.persistence;

import soen.online_store.java.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Korjon Chang-Jones
 */
public class DataManager {

    private final DatabaseConnection dbConnection;

    public DataManager(DatabaseConnection dbConnection) {

        this.dbConnection = dbConnection;

    }

    /**
     * Function creates products
     *
     * @param sku
     * @param name
     */
    public void createProduct(String sku, String name) {

        try (Connection conn = dbConnection.getConnection()) {

            String sql = "INSERT INTO PRODUCTS (sku, name) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sku);
            ps.setString(2, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    
    /**
     * Method gets a product based on an SKU
     * @param sku
     * @return 
     */
    public Product getProduct(String sku) {

        Product p = null;
        try (Connection conn = dbConnection.getConnection()) {

            String sql = "select * from PRODUCTS where sku = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sku);

            ResultSet rs = ps.executeQuery();

            
            while (rs.next()) {

                p = new Product(
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("vendor"),
                        rs.getString("urlSlug"),
                        rs.getString("sku"),
                        rs.getDouble("price")
                );
            }
            
           return p;
        } catch (SQLException e) {
            e.printStackTrace();
        }
           
            return p;
        
    }

}
