package org.example.controller;

import org.example.DAOs.CartItemDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.entities.Cartitem;
import org.example.entities.User;
import org.example.exceptions.IncorrectPasswordException;
import org.example.exceptions.notfound.UserNotFoundException;
import org.example.services.CartitemServiceImpl;
import org.example.services.UserService;
import org.example.services.UserServiceImp;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private UserService userService ;
    private CartItemDAO cartitemDAO;

    public UserController(){
        userService = new UserServiceImp();
        cartitemDAO = new CartItemDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "register":
                handleRegister(request, response);
                break;
            case "login":
                handleLogin(request, response);
                break;
            case "logout":
                handleLogout(request, response);
                break;
            case "updateProfile":
                handleProfileUpdate(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
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
        catch(UserNotFoundException e){

        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

            try{
                User user = userService.login(email, password);
                List<Cartitem> cart = cartitemDAO.findByUserId(user.getId());

                if(cart == null){
                    cart = new ArrayList<Cartitem>();
                }
                // Invalidate the current session (if exists) and create a new session
                HttpSession oldSession = request.getSession(false); // Fetch current session without creating a new one
                if (oldSession != null) {
                    oldSession.invalidate(); // Invalidate the existing session to create a new one
                }
                HttpSession newSession = request.getSession(true);

                newSession.setAttribute("user", user);
                newSession.setAttribute("cart", cart);
                response.sendRedirect("index.jsp");
            }
           catch(UserNotFoundException e){
               response.sendRedirect("login.jsp");
            }
            catch(IncorrectPasswordException e){
                response.sendRedirect("login.jsp");
            }
    }

    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session =request.getSession(false);
        ending(session);
        session.invalidate();  // Invalidate the session to log out
        System.out.println("Session invalidated in servlet.");
        response.sendRedirect("login.jsp");  // Redirect to login page
    }

    private void ending(HttpSession  session){
        User user = (User) session.getAttribute("user");
        List<Cartitem> cart = (List<Cartitem>) session.getAttribute("cart");

        if (user != null && cart != null) {
            CartitemServiceImpl cartitemService = new CartitemServiceImpl(session);

            // Update the Cart in the database
            cartitemService.updateCartItemFromSession();
        }
        System.out.println("Session destroyed: " + session.getId());
    }

    private void handleProfileUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieve user data from request parameters
        User user = (User) request.getSession().getAttribute("user");
        String name = request.getParameter("editName");
        String password = request.getParameter("editPassword");
        String email = request.getParameter("editEmail");
        String job = request.getParameter("editJob");
        String credit = request.getParameter("editCredit");
        String city = request.getParameter("editCity");
        String street = request.getParameter("editStreet");

        // Convert the ID to Integer
        Integer id = user.getId();

        // Create a new User object with updated information
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setJob(job);
        user.setCredit(credit);
        user.setCity(city);
        user.setStreet(street);

        // Call the updateUser method from UserService
        userService.updateUser(user);

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}
