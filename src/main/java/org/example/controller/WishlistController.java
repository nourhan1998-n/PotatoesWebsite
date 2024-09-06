package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entities.User;
import org.example.entities.Wishlist;
import org.example.entities.WishlistId;
import org.example.entities.Product;
import org.example.services.WishlistService;
import org.example.services.WishlistServiceImpl;

import java.io.IOException;

@WebServlet("/wishlist")
public class WishlistController extends HttpServlet {
    private WishlistService wishlistService = new WishlistServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Integer productId = Integer.parseInt(request.getParameter("productId"));

        if (user != null) {
            Wishlist wishlist = new Wishlist();
            WishlistId wishlistid = new WishlistId();
            wishlistid.setIduser(user.getId());
            wishlistid.setIdproduct(productId);
            wishlist.setId(wishlistid);

            wishlistService.addToWishlist(wishlist);
            response.sendRedirect("wishlist.html");
        } else {
            response.sendRedirect("shop.html");
        }
    }
}

