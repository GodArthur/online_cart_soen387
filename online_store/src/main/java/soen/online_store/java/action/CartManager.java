
package soen.online_store.java.action;
import soen.online_store.java.*;
import java.util.*;
/**
 * Class Manages and handles operations done with Carts
 * 
 * @author Korjon Chang
 */
public class CartManager {
    
    private ProductManager productManager;
    
    
    public CartManager(List<Cart> carts, ProductManager productManager){
        
        //this.carts = carts;
        this.productManager = productManager;
    }
        
    public List<Product> getCart(User user){        
                    
                
        return user.getCart().getProducts();
    }
    
    public void addProductToCart(User user, String SKU){
        
        List<Product> allProducts = productManager.getAllProducts();
        
        Product p = allProducts.stream()
                .filter(e -> e.getSKU().equals(SKU))
                .findFirst()
                .orElse(null);

        if (p == null) {
        //Checks if the product is not found or empty
        throw new IllegalArgumentException("Product not found, for SKU: " + SKU);
        }
        
        
        boolean isInCart = user.getCart().getProducts().stream()
                .anyMatch(e -> e.getSKU().equals(p.getSKU()));
        
        if (!isInCart) {
        user.getCart().getProducts().add(p);
    } else {
        //throws message that the product already exists in the cart
        throw new IllegalStateException("Product is in the cart already");
    }
                
    }
    
    /**
     * Method removes an item from the cart
     * @param user the user who needs the item removed
     * @param SKU  unique identifier of the item being removed
     */
   
    public void removeProductFromCart(User user, String SKU){

        boolean removal = user.getCart().getProducts().removeIf(e -> e.getSKU().equals(SKU));

        if (!removal) {
        //Throws message if the product isnt in the cart
        throw new IllegalStateException("Product not found, for SKU: " + SKU);
    }
        
    }
    
    
}
