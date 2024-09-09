package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.entities.User;
import org.example.services.UserService;
import org.example.services.UserServiceImp;

import java.io.IOException;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private UserService userService = new UserServiceImp();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            User user = new User();
            user.setName(request.getParameter("name"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            user.setJob(request.getParameter("job"));
            user.setCredit(request.getParameter("credit"));
            user.setCity(request.getParameter("city"));
            user.setStreet(request.getParameter("street"));

            userService.register(user);
            response.sendRedirect("login.jsp");

        }
        else if ("login".equals(action)) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = userService.login(email, password);
            if (userService.authenticate(email, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("profile.html");
            }
            else {
                //alert error msg
                response.sendRedirect("home.jsp");
            }
        }
    }
}

