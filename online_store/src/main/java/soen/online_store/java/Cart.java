package soen.online_store.java;

import java.util.*;

/**
 * Entity class for carts
 *
 * @author Korjon Chang
 */
public class Cart {

    private int cartId;
    private List<CartItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<>();

    }

    public Cart(int cartId) {

        this.cartItems = new ArrayList<>();
        this.cartId = cartId;
    }

    public Cart(int cartId, List<CartItem> cartItems) {
        this.cartId = cartId;
        this.cartItems = cartItems;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

 
}
