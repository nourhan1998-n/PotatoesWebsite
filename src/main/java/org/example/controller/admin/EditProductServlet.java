package org.example.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entities.Product;
import org.example.services.ProductService;
import org.example.services.ProductServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;
@WebServlet("/EditProductServlet")
public class EditProductServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        System.out.println ("name");
        Product product = productService.getProductById(id);
        product.setName(name);
        product.setPrice(BigDecimal.valueOf(price));
        product.setQuantity(BigDecimal.valueOf(quantity));
        productService.updateProduct(product);
        response.sendRedirect("adminDashboard");

    }
}

