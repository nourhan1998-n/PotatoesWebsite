package org.example.services;

import jakarta.servlet.http.HttpSession;
import org.example.DAOs.CartItemDAO;
import org.example.entities.User;
import org.example.entities.Cartitem;
import org.example.exceptions.alreadyexist.CartitemAlreadyExistsException;
import org.example.exceptions.notfound.CartitemNotFoundException;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

public class CartitemServiceImpl implements CartitemService {

    private final HttpSession session; // Injected session to work with session attributes
    private final CartItemDAO cartItemDAO = new CartItemDAO();

    // Constructor to initialize HttpSession
    public CartitemServiceImpl(HttpSession session) {
        this.session = session; // Assign session to class
    }

    @Override
    public List<Cartitem> addCartItem(Cartitem cartitem) throws CartitemAlreadyExistsException {
        User user = (User) session.getAttribute("user"); // Fetch user from session
        List<Cartitem> sessionCart = (List<Cartitem>) session.getAttribute("cart"); // Fetch cart from session

        if (user == null) {
            throw new CartitemNotFoundException("User not found in session.");
        }

        // Initialize cart if it does not exist
        if (sessionCart == null) {
            sessionCart = new ArrayList<>();
            session.setAttribute("cart", sessionCart);
        }

        // Check if the cart item already exists
        for(Cartitem sessionCartItem: sessionCart){
            if(sessionCartItem.getId().equals(cartitem.getId())) {
                throw new CartitemAlreadyExistsException("Cart item with ID " + cartitem.getId() + " already exists.");
            }
        }

        // Add the cart item to the session
        sessionCart.add(cartitem);
        session.setAttribute("cart", sessionCart); // Update session with the new cart
        return sessionCart;
    }

    @Override
    public void updateCartItem(Cartitem cartitem) throws CartitemNotFoundException   {
        User user = (User) session.getAttribute("user"); // Fetch user from session
        List<Cartitem> sessionCart = (List<Cartitem>) session.getAttribute("cart"); // Fetch cart from session

        if (user == null) {
            throw new CartitemNotFoundException("User not found in session.");
        }

        // Initialize cart if it does not exist
        if (sessionCart == null) {
            throw new CartitemNotFoundException("Cart not found in session.");
        }

        boolean found = false;

        // Update the cart item in the session
        for (Cartitem item : sessionCart) {
            if (item.getId().equals(cartitem.getId())) {
                item.setQuantity(cartitem.getQuantity()); // Update quantity
                found = true;
                break;
            }
        }

        if (!found) {
            throw new CartitemNotFoundException("Cart item with ID " + cartitem.getId() + " not found.");
        }

        // Update the session with the modified cart
        session.setAttribute("cart", sessionCart);
    }

    @Override
    public void removeCartItem(Cartitem cartitem) throws CartitemNotFoundException {
        User user = (User) session.getAttribute("user"); // Fetch user from session
        List<Cartitem> sessionCart = (List<Cartitem>) session.getAttribute("cart"); // Fetch cart from session

        if (user == null) {
            throw new CartitemNotFoundException("User not found in session.");
        }

        // Initialize cart if it does not exist
        if (sessionCart == null) {
            throw new CartitemNotFoundException("Cart not found in session.");
        }

        boolean removed = sessionCart.removeIf(item -> item.getId().equals(cartitem.getId()));

        if (!removed) {
            throw new CartitemNotFoundException("Cart item with ID " + cartitem.getId() + " not found.");
        }

        // Update the session with the modified cart
        session.setAttribute("cart", sessionCart);
    }

    @Override
    public List<Cartitem> getCartItemsByUserId() throws CartitemNotFoundException {
        User user = (User) session.getAttribute("user"); // Fetch user from session

        if (user == null) {
            throw new CartitemNotFoundException("User not found in session.");
        }

        // Fetch cart from session, initialize if necessary
        List<Cartitem> sessionCart = (List<Cartitem>) session.getAttribute("cart");
        if (sessionCart == null) {
            sessionCart = new ArrayList<>();
            session.setAttribute("cart", sessionCart);
        }

        return sessionCart; // Return the cart from session
    }

    @Override
    public void increaseQuantity(Integer productId, Integer quantity) throws CartitemNotFoundException {
        User user = (User) session.getAttribute("user"); // Fetch user from session
        List<Cartitem> sessionCart = (List<Cartitem>) session.getAttribute("cart"); // Fetch cart from session

        if (user == null) {
            throw new CartitemNotFoundException("User not found in session.");
        }

        // Initialize cart if it does not exist
        if (sessionCart == null) {
            throw new CartitemNotFoundException("Cart not found in session.");
        }

        boolean found = false;

        // Increase the quantity of the cart item in the session
        for (Cartitem item : sessionCart) {
            if (item.getIdproduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity); // Increase quantity
                found = true;
                break;
            }
        }

        if (!found) {
            throw new CartitemNotFoundException("Cart item for product ID " + productId + " not found.");
        }

        // Update the session with the modified cart
        session.setAttribute("cart", sessionCart);
    }

    @Override
    public void updateCartItemFromSession() throws CartitemNotFoundException{
        User user = (User) session.getAttribute("user"); // Fetch user from session
        List<Cartitem> sessionCart = (List<Cartitem>) session.getAttribute("cart"); // Fetch cart from session

        if (user == null) {
            throw new CartitemNotFoundException("User not found in session.");
        }

        // Initialize cart if it does not exist
        if (sessionCart == null) {
            throw new CartitemNotFoundException("Cart not found in session.");
        }
        cartItemDAO.updateByUserAndProduct(user.getId(),sessionCart);
    }
}
