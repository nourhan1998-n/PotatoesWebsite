package org.example.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;  // This is for managing transactions
import jakarta.servlet.http.HttpSession;
import org.example.DAOs.*;
import org.example.entities.*;
import org.example.exceptions.*;
import org.example.exceptions.notfound.UserNotFoundException;
import org.example.exceptions.FailedToUpdateProductQuantityException;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO ;
    private final OrderItemDAO orderItemDAO ;
    private final UserDAO userDAO ;
    private final ProductDAO productDAO ;
    private final EntityManagerFactory emf ;
    private final EntityManager em;
    private final EntityTransaction transaction ;
    private final HttpSession session; // Injected session to work with session attributes
    Order order;

    public OrderServiceImpl(HttpSession session) {
        this.orderDAO = new OrderDAO();
        this.orderItemDAO = new OrderItemDAO();
        this.userDAO = new UserDAO();
        this.productDAO = new ProductDAO();
        this.emf = new EntityManagerFactorySinglton().getEntityManagerFactory();
        this.em = emf.createEntityManager();
        this.transaction = em.getTransaction();
        this.session = session;
        this.order = null;

    }

    @Override
    public List<Orderitem> checkout() throws InsufficientCreditException, QuantityLimitExceededException, FailedToUpdateProductQuantityException {
        // Get the user and cart items from the session
        User user = getUserFromSession();
        List<Cartitem> cartItems = getCartItemsFromSession();

        List<Orderitem> orderItems = new ArrayList<>();

        order = createOrder(user);


        //Calculates the total price of the Order and fills the List<Cartitem> cartItems
        BigDecimal totalOrderPrice = calculateTotalOrderPrice(cartItems, orderItems);

        transaction.begin();

        //Checks if the user has sufficient credit
        subtractUserCredit(user, totalOrderPrice);

        saveOrderAndOrderItems(orderItems);

        //rollback added internally
        updateProductQuantities(orderItems);

        clearCart();

        return orderItems;
    }

    private User getUserFromSession() {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new UserNotFoundException("User not found in session.");
        }
        return user;
    }

    private List<Cartitem> getCartItemsFromSession() {
        List<Cartitem> cartItems = (List<Cartitem>) session.getAttribute("cart");
        if (cartItems == null || cartItems.isEmpty()) {
            throw new IllegalStateException("No cart items found in session.");
        }
        return cartItems;
    }

    private Order createOrder(User user) {
        Order thisOrder = new Order();
        thisOrder.setDate(LocalDate.now());
        thisOrder.setIduser(user);
        return thisOrder;
    }


    private BigDecimal calculateTotalOrderPrice(List<Cartitem> cartItems, List<Orderitem> orderItems) throws QuantityLimitExceededException {
        BigDecimal totalOrderPrice = BigDecimal.ZERO;
        for (Cartitem cartItem : cartItems) {
            Orderitem orderItem = new Orderitem();

            OrderitemId orderitemId = new OrderitemId();
            orderitemId.setIdorder(order.getId());
            orderitemId.setIdproduct(cartItem.getId().getIdproduct());

            Product product = productDAO.findById(cartItem.getId().getIdproduct());

            BigDecimal cartItemPrice = product.getPrice().multiply(new BigDecimal(cartItem.getQuantity()));


            totalOrderPrice = totalOrderPrice.add(cartItemPrice);

            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItemPrice);
            orderItem.setId(orderitemId);
            orderItem.setIdorder(order);
            orderItem.setIdproduct(productDAO.findById(cartItem.getId().getIdproduct()) );

            orderItems.add(orderItem);

            checkQuantityLimit(cartItem);
        }
        return totalOrderPrice;
    }

    private void checkQuantityLimit(Cartitem cartItem) throws QuantityLimitExceededException {
        Product product = productDAO.findById(cartItem.getId().getIdproduct());
        BigDecimal maxQuantity = product.getQuantity();
        BigDecimal cartProductQuantity = new BigDecimal(cartItem.getQuantity());
        if (cartProductQuantity.compareTo(maxQuantity) > 0) {
            throw new QuantityLimitExceededException("The maximum quantity for the product " + productDAO.findById(cartItem.getId().getIdproduct()).getName() + " is " + cartItem.getIdproduct().getQuantity());
        }
    }

    private void subtractUserCredit(User user, BigDecimal totalOrderPrice) throws InsufficientCreditException {
        BigDecimal userCredit = new BigDecimal(user.getCredit());
        BigDecimal deliveryFees = BigDecimal.valueOf(10);
        BigDecimal price = totalOrderPrice.add(deliveryFees);
        if (totalOrderPrice.compareTo(userCredit) > 0) {
            em.close();
            throw new InsufficientCreditException("Insufficient credit to complete the purchase.");
        }
        user.setCredit(String.valueOf(userCredit.subtract(price)));
        em.merge(user);
    }


    private void saveOrderAndOrderItems( List<Orderitem> orderItems) throws FailedToSaveOrderException {
        em.persist(order);
        Orderitem orderitemFailed = null;

        try{
            for (Orderitem orderItem : orderItems) {
                orderitemFailed = orderItem;
                Product managedProduct = em.merge(productDAO.findById(orderItem.getId().getIdproduct()));
                orderItem.setIdproduct(managedProduct);
                em.persist(orderItem); // Save order items
            }
        }
        catch(Exception e){
            transaction.rollback();
            em.close();
            if(orderitemFailed == null){
                throw new FailedToUpdateProductQuantityException("Error occurred while upadating saving order.");
            }
            throw new FailedToUpdateProductQuantityException("Error occurred while saving order " +productDAO.findById(orderitemFailed.getId().getIdproduct()).getName() + ".");
        }

    }


    private void updateProductQuantities(List<Orderitem> orderItems) throws FailedToUpdateProductQuantityException {
        Product errorProduct = null;
        try {
            for (Orderitem orderItem : orderItems) {
                Product product = orderItem.getIdproduct();
                errorProduct = product;

                BigDecimal oldQuantity = product.getQuantity();
                BigDecimal orderItemQuantity = new BigDecimal(orderItem.getQuantity());


                // Subtract the order quantity from the product's available quantity
                BigDecimal newQuantity = oldQuantity.subtract(orderItemQuantity);
                product.setQuantity(newQuantity);

                // Update the product in the database
                em.merge(product); // Assuming productDAO has an update method
            }
        } catch (Exception e) {
            transaction.rollback();
            if(errorProduct == null){
                throw new FailedToUpdateProductQuantityException("Error occurred while upadating product quantites.");
            }
            throw new FailedToUpdateProductQuantityException("Error occurred while upadating quantity of " + errorProduct.getName() + ".");
        }
        finally{
            transaction.commit();
            em.close();
        }
    }


    private void clearCart() {
        session.setAttribute("cart", new ArrayList<Cartitem>());
    }




    public List<Order> getAllOrders() {
        return orderDAO.findAll();
    }

    public List<Orderitem> getAllOrdersByUserId(Integer userId) throws UserNotFoundException {
        if (userDAO.findById(userId) == null) {
            throw new UserNotFoundException("User with Id " + userId + " not found.");
        }
        return orderItemDAO.findAllByUserId(userId);
    }

    public List<Orderitem> getMyOrders() throws UserNotFoundException {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new UserNotFoundException("User with Id " + user.getId() + " not found.");
        }
        return orderItemDAO.findAllByUserId(user.getId());
    }
}
