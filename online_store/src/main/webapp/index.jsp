<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="soen.online_store.java.*" %>
<%@ page import="java.util.*" %>
<%@ page import="soen.online_store.java.action.*" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Online Storefront</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <!-- Optionally include your custom CSS here -->
    </head>
    <body>
            <%-- Check if the user is logged in by looking for the "username" attribute in the session --%>
            <% User currentUser = (User) session.getAttribute("user"); %>
        <jsp:include page="/WEB-INF/tags/header.tag" />

        <main>
             <div>

            <%-- Display a welcome message if the user is logged in --%>
            <% if (currentUser != null) { %>
                <p>Welcome, <%= currentUser.getUsername() %>!</p>
            <% } else { %>
            <%-- Display logout message if present --%>
            <% String logoutMessage = (String) session.getAttribute("logoutMessage"); %>
            <% if (logoutMessage != null) { %>
                <p><%= logoutMessage %></p>
            <% } %>
                <p>Please Login to continue</p>
                
            <% } %>
            
            </div>
            
            <section id="products" class="container mt-5">
                <div class="row">

                <% 
                    ProductManager productManager = (ProductManager) getServletContext().getAttribute("productManger");
                    List<Product> products = productManager.getAllProducts();
                    for(Product product : products) {
                %>

                <div class="col-md-4 mb-4">
                    <article class="product card">
                        <img src="images/spendr_logo1-removebg.png" alt="<%= product.getName() %>" class="card-img-top">
                        <div class="card-body">
                            <h2 class="card-title"><%= product.getName() %></h2>
                            <p class="card-text"><%= product.getDescription() %></p>
                            <p class="card-text">Price: $<%= product.getPrice() %></p>
                            <button class="btn btn-primary">Add to Cart</button>
                        </div>
                    </article>
                </div>

                <% } %>

                </div>
            </section>
        </main>

        <footer>
            <p>© 2023 Online Storefront</p>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

    </body>
</html>
