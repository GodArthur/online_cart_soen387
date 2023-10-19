package soen.online_store.java.listeners;

import soen.online_store.java.*;
import soen.online_store.java.action.*;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Class initializes default values before the start of the program
 * in memory
 * 
 * @author Korjon Chang
 */
@WebListener
public class InitializationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // This method is called when the servlet context is initialized(when the web application is started).

        // Create some mock data for the products and add them to the context
        initializeManagement(sce);
        initializeUsers(sce);


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // This method is called when the servlet context is about to be shut down.
    }

    private void initializeManagement(ServletContextEvent sce) {

        List<Product> products = new ArrayList<>();
        products.add(new Product("Blender", "High-speed countertop blender for smoothies and shakes", "KitchenTech", "blender-speedmaster", "SKU401", 59.99));
        products.add(new Product("Bicycle Helmet", "Adjustable helmet suitable for adults, safety-certified", "RideSafe", "helmet-adult", "SKU402", 24.49));
        products.add(new Product("Cotton Bath Towel", "Soft and absorbent cotton bath towel in beige", "HomeComforts", "towel-bath-beige", "SKU403", 12.99));
        products.add(new Product("Wireless Mouse", "Ergonomic wireless mouse with USB receiver", "TechGear", "mouse-wireless", "SKU404", 18.99));
        products.add(new Product("Garden Chair", "Foldable garden chair with armrests, green", "OutdoorLife", "chair-garden-green", "SKU405", 19.49));

        ProductManager productManager = new ProductManager(products);
        CartManager cartManager = new CartManager(productManager);
        
        // Store the products list in the application context so it can be accessed across the application
        sce.getServletContext().setAttribute("productManger", productManager);
        sce.getServletContext().setAttribute("cartManager", cartManager);
    }

    private void initializeUsers(ServletContextEvent sce) {
        
        List<User> users = new ArrayList<>();

        Cart cart1 = new Cart("1");
        users.add(new User("user001", "johnDoe", "password1", "John", "Doe", false, cart1));

        Cart cart2 = new Cart("2");
        users.add(new User("user002", "aliceSmith", "password2", "Alice", "Smith", false, cart2));

        Cart cart3 = new Cart("3");
        users.add(new User("user003", "bobBrown", "password3", "Bob", "Brown", true, cart3)); // Bob is a staff

        Cart cart4 = new Cart("4");
        users.add(new User("user004", "lucyJones", "password4", "Lucy", "Jones", false, cart4));

        Cart cart5 = new Cart("5");
        users.add(new User("user005", "michaelLee", "password5", "Michael", "Lee", false, cart5));

        sce.getServletContext().setAttribute("users", users);
        
    }
}
