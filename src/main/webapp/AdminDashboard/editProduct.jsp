<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.entities.Product" %>
<%@ page import="org.example.services.ProductService" %>
<%@ page import="org.example.services.ProductServiceImpl" %>
<%@ include file="adminheader.jsp" %>
<%@ page import="org.example.services.ProductService, org.example.services.ProductServiceImpl" %>
<%
    // Retrieve the product ID from the URL query parameter
    int productId = Integer.parseInt(request.getParameter("id"));

    // Fetch product details using the service
    ProductService productService = new ProductServiceImpl();
    Product product = productService.getProductById(productId);

    if (product == null) {
        response.sendRedirect("adminDashboard.jsp");
        return;
    }
%>
<div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8 text-center heading-section">
                <h2 class="mb-4">Admin Dashboard - Edit Product</h2>
            </div>
        </div>
<div class="row">
<div class="col-md-12">
<form id="editProductForm" method="POST" action="/VegesFoodWebsite/EditProductServlet">
    <input type="hidden" name="id" value="<%=product.getId()%>">
    <label for="name">Name:</label>
    <input type="text" name="name" value="<%=product.getName()%>" required><br>
    <label for="price">Price:</label>
    <input type="number" name="price" value="<%=product.getPrice()%>" required><br>
    <label for="quantity">Quantity:</label>
    <input type="number" name="quantity" value="<%=product.getQuantity()%>" required><br>
    <button type="submit">Save changes</button>
</form>
</div> </div> </div>
<%@ include file="adminfooter.jsp" %>