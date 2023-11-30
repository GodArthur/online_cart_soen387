package soen.online_store.java.persistence;

import soen.online_store.java.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korjon Chang-Jones
 */
public class DataManager {

    private final DatabaseConnection dbConnection;

    /**
     * Constructor for the DataManager
     *
     * @param dbConnection A database Connection object
     */
    public DataManager(DatabaseConnection dbConnection) {

        this.dbConnection = dbConnection;

    }

    // Method to retrieve a user by username and password from the database
    public User getUserByUsernameAndPassword(String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            // Get a database connection
            connection = dbConnection.getConnection();

            // Create a SQL query to check the username and password
            String query = "SELECT * FROM USERS WHERE username = ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Check if a user with the provided username and password exists
            if (resultSet.next()) {
                // Create a User object with the retrieved data
                user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getBoolean("is_staff"),
                        // You can pass null for cart and orders, as these may be loaded separately
                        null,
                        null
                );
                // Add more user attributes as needed
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close database resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    // Define a method to update the isStaff attribute for a user based on password
    public void setUserIsStaff(int userID, boolean isStaff) throws SQLException {
        String sql = "UPDATE USERS SET is_staff = ? WHERE user_id = ?";

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBoolean(1, isStaff);
            ps.setInt(2, userID);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User's isStaff updated successfully!");
            } else {
                System.out.println("User with userID " + userID + " not found.");
            }
        }
    }

    /**
     * Function creates products
     *
     * @param sku
     * @param name
     * @param description
     * @param vendor
     * @param urlSlug
     * @param price
     */
    public void createProduct(String sku, String name, String description, String vendor, String urlSlug, double price) {

        // Define the SQL query for inserting a new product
        String sql = "INSERT INTO PRODUCTS (sku, name, description, vendor, urlSlug, price) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set the parameters for the SQL query
            ps.setString(1, sku);
            ps.setString(2, name);
            ps.setString(3, description);
            ps.setString(4, vendor);
            ps.setString(5, urlSlug);
            ps.setDouble(6, price);

            // Execute the insert query
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Product inserted successfully!");
            } else {
                System.out.println("Product not inserted.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Product> getAllProducts() {

        List<Product> products = new ArrayList<>();
        Product p;
        try (Connection conn = dbConnection.getConnection()) {

            //Query String
            String sql = "SELECT name, sku, description, vendor, urlSlug, price from PRODUCTS";

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
     *
     * @param sku
     * @return
     */
    public Product getProduct(String sku) {

        Product p = null;
        try (Connection conn = dbConnection.getConnection()) {

            //Query String
            String sql = "SELECT name, sku, description, vendor, urlSlug, price from PRODUCTS WHERE sku = ?";

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
     *
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

        if (name != null) {
            fieldsToUpdate.add("name = ?");
        }
        if (description != null) {
            fieldsToUpdate.add("description = ?");
        }
        if (vendor != null) {
            fieldsToUpdate.add("vendor = ?");
        }
        if (urlSlug != null) {
            fieldsToUpdate.add("urlSlug = ?");
        }
        if (price != null) {
            fieldsToUpdate.add("price = ?");
        }

        if (fieldsToUpdate.isEmpty()) {
            throw new IllegalArgumentException("No fields provided for update");
        }

        String sql = "UPDATE PRODUCTS SET " + String.join(", ", fieldsToUpdate) + " WHERE sku = ?";

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            int index = 1;
            if (name != null) {
                ps.setString(index++, name);
            }
            if (description != null) {
                ps.setString(index++, description);
            }
            if (vendor != null) {
                ps.setString(index++, vendor);
            }
            if (urlSlug != null) {
                ps.setString(index++, urlSlug);
            }
            if (price != null) {
                ps.setDouble(index++, price);
            }

            ps.setString(index, sku);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("Product with SKU " + sku + " not found.");
            }
        }
    }

    public void createCart(User user) {
        try (Connection conn = dbConnection.getConnection()) {

            String sql = "INSERT INTO CARTS (user_id) values (?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserID());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Cart getCart(User user) {

        Cart cart;

        int cartId = -1;
        if (user.getCart() != null) {
            cartId = user.getCart().getCartId();

        } else {

            try (Connection conn = dbConnection.getConnection()) {

                String sql = "SELECT cart_id FROM USERS "
                        + "INNER JOIN CARTS using(user_id)"
                        + "WHERE user_id = ?";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, user.getUserID());

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    cartId = rs.getInt("cart_id");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        List<CartItem> cartItems = getCartItems(cartId);

        cart = new Cart(cartId, cartItems);
        return cart;

    }

    /**
     * Helper function for getting cartItems in a cart
     *
     * @param cartId
     * @return
     */
    private List<CartItem> getCartItems(int cartId) {

        List<CartItem> cartItems = new ArrayList<>();

        try (Connection conn = dbConnection.getConnection()) {

            String sql = "SELECT sku, cart_id, quantity FROM CART_ITEMS"
                    + " WHERE cart_id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cartId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                //Getting product by sku that corresponds to this cart item
                Product p = getProduct(rs.getString("sku"));
                int quantity = rs.getInt("quantity");

                cartItems.add(new CartItem(p, quantity));
            }

            return cartItems;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }

// Function to add a product to the user's cart
    public void addProductToCart(User user, String sku) {

        int cartId = -1;

        String sql = "SELECT cart_id FROM CARTS WHERE user_id = ?";

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            if (user.getCart() != null) {
                cartId = user.getCart().getCartId();

            } else {

                // First, find the user's cart_id
                ps.setInt(1, user.getUserID());
                ResultSet rs = ps.executeQuery();

                // Check if the cart_id is found
                if (rs.next()) {
                    cartId = rs.getInt("cart_id");

                }
            }

            if (isProductInCart(cartId, sku)) {

                sql = "UPDATE CART_ITEMS SET quantity = quantity + 1 WHERE cart_id = ? AND sku = ?";
                PreparedStatement updatePs = conn.prepareStatement(sql);
                updatePs.setInt(1, cartId);
                updatePs.setString(2, sku);
                updatePs.executeUpdate();

            } else {

                String insertSql = "INSERT INTO CART_ITEMS (cart_id, sku) VALUES (?, ?)";
                PreparedStatement insertPs = conn.prepareStatement(insertSql);
                insertPs.setInt(1, cartId);
                insertPs.setString(2, sku);
                insertPs.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isProductInCart(int cartId, String sku) {

        try (Connection conn = dbConnection.getConnection()) {
            // First, find the user's cart_id
            String sql = "SELECT * FROM CART_ITEMS WHERE cart_id = ? AND sku = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cartId);
            ps.setString(2, sku);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

// Function to remove a product from the user's cart
    public void removeProductFromCart(User user, String sku) {
        try (Connection conn = dbConnection.getConnection()) {

            // Delete the product from the cart
            String deleteSql = "DELETE FROM CART_ITEMS WHERE cart_id = ? AND sku = ?";
            PreparedStatement deletePs = conn.prepareStatement(deleteSql);
            deletePs.setInt(1, user.getCart().getCartId());
            deletePs.setString(2, sku);
            deletePs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// Function to set product quantity in the user's cart
    public void setProductQuantityInCart(User user, String sku, int quantity) {

        try (Connection conn = dbConnection.getConnection()) {
            if (!doesCartExist(user)) {
                createCart(user);
            }
            // First, find the user's cart_id
            String findCartSql = "SELECT cart_id FROM CARTS WHERE user_id = ?";
            PreparedStatement findCartPs = conn.prepareStatement(findCartSql);
            findCartPs.setInt(1, user.getUserID());
            ResultSet cartRs = findCartPs.executeQuery();

            // Check if the cart_id is found
            if (cartRs.next()) {
                int cartId = cartRs.getInt("cart_id");

                System.out.println("PRODUCT IS IN CART. PREPARING UPDATE");
                String sqlUpdate = "UPDATE CART_ITEMS SET quantity = ? WHERE cart_id = ? AND sku = ?";
                PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
                psUpdate.setInt(1, quantity);
                psUpdate.setInt(2, cartId);
                psUpdate.setString(3, sku);
                psUpdate.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean doesCartExist(User user) {

        try (Connection conn = dbConnection.getConnection()) {
            // First, find the user's cart_id
            String findCartSql = "SELECT cart_id FROM CARTS WHERE user_id = ?";
            PreparedStatement findCartPs = conn.prepareStatement(findCartSql);
            findCartPs.setInt(1, user.getUserID());
            ResultSet cartRs = findCartPs.executeQuery();

            // Check if the cart_id is found
            if (cartRs.next()) {

                user.setCart(new Cart(cartRs.getInt("cart_id")));
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

// Function to clear the cart for a given user
    public void clearCart(User user) {
        try (Connection conn = dbConnection.getConnection()) {

            // Delete the cart items for the found cart_id
            String sqlDelete = "DELETE FROM CART_ITEMS WHERE cart_id = ?";
            PreparedStatement psDelete = conn.prepareStatement(sqlDelete);
            psDelete.setInt(1, user.getCart().getCartId());
            psDelete.executeUpdate();

        } // If no cart exists, no action is taken.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrder(User user, String shippingAddress) {
        //Query String
        String sql = "INSERT INTO ORDERS (user_id, shipping_address) values(?, ?)";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            //Creating the query object used to execute the query
            ps.setInt(1, user.getUserID()); // Assuming getUserId() returns the user's ID
            ps.setString(2, shippingAddress);

            //Resultset is the set of rows returned from the query
            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);

                // Now we have the order ID, we can insert into ORDER_ITEMS
                //Getting the user's cart
                user.setCart(getCart(user));
                for (CartItem item : user.getCart().getCartItems()) {
                    // Copy cart items to order items
                    String copyItemsSql = "INSERT INTO ORDER_ITEMS (sku, order_id, quantity) values (?, ?, ?)";
                    PreparedStatement itemPs = conn.prepareStatement(copyItemsSql);
                    itemPs.setString(1, item.getProduct().getSKU()); // Set the order ID
                    itemPs.setInt(2, orderId); // Set the cart ID
                    itemPs.setInt(3, item.getQuantity());
                    itemPs.executeUpdate();
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    public List<Order> getOrders(User user) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT order_id, shipping_address, tracking_number, isShipped FROM ORDERS WHERE user_id = ?";

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, user.getUserID());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setShippingAddress(rs.getString("shipping_address"));
                order.setTrackingNumber(rs.getString("tracking_number"));
                order.setIsShipped(rs.getBoolean("isShipped"));

                order.setOrderItems(getOrderItems(order.getOrderId()));
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT order_id, shipping_address, tracking_number, isShipped FROM ORDERS ";

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setShippingAddress(rs.getString("shipping_address"));
                order.setTrackingNumber(rs.getString("tracking_number"));
                order.setIsShipped(rs.getBoolean("isShipped"));

                order.setOrderItems(getOrderItems(order.getOrderId()));
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public List<OrderItem> getOrderItems(int orderId) {

        List<OrderItem> orderItems = new ArrayList<>();
        String sql = "SELECT order_id, quantity, sku FROM ORDER_ITEMS WHERE order_id = ?";

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int quantity = rs.getInt("quantity");
                String sku = rs.getString("sku");

                Product p = getProduct(sku);

                orderItems.add(new OrderItem(p, quantity));
            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return orderItems;
    }

    public Order getOrder(int orderId) throws SQLException {
        Order order = null;
        String sql = "SELECT order_id, shipping_address, tracking_number, isShipped FROM ORDERS WHERE order_id = ?";

        try (Connection conn = dbConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setShippingAddress(rs.getString("shipping_address"));
                order.setTrackingNumber(rs.getString("tracking_number"));
                order.setIsShipped(rs.getBoolean("isShipped"));

                order.setOrderItems(getOrderItems(order.getOrderId()));
            }
        } catch (SQLException e) {

            e.printStackTrace();

        }

        return order;
    }

    public void shipOrder(int orderId, String trackingNumber) throws SQLException {
        // SQL to update the tracking number and is_shipped status
        String sqlUpdateOrder = "UPDATE ORDERS SET tracking_number = CONCAT('TRACK', SUBSTRING(UUID(), 1, 4), SUBSTRING(UUID(), LENGTH(UUID()) - 3, 4)), isShipped = TRUE WHERE order_id = ? AND isShipped = FALSE";

        try (Connection conn = dbConnection.getConnection(); PreparedStatement psUpdateOrder = conn.prepareStatement(sqlUpdateOrder)) {

            // Set the order ID
            psUpdateOrder.setInt(1, orderId);

            int rowsAffected = psUpdateOrder.executeUpdate();
            if (rowsAffected == 0) {
                // No rows affected means either the order doesn't exist or it's already been shipped
                throw new SQLException("Order not found or it's already been shipped.");
            }
        }
    }

    public void setOrderOwner(int orderId, int userId) throws SQLException {
        String sql = "UPDATE ORDERS SET user_id = ? WHERE order_id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement psOrderOwn = conn.prepareStatement(sql)) {
            psOrderOwn.setInt(1, userId);
            psOrderOwn.setInt(2, orderId);
            psOrderOwn.executeUpdate();
        }
    }

    public boolean setPasscode(User user, String passCode) {
        String selectSql = "SELECT count(*) FROM USERS WHERE password = ?";
        String sql_mod = " UPDATE USERS SET password = ? where user_id = ?";

        try (Connection conn = dbConnection.getConnection(); PreparedStatement checkStmt = conn.prepareStatement(selectSql); PreparedStatement psSetPass = conn.prepareStatement(sql_mod)) {

            // Check if passcode is taken
            checkStmt.setString(1, passCode);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                throw new SQLException("Passcode is already taken.");
            }

            // Set new passcode
            psSetPass.setString(1, passCode);
            psSetPass.setInt(2, user.getUserID());
            psSetPass.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }
        return true;
    }

    public void changePermission(User user, String role) throws SQLException {
        String sql = "UPDATE USERS SET is_staff = ? WHERE user_id = ?";
        try (Connection conn = dbConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, "staff".equals(role));
            pstmt.setInt(2, user.getUserID());
            pstmt.executeUpdate();
        }
    }

public boolean isOrderClaimable(int orderId) throws SQLException {
    String sql = "SELECT user_id FROM ORDERS WHERE order_id = ?";
    
    try (Connection conn = dbConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, orderId);
        ResultSet rs = ps.executeQuery();
        
        if (rs.next()) {
            int userId = rs.getInt("user_id");
            // If the user_id is not null and not zero, the order is already claimed
            return userId == 0;
        }
    }
    return false; // Order is not claimable if not found
}





}
