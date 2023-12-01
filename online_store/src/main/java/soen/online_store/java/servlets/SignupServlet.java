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

/**
 *
 * @author Kojo
 */
@WebServlet(name = "SignupServlet", urlPatterns = {"/Signup"})
public class SignupServlet extends HttpServlet {

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

        request.getRequestDispatcher("/signup.jsp").forward(request, response);
    }

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");

        //Retrieving db credentials from config located in a Servlet Context
        Properties configProps = (Properties) getServletContext().getAttribute("dbConfig");
        String dbUrl = configProps.getProperty("database.url");

        DatabaseConnection dbConnection = new DatabaseConnection(dbUrl);
        DataManager dataManager = new DataManager(dbConnection);

        boolean isUserCreated = dataManager.createUser(firstname, lastname, password);

        if (isUserCreated) {

            request.setAttribute("success", "Password change successfull");
        } else {

            request.setAttribute("error", "Password already exists");
        }

        request.getRequestDispatcher("/Login").forward(request, response);

    }
}
