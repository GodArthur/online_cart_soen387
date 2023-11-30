<%-- 
    Document   : orderClaims
    Created on : 28 Nov 2023, 9:06:53 PM
    Author     : sami
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="soen.online_store.java.*" %>
<%@ page import="java.util.*" %>
<%@ page import="soen.online_store.java.action.*" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Claims</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <main>
        <header>
            <jsp:include page="/WEB-INF/tags/header.tag" />
            <%-- Additional header content here --%>
        </header>

        <%-- Display success or error messages --%>
        <% String claimSuccess = (String) session.getAttribute("claimSuccess");
           String claimError = (String) session.getAttribute("claimError");
           session.removeAttribute("claimSuccess");
           session.removeAttribute("claimError");
        %>
        <% if (claimSuccess != null) { %>
            <div class="alert alert-success" role="alert">
                <%= claimSuccess %>
            </div>
        <% } %>
        <% if (claimError != null) { %>
            <div class="alert alert-danger" role="alert">
                <%= claimError %>
            </div>
        <% } %>

        <%-- Claim/Recover Order Section --%>
<% User currentUser = (User) session.getAttribute("user"); %>
<% if (currentUser != null) { %>
    <section id="claimOrder" class="container mt-5">
        <h2>Claim or Recover Your Order</h2>
        <p>Please enter the order ID for the order you wish to claim or recover.</p>
        <form action="orderClaims" method="post"> 
            <input type="hidden" name="action" value="claimOrder">
            <div class="mb-3">
                <label for="orderId" class="form-label">Order ID:</label>
                <input type="text" class="form-control" id="orderId" name="orderId" required>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </section>
<% } else { %>
    <section class="container mt-5">
        <div class="alert alert-warning" role="alert">
            You need to be logged in to claim or recover an order. Please <a href="login.jsp">log in</a> if you don't have an account.
        </div>
    </section>
<% } %>


    </main>

    <footer>
        
    </footer>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>
