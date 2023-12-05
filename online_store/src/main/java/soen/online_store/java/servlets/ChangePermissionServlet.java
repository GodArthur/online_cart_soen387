package soen.online_store.java.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import soen.online_store.java.Cart;
import soen.online_store.java.CartItem;
import soen.online_store.java.Product;
import soen.online_store.java.User;
import soen.online_store.java.action.CartManager;
import soen.online_store.java.persistence.DataManager;
import soen.online_store.java.persistence.DatabaseConnection;

/**
 *
 * @author Djamal
 */
@WebServlet(urlPatterns = {"/ChangePermission"})
public class ChangePermissionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (!currentUser.isIsStaff()) {
            // User is not logged in, redirect to the login page
            response.sendRedirect(request.getContextPath() + "/Home"); // Replace "Login" with the URL of your login page
            return; // Terminate the servlet to prevent further processing
        }
        
        Properties configProps = (Properties) getServletContext().getAttribute("dbConfig");
        String dbUrl = configProps.getProperty("database.url");
        
        DatabaseConnection dbConnection = new DatabaseConnection(dbUrl);
        DataManager dataManager = new DataManager(dbConnection);
        // Fetch the user by userId, then change their role
        
        List<User> users = dataManager.getAllUsers();
       

        request.setAttribute("users", users);
        request.getRequestDispatcher("/user-permission.jsp").forward(request, response);
    }
    
    
    
    
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
         // Establish a database connection
            Properties configProps = (Properties) getServletContext().getAttribute("dbConfig");
            String dbUrl = configProps.getProperty("database.url");
            
            DatabaseConnection dbConnection = new DatabaseConnection(dbUrl);
            DataManager dataManager = new DataManager(dbConnection);
        
            int userID = Integer.parseInt(request.getParameter("userId"));
            boolean isStaff = Boolean.parseBoolean(request.getParameter("isStaff"));
            
             try {
            // Toggle the is_staff status
            dataManager.setUserIsStaff(userID, !isStaff); // Invert the current status
            List<User> users = dataManager.getAllUsers();      
            request.setAttribute("users", users);
            request.getRequestDispatcher("/user-permission.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQL exception
        }
            
        }
}

  
   
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */



