/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package soen.online_store.java.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Properties;
import soen.online_store.java.User;
import soen.online_store.java.persistence.DataManager;
import soen.online_store.java.persistence.DatabaseConnection;

/**
 *
 * @author sami
 */
@WebServlet(name = "orderClaims", urlPatterns = {"/orderClaims"})
public class orderClaims extends HttpServlet {

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
    // Check if the user is logged in and forward them to the orderClaims.jsp page
    HttpSession session = request.getSession();
    User currentUser = (User) session.getAttribute("user");

    if (currentUser != null) {
        request.getRequestDispatcher("/orderClaims.jsp").forward(request, response);
    } else {
        // Redirect to login page if the user is not logged in
        response.sendRedirect("login.jsp");
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
    String action = request.getParameter("action");

 HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        
        // Establish a database connection
            Properties configProps = (Properties) getServletContext().getAttribute("dbConfig");
            String dbUrl = configProps.getProperty("database.url");
            

            DatabaseConnection dbConnection = new DatabaseConnection(dbUrl);
            DataManager dataManager = new DataManager(dbConnection);

    if ("claimOrder".equals(action)) {
    String orderIdString = request.getParameter("orderId");
    try {
        int orderId = Integer.parseInt(orderIdString);
        
        if (dataManager.isOrderClaimable(orderId)) {
            // Logic to claim the order, could be setting the current user as the owner
            // and updating the order details in the database
            dataManager.setOrderOwner(orderId, currentUser.getUserID());
            session.setAttribute("claimSuccess", "Order has been successfully claimed.");
        } else {
            // Set error message indicating the order is already claimed
            session.setAttribute("claimError", "Order is already claimed.");
        }
    } catch (NumberFormatException e) {
        session.setAttribute("claimError", "Invalid order ID format.");
    } catch (SQLException e) {
        session.setAttribute("claimError", "Database error occurred.");
    }
}

    // Redirect to the same page to display success or error message
    response.sendRedirect("orderClaims.jsp");
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
