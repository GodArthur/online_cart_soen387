<%-- 
    Document   : login
    Created on : Oct 19, 2023, 2:19:52 PM
    Author     : Djamal Namoko
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="soen.online_store.java.*" %>
<%@ page import="java.util.*" %>
<%@ page import="soen.online_store.java.action.*" %>




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
        <%-- Check if the user is logged in by looking for the "username" attribute in the session --%>
            <% User currentUser = (User) session.getAttribute("user"); %>
        <header>
            <nav class="navbar bg-dark navbar-expand-lg bg-body-tertiary p-4 justify-content-center" data-bs-theme="dark">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">
                        <img src="images/diff_logo_enhanced-removebg.png" width="160"   alt="No_IMG_Found" class="d-inline-block align-text-top">
                        
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="#">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="Login">Login</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Cart</a>
                            </li>
                        </ul>
                        <%-- Logout button is the user is connected --%>
                        <% if (currentUser != null) { %>
                        <form action="Logout" method="post">
                        <input type="submit" value="Logout">
                        </form>
                        <% } %>
                        <form class="d-flex" role="search">
                            <input class="form-control me-2" type="password" placeholder="Secret Code" aria-label="Password">
                            <button class="btn btn-outline-success" type="submit">Enter</button>
                        </form>
                    </div>
                </div>
            </nav>
        </header>
        
        
        
        <% if (currentUser != null) { %>
            <p> You are already logged in,  <%= currentUser.getUsername()%> </p>
                        
        <% } else { %>
        <form action="Login" method="post">  
            Name:<input type="text" name="username"/><br/><br/>  
            Password:<input type="password" name="password"/><br/><br/>  
            <input type="submit" value="login"/>  
        </form>  
        <% } %>
        
        
        
        <%-- Display the error message if present --%>
        <% if (request.getAttribute("error") != null) { %>
            <p style="color: red;"><%= request.getAttribute("error") %></p>
        <% } %>
        
        
    </body>
</html>
