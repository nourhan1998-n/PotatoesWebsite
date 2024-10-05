<!-- view-users.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.entities.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List" %><%@ include file="adminheader.jsp" %>
<%
    // Retrieve the admin object from the session
    List<User> users = (List<User>) request.getAttribute("users");
    if (users == null) {
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
        <div class="row">
            <div class="col-md-12">
                <!-- Product Management Table -->
                <table id="usersTable" class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Job</th>
                            <th>City</th>
                            <th>Credit</th>
                            <th>Orders</th>
                        </tr>
                    </thead>
  <tbody>
  <c:forEach items="${users}" var="user">
    <tr>
      <td>${user.id}</td>
      <td>${user.name}</td>
      <td>${user.email}</td>
      <td>${user.job}</td>
      <td>${user.city}</td>
      <td>${user.credit}</td>
      <td>
        <form action="viewOrders" method="POST" style="display:inline;">
           <input type="hidden" name="userId" value="${user.id}" />
           <button type="submit" class="btn btn-primary">View Orders</button>
        </form>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</div>
</div>
<%@ include file="adminfooter.jsp" %>