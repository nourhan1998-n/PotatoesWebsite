package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.DAOs.CategoryDAO;
import org.example.entities.Category;
import org.example.entities.Product;
import org.example.exceptions.alreadyexist.ProductAlreadyExistsException;
import org.example.exceptions.notfound.ProductNotFoundException;
import org.example.services.CategoryService;
import org.example.services.CategoryServiceImpl;
import org.example.services.ProductService;
import org.example.services.ProductServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/products")
public class ProductController extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();
    private CategoryDAO categoryDAO = new CategoryDAO() ;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.equals("list")) {
            listAllProducts(request, response);
        } else if (action.equals("findById")) {
            findProductById(request, response);
        } else if (action.equals("findByCategory")) {
            findProductsByCategory(request, response);
        } else if (action.equals("findByName")) {
            findProductsByName(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action.equals("add")) {
            addNewProduct(request, response);
        } else if (action.equals("update")) {
            updateProduct(request, response);
        } else if (action.equals("deleteByName")) {
            deleteProductByName(request, response);
        } else if (action.equals("deleteById")) {
            deleteProductById(request, response);
        }
    }

    // Method to list all products
    private void listAllProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = productService.getAllProducts();
        List<Category> categoryList = categoryDAO.findAll();
        request.setAttribute("products", productList);
        request.setAttribute("category", categoryList);
        request.getRequestDispatcher("shop.jsp").forward(request, response);
    }

    // Method to find a product by ID
    private void findProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        try {
            Product product = productService.getProductById(id);
            request.setAttribute("product", product);
            request.getRequestDispatcher("productDetails.jsp").forward(request, response);
        } catch (ProductNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
    }

    // Method to find products by category name
    private void findProductsByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryName = request.getParameter("categoryName");
        List<Product> productList = productService.findByCategoryName(categoryName);
        request.setAttribute("products", productList);
        request.getRequestDispatcher("shop.jsp").forward(request, response);
    }

    // Method to find products by product name
    private void findProductsByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        List<Product> productList = productService.findByProductName(productName);
        request.setAttribute("products", productList);
        request.getRequestDispatcher("shop.jsp").forward(request, response);
    }

    // Method to add a new product
    private void addNewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Product product = new Product();
            product.setName(request.getParameter("name"));
            product.setQuantity(new BigDecimal(request.getParameter("quantity")));
            product.setPrice(new BigDecimal(request.getParameter("price")));
            product.setImg(request.getParameter("img"));
            product.setSize(request.getParameter("size"));
            // Set the category (retrieved from request, may involve CategoryService)
            // product.setIdcategory(...);

            productService.addProduct(product);
            response.sendRedirect("products?action=list");
        } catch (ProductAlreadyExistsException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("addProduct.jsp").forward(request, response);
        }
    }

    // Method to update an existing product
    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Product product = productService.getProductById(id);
            product.setName(request.getParameter("name"));
            product.setQuantity(new BigDecimal(request.getParameter("quantity")));
            product.setPrice(new BigDecimal(request.getParameter("price")));
            product.setImg(request.getParameter("img"));
            product.setSize(request.getParameter("size"));
            // Update category if needed
            // product.setIdcategory(...);

            productService.updateProduct(product);
            response.sendRedirect("products?action=list");
        } catch (ProductNotFoundException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
        }
    }

    // Method to delete a product by name
    private void deleteProductByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName");
        try {
            productService.deleteProductByName(productName);
            response.sendRedirect("products?action=list");
        } catch (ProductNotFoundException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("shop.jsp").forward(request, response);
        }
    }

    // Method to delete a product by ID
    private void deleteProductById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        try {
            productService.deleteProductById(id);
            response.sendRedirect("products?action=list");
        } catch (ProductNotFoundException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("shop.jsp").forward(request, response);
        }
    }
}
