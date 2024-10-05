package org.example.controller.admin;

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
@WebServlet("/viewUsers")
public class ViewUsers extends HttpServlet {
    private UserDAO user=new UserDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = user.findAll();
        System.out.println(users);
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("AdminDashboard/view-users.jsp");
        dispatcher.forward(request, response);
    }
}
