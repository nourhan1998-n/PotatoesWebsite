package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import org.example.entities.*;
import org.example.exceptions.InsufficientCreditException;
import org.example.exceptions.QuantityLimitExceededException;
import org.example.services.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/order")
public class OrderController extends HttpServlet {

    private OrderServiceImpl orderService;
    private ProductService productService;
    @Override
    public void init() throws ServletException {
        // No need to initialize the session here, it will be fetched from the request
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handle checkout functionality
//        handleCheckout(request, response);
        // Get session from the request
        HttpSession session = request.getSession(false);  // Fetch existing session, do not create a new one

        if (session == null || session.getAttribute("user") == null) {
            //response.sendRedirect("login.html");
            return;
        }

        orderService = new OrderServiceImpl(session);

        String action = request.getParameter("action");

        if ("myOrders".equals(action)) {
            handleGetMyOrders(request, response);
        } else if ("allOrders".equals(action)) {
            handleGetAllOrders(request, response);
        } if ("checkOut".equals(action)) {
            handleCheckout(request, response);
        }else {
            //response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session from the request
        HttpSession session = request.getSession(false);  // Fetch existing session, do not create a new one

        if (session == null || session.getAttribute("user") == null) {
            //response.sendRedirect("login.html");
            return;
        }

        orderService = new OrderServiceImpl(session);

        String action = request.getParameter("action");

        if ("myOrders".equals(action)) {
            handleGetMyOrders(request, response);
        } else if ("allOrders".equals(action)) {
            handleGetAllOrders(request, response);
        }if ("checkOut".equals(action)) {
            handleCheckout(request, response);
        } else {
            //response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
        }
    }

    private void handleCheckout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get session from the request
        HttpSession session = request.getSession(false);
        productService = new ProductServiceImpl();
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        orderService = new OrderServiceImpl(session);
        List<Orderitem> orderitems = null;

        try {
            orderitems = orderService.checkout();// Perform the checkout
            Map<Integer, Product> productMap = new HashMap<>();
            Double totalPrice = 0.0;
            for (Orderitem orderitem : orderitems) {
                Integer productId = orderitem.getId().getIdproduct();
                Product product = productService.getProductById(productId);  // Fetch product details
                productMap.put(productId, product);
                totalPrice += orderitem.getQuantity() * product.getPrice().intValue();
            }
            request.setAttribute("totalPrice", totalPrice);
            request.setAttribute("productMap", productMap);
            request.setAttribute("orderitems", orderitems);
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
            //response.sendRedirect("orderSuccess.jsp");  // Redirect to success page
        } catch (InsufficientCreditException | QuantityLimitExceededException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            // Empty catch block as per user's preference
            e.printStackTrace();
        }
    }

    private void handleGetMyOrders(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            // Get session and user info
            HttpSession session = request.getSession(false);
            CartitemService cartitemService = new CartitemServiceImpl(session);
            if (session == null || session.getAttribute("user") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

        } catch (Exception e) {
            System.out.println("error in servlet");
            e.printStackTrace();
        }
            request.setAttribute("myOrders", orderService.getMyOrders());
            request.getRequestDispatcher("checkout.jsp").forward(request, response);

    }

    private void handleGetAllOrders(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            // Fetch and set all orders to be displayed in the JSP
            request.setAttribute("allOrders", orderService.getAllOrders());
            //request.getRequestDispatcher("allOrders.jsp").forward(request, response);
        } catch (Exception e) {
            // Empty catch block as per user's preference
        }
    }
}
