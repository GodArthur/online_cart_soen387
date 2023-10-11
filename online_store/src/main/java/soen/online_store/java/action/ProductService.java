
package soen.online_store.java.action;
import java.util.*;
import soen.online_store.java.*;

/**
 *
 * @author Korjon Chang
 */
public class ProductService {
    
    
    List<Product> products;
    
    
    public void CreateProduct(String sku, String name){
        
        products.add(new Product(sku, name));
    }
    
    public void updateProduct(String SKU, String name, String vendor, String URLSlug, double price){
    
        Product p = products.stream()
                .filter(e -> e.getSKU().equals(SKU))
                .findFirst()
                .orElse(null);
        
        //Checking if the fields passed were null
        if (name != null){
            p.setName(name);
        }
        else if(vendor != null){
            
            p.setVendor(vendor);
            
        }
         else if(URLSlug != null){
            
            p.setURLSlug(URLSlug);
            
        }
        else if(price != -1){
            
            p.setPrice(price);
            
        }
    }
}
