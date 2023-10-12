
package soen.online_store.java;
import java.util.*;
/**
 * Entity class for carts
 * @author Korjon Chang
 */
public class Cart {
      
    List<Product> products;
    private String cartId;
    
    
    public Cart(){
    
        products = new ArrayList<>();
    }
    
    public Cart(List<Product> products, String cartId){
        
        this.products = products;
        this.cartId = cartId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
    
}
