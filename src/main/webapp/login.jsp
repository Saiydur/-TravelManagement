<%--
  Created by IntelliJ IDEA.
  User: saiydur
  Date: 3/8/2022
  Time: 11:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="partial/header.jsp"%>

    <%
        if (request.getSession().getAttribute("userid")!=null)
        {
            out.println("<p class=\"alert alert-success\" role=\"alert\">");
            String message = request.getSession().getAttribute("userid").toString();
            out.println(message);
            out.println("</p>");
        }
    %>

<section class="container">
    <form method="post" novalidate action="login" autocomplete="off">
        <fieldset>
            <legend>
                Login Here
            </legend>
            <div class="form-group">
                <label for="emailorusername">Email or Username</label>
                <input type="text" class="form-control" id="emailorusername" name="emailorusername" placeholder="Enter Your Username or email" required>
            </div>
            <div class="form-group">
                <label for="password">Last Name</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="Enter Your password" required>
            </div>
            <button type="submit" class="btn btn-primary mt-2">Login</button>
        </fieldset>
    </form>
</section>
