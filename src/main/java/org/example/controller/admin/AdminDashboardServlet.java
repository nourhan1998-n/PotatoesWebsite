package org.example.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.DAOs.CategoryDAO;
import org.example.entities.Category;
import org.example.entities.Product;
import org.example.services.ProductService;
import org.example.services.ProductServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {
    private ProductService productService = new ProductServiceImpl();
    private CategoryDAO categoryDAO = new CategoryDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Product> products = productService.getAllProducts();
        request.setAttribute("products", products);

        List<Category> categories = categoryDAO.findAll();
        request.setAttribute("categories", categories);

        // Forward the request to the JSP page
        //request.getRequestDispatcher("/add-product.jsp").forward(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminDashboard/admin-dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
