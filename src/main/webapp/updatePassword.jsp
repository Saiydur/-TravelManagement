<%--
  Created by IntelliJ IDEA.
  User: saiydur
  Date: 3/21/2022
  Time: 2:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    if (request.getSession().getAttribute("userid")==null){
        response.sendRedirect("login");
    }


%>

<%@include file="partial/header.jsp"%>
    <%
        if (request.getAttribute("update")!=null){
            out.println("<p class=\"alert alert-primary\">"+(String)request.getAttribute("update")+"</p>");
        }
    %>
    <section class="container mt-5">
        <form action="" method="post">
            <div class="mb-3">
                <label for="oldPassword" class="form-label">Old Password</label>
                <input type="text" class="form-control" id="oldPassword" name="oldPassword">
            </div>
            <div class="mb-3">
                <label for="newPassword" class="form-label">New Password</label>
                <input type="text" class="form-control" id="newPassword" name="newPassword">
            </div>
            <button type="submit" class="btn btn-primary">Update Password</button>
        </form>
    </section>
<%@include file="partial/footer.jsp"%>