<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="adminheader.jsp" %>

<div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8 text-center heading-section">
                <h2 class="mb-4">Admin Dashboard - Add Product</h2>
            </div>
        </div>
<div class="row">
<div class="col-md-12">
    <form action="addProduct" method="post">
        <label for="name">Product Name:</label>
        <input type="text" id="name" name="name" required><br>

        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" step="0.01" required><br>

        <label for="price">Price:</label>
        <input type="number" id="price" name="price" step="0.01" required><br>

        <label for="img">Image URL:</label>
        <input type="text" id="img" name="img"><br>

        <label for="size">Size:</label>
        <input type="text" id="size" name="size"><br>

        <label for="category">Category:</label>
        <select id="category" name="category" required>
            <!-- Populate categories dynamically -->
            <c:forEach var="category" items="${categories}">
                <option value="${category.id}">${category.name}</option>
            </c:forEach>
        </select><br>

        <button type="submit">Add Product</button>
    </form></div></div></div>
    <%@ include file="adminfooter.jsp" %>