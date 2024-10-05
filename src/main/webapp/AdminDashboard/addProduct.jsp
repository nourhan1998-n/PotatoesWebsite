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
    <form action="addProduct?action=add" method="post">
                <div class="form-group">
                    <label for="name">Product Name</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="Enter product name" required>
                </div>
                <div class="form-group">
                    <label for="quantity">Quantity</label>
                    <input type="number" class="form-control" id="quantity" name="quantity"  placeholder="Enter quantity" required>
                </div>
                <div class="form-group">
                    <label for="price">Price</label>
                    <input type="number" class="form-control" id="price" name="price"   placeholder="Enter price" required>
                </div>
                <div class="form-group">
                    <label for="img">Image URL</label>
                    <input type="text" class="form-control" id="img" name="img" placeholder="Enter image URL">
                </div>
                <div class="form-group">
                    <label for="size">Size</label>
                    <input type="text" class="form-control" id="size" name="size" placeholder="Enter product size">
                </div>
                <div class="form-group">
                    <label for="category">Category</label>
                    <select class="form-control" id="category" name="category" required>
                        <c:forEach items="${categories}" var="category">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Add Product</button>
            </form>

            <!-- Display error message if product already exists -->
            <c:if test="${not empty errorMessage}">
                <div class="alert alert-danger mt-3">${errorMessage}</div>
            </c:if>
            </div></div></div>
    <%@ include file="adminfooter.jsp" %>