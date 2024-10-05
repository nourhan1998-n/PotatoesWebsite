package org.example.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.entities.*;
import org.example.services.CartitemService;
import org.example.services.CartitemServiceImpl;
import org.example.exceptions.alreadyexist.CartitemAlreadyExistsException;
import org.example.exceptions.notfound.CartitemNotFoundException;
import org.example.services.ProductService;
import org.example.services.ProductServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/cart")
public class CartitemController extends HttpServlet {

    private CartitemService cartitemService;
    private ProductService productService;
    private HttpSession session;

    @Override
    public void init() throws ServletException {
        // Initialize session for the CartitemService
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (session == null) {
            session = request.getSession();
        }
        if (cartitemService == null) {
            cartitemService = new CartitemServiceImpl(session);
        }

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            handleAddCartItem(request, response);
        } else if ("increase".equals(action)) {
            handleIncreaseQuantity(request, response);
        } else if ("show".equals(action)) {
            show(request, response);
        } else if ("remove".equals(action)) {
            handleRemoveCartItem(request, response);
        } else if ("update".equals(action)) {
            handleUpdateCartItem(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (session == null) {
            session = request.getSession();
        }
        if (cartitemService == null) {
            cartitemService = new CartitemServiceImpl(session);
        }

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            handleAddCartItem(request, response);
        } else if ("increase".equals(action)) {
            handleIncreaseQuantity(request, response);
        } else if ("show".equals(action)) {
            show(request, response);
        } else if ("remove".equals(action)) {
            handleRemoveCartItem(request, response);
        } else if ("update".equals(action)) {
            handleUpdateCartItem(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action: " + action);
        }
    }

    private void handleAddCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        productService = new ProductServiceImpl();
        try {
            User user = (User) request.getSession().getAttribute("user");

            if (user == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            Integer productId = Integer.parseInt(request.getParameter("productId"));
            Product product = productService.getProductById(productId);

            Cartitem cartitem = new Cartitem();
            CartitemId cartitemid = new CartitemId();
            cartitemid.setIdproduct(product.getId());
            cartitemid.setIduser(user.getId());

            cartitem.setId(cartitemid);
            cartitem.setIduser(user);
            cartitem.setIdproduct(product);
            cartitem.setQuantity(1);

            cartitemService.addCartItem(cartitem);

            // Prepare JSON response
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"status\": \"success\", \"message\": \"Item added to cart successfully!\"}");

        } catch (CartitemAlreadyExistsException e) {
            // Handle item already exists case (e.g., increase quantity)
            handleIncreaseQuantity(request, response);
        } catch (NumberFormatException e) {
            System.out.println("Number format exception: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } catch (Exception e) {
            System.out.println("Unknown exception occurred: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    private void show(HttpServletRequest request, HttpServletResponse response) throws IOException {
        productService = new ProductServiceImpl();
        try {
            List<Cartitem> cartitems = cartitemService.getCartItemsByUserId();
            Map<Integer, Product> productMap = new HashMap<>();
            Double totalPrice = 0.0;
            for (Cartitem cartitem : cartitems) {
                Integer productId = cartitem.getId().getIdproduct();
                Product product = productService.getProductById(productId);  // Fetch product details
                productMap.put(productId, product);
                totalPrice += cartitem.getQuantity() * product.getPrice().intValue();
//                System.out.println( product.getPrice());
            }
            request.setAttribute("totalPrice", totalPrice);

            request.setAttribute("productMap", productMap);
            request.setAttribute("cart", cartitems);
            System.out.println("in show ******************");
            RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
            dispatcher.forward(request, response);

        } catch (CartitemNotFoundException e) {
            System.out.println("Cart item Not Found Exception: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Number format exception: " + e.getMessage());
            System.out.println("in show here");
        } catch (Exception e) {
            System.out.println("Unknown exception occurred: " + e.getMessage());
        }
    }

    private void handleIncreaseQuantity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");

            if (user == null) {
                response.sendRedirect("login.html");
                return;
            }

            Integer productId = Integer.parseInt(request.getParameter("productId"));
            Integer quantity = Integer.parseInt(request.getParameter("quantity"));

            cartitemService.increaseQuantity(productId, quantity);
            response.sendRedirect("cart.jsp");

        } catch (CartitemNotFoundException e) {
            System.out.println("Cartitem not found exception: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Number format exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unknown exception occurred: " + e.getMessage());
        }
    }

    private void handleRemoveCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");

            if (user == null) {
                response.sendRedirect("login.html");
                return;
            }

            Integer productId = Integer.parseInt(request.getParameter("itemId"));

            Cartitem cartitem = new Cartitem();

            CartitemId cartId = new CartitemId();
            cartId.setIdproduct(productId);
            cartId.setIduser(user.getId());

            cartitem.setId(cartId);

            cartitemService.removeCartItem(cartitem);
            //show(request,response);
            response.sendRedirect("cart.jsp");

        } catch (CartitemNotFoundException e) {
            System.out.println("Cartitem not found exception: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Number format exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Unknown exception occurred: " + e.getMessage());
        }
    }

    private void handleUpdateCartItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Retrieve the cart from the session
            List<Cartitem> cartitems = (List<Cartitem>) request.getSession().getAttribute("cart");

            // Get the product ID and new quantity from the AJAX request
            int productId = Integer.parseInt(request.getParameter("itemId"));
            int newQuantity = Integer.parseInt(request.getParameter("newQuantity"));

            // Find the cart item and update the quantity
            for (Cartitem cartitem : cartitems) {
                if (cartitem.getId().getIdproduct() == productId) {
                    cartitem.setQuantity(newQuantity);
                    break;
                }
            }

            // Update the cart in the session
            request.getSession().setAttribute("cart", cartitems);

            // Respond with success
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid quantity format.");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error updating cart: " + e.getMessage());
        }
    }

}
