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
import soen.online_store.java.Cart;
import soen.online_store.java.CartItem;
import soen.online_store.java.Order;
import soen.online_store.java.Product;
import soen.online_store.java.User;
import soen.online_store.java.action.CartManager;
import soen.online_store.java.persistence.DataManager;
import soen.online_store.java.persistence.DatabaseConnection;

/**
 *
 * @author User
 */
@WebServlet(name = "OrdersServlet", urlPatterns = {"/orders"})
public class OrdersServlet extends HttpServlet {

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
        
        if (currentUser != null) {
            List<Order> orders = dataManager.getOrders(currentUser);
            // Set the orders as an attribute in the request
            request.setAttribute("orders", orders);
            // Forward the request to the "orders.jsp" page
            request.getRequestDispatcher("/orders.jsp").forward(request, response);
        } else {
            // Redirect to the login page if the user is not logged in
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
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
