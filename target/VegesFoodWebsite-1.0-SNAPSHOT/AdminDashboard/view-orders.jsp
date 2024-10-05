



<!-- view-orders.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.entities.Orderitem" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="adminheader.jsp" %>
<%@ page import="java.util.List" %>
<%
    // Retrieve the admin object from the session
    List<Orderitem> orders = (List<Orderitem>) request.getAttribute("orderitems");


    if (orders == null) {
        // If admin is not logged in, redirect to login page
        response.sendRedirect("admin-dashboard.jsp");
        return;  // Prevent further JSP processing
    }
%>

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8 text-center heading-section">
                <h2 class="mb-4">Admin Dashboard - Users</h2>
            </div>
        </div>
                <!-- Product Management Table -->
                <div class="row">
                <div class="col-md-12">
                <table id="usersTable" class="table">
                    <thead>
                        <tr>
                            <th>User Name</th>
                            <th>Order ID</th>
                            <th>Order Date</th>
                            <th>Product</th>
                            <th>Quantity</th>
                            <th>Price</th>
                        </tr>
                    </thead>
  <tbody>
  <c:forEach items="${orderitems}" var="o">
    <tr>
      <td>${o.getIdorder().getIduser().getName()}</td>
      <td>${o.getIdorder().getId()}</td>
      <td>${o.getIdorder().getDate()}</td>
      <td>${o.getIdproduct().getName()}</td>
      <td>${o.getQuantity()}</td>
      <td>${o.getPrice()}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</div>
</div>
</div>
<%@ include file="adminfooter.jsp" %>