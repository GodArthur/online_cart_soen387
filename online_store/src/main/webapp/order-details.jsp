<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="soen.online_store.java.*" %>
<%@ page import="java.util.*" %>
<%@ page import="soen.online_store.java.action.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
        <section id="order-details" class="container mt-5">
            <div class="row">
                <div class="col-md-6">
                    <h1>Order Details</h1>
                    <table class="table">
                        <tbody>
                            <tr>
                                <th scope="row">Order ID:</th>
                                <td>${order.orderId}</td>
                            </tr>
                            <tr>
                                <th scope="row">Shipping Address:</th>
                                <td>${order.shippingAddress}</td>
                            </tr>
                            <tr>
                                <th scope="row">Tracking Number:</th>
                                <td>${order.trackingNumber}</td>
                            </tr>
                            <tr>
                                <th scope="row">Shipping Status:</th>
                                <td>${order.isShipped ? 'Shipped' : 'Not Shipped'}</td>
                            </tr>
                             <tr>
                                <th scope="row">Shipping Status:</th>
                                <td>${total}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
                            <% if (currentUser.isIsStaff() ) { %>
                            <form action="OrderDetailsServlet" method="POST">
                            <input type="hidden" name="orderId" value="${order.orderId}">
                            <input type="hidden" name="action" value="shipOrder">
                            <button type="submit" class="btn btn-success">Ship Now</button>
                            </form>
                            <% } %>
        </section>
                            <div class="row">
                            <h2 class="card-title">Here are your products</h2>
                             <c:forEach var="item" items="${items}">
                                <div class="col-md-4 mb-4">
                                    <article class="product card">
                                        <div style="width: 40%;">
                                        <img src="/online_store/images/spendr_logo1-removebg.png" alt="${item.product.name}" class="card-img-top" >
                                        </div>
                                        <div class="card-body">
                                            <h2 class="card-title">${item.product.name}</h2>
                                            <p class="card-text">${item.product.description}</p>
                                            <p class="card-text">Price: $${item.product.price}</p>
                                            <p class="card-text">${item.quantity}</p>
                                        </div>
                                    </article>
                                </div>
                            </c:forEach>
                            </div>
                            
    </main>

<script>
    // JavaScript to handle the "Edit" button click event
    const editButton = document.getElementById('editButton');
    const editForm = document.getElementById('editForm');

    editForm.style.display = 'none';

    editButton.addEventListener('click', function () {
        // Toggle the visibility of the edit form when the "Edit" button is clicked
        if (editForm.style.display === 'block') {
            editForm.style.display = 'none';
        } else {
            editForm.style.display = 'block';
        }
    });

    // JavaScript to handle the set blank fields to default value
    document.getElementById("editForm").addEventListener("submit", function (e) {
        const inputs = this.querySelectorAll("input");
        inputs.forEach(function (input) {
            if (input.value.trim() === "" && input.placeholder) {
                input.value = input.placeholder;
            }
        });
    });


</script>

<footer>
    <p>© 2023 Online Storefront</p>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</body>
</html>
