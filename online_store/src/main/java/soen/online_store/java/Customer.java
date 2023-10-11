
package soen.online_store.java;

/**
 *
 * @author Kojo
 */
public class Customer extends User{
    
    private String customerId;
    private String address;
    private String phoneNumber;
    private String email;
    private Cart cart;
    
    public Customer(String customerId, String address, String phoneNumber, String userID, String username, String password, String role) {
        super(userID, username, password, role);
        this.customerId = customerId;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    
    
    
}
