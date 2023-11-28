<%-- 
    Document   : settings
    Created on : Nov. 27, 2023, 9:03:20 p.m.
    Author     : Kojo
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="soen.online_store.java.*" %>
<%@ page import="java.util.*" %>
<%@ page import="soen.online_store.java.action.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Storefront</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

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

<!-- Settings content -->
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <h2>Change Password</h2>
            <form action="Settings" method="post">
                <input type="hidden" name ="_method" value="put">
                <div class="form-group">
                    <label for="currentPassword">Current Password</label>
                    <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
                </div>
                <div class="form-group">
                    <label for="newPassword">New Password</label>
                    <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="min 4 characters" required>
                </div>
                <div class="form-group">
                    <label for="confirmNewPassword">Confirm New Password</label>
                    <input type="password" class="form-control" id="confirmNewPassword" name="confirmNewPassword" required>
                </div>
                <button type="submit" class="btn btn-primary">Change Password</button>
            </form>
        </div>
    </div>
    <!-- Display the error message if present -->
    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-danger text-center mt-4" role="alert">
        <%= request.getAttribute("error") %>
    </div>
    <% } %>
    
      <!-- Display the Success message if present -->
    <% if (request.getAttribute("success") != null) { %>
    <div class="alert alert-success text-center mt-4" role="alert">
        <%= request.getAttribute("success") %>
    </div>
    <% } %>
</div>


</body>
</html>
