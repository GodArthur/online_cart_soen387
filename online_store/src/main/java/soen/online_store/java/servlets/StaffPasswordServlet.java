package soen.online_store.java.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import soen.online_store.java.User;

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
        boolean OriginalUserStatus = currentUser.isIsStaff();
        
        if (currentUser != null) {
            if ("MagicIn".equals(password)) {
                currentUser.setIsStaff(true);
            } else if ("MagicOut".equals(password)) {
                currentUser.setIsStaff(false);
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
