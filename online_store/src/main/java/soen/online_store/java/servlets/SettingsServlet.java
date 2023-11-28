package soen.online_store.java.servlets;

import java.util.*;
import soen.online_store.java.persistence.*;
import soen.online_store.java.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Kojo
 */
@WebServlet(name = "SettingsServlet", urlPatterns = {"/Settings"})
public class SettingsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            // Redirect to the login page if the user is not logged in
            response.sendRedirect(request.getContextPath() + "/Login");
        } else {
            request.getRequestDispatcher("/settings.jsp").forward(request, response);
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

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        String method = request.getParameter("_method");

        Properties configProps = (Properties) getServletContext().getAttribute("dbConfig");
        String dbUrl = configProps.getProperty("database.url");

        DatabaseConnection dbConnection = new DatabaseConnection(dbUrl);
        DataManager dataManager = new DataManager(dbConnection);

        if (!isValidInput(request, currentUser, currentPassword, newPassword, confirmNewPassword)) {

            request.getRequestDispatcher("/settings.jsp").forward(request, response);
        } else {

            boolean isPasswordFree = dataManager.setPasscode(currentUser, newPassword);

            if (isPasswordFree) {

                currentUser.setPassword(newPassword);
                request.setAttribute("success", "Password change successfull");
                request.getRequestDispatcher("/settings.jsp").forward(request, response);

            } else {
                request.setAttribute("error", "Password already exists");
                request.getRequestDispatcher("/settings.jsp").forward(request, response);

            }
        }

    }

    private boolean isValidInput(HttpServletRequest request, User currentUser, String currentPassword, String newPassword, String confirmNewPassword) {

        //Checking if the current password 
        //matches the actual user's password
        String error = "error";
        if (!currentUser.getPassword().equals(currentPassword)) {
            request.setAttribute(error, "Invalid current password");
            return false;

        } else if (newPassword.length() < 4) {
            request.setAttribute(error, "New password is less than 4 characters");
            return false;

        } else if (!newPassword.equals(confirmNewPassword)) {
            request.setAttribute(error, "Confirmation password does not match new password");
            return false;
        }

        return true;
    }

}
