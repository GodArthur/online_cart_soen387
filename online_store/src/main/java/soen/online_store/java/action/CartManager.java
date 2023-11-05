
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
    
    
    public CartManager(ProductManager productManager){
        
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
        
        
        boolean isInCart = user.getCart().getProducts().stream()
                .anyMatch(e -> e.getSKU().equals(p.getSKU()));
        
        if(! isInCart){
            
            user.getCart().getProducts().add(p);
        }
                
    }
    
    /**
     * Method removes an item from the cart
     * @param user the user who needs the item removed
     * @param SKU  unique identifier of the item being removed
     */
   
    public void removeProductFromCart(User user, String SKU){
        
        
        user.getCart().getProducts().removeIf(e -> e.getSKU().equals(SKU));
    }
    
    
    public void setProductQuantityInCart(User user, String SKU, int quantity) {
    // Ensure the user has a cart, create one if necessary
    if (user.getCart() == null) {
        user.setCart(new Cart());
    }
    
    Cart cart = user.getCart();
    List<Product> products = cart.getProducts();

    // Try to find the product in the cart
    Product productInCart = products.stream()
                                    .filter(p -> p.getSKU().equals(SKU))
                                    .findFirst()
                                    .orElse(null);

    if (productInCart != null) {
        // If found and quantity is zero, remove it from the cart
        if (quantity == 0) {
            products.remove(productInCart);
        } else {
            // If found and quantity is greater than zero, set the new quantity
            productInCart.setQuantity(quantity);
        }
    } else if (quantity > 0) {
        // If the product is not in the cart and the quantity is greater than zero
        // Fetch the product from the product manager
        Product product = productManager.getProduct(SKU);
        if (product != null) {
            // Create a new product instance with the quantity set
            Product newProduct = new Product(product.getSKU(), product.getName());
            newProduct.setQuantity(quantity);
            // Copy the other product details if necessary
            newProduct.setDescription(product.getDescription());
            newProduct.setVendor(product.getVendor());
            newProduct.setURLSlug(product.getURLSlug());
            newProduct.setPrice(product.getPrice());
            
            // Add the new product with the quantity to the cart
            products.add(newProduct);
        }
    }
    // If quantity is 0 and the product is not in the cart, nothing happens.
}
    
    
public void clearCart(User user) {
    // Check if the user has a cart
    if (user.getCart() != null) {
        // If so, retrieve the cart
        Cart cart = user.getCart();
        // Clear all products from the cart
        cart.getProducts().clear();
    }
    // If the user does not have a cart, there is nothing to clear, so do nothing.
}


   
}
