package org.example.controller;

import org.example.entities.Category;
import org.example.services.CategoryService;
import org.example.services.CategoryServiceImpl;
import org.example.exceptions.notfound.CategoryNotFoundException;

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
        categoryService = new CategoryServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing.");
            return;
        }

        try {
            switch (action) {
                case "add":
                    handleAddCategory(request);
                    break;
                case "removeByName":
                    handleRemoveCategoryByName(request);
                    break;
                case "removeById":
                    handleRemoveCategoryById(request);
                    break;
                case "getByName":
                    handleGetCategoryByName(request, response);
                    break;
                case "getById":
                    handleGetCategoryById(request, response);
                    break;
                case "update":
                    handleUpdateCategory(request);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception
        }
    }

    private void handleAddCategory(HttpServletRequest request) {
        String categoryName = request.getParameter("categoryName");
        if (categoryName == null || categoryName.isEmpty()) {
            System.out.println("Category name is required.");
            return;
        }

        try {
            categoryService.addCategoryByName(categoryName);
            System.out.println("Category added successfully.");
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception
        }
    }

    private void handleRemoveCategoryByName(HttpServletRequest request) {
        String categoryName = request.getParameter("categoryName");
        if (categoryName == null || categoryName.isEmpty()) {
            System.out.println("Category name is required.");
            return;
        }

        try {
            categoryService.removeCategoryByName(categoryName);
            System.out.println("Category removed successfully.");
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception
        }
    }

    private void handleRemoveCategoryById(HttpServletRequest request) {
        String categoryIdStr = request.getParameter("categoryID");
        if (categoryIdStr == null || categoryIdStr.isEmpty()) {
            System.out.println("Category ID is required.");
            return;
        }

        try {
            Integer categoryID = Integer.valueOf(categoryIdStr);
            categoryService.removeCategoryById(categoryID);
            System.out.println("Category removed successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid category ID.");
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception
        }
    }

    private void handleGetCategoryByName(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String categoryName = request.getParameter("categoryName");
        if (categoryName == null || categoryName.isEmpty()) {
            System.out.println("Category name is required.");
            return;
        }

        try {
            Category category = categoryService.getCategoryByName(categoryName);
            request.setAttribute("category", category);
            System.out.println("Category retrieved successfully: " + category);
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception
        }
    }

    private void handleGetCategoryById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String categoryIdStr = request.getParameter("categoryID");
        if (categoryIdStr == null || categoryIdStr.isEmpty()) {
            System.out.println("Category ID is required.");
            return;
        }

        try {
            Integer categoryID = Integer.valueOf(categoryIdStr);
            Category category = categoryService.getCategoryByID(categoryID);
            request.setAttribute("category", category);
            System.out.println("Category retrieved successfully: " + category);
        } catch (NumberFormatException e) {
            System.out.println("Invalid category ID.");
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception
        }
    }

    private void handleUpdateCategory(HttpServletRequest request) {
        String categoryIdStr = request.getParameter("categoryID");
        String categoryName = request.getParameter("categoryName");

        if (categoryIdStr == null || categoryIdStr.isEmpty() || categoryName == null || categoryName.isEmpty()) {
            System.out.println("Category ID and Name are required.");
            return;
        }

        try {
            Integer categoryID = Integer.valueOf(categoryIdStr);
            Category category = categoryService.getCategoryByID(categoryID);
            category.setId(categoryID);
            category.setName(categoryName);

            categoryService.updateCategory(category);
            System.out.println("Category updated successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid category ID.");
        } catch (CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception
        }
    }
}
