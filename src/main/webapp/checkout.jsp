<%@ include file="header.jsp" %>
<%@ page import="org.example.entities.Cartitem" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List" %>
<%
    Double totalPrice = (Double) request.getAttribute("totalPrice");

%>

    <div class="hero-wrap hero-bread" style="background-image: url('images/bg_1.jpg');">
      <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
          <div class="col-md-9 ftco-animate text-center">
          	<p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home</a></span> <span>Checkout</span></p>
            <h1 class="mb-0 bread">Checkout</h1>
          </div>
        </div>
      </div>
    </div>

<section class="ftco-section ftco-cart">
			<div class="container">
				<div class="row">
    			<div class="col-md-12 ftco-animate">
    				<div class="cart-list">
	    				<form action="cart?action=update" method="post">
	    				<table class="table">
						    <thead class="thead-primary">
						      <tr class="text-center">
						        <th>&nbsp;</th>
						        <th>Product name</th>
						        <th>Price</th>
						        <th>Quantity</th>
						        <th>Total</th>
						      </tr>
						    </thead>
						    <tbody>
						    <c:forEach items="${orderitems}" var="item">
						    <c:set var="product" value="${productMap[item.id.idproduct]}" />
						      <tr class="text-center">
						        <td class="image-prod"><div class="img" style="background-image:url(${product.img});"></div></td>

						        <td class="product-name">
						        	<h3>${product.name}</h3>
						        </td>

						        <td class="price">$${item.price}</td>

						        <td class="quantity"> ${item.quantity}  </td>
						        <c:set var="totalPrice" value="${product.price * item.quantity}" />
						        <td class="total">$${totalPrice}</td>
						      </tr><!-- END TR-->
                              </c:forEach>
						    </tbody>
						  </table>
                       </form>
					  </div>
    			</div>
    		</div>
    		<div class="row justify-content-end">

    			<div class="col-lg-4 mt-5 cart-wrap ftco-animate">
    				<div class="cart-total mb-3">
    					<h3>Cart Totals</h3>
    					<p class="d-flex">
    						<span>Subtotal</span>

    						<span>$<%=totalPrice%></span>
    					</p>
    					<p class="d-flex">
    						<span>Delivery</span>
    						<span>$10</span>
    					</p>
    					<hr>
    					<c:set var="grandPrice" value="<%= totalPrice+10 %>" />
    					<p class="d-flex total-price">
    						<span>Total</span>
    						<span>$${grandPrice}</span>
    					</p>
    				</div>
    				<p><a href="#"  id="place-order-btn"  class="btn btn-primary py-3 px-4">Place an order</a></p>
    			</div>
    		</div>
			</div>
		</section>
 <script src="js/jquery.min.js"></script>
  <script src="js/jquery-migrate-3.0.1.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/jquery.easing.1.3.js"></script>
  <script src="js/jquery.waypoints.min.js"></script>
  <script src="js/jquery.stellar.min.js"></script>
  <script src="js/owl.carousel.min.js"></script>
  <script src="js/jquery.magnific-popup.min.js"></script>
  <script src="js/aos.js"></script>
  <script src="js/jquery.animateNumber.min.js"></script>
  <script src="js/bootstrap-datepicker.js"></script>
  <script src="js/scrollax.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="js/google-map.js"></script>
  <script src="js/main.js"></script>

  <script>
		$(document).ready(function(){
        $('#place-order-btn').click(function(e) {
              e.preventDefault(); // Prevent the default action of the anchor tag

              // Show a custom alert with enhanced CSS
              $('body').append('<div class="order-alert" style="position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%); background-color: #28a745; color: white; padding: 20px; font-size: 18px; border-radius: 8px; z-index: 9999; text-align: center;">The order is placed, wait for your delivery within days!</div>');

              // Remove the alert after a few seconds and redirect to index.jsp
              setTimeout(function() {
                $('.order-alert').fadeOut(500, function() {
                  $(this).remove(); // Remove alert from DOM after fade out
                  window.location.href = 'index.jsp'; // Redirect to index.jsp
                });
              }, 3000); // Show the alert for 3 seconds
            });
		var quantitiy=0;
		   $('.quantity-right-plus').click(function(e){
		        
		        // Stop acting like a button
		        e.preventDefault();
		        // Get the field name
		        var quantity = parseInt($('#quantity').val());
		        
		        // If is not undefined
		            
		            $('#quantity').val(quantity + 1);

		          
		            // Increment
		        
		    });

		     $('.quantity-left-minus').click(function(e){
		        // Stop acting like a button
		        e.preventDefault();
		        // Get the field name
		        var quantity = parseInt($('#quantity').val());
		        
		        // If is not undefined
		      
		            // Increment
		            if(quantity>0){
		            $('#quantity').val(quantity - 1);
		            }
		    });
		    
		});
	</script>
<%@ include file="footer.jsp" %>