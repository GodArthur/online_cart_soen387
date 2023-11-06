package soen.online_store.java.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.ServletContext;
import java.util.Properties;
import java.io.InputStream;
/**
 *
 * @author Korjon Chang-Jones
 */
@WebListener
public class DBContextListener implements ServletContextListener{
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties configProps = new Properties();
        String configFile = "/WEB-INF/config.properties"; // The path to the config file

        try (InputStream inputStream = sce.getServletContext().getResourceAsStream(configFile)) {
            if (inputStream == null) {
                throw new IllegalStateException("Configuration file '" + configFile + "' not found in the application.");
            }
            configProps.load(inputStream);
            // Store the configuration to be accessible in the servlet context
            sce.getServletContext().setAttribute("dbConfig", configProps);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load database configuration", e);
        }
    }
    
    /*
    
    Once you need access to these configuration properties, 
    */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Perform cleanup if necessary
    }
    
}
