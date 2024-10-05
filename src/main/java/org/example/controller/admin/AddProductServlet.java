package org.example.controller.admin;


import jakarta.servlet.RequestDispatcher;
import org.example.DAOs.CategoryDAO;
import org.example.DAOs.ProductDAO;
import org.example.entities.Category;
import org.example.entities.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.exceptions.alreadyexist.ProductAlreadyExistsException;
import org.example.services.ProductService;
import org.example.services.ProductServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {

    private ProductService productService=new ProductServiceImpl();
    private CategoryDAO categoryDAO =new CategoryDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if("edit".equals(action)){
            List<Category> categoryList = categoryDAO.findAll();
            request.setAttribute("categories", categoryList);
            request.getRequestDispatcher("AdminDashboard/addProduct.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            System.out.println("HTTP Method: " + request.getMethod()); // Should print POST
            String name = request.getParameter("name");
            BigDecimal quantity = new BigDecimal(request.getParameter("quantity"));
            BigDecimal price = new BigDecimal(request.getParameter("price"));
            String img = request.getParameter("img");
            String size = request.getParameter("size");
            int categoryId = Integer.parseInt(request.getParameter("category"));

            Category category = categoryDAO.findById(categoryId);
            Product product = new Product();
            product.setName(name);
            product.setQuantity(quantity);
            product.setPrice(price);
            product.setImg(img);
            product.setSize(size);
            product.setIdcategory(category);

            try {
                productService.addProduct(product);
                response.sendRedirect("adminDashboard");; // Redirect to product list after successful addition
            } catch (ProductAlreadyExistsException e) {
                request.setAttribute("errorMessage", e.getMessage());
                request.getRequestDispatcher("AdminDashboard/addProduct.jsp").forward(request, response);
            }
        }

    }


}
