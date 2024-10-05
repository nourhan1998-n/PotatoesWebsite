<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.entities.Admin" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="adminheader.jsp" %>
<%
    // Retrieve the admin object from the session
    Admin admin = (Admin) session.getAttribute("admin");

    if (admin == null) {
        // If admin is not logged in, redirect to login page
        response.sendRedirect("adminlogin.jsp");
        return;  // Prevent further JSP processing
    }
%>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8 text-center heading-section">
                <h2 class="mb-4">Admin Dashboard - Product Management</h2>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <!-- Product Management Table -->
                <table id="productsTable" class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Image</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${products}">
                            <tr>
                                <td>${product.id}</td>
                                <td>${product.name}</td>
                                <td>${product.price}</td>
                                <td>${product.quantity}</td>
                                <td><img src="${product.img}" width="100" height="100"></td>
                                <td>
                                    <button class="btn btn-edit edit-product" data-id="${product.id}">Edit</button>
                                    <button class="btn btn-delete delete-product" data-id="${product.id}">Delete</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        // Edit product using XMLHttpRequest
        document.querySelectorAll('.edit-product').forEach(function(button) {
            button.addEventListener('click', function() {
                var productId = this.getAttribute('data-id');
                window.location.href = 'AdminDashboard/editProduct.jsp?id=' + productId;  // Redirect to the new edit page
            });
        });

        // Delete product using XMLHttpRequest
        document.querySelectorAll('.delete-product').forEach(function(button) {
            button.addEventListener('click', function() {
                var productId = this.getAttribute('data-id');
                if (confirm('Are you sure you want to delete this product?')) {
                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', 'DeleteProductServlet', true);
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    xhr.onload = function() {
                            alert('Product deleted successfully');
                            location.reload();
                    };
                    xhr.send('id=' + productId);
                }
            });
        });
    });
</script>

<%@ include file="adminfooter.jsp" %>