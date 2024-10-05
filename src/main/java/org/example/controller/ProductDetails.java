package org.example.controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.DAOs.UserDAO;
import org.example.entities.Product;
import org.example.entities.User;
import org.example.services.*;

import java.io.IOException;
import java.util.List;
@WebServlet("/productdetails")
public class ProductDetails extends HttpServlet {
    ProductService productService = new ProductServiceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productIdParam = request.getParameter("productId");
        int productId = Integer.parseInt(productIdParam);

        Product product = productService.getProductById(productId);
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-single.jsp");
        dispatcher.forward(request, response);
    }
}
