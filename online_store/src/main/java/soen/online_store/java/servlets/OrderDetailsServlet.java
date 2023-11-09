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
import soen.online_store.java.Order;
import soen.online_store.java.OrderItem;
import soen.online_store.java.Product;
import soen.online_store.java.User;
import soen.online_store.java.action.CartManager;
import soen.online_store.java.persistence.DataManager;
import soen.online_store.java.persistence.DatabaseConnection;

/**
 *
 * @author Djamal
 */
@WebServlet(name = "OrderDetailsServlet", urlPatterns = {"/orders/*"})
public class OrderDetailsServlet extends HttpServlet {

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
        
        // Establish a database connection
            Properties configProps = (Properties) getServletContext().getAttribute("dbConfig");
            String dbUrl = configProps.getProperty("database.url");
            String dbUser = configProps.getProperty("database.user");
            String dbPassword = configProps.getProperty("database.password");
            String dbDriver = configProps.getProperty("database.driver");

            DatabaseConnection dbConnection = new DatabaseConnection(dbUrl, dbUser, dbPassword, dbDriver);
            DataManager dataManager = new DataManager(dbConnection);
        
        
    // Get the order ID from the URL path
    String pathInfo = request.getPathInfo();
    if (pathInfo != null && pathInfo.length() > 1) {
        String orderIdString = pathInfo.substring(1); // Remove the leading "/"
         System.out.println("Extracted Order ID: " + orderIdString);
        try {
            // Parse the order ID as an integer
            int orderId = Integer.parseInt(orderIdString);
            double orderTotal=0;
            
            // Retrieve order details and Item list based on the order ID
            Order order = dataManager.getOrder(orderId);
            List<OrderItem> orderItems = dataManager.getOrderItems(orderId);
            
            //Calculating the full price of the Order
            for (OrderItem item : orderItems) {
                    orderTotal += item.getProduct().getPrice() * item.getQuantity();
                }
            
            if (order != null) {
                // setting the attributes for the list and order details
                // Forward the request to the order-details.jsp page
                request.setAttribute("order", order);
                request.setAttribute("items", orderItems);
                request.setAttribute("total", orderTotal);
                request.getRequestDispatcher("/order-details.jsp").forward(request, response);
                return; // Exit the servlet
            }
        } catch (NumberFormatException e) {
            // Handle invalid order ID format (e.g., non-integer)
        }   catch (SQLException ex) {
                Logger.getLogger(OrderDetailsServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    // If order details retrieval fails or order ID is invalid, redirect to an error page
    response.sendRedirect(request.getContextPath() + "/error.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
