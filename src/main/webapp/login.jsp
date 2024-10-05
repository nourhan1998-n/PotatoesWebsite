<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %> <!-- Assuming you have a header.jsp -->

<div class="hero-wrap hero-bread" style="background-image: url('images/bg_1.jpg');">
  <div class="container">
    <div class="row no-gutters slider-text align-items-center justify-content-center">
      <div class="col-md-9 ftco-animate text-center">
        <h1 class="mb-0 bread">Login</h1>
      </div>
    </div>
  </div>
</div>

<section class="ftco-section">
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-6 col-lg-6">
        <div class="login-wrap p-4 p-md-5">
          <div class="icon d-flex align-items-center justify-content-center">
            <span class="fa fa-user"></span>
          </div>
          <h3 class="text-center mb-4">Log in to your account</h3>
          <form action="user?action=login" method="post" class="login-form">
            <div class="form-group">
              <input type="text" class="form-control" name="email" placeholder="Email" required>
            </div>
            <div class="form-group">
              <input type="password" class="form-control" name="password" placeholder="Password" required>
            </div>
            <div class="form-group">
              <button type="submit" class="btn btn-primary form-control">Log In</button>
            </div>
            <div class="form-group">
              <div class="text-center mb-4">
                <a href="signup.jsp">No account signup! </a>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</section>

<%@ include file="footer.jsp" %>