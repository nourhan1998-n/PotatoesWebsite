package org.example.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.services.ProductService;
import org.example.services.ProductServiceImpl;

import java.io.IOException;

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();  // Assuming ProductServiceImpl is implemented

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Parse the product ID from the request
        int id = Integer.parseInt(request.getParameter("id"));

        // Delegate the deletion logic to the ProductService
        productService.deleteProductById(id);

        // Redirect back to the dashboard after deletion
        response.sendRedirect("AdminDashboard/adminDashboard.jsp");
    }
}

