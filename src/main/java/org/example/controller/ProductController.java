package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entities.Product;
import org.example.services.ProductService;
import org.example.services.ProductServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductController extends HttpServlet {
//    Dependency Injection (Manual):
//    This line manually injects an instance of ProductServiceImpl into
//    the variable productService. This technique is often used in service-oriented
//    designs to decouple the code, allowing you to change the implementation without
//    affecting the rest of the system, as long as the interface remains the same.
    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = productService.getAllProducts();
        request.setAttribute("products", productList);
        request.getRequestDispatcher("shop.jsp").forward(request, response);
    }
}

