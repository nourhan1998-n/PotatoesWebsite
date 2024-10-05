<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ page import="org.example.entities.Category" %>
<%@ page import="org.example.entities.Product" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List" %>

<div class="hero-wrap hero-bread" style="background-image: url('images/bg_1.jpg');">
  <div class="container">
    <div class="row no-gutters slider-text align-items-center justify-content-center">
      <div class="col-md-9 ftco-animate text-center">
        <p class="breadcrumbs">
          <span class="mr-2"><a href="index.html">Home</a></span>
          <span>Products</span>
        </p>
        <h1 class="mb-0 bread">Products</h1>
      </div>
    </div>
  </div>
</div>

<section class="ftco-section">
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-10 mb-5 text-center">
        <ul class="product-category">
          <li><a href="#" class="active" data-filter="all">All</a></li>
          <c:forEach items="${category}" var="c">
            <li><a href="#" data-filter="${c.getName()}">${c.getName()}</a></li>
          </c:forEach>
        </ul>
      </div>
    </div>
    <div class="row">
      <c:forEach items="${products}" var="p">
        <div class="col-md-6 col-lg-3 ftco-animate" data-category="${p.getIdcategory().getName()}">
          <div class="product">
            <a href="productdetails?productId=${p.getId()}" class="img-prod">
              <img class="img-fluid" src="${p.getImg()}" alt="Colorlib Template">
              <div class="overlay"></div>
            </a>
            <div class="text py-3 pb-4 px-3 text-center">
              <h3><a href="productdetails?productId=${p.getId()}">${p.getName()}</a></h3>
              <div class="d-flex">
                <div class="pricing">
                  <p class="price"><span>$ ${p.getPrice()}</span></p>
                </div>
              </div>
              <div class="bottom-area d-flex px-3">
                <div class="m-auto d-flex">
                  <a href="javascript:void(0);" class="buy-now d-flex justify-content-center align-items-center mx-1 add-to-cart" data-product-id="${p.getId()}">
                      <span><i class="ion-ios-cart"></i></span>
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
<!--
 <div class="row mt-5">
      <div class="col text-center">
        <div class="block-27">
          <ul>
            <li><a href="#">&lt;</a></li>
            <li class="active"><span>1</span></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">&gt;</a></li>
          </ul>
        </div>
      </div>
    </div>
-->
  </div>
</section>




<!-- jQuery for product filtering -->
<script src="js/jquery.min.js"></script>
<script>
  $(document).ready(function() {
    // Handle category click
    $('.product-category a').click(function(e) {
      e.preventDefault();
      var filter = $(this).attr('data-filter');

      // Remove active class from all links
      $('.product-category a').removeClass('active');
      // Add active class to clicked link
      $(this).addClass('active');

      // Show/Hide products
      if (filter === 'all') {
        $('.ftco-animate').show();
      } else {
        $('.ftco-animate').hide();
        $('.ftco-animate[data-category="' + filter + '"]').show();
      }
    });

// Handle Add to Cart button click
    $('.add-to-cart').click(function(e) {
      e.preventDefault();

      // Get the product ID from the data attribute
      var productId = $(this).data('product-id');

      // Send AJAX request to add the product to the cart
      $.ajax({
        url: 'cart?action=add',
        type: 'GET',
        data: { productId: productId },
        success: function(response) {
          // Handle successful response (you can show a message or update cart icon)
          //alert(response.message);  // Display success message
        },
        error: function(xhr, status, error) {
          // Handle errors
          alert("Item is in cart already!");
        }
      });
    });

  });
</script>





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
<%@ include file="footer.jsp" %>