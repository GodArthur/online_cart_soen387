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
            <% if (true) { %> <%--pt the conditions to see if user is staff and if user is the same as the id--%>
                        <%--<div class="row">
        
                        <div class="col-md-4 mb-4">
                                        <article class="product card">
                                            <img src="images/spendr_logo1-removebg.png" alt="${product.name}" class="card-img-top">
                                            <div class="card-body">
                                                <h2 class="card-title">${product.name}</h2>
                                                <p class="card-text">${product.description}</p>
                                                <p class="card-text">Price: ${product.price}</p>
                                                <a href="product-details?slug=${product.URLSlug}"  class="btn btn-primary">${product.URLSlug}</a>
                                                <p class="card-text">Vendor: ${product.vendor}</p>
                                                <p class="card-text">SKU: ${product.SKU}</p>

                                                <% if (currentUser != null) { %>
                                                    <form action="Logout" method="post">
                                                    <input type="submit" value="Add to cart">
                                                    </form>
                                                  <% if (currentUser.isIsStaff()) { %>  
                                                    <button id="editButton" class="btn btn-primary">Edit Product</button>


                                                    <% } %>
                                                <% } %>

                                            </div>
                                        </article>
                        </div>*/


                        </div>--%>
                        
                        
                                            <h1>Your Cart</h1>

                        <c:if test="${cartEmpty}">
                            <p>No products in the cart.</p>
                        </c:if>

                        <c:if test="${not empty cart}">
                            <table>
                                <tr>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Price</th>
                                </tr>
                                <c:forEach items="${cart}" var="product">
                                    <tr>
                                        <td>${product.name}</td>
                                        <td>${product.description}</td>
                                        <td>${product.price}</td>
                                        <td>
                                            <form action="Cart" method="post">
                                                <input type="hidden" name="_method" value="delete">
                                                <input type="hidden" name="sku" value="${product.SKU}">
                                                <button type="submit" class="btn btn-danger">Remove From Cart</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:if>
            
                        <% } else { %>
                        <p class="card-text">You are not allowed to see that</p>
                        <% } %>
            
     
            
           
        </main>
                            

        <footer>
            <p>© 2023 Online Storefront</p>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

    </body>
</html>