package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.entities.Cartitem;
import org.example.entities.Product;
import org.example.entities.User;
import org.example.services.CartitemService;
import org.example.services.CartitemServiceImpl;
import org.example.services.OrderService;
import org.example.service.OrderServiceImpl;

import java.io.IOException;

@WebServlet("/cart")
public class CartitemController extends HttpServlet {
    private CartitemService cartitemService = new CartitemServiceImpl();
    private OrderService orderService = new OrderServiceImpl();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (user != null) {
                Integer productId = Integer.parseInt(request.getParameter("productId"));
                Integer quantity = Integer.parseInt(request.getParameter("quantity"));

                // Assuming product has been retrieved based on productId
                Product product = new Product(); // In real case, you'd retrieve it using a ProductService

                Cartitem cartitem = new Cartitem();
                cartitem.setIdproduct(product);
                cartitem.setIduser(user);
                cartitem.setQuantity(quantity);

                cartitemService.addCartItem(cartitem);
                response.sendRedirect("cart.html");
            } else {
                response.sendRedirect("login.html");
            }
        }
        else if ("increase".equals(action)) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (user != null) {
                Integer productId = Integer.parseInt(request.getParameter("productId"));
                Integer quantity = Integer.parseInt(request.getParameter("quantity"));

                cartitemService.increaseQuantity(user.getId(), productId, quantity);
                response.sendRedirect("cart.html");
            }
        }
        else if ("checkout".equals(action)) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (user != null) {
                orderService.checkout(user);
                response.sendRedirect("orderConfirmation.html");  // Redirect to a confirmation page
            } else {
                response.sendRedirect("login.html");
            }
        }
        else if ("remove".equals(action)) {
            // Implementation to remove a cart item
        }
    }
}
