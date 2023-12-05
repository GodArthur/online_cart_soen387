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
import soen.online_store.java.User;
import soen.online_store.java.persistence.DataManager;
import soen.online_store.java.persistence.DatabaseConnection;

/**
 *
 * @author Djamal
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

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
        
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
        String password = request.getParameter("password");
        
        
         // Establish a database connection
    Properties configProps = (Properties) getServletContext().getAttribute("dbConfig");
    String dbUrl = configProps.getProperty("database.url");
    

    DatabaseConnection dbConnection = new DatabaseConnection(dbUrl);
    DataManager dataManager = new DataManager(dbConnection);
        
     // Check if the username and password match a user in the database
    User loggedUser = dataManager.getUserByPassword(password);

    if (loggedUser != null) {
        // get or create session
        HttpSession session = request.getSession();

        // Store user information in the session
        session.setAttribute("user", loggedUser);

        // Successful login, redirect to a success page
        response.sendRedirect(request.getContextPath() + "/Home");

    } else {
        // Failed login, redirect back to the login page with an error message
        request.setAttribute("error", "Invalid password");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    
   
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
