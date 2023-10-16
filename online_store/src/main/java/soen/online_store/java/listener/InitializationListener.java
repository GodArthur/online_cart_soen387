
package soen.online_store.java.listener;
import soen.online_store.java.*;
import soen.online_store.java.action.*;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.util.*;


/**
 *
 * @author Korjon Chang
 */
@WebListener
public class InitializationListener  implements ServletContextListener{
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // This method is called when the servlet context is initialized(when the web application is started).
        
        // Create some mock data for the products and add them to the context
        List<Product> products = new ArrayList<>();
        //products.add(new Product("pencil-1", "Pencil", "A standard pencil", "John's Store", "/products/pencil", "SKU123", 0.99));
        //products.add(new Product("pen-1", "Pen", "A standard pen", "John's Store", "/products/pen", "SKU124", 1.99));
        // ... add more mock products
        
        // Store the products list in the application context so it can be accessed across the application
        sce.getServletContext().setAttribute("products", products);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // This method is called when the servlet context is about to be shut down.
    }

}







