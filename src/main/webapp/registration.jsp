<%--
  Created by IntelliJ IDEA.
  User: saiydur
  Date: 3/8/2022
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="partial/header.jsp"%>
<%--Registration Section--%>
<section class="container">
  <p class="alert alert-success" role="alert">
    <%
      if (request.getSession().getAttribute("success")!=null)
      {
        String message = request.getSession().getAttribute("success").toString();
        out.println(message);
      }
    %>
  </p>
  <form method="post" novalidate action="registration">
    <fieldset>
      <legend>
        Basic Information
      </legend>
      <div class="form-group">
        <label for="firstName">First Name</label>
        <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name" required>
      </div>
      <div class="form-group">
        <label for="lastName">Last Name</label>
        <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" required>
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
      </div>
      <div class="form-group">
        <label for="phone">Phone</label>
        <input type="tel" class="form-control" id="phone" name="phone" placeholder="Phone" required>
      </div>
      <div class="form-group">
        <label for="username">Password</label>
        <input type="text" class="form-control" id="username" name="username" placeholder="username" required>
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
      </div>
      <div class="form-group">
        <label >Select Account Type</label><br>
        <input type="radio" name="role" id="travel_agent" value="travel_agent"> Travel Agent
        <input type="radio" name="role" id="traveler" value="traveler"> Traveler
      </div>
      <button class="btn btn-primary" type="submit">Register Now</button>
    </fieldset>
  </form>
</section>