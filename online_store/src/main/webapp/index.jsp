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
    <div>

        <%-- Display a welcome message if the user is logged in --%>
        <% if (currentUser != null) { %>
        <p>Welcome, <%= currentUser.getUsername()%>, password = <%=currentUser.getPassword()%> </p>

        <%-- If the user is a staff, show him the create item option --%>
        <% if (currentUser.isIsStaff()) { %>
        <div class="center-button">
            <a href="newItem" class="btn btn-outline-success">Create New Item</a>
        </div>
        <% } %>

        <% } else { %>
        <%-- Display logout message if present --%>
        <% String logoutMessage = (String) session.getAttribute("logoutMessage"); %>
        <% if (logoutMessage != null) { %>
        <p><%= logoutMessage %></p>
        <% } %>
        <p>Please Login to continue</p>

        <% } %>

    </div>




   

        </div>
    </section>
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

    <%-- If the user is a staff, show him the download catalog option--%>
    <% if (currentUser != null && currentUser.isIsStaff()) { %>

    <div class="center-button">
        <button onclick="downloadCatalog()" class="btn btn-outline-success">Download catalog</button>
    </div>


    <% } %>
</main>

<footer>
    <p>© 2023 Online Storefront</p>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

<script>
             function downloadCatalog() {
                 // Create an invisible iframe to trigger the download
                 var iframe = document.createElement('iframe');
                 iframe.style.display = 'none';
                 iframe.src = 'download-catalog';
                 document.body.appendChild(iframe);
             }
</script>

</body>
</html>
