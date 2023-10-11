
package soen.online_store.java.action;
import soen.online_store.java.*;
import java.util.*;
/**
 *
 * @author Korjon Chang
 */
public class CartService {
    
    private List<Cart> carts;
    
    
    public List<Product> getCart(User user){
        
      final Customer cust = (user instanceof Customer) ? (Customer) user : null;
        
    
        Cart c = carts.stream()
                .filter(e ->  e.getCartId().equals(cust.getCart().getCartId()))
                .findFirst()
                .orElse(null);                 
                
        return c.getProducts();
    }
    
    public void addProductToCart(User user, String SKU){
        
        //List<Products> p = ProductService.getAllProducts();
    }
}
