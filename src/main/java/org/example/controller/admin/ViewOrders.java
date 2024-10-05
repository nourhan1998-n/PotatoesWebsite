package org.example.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entities.Orderitem;
import org.example.services.OrderService;
import org.example.services.OrderServiceImpl;

import java.io.IOException;
import java.util.List;
@WebServlet("/viewOrders")
public class ViewOrders extends HttpServlet {
    private OrderService orderService ; // Assuming OrderService handles order retrieval

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        orderService = new OrderServiceImpl(request.getSession());
        // Get the user ID from the request
        String userIdParam = request.getParameter("userId");
        int userId = Integer.parseInt(userIdParam);

        List<Orderitem> orderitems = orderService.getAllOrdersByUserId(userId);
        request.setAttribute("orderitems", orderitems);
        request.getRequestDispatcher("AdminDashboard/view-orders.jsp").forward(request, response);
    }
}