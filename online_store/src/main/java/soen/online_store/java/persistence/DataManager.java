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
     * @param dbConnection A database Connection object
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
    
    public void updateProduct(String sku, String name, String description, String vendor, String urlSlug, Double price) throws SQLException {
    List<String> fieldsToUpdate = new ArrayList<>();
    
    if (name != null) fieldsToUpdate.add("name = ?");
    if (description != null) fieldsToUpdate.add("description = ?");
    if (vendor != null) fieldsToUpdate.add("vendor = ?");
    if (urlSlug != null) fieldsToUpdate.add("urlSlug = ?");
    if (price != null) fieldsToUpdate.add("price = ?");
    
    if (fieldsToUpdate.isEmpty()) {
        throw new IllegalArgumentException("No fields provided for update");
    }
    
    String sql = "UPDATE PRODUCTS SET " + String.join(", ", fieldsToUpdate) + " WHERE sku = ?";
    
    try (Connection conn = dbConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        int index = 1;
        if (name != null) ps.setString(index++, name);
        if (description != null) ps.setString(index++, description);
        if (vendor != null) ps.setString(index++, vendor);
        if (urlSlug != null) ps.setString(index++, urlSlug);
        if (price != null) ps.setDouble(index++, price);
        
        ps.setString(index, sku);
        
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product with SKU " + sku + " not found.");
        }
    }
}


		/*
		 * try-catch blocks for cart-related methods
		 */

public List<Product> getCart(String user) {
    List<Product> cartProducts = new ArrayList<>();
    try (Connection conn = dbConnection.getConnection()) {
        String sql = "SELECT p.* FROM PRODUCTS p " +
                     "JOIN CART_ITEMS ci ON p.sku = ci.sku " +
                     "JOIN CARTS c ON ci.cart_id = c.cart_id " +
                     "JOIN USERS u ON c.user_id = u.user_id " +
                     "WHERE u.username = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            // Assuming the Product constructor takes the following parameters: name, description, vendor, urlSlug, sku, price
            String name = rs.getString("name");
            String description = rs.getString("description");
            String vendor = rs.getString("vendor");
            String urlSlug = rs.getString("urlSlug");
            String sku = rs.getString("sku");
            double price = rs.getDouble("price");
            
            Product product = new Product(name, description, vendor, urlSlug, sku, price);
            cartProducts.add(product);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return cartProducts;
}


// Function to add a product to the user's cart
public void addProductToCart(String username, String sku) {
    try (Connection conn = dbConnection.getConnection()) {
        // First, find the user's cart_id
        String findCartSql = "SELECT cart_id FROM CARTS WHERE user_id = (SELECT user_id FROM USERS WHERE username = ?)";
        PreparedStatement findCartPs = conn.prepareStatement(findCartSql);
        findCartPs.setString(1, username);
        ResultSet cartRs = findCartPs.executeQuery();

        // Check if the cart_id is found
        if (cartRs.next()) {
            int cartId = cartRs.getInt("cart_id");

            // Insert the product into the cart
            String insertSql = "INSERT INTO CART_ITEMS (cart_id, sku) VALUES (?, ?)";
            PreparedStatement insertPs = conn.prepareStatement(insertSql);
            insertPs.setInt(1, cartId);
            insertPs.setString(2, sku);
            insertPs.executeUpdate();
        } else {
            // Handle the case where the user does not have a cart_id
            // This might involve creating a new cart or throwing an exception
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


// Function to remove a product from the user's cart
public void removeProductFromCart(String username, String sku) {
    try (Connection conn = dbConnection.getConnection()) {
        // First, find the user's cart_id
        String findCartSql = "SELECT cart_id FROM CARTS WHERE user_id = (SELECT user_id FROM USERS WHERE username = ?)";
        PreparedStatement findCartPs = conn.prepareStatement(findCartSql);
        findCartPs.setString(1, username);
        ResultSet cartRs = findCartPs.executeQuery();

        // Check if the cart_id is found
        if (cartRs.next()) {
            int cartId = cartRs.getInt("cart_id");

            // Delete the product from the cart
            String deleteSql = "DELETE FROM CART_ITEMS WHERE cart_id = ? AND sku = ?";
            PreparedStatement deletePs = conn.prepareStatement(deleteSql);
            deletePs.setInt(1, cartId);
            deletePs.setString(2, sku);
            deletePs.executeUpdate();
        } else {
            // Handle the case where the user does not have a cart_id
            // This could be an exception or a no-op if it's valid for a user not to have a cart
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    // Function to set product quantity in the user's cart
public void setProductQuantityInCart(String username, String sku, int quantity) {
    try (Connection conn = dbConnection.getConnection()) {
        // First, find the user's cart_id
        String findCartSql = "SELECT cart_id FROM CARTS WHERE user_id = (SELECT user_id FROM USERS WHERE username = ?)";
        PreparedStatement findCartPs = conn.prepareStatement(findCartSql);
        findCartPs.setString(1, username);
        ResultSet cartRs = findCartPs.executeQuery();

        // Check if the cart_id is found
        if (cartRs.next()) {
            int cartId = cartRs.getInt("cart_id");

            // Check if the product is already in the cart
            String sqlCheck = "SELECT quantity FROM CART_ITEMS WHERE cart_id = ? AND sku = ?";
            PreparedStatement psCheck = conn.prepareStatement(sqlCheck);
            psCheck.setInt(1, cartId);
            psCheck.setString(2, sku);
            ResultSet rs = psCheck.executeQuery();

            // If the product is already in the cart, update the quantity
            if (rs.next()) {
                String sqlUpdate = "UPDATE CART_ITEMS SET quantity = ? WHERE cart_id = ? AND sku = ?";
                PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
                psUpdate.setInt(1, quantity);
                psUpdate.setInt(2, cartId);
                psUpdate.setString(3, sku);
                psUpdate.executeUpdate();
            } else if (quantity > 0) { // If not in the cart and quantity is greater than zero, add it
                addProductToCart(username, sku); // Re-use your existing method adapted to use username
                setProductQuantityInCart(username, sku, quantity); // Recursively call to set the quantity
            }
        } else {
            // Handle the case where the user does not have a cart_id
            // This could be an exception or a no-op if it's valid for a user not to have a cart
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


// Function to clear the cart for a given user
public void clearCart(String username) {
    try (Connection conn = dbConnection.getConnection()) {
        // First, find the user's cart_id
        String findCartSql = "SELECT cart_id FROM CARTS WHERE user_id = (SELECT user_id FROM USERS WHERE username = ?)";
        PreparedStatement findCartPs = conn.prepareStatement(findCartSql);
        findCartPs.setString(1, username);
        ResultSet cartRs = findCartPs.executeQuery();

        // If the user has a cart, clear it
        if (cartRs.next()) {
            int cartId = cartRs.getInt("cart_id");

            // Delete the cart items for the found cart_id
            String sqlDelete = "DELETE FROM CART_ITEMS WHERE cart_id = ?";
            PreparedStatement psDelete = conn.prepareStatement(sqlDelete);
            psDelete.setInt(1, cartId);
            psDelete.executeUpdate();

            // Optionally, if the entire cart should be removed, not just its items, add:
            // String sqlDeleteCart = "DELETE FROM CARTS WHERE cart_id = ?";
            // PreparedStatement psDeleteCart = conn.prepareStatement(sqlDeleteCart);
            // psDeleteCart.setInt(1, cartId);
            // psDeleteCart.executeUpdate();
        }
        // If no cart exists, no action is taken.
    } catch (SQLException e) {
        e.printStackTrace();
    }
}







    



public void createOrder(String user, String shippingAddress) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        conn = dbConnection.getConnection();
        // Start transaction
        conn.setAutoCommit(false);
        
        // Step 1: Retrieve the cart_id
        String getCartIdSql = "SELECT cart_id FROM CARTS WHERE user_id = (SELECT user_id FROM USERS WHERE username = ?)";
        ps = conn.prepareStatement(getCartIdSql);
        ps.setString(1, user);
        rs = ps.executeQuery();
        int cartId = -1;
        if (rs.next()) {
            cartId = rs.getInt("cart_id");
        } else {
            // Handle the case where there is no cart for the user.
            // For example, throw an exception or return early.
            conn.rollback(); // Rollback transaction as there's nothing to do
            return;
        }
        
        // Step 2: Insert a new order
        String insertOrderSql = "INSERT INTO ORDERS (shipping_address, user_id) VALUES (?, (SELECT user_id FROM USERS WHERE username = ?))";
        ps = conn.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, shippingAddress);
        ps.setString(2, user);
        ps.executeUpdate();
        rs = ps.getGeneratedKeys();
        int orderId = -1;
        if (rs.next()) {
            orderId = rs.getInt(1); // Retrieve the auto generated order_id
        }
        
        // Step 3: Copy cart items to order items
        String copyItemsSql = "INSERT INTO ORDER_ITEMS (sku, order_id, quantity) SELECT sku, ?, quantity FROM CART_ITEMS WHERE cart_id = ?";
        ps = conn.prepareStatement(copyItemsSql);
        ps.setInt(1, orderId);
        ps.setInt(2, cartId);
        ps.executeUpdate();
        
        // Commit transaction
        conn.commit();
    } catch (SQLException e) {
        if (conn != null) {
            try {
                conn.rollback(); // Rollback on error
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        e.printStackTrace();
    } finally {
        // Clean up resources
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) {
                conn.setAutoCommit(true); // Reset auto-commit to default
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



public List<Order> getOrders(int userId) {
    List<Order> orders = new ArrayList<>();
    String sql = "SELECT order_id, shipping_address, tracking_number, is_shipped FROM ORDERS WHERE user_id = ?";

    try (Connection conn = dbConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Order order = new Order();
            order.setOrderId(rs.getInt("order_id"));
            order.setShippingAddress(rs.getString("shipping_address"));
            order.setTrackingNumber(rs.getString("tracking_number"));
            order.setShipped(rs.getBoolean("is_shipped"));

            orders.add(order);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return orders;
}




public Order getOrder(String user, int orderId) throws SQLException {
    Order order = null;
    try (Connection conn = dbConnection.getConnection()) {
        String sql;

        if (user != null && !user.isEmpty()) {
            // SQL to check that the order belongs to the user
            sql = "SELECT * FROM ORDERS o " +
                  "JOIN ORDER_ITEMS oi ON o.order_id = oi.order_id " +
                  "WHERE o.order_id = ? AND o.user_id = (SELECT user_id FROM USERS WHERE username = ?)";
        } else {
            // SQL to just retrieve the order without checking the user
            sql = "SELECT * FROM ORDERS o " +
                  "JOIN ORDER_ITEMS oi ON o.order_id = oi.order_id " +
                  "WHERE o.order_id = ?";
        }

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);

            if (user != null && !user.isEmpty()) {
                ps.setString(2, user);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    if (order == null) {
                        order = new Order();
                        order.setOrderId(rs.getInt("order_id"));
                        order.setShippingAddress(rs.getString("shipping_address"));
                        order.setTrackingNumber(rs.getString("tracking_number"));
                    }

                    OrderItem item = new OrderItem();
                    item.setSku(rs.getString("sku"));
                    item.setQuantity(rs.getInt("quantity"));

                    order.addItem(item);
                }
            }
        }
    }

    if (order == null && user != null && !user.isEmpty()) {
        // If no order was found and a username was provided, throw an exception
        throw new SQLException("Order not found or does not belong to the user");
    }

    return order;
}



public void shipOrder(int orderId, String trackingNumber) throws SQLException {
    // SQL to update the tracking number and is_shipped status
    String sqlUpdateOrder = "UPDATE ORDERS SET tracking_number = ?, is_shipped = TRUE WHERE order_id = ? AND is_shipped = FALSE";

    try (Connection conn = dbConnection.getConnection();
         PreparedStatement psUpdateOrder = conn.prepareStatement(sqlUpdateOrder)) {
        
        // Set the tracking number
        psUpdateOrder.setString(1, trackingNumber);
        // Set the order ID
        psUpdateOrder.setInt(2, orderId);
        
        int rowsAffected = psUpdateOrder.executeUpdate();
        if (rowsAffected == 0) {
            // No rows affected means either the order doesn't exist or it's already been shipped
            throw new SQLException("Order not found or it's already been shipped.");
        }
    }
}


	
    

    
}
