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
            
    <div class="row">
        
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
                                <form action="Cart" method="post">
                                <input type="hidden" name="sku" value="${product.SKU}">
                                <input type="hidden" name="_method" value="post">
                                <input type="submit" value="Add to Cart">
                                 </form>
                              <% if (currentUser.isIsStaff()) { %>  
                                <button id="editButton" class="btn btn-primary">Edit Product</button>
    
                                
                                <% } %>
                            <% } %>
                            
                        </div>
                    </article>
    </div>
                            
    <div class="col-md-4 mb-4">
        <article class="product card">
                     <!-- Edit form (initially hidden) -->
                     
                                <form id="editForm" action="product-details" method="post" >
                                    <img src="images/spendr_logo1-removebg.png" alt="${product.name}" class="card-img-top">
                                   
                                   <h2 class="card-title"> 
                                    <input type="text" name="name" placeholder=${product.name}>
                                   </h2>
                                   
                                   <p class="card-text">
                                    <input type="text" name="description" placeholder=${product.description}>
                                   </p>
                                    
                                   <p class="card-text">
                                    <input type="text" name="price"  placeholder="New Price" value =${product.price}>
                                   </p>
                                     
                                   <p class="card-text">
                                    <input type="text" name="urlSlug" placeholder=${product.URLSlug}>
                                   </p>
                                   
                                   <p class="card-text">
                                    <input type="text" name="vendor" placeholder=${product.vendor}>
                                   </p>
                                   
                                   <p class="card-text">
                                    <input type="text" name="sku" placeholder=${product.SKU} readonly>
                                   </p>
                                     
                                    <input type="submit" value="Save">
                                </form>  
        </article>
    </div>
           
    
    </div> 
            
           
        </main>
                            
       <script>
   // JavaScript to handle the "Edit" button click event
   const editButton = document.getElementById('editButton');
   const editForm = document.getElementById('editForm');

    editForm.style.display = 'none';

   editButton.addEventListener('click', function() {
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
