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
import soen.online_store.java.Product;
import soen.online_store.java.User;
import soen.online_store.java.action.ProductManager;
import soen.online_store.java.persistence.DataManager;
import soen.online_store.java.persistence.DatabaseConnection;

/**
 *
 * @author Djamal
 */
@WebServlet(name = "ProductDetails", urlPatterns = {"/product-details"})
public class ProductDetailsServlet extends HttpServlet {

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
      
        String urlSlug = null;

        // Retrieve the product URL slug from the URL parameter
        String slugParameter = request.getParameter("slug");
        
        
        // If slugParameter is null (not provided in the URL), try to retrieve it from the path
        if (slugParameter == null) {
            String pathInfo = request.getPathInfo();
            if (pathInfo != null && pathInfo.length() > 1) {
                urlSlug = pathInfo.substring(1); // Remove the leading "/"
            }
        } else {
            urlSlug = slugParameter;
        }

        if (urlSlug != null) {
            //Retrieving db credentials from config located in a Servlet Context
        Properties configProps = (Properties) getServletContext().getAttribute("dbConfig");
        String dbUrl = configProps.getProperty("database.url");
        String dbUser = configProps.getProperty("database.user");
        String dbPassword = configProps.getProperty("database.password");
        String dbDriver = configProps.getProperty("database.driver");
        
        DatabaseConnection dbConnection = new DatabaseConnection(dbUrl, dbUser, dbPassword, dbDriver);
        DataManager dataManager = new DataManager(dbConnection);
        Product product = dataManager.getProductBySlug(urlSlug); // Adjust this to your specific implementation

            if (product != null) {
                // Forward to the product details page, passing the product details
                request.setAttribute("product", product);
                request.getRequestDispatcher("/product-details.jsp").forward(request, response);
            } else {
                // Handle product not found, such as displaying an error page or redirecting to the home page.
                response.sendRedirect("product-not-found.jsp");
            }
        } else {
            // Handle cases where the URL slug is not provided.
            response.sendRedirect("product-not-found.jsp");
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
        
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String vendor = request.getParameter("vendor");
        String urlSlug = request.getParameter("urlSlug");
        String sku = request.getParameter("sku");
        double price = Double.parseDouble(request.getParameter("price")); // Parse price as double
        
         // Establish a database connection
            Properties configProps = (Properties) getServletContext().getAttribute("dbConfig");
            String dbUrl = configProps.getProperty("database.url");
            String dbUser = configProps.getProperty("database.user");
            String dbPassword = configProps.getProperty("database.password");
            String dbDriver = configProps.getProperty("database.driver");

            DatabaseConnection dbConnection = new DatabaseConnection(dbUrl, dbUser, dbPassword, dbDriver);
            DataManager dataManager = new DataManager(dbConnection);

    try {
        // Update the product in the database
        dataManager.updateProduct(sku, name, description, vendor, urlSlug, price);

        // Redirect to the product details page after the update
        response.sendRedirect(request.getContextPath() + "/product-details?slug=" + urlSlug);
    } catch (SQLException e) {
        // Handle any database update errors here
        e.printStackTrace(); // You can log the error or display an error message to the user
        // Redirect to an error page or display an error message to the user
        
        response.sendRedirect(request.getContextPath() + "/error-page.jsp");
    }

        
        
        
        
//        ProductManager productManager = (ProductManager) getServletContext().getAttribute("productManger");
//        productManager.updateProduct(sku, name, description, vendor, urlSlug, price);
//        // Update the ServletContext attribute with the updated product manager (not necessary in this case)
//        getServletContext().setAttribute("productManger", productManager);
//        response.sendRedirect(request.getContextPath() + "/product-details?slug=" + urlSlug );
        
        
        
        
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
