package soen.online_store.java.persistence;

import soen.online_store.java.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author Korjon Chang-Jones
 */
public class DataManager {

    private final DatabaseConnection dbConnection;

    /**
     * Constructor for the DataManager
     * @param da A database Connection object
     */
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
    
    public List<Product> getAllProducts(){
        
        List<Product> products = new ArrayList<>();
        Product p;
        try (Connection conn = dbConnection.getConnection()) {

            
            //Query String
            String sql = "select name, sku, description, vendor, urlSlug, price from PRODUCTS";
            
            //Creating the query object used to execute the query
            PreparedStatement ps = conn.prepareStatement(sql);
          

            //Resultset is the set of rows returned from the query
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
                
                products.add(p);
            }
            
           return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
           
            return products;
        
    }
    
    /**
     * Method gets a product based on an SKU
     * @param sku
     * @return 
     */
    public Product getProduct(String sku) {

        Product p = null;
        try (Connection conn = dbConnection.getConnection()) {

            
            //Query String
            String sql = "select name, sku, description, vendor, urlSlug, price from PRODUCTS where sku = ?";
            
            //Creating the query object used to execute the query
            PreparedStatement ps = conn.prepareStatement(sql);
            
            //Setting the value in the question mark
            ps.setString(1, sku);

            //Resultset is the set of rows returned from the query
            ResultSet rs = ps.executeQuery();

            //Iterating through the resultSet (or rows)
            //And checking if there is a next row to iterate over
            //next() is the current row, so if it currently has a row
            //to check
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
    
    
    /**
     * Method gets a product based on an SKU
     * @param urlSlug
     * @return a Product
     */
    public Product getProductBySlug(String urlSlug) {

        Product p = null;
        try (Connection conn = dbConnection.getConnection()) {

            
            //Query String
            String sql = "select name, sku, description, vendor, urlSlug, price from PRODUCTS where urlSlug = ?";
            
            //Creating the query object used to execute the query
            PreparedStatement ps = conn.prepareStatement(sql);
            
            //Setting the value in the question mark
            ps.setString(1, urlSlug);

            //Resultset is the set of rows returned from the query
            ResultSet rs = ps.executeQuery();

            //Iterating through the resultSet (or rows)
            //And checking if there is a next row to iterate over
            //next() is the current row, so if it currently has a row
            //to check
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
