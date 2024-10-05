<%@ include file="header.jsp" %>
<%@ page import="org.example.entities.Cartitem" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List" %>
<%
    Double totalPrice = (Double) request.getAttribute("totalPrice");

%>

<p>Total Price: </p>
    <div class="hero-wrap hero-bread" style="background-image: url('images/bg_1.jpg');">
      <div class="container">
        <div class="row no-gutters slider-text align-items-center justify-content-center">
          <div class="col-md-9 ftco-animate text-center">
          	<p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home</a></span> <span>Cart</span></p>
            <h1 class="mb-0 bread">My Cart</h1>
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
						        <th>&nbsp;</th>
						        <th>Product name</th>
						        <th>Price</th>
						        <th>Quantity</th>
						        <th>Total</th>
						      </tr>
						    </thead>
						    <tbody>
						    <c:forEach items="${cart}" var="item">
						    <c:set var="product" value="${productMap[item.id.idproduct]}" />
						      <tr class="text-center">
						        <td class="product-remove"><a href="#"><span class="ion-ios-close"></span></a></td>
						        
						        <td class="image-prod"><div class="img" style="background-image:url(${product.img});"></div></td>
						        
						        <td class="product-name">
						        	<h3>${product.name}</h3>
						        </td>
						        
						        <td class="price">$${product.price}</td>
						        
						        <td class="quantity">
                                    <div class="input-group mb-3">
                                        <input type="number" name="quantity_${item.id.idproduct}"
                                         class="quantity form-control input-number"
                                         value="${item.quantity}" min="1" max="100">
                                    </div>
                                </td>
						        <c:set var="totalPrice" value="${product.price * item.quantity}" />
						        <td class="total">$${totalPrice}</td>
						      </tr><!-- END TR-->
                              </c:forEach>
						    </tbody>
						  </table>
                        <div style="text-align: center;">
                            <button type="submit" class="btn btn-primary py-3 px-8" style="width: 200px;">Update Cart</button>
                        </div>
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
    						<span>$<%= totalPrice %></span>
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
    				<p><a href="order?action=myOrders" class="btn btn-primary py-3 px-4">Proceed to Checkout</a></p>
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