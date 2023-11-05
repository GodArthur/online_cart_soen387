/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package soen.online_store.java.action;
import java.util.*;
import soen.online_store.java.*;

/**
 *
 * @author sami
 */
public class OrderManager {

    List<Order> orders;

    public OrderManager(List<Order> orders) {
        this.orders = orders;
    }

    // Create a new order
    public void createOrder(User user,String shippingAddress, List<Product> productList) {
        Order newOrder = new Order(user,shippingAddress, productList);
        orders.add(newOrder);
    }

    // View an order by its ID
    public Order viewOrder(String orderID) {
        return orders.stream()
                .filter(order -> order.getOrderID().equals(orderID))
                .findFirst()
                .orElse(null);
    }

    // Get all orders
    public List<Order> getOrders(User user) {
    List<Order> userOrders = new ArrayList<>();

    for (Order order : orders) {
        if (order.getUser().getUserID().equals(user.getUserID())) {
            userOrders.add(order);
        }
    }

    return userOrders;
}


    // For staff only: Create a new product
    // I'm assuming you have a ProductManager instance in this class to handle this
    public void createProduct(String sku, String name) {
        ProductManager productManager = new ProductManager(new ArrayList<Product>());
        productManager.CreateProduct(sku, name);
    }

    // For staff only: Mark an order as shipped
    public void shipOrder(String orderID, String trackingNumber) {
    Order orderToShip = orders.stream()
            .filter(order -> order.getOrderID().equals(orderID))
            .findFirst()
            .orElse(null);

    if (orderToShip != null && !orderToShip.isShipped()) {
        orderToShip.setShipped(true);
        orderToShip.setTrackingNumber(trackingNumber); // Set the tracking number
    } else {
                throw new IllegalArgumentException("This order is either already shipped or not found");    }
}

    
    
    public Order getOrder(User user, String ID) {
    for (Order order : orders) {
        if (order.getOrderID().equals(ID)) {
            if (user == null || order.getUser().getUserID().equals(user.getUserID())) {
                return order;
            } else {
                throw new IllegalArgumentException("This order does not belong to the specified user.");
            }
        }
    }
    return null; // return null if no order with the given ID was found. Handle this scenario appropriately in your application.
}


    
}
