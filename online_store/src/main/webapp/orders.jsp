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
  

           <h2 >Your orders</h2>
         <section id="products" class="container mt-5">
        <div class="row">

            
          <c:forEach var="order" items="${orders}">
            <div class="col-md-4 mb-4">
                <article class="product card">
                    <div class="card-body">
                        <h2 class="card-title">#00${order.orderId}</h2>
                        <p class="card-text">Shipping Address: ${order.shippingAddress}</p>
                        <p class="card-text">Tracking# ${order.trackingNumber}</p>
                        <p class="card-text">Shipping Status : ${order.isShipped ? 'Shipped' : 'Not Shipped'}</p>
                        <a href="orders/${order.orderId}" class="btn btn-primary">See Details</a>
                    </div>
                </article>
            </div>
        </c:forEach>

        </div>
    </section>
    
    
     <%--A simple div for styling purposes--%>
<div style="background-color: white; height: 50px;"></div> <!-- Creates a black space of 20px height -->
    
    
    <% if (currentUser.isIsStaff()) { %>
    <h2 >As a staff member, you get to see all the orders</h2>
    
    <section id="products" class="container mt-5">
        <div class="row">

          <c:forEach var="order" items="${allorders}">
            <div class="col-md-4 mb-4">
                <article class="product card">
                    <div class="card-body">
                        <h2 class="card-title">#00${order.orderId}</h2>
                        <p class="card-text">Shipping Status : ${order.isShipped ? 'Shipped' : 'Not Shipped'}</p>
                        <a href="orders/${order.orderId}" class="btn btn-primary">See Details</a>
                    </div>
                </article>
            </div>
        </c:forEach>

        </div>
    </section>
<!--    This is an attempt at making a filter, but this doesnt work-->    
<!--    <div class="mb-3">
    <label for="filterShipped">Filter by Shipped:</label>
    <select id="filterShipped">
        <option value="all">All Orders</option>
        <option value="notShipped">Not Shipped Orders</option>
    </select>
</div>-->
    

<!--    <section id="products" class="container mt-5">
    <div id="filteredOrdersContainer" class="row">
        <c:choose>
            <c:when test="${filterShipped == 'notShipped'}">
                <c:forEach var="order" items="${allorders}">
                    <c:if test="${!order.isShipped}">
                         Display the order here 
                        <div class="col-md-4 mb-4">
                            <article class="product card">
                                <div class="card-body">
                                    <h2 class="card-title">#00${order.orderId}</h2>
                                    <p class="card-text">Shipping Status : Not Shipped</p>
                                    <a href="orders/${order.orderId}" class="btn btn-primary">See Details</a>
                                </div>
                            </article>
                        </div>
                    </c:if>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:forEach var="order" items="${allorders}">
                     Display all orders here 
                    <div class="col-md-4 mb-4">
                        <article class="product card">
                            <div class="card-body">
                                <h2 class="card-title">#00${order.orderId}</h2>
                                <p class="card-text">Shipping Status : ${order.isShipped ? 'Shipped' : 'Not Shipped'}</p>
                                <a href="orders/${order.orderId}" class="btn btn-primary">See Details</a>
                            </div>
                        </article>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</section>-->

   <% } %>
   
   
</main>

<footer>
    <p>© 2023 Online Storefront</p>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<script>
    $(document).ready(function () {
    // Initialize the page with all orders
    loadOrders("all");

    // Handle the change event of the filter dropdown
    $("#filterShipped").on("change", function () {
        var filterValue = $(this).val();
        loadOrders(filterValue);
    });
});

function loadOrders(filter) {
    $.ajax({
        url: "orders", // Your servlet URL
        type: "GET",
        data: { filterShipped: filter },
        dataType: "html",
        success: function (data) {
            // Update the container with the filtered orders
            $("#filteredOrdersContainer").html(data);
        },
        error: function () {
            alert("An error occurred while fetching orders.");
        }
    });
}
</script>


</body>
</html>

