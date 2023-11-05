/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soen.online_store.java;

/**
 *
 * @author sami
 */
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {

    private static final AtomicInteger count = new AtomicInteger(0); // To generate unique order IDs
    private User user; // Assuming user is represented by a unique string identifier. Adjust as needed.
    private String orderID;
    private String shippingAddress;
    private List<Product> products;
    private boolean isShipped;
    private String trackingNumber;  // Added trackingNumber attribute


    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
    // Constructor
    public Order(User user, String shippingAddress, List<Product> productList) {
        this.user=user;
        this.orderID = String.valueOf(count.incrementAndGet());
        this.shippingAddress = shippingAddress;
        this.products = productList;
        this.isShipped = false;
    }

    // Getter for orderID
    public String getOrderID() {
        return orderID;
    }

    // Getter for shippingAddress
    public String getShippingAddress() {
        return shippingAddress;
    }

    // Setter for shippingAddress
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    // Getter for products
    public List<Product> getProducts() {
        return products;
    }

    // Setter for products
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // Check if order is shipped
    public boolean isShipped() {
        return isShipped;
    }

    // Mark order as shipped
    public void setShipped(boolean shipped) {
        isShipped = shipped;
    }

    // Add a product to the order
    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
    }

    // Remove a product from the order
    public void removeProduct(Product product) {
        if (products != null) {
            products.remove(product);
        }
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
