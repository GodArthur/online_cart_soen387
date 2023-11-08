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
import soen.online_store.java.User;
import soen.online_store.java.persistence.DataManager;
import soen.online_store.java.persistence.DatabaseConnection;

/**
 *
 * @author Djamal
 */
@WebServlet(name = "StaffPassword", urlPatterns = {"/StaffPassword"})
public class StaffPasswordServlet extends HttpServlet {

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
        
        String password = request.getParameter("password");
        
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
            if ("MagicIn".equals(password)) {
                try {
                    dataManager.setUserIsStaff(currentUser.getUserID(), true);
                    currentUser.setIsStaff(true);
                } catch (SQLException ex) {
                    Logger.getLogger(StaffPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if ("MagicOut".equals(password)) {
                try {
                    dataManager.setUserIsStaff(currentUser.getUserID(), false);
                    currentUser.setIsStaff(false);
                } catch (SQLException ex) {
                    Logger.getLogger(StaffPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        // just trying to track change in user staff stattus  
        //not implemented
        /*if(OriginalUserStatus != currentUser.isIsStaff()){
            session.setAttribute("StaffStatus", currentUser.isIsStaff());
        }else {
            session.setAttribute("StaffStatus", null);
        }*/
        
        
        // Update the user in the session
        session.setAttribute("user", currentUser);
        }
        
        response.sendRedirect(request.getContextPath());
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
