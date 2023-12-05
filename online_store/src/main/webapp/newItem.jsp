<%-- 
    Document   : login
    Created on : Oct 19, 2023, 2:19:52 PM
    Author     : Djamal Namoko
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="soen.online_store.java.*" %>
<%@ page import="java.util.*" %>
<%@ page import="soen.online_store.java.action.*" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>




<!DOCTYPE html>
<html>
    <head>
         <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <!-- Optionally include your custom CSS here -->
    </head>
    <body>
        <header>
                    <%-- Check if the user is logged in by looking for the "username" attribute in the session --%>
                    <% User currentUser = (User) session.getAttribute("user"); %>
                    
                    <%-- Includes the different tabs of the header --%>
                        <jsp:include page="/WEB-INF/tags/header.tag" />
                        
                        
                    <%--Logout button is the user is connected --%>
                        <% if (currentUser != null) { %>
                        <form action="Logout" method="post">
                        <input type="submit" value="Logout">
                        </form>
                         <form class="d-flex" role="search" method="post" action="StaffPassword">
                         <input class="form-control me-2" name="password" type="password" placeholder="Secret Code" aria-label="Password">
                         <button class="btn btn-outline-success" type="submit">Enter</button>
                         </form>  
                        <% } %>
                        
                        
                        <%--These are div's for the header, should be remodeled once the tag is working--%>
                        <%--Current problem is that you cannot test for the user information, the session info is 
                        not sent to the tag. This creates modeling issues, and moves everything around without
                        getting the user infos--%>
                         </div>
                         </div>
                         </nav>
        </header>
        <p> Only a test for redirection </p>
        
        <% if (currentUser != null && currentUser.isIsStaff()) { %>
            <p> Hi  <%= currentUser.getUsername()%> , here you can create new items for the store !</p>
                       
            <form action="newItem" method="post">
            <!-- Input fields for item details -->
            <input type="text" name="name" placeholder="Item Name" required>
            <input type="text" name="description" placeholder="Description" required>
            <input type="text" name="vendor" placeholder="Vendor" required>
            <input type="text" name="urlSlug" placeholder="URL Slug" required>
            <input type="text" name="sku" placeholder="SKU" required>
            <input type="text" name="price" placeholder="Price" required>

            <input type="submit" value="Add Item">
            </form>
            
        <% } else { %>
        <p> Sorry, this section is only available to staff members.</p> 
        <% } %>
        
        
        
        
        
        
        
    </body>
</html>
