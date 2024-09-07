package org.example.controller;

import org.example.entities.Category;
import org.example.services.CategoryService;
import org.example.services.CategoryServiceImpl; // Assuming you have an implementation class

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/category")
public class CategoryController extends HttpServlet {
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialize the CategoryService (consider using dependency injection if possible)
        categoryService = new CategoryServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing.");
            return;
        }

        switch (action) {
            case "add":
                handleAddCategory(request, response);
                break;
            case "removeByName":
                handleRemoveCategoryByName(request, response);
                break;
            case "removeById":
                handleRemoveCategoryById(request, response);
                break;
            case "getByName":
                handleGetCategoryByName(request, response);
                break;
            case "getById":
                handleGetCategoryById(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
        }
    }

    private void handleAddCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String categoryName = request.getParameter("categoryName");
        if (categoryName == null || categoryName.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Category name is required.");
            return;
        }
        categoryService.addCategoryByName(categoryName);
        response.sendRedirect("success.jsp"); // Redirect or send a success response
    }

    private void handleRemoveCategoryByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String categoryName = request.getParameter("categoryName");
        if (categoryName == null || categoryName.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Category name is required.");
            return;
        }
        categoryService.removeCategoryByName(categoryName);
        response.sendRedirect("success.jsp"); // Redirect or send a success response
    }

    private void handleRemoveCategoryById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String categoryIdStr = request.getParameter("categoryID");
        if (categoryIdStr == null || categoryIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Category ID is required.");
            return;
        }
        try {
            Integer categoryID = Integer.valueOf(categoryIdStr);
            categoryService.removeCategoryById(categoryID);
            response.sendRedirect("success.jsp"); // Redirect or send a success response
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid category ID.");
        }
    }

    private void handleGetCategoryByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String categoryName = request.getParameter("categoryName");
        if (categoryName == null || categoryName.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Category name is required.");
            return;
        }
        Category category = categoryService.getCategoryByName(categoryName);
        request.setAttribute("category", category);
        //request.getRequestDispatcher("category.jsp").forward(request, response); // Forward to a JSP page
    }

    private void handleGetCategoryById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String categoryIdStr = request.getParameter("categoryID");
        if (categoryIdStr == null || categoryIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Category ID is required.");
            return;
        }
        try {
            Integer categoryID = Integer.valueOf(categoryIdStr);
            Category category = categoryService.getCategoryByID(categoryID);
            request.setAttribute("category", category);
            //request.getRequestDispatcher("category.jsp").forward(request, response); // Forward to a JSP page
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid category ID.");
        }
    }
}
