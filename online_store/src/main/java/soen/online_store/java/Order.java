
package soen.online_store.java;

import java.util.*;

/**
 * Entity class for orders
 * @author Korjon Chang-Jones
 */
public class Order {
    
    private int orderId;
    private String shippingAddress;
    private String trackingNumber;
    private boolean isShipped;
    List<OrderItem> orderItems;

    public Order(int orderId, String shippingAddress, String trackingNumber, List<OrderItem> orderItems, boolean isShipped) {
        this.orderId = orderId;
        this.shippingAddress = shippingAddress;
        this.trackingNumber = trackingNumber;
        this.orderItems = orderItems;
    }
    
    public Order(){
        
        orderId = 0;
        shippingAddress = null;
        trackingNumber = null;
        orderItems = null;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public boolean isIsShipped() {
        return isShipped;
    }

    public void setIsShipped(boolean isShipped) {
        this.isShipped = isShipped;
    }
   
    
    
    
}
