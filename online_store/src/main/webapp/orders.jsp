<%-- 
    Document   : orders
    Created on : Nov 8, 2023, 4:13:11 PM
    Author     : User
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="soen.online_store.java.*" %>
<%@ page import="java.util.*" %>
<%@ page import="soen.online_store.java.action.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


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

<main>
  

<table border="1">
        <tr>
            <th>Order ID</th>
            <th>Shipping Address</th>
            <th>Tracking Number</th>
            <th>Is Shipped</th>
        </tr>
        <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.orderId}</td>
                <td>${order.shippingAddress}</td>
                <td>${order.trackingNumber}</td>
                <td>${order.isShipped}</td>
            </tr>
        </c:forEach>
    </table>


   

        
         <section id="products" class="container mt-5">
        <div class="row">

          <c:forEach var="product" items="${requestScope.productList}">
            <div class="col-md-4 mb-4">
                <article class="product card">
                    <img src="images/spendr_logo1-removebg.png" alt="${product.name}" class="card-img-top">
                    <div class="card-body">
                        <h2 class="card-title">${product.name}</h2>
                        <p class="card-text">${product.description}</p>
                        <p class="card-text">Price: $${product.price}</p>
                        <a href="product-details?slug=${product.URLSlug}" class="btn btn-primary">See Details</a>
                    </div>
                </article>
            </div>
        </c:forEach>

        </div>
    </section>

   
</main>

<footer>
    <p>© 2023 Online Storefront</p>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>


</body>
</html>

