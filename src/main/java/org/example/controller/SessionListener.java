package org.example.controller;
import org.example.DAOs.CartItemDAO;
import org.example.entities.Cartitem;
import org.example.entities.User;


import java.util.List;
import java.util.Set;


import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;



@WebListener
public class SessionListener implements HttpSessionListener {



    public SessionListener(){
    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        // Code to be executed when the session starts
        System.out.println("Session started: " + event.getSession().getId());


    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {

        HttpSession session = event.getSession();

        // Retrieve the User and cart from the session before it is destroyed
        User user = (User) session.getAttribute("user");
        List<Cartitem> cart = (List<Cartitem>) session.getAttribute("cart");


        if (user != null && cart != null) {
            CartItemDAO cartDAO = new CartItemDAO();
            Integer userId = user.getId();  // Assuming User has a getId() method

            // Update the cart in the database
            cartDAO.updateByUserAndProduct(userId, cart);
        }
        System.out.println("Session destroyed: " + session.getId());

    }
}
