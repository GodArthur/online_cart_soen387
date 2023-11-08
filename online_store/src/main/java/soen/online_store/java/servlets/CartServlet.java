package soen.online_store.java.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Properties;
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
@WebServlet(name = "CartServlet", urlPatterns = {"/Cart"})
public class CartServlet extends HttpServlet {

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
        if (currentUser == null) {
        // User is not logged in, redirect to the login page
        response.sendRedirect(request.getContextPath() + "/Login"); // Replace "Login" with the URL of your login page
        return; // Terminate the servlet to prevent further processing
    }
        Properties configProps = (Properties) getServletContext().getAttribute("dbConfig");
        String dbUrl = configProps.getProperty("database.url");
        String dbUser = configProps.getProperty("database.user");
        String dbPassword = configProps.getProperty("database.password");
        String dbDriver = configProps.getProperty("database.driver");
        
        DatabaseConnection dbConnection = new DatabaseConnection(dbUrl, dbUser, dbPassword, dbDriver);
        DataManager dataManager = new DataManager(dbConnection);
        
        Cart currentCart = dataManager.getCart(currentUser);
        //Cart currentCart = (Cart) currentUser.getCart();
        if(currentUser.getCart() == null){
        currentUser.setCart(currentCart);
        }
        List<CartItem> cartItems = currentCart.getCartItems();
        
        
        
        if (cartItems.isEmpty() || currentCart == null) {
             request.setAttribute("cartEmpty", true);
        } else {
            request.setAttribute("cart", cartItems);
        }

        request.getRequestDispatcher("/cart.jsp").forward(request, response);
        
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
        String sku = request.getParameter("sku");
    
    HttpSession session = request.getSession();
    User currentUser = (User) session.getAttribute("user");
    String method = request.getParameter("_method");
    
    CartManager cartManager = (CartManager) getServletContext().getAttribute("cartManager");
    
    if (currentUser != null) {
        switch(method) {
            
        // Call the method to add the product to the cart
        case "post":
         
        //cartManager.addProductToCart(currentUser, sku);
          break;
          
        // Call the method to add the product to the cart 
        case "delete":
        //cartManager.removeProductFromCart(currentUser, sku);
          break;

        }
        
        
        // Redirect back to the product details page or any other page as needed
         response.sendRedirect("Cart");
    } else {
        // Handle the case where the user is not logged in
        response.sendRedirect("login.jsp"); // Redirect to the login page
        }
    
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    /*Not working*/
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String sku = request.getParameter("sku");
    
    HttpSession session = request.getSession();
    User currentUser = (User) session.getAttribute("user");
    
    CartManager cartManager = (CartManager) getServletContext().getAttribute("cartManager");
    
    
     response.sendRedirect("Home");
    
        
    }
    
    
    
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
