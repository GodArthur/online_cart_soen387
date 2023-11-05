package soen.online_store.java.servlets;

import jakarta.servlet.ServletContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import soen.online_store.java.Product;
import soen.online_store.java.User;
import soen.online_store.java.action.ProductManager;

/**
 *
 * @author Djamal
 */
@WebServlet(name = "NewItem", urlPatterns = {"/newItem"})
public class NewItemServlet extends HttpServlet {

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
        
        request.getRequestDispatcher("/newItem.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String vendor = request.getParameter("vendor");
        String urlSlug = request.getParameter("urlSlug");
        String sku = request.getParameter("sku");
        double price = Double.parseDouble(request.getParameter("price")); // Parse price as double
        
         Product newProduct = new Product(sku,name);
         newProduct.setDescription(description);
         newProduct.setVendor(vendor);
         newProduct.setURLSlug(urlSlug);
         newProduct.setPrice(price);
         
         
         ProductManager productManager = (ProductManager) getServletContext().getAttribute("productManger");
         List<Product> products = productManager.getAllProducts(); 
         products.add(newProduct);
                
         // Update the ServletContext attribute with the updated product manager (not necessary in this case)
        getServletContext().setAttribute("productManger", productManager);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
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
