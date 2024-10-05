<!-- login.jsp -->
<form action="/VegesFoodWebsite/AdminLoginServlet" method="post" class="login-form">
  <input type="email" name="email" placeholder="Email" required />
  <input type="password" name="password" placeholder="Password" required />
  <button type="submit">Login</button>
</form>

<style>
  /* General Form Styling */
  .login-form {
    width: 100%;
    max-width: 400px;
    margin: 50px auto;
    padding: 20px;
    background-color: #f8f9fa;
    box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
  }

  .login-form input {
    width: 100%;
    padding: 15px;
    margin: 10px 0;
    border: 1px solid #ced4da;
    border-radius: 5px;
    font-size: 16px;
  }

  .login-form input:focus {
    border-color: #80bdff;
    outline: none;
    box-shadow: 0 0 5px rgba(0, 123, 255, 0.25);
  }

  .login-form button {
    width: 100%;
    padding: 15px;
    background-color: #28a745;
    border: none;
    color: white;
    font-size: 18px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s;
  }

  .login-form button:hover {
    background-color: #218838;
  }

  /* Placeholder Styling */
  ::placeholder {
    color: #6c757d;
    font-size: 14px;
  }

  /* Form Container Styling */
  .login-form {
    display: flex;
    flex-direction: column;
  }

  /* Add some spacing between elements */
  .login-form input,
  .login-form button {
    margin-bottom: 20px;
  }

  /* Media Queries for Mobile Devices */
  @media (max-width: 576px) {
    .login-form {
      padding: 15px;
    }

    .login-form input, .login-form button {
      font-size: 14px;
    }
  }
</style>

<%@ include file="adminfooter.jsp" %>