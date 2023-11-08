package soen.online_store.java;

import java.util.*;
/**
 *
 * @author Korjon Chang
 */
public class User {

    private int userID;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Cart cart;
    private List<Order> orders;
    private boolean isStaff;
   

    public User(int userID, String username, String password, String firstName, String lastName, boolean isStaff, Cart cart, List<Order> orders) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isStaff = isStaff;
        this.cart = cart;
        this.orders = orders;
        
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public boolean isIsStaff() {
        return isStaff;
    }

    public void setIsStaff(boolean isStaff) {
        this.isStaff = isStaff;
    }

}
