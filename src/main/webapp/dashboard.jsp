<%@ page import="java.util.List" %>
<%@ page import="com.travelmanagement.model.UserModel" %><%--
  Created by IntelliJ IDEA.
  User: saiydur
  Date: 3/15/2022
  Time: 1:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="partial/header.jsp"%>

<%
    if (request.getSession().getAttribute("userid")==null){
        response.sendRedirect("login");
    }
    if (request.getAttribute("isDone")!=null){
        if ((boolean) request.getAttribute("isDone")==true){
            out.println("<p class=\"alert alert-success\">");
            out.println("Successfully Updated");
            out.println("</p>");
        }
        else {
            out.println("<p class=\"alert alert-danger\">");
            out.println("Information Update Failed");
            out.println("</p>");
        }
    }
    List<UserModel> persons = (List<UserModel>) request.getAttribute("users");
%>

<section class="container mt-5">
    <form action="dashboard" method="post">
        <fieldset>
            <legend>
                Profile Information
            </legend>
            <%
                if (persons!=null){
                    for (UserModel user :
                            persons) {
                        out.println("<div class=\"mb-3\">");
                        out.println("<label for=\"firstName\" class=\"form-label\">First Name</label>\n" +
                                "        <input type=\"text\" name=\"firstName\" value=\""+user.getFirstName()+"\" class=\"form-control\" id=\"firstName\">");
                        out.println("</div>");
                        out.println("<div class=\"mb-3\">");
                        out.println("<label for=\"lastName\" class=\"form-label\">Last Name</label>\n" +
                                "        <input type=\"text\" name=\"lastName\" value=\""+user.getLastName()+"\" class=\"form-control\" id=\"lastName\">");
                        out.println("</div>");
                        out.println("<div class=\"mb-3\">");
                        out.println("<label for=\"email\" class=\"form-label\">Email</label>\n" +
                                "        <input type=\"email\" name=\"email\" value=\""+user.getEmail()+"\" class=\"form-control\" id=\"email\">");
                        out.println("</div>");
                        out.println("<div class=\"mb-3\">");
                        out.println("<label for=\"phoneNumber\" class=\"form-label\">Phone Number</label>\n" +
                                "        <input type=\"tel\" name=\"phoneNumber\" value=\""+user.getPhoneNumber()+"\" class=\"form-control\" id=\"phoneNumber\">");
                        out.println("</div>");
                        request.getSession().setAttribute("password",user.getPassword());
                        out.println("<a href=\"updatePassword\" class=\"btn btn-primary\">Change Password</a>");
                        out.println("<button type=\"submit\" class=\"btn btn-primary\">Update</button>");
                    }
                }
            %>
        </fieldset>
    </form>
</section>

<%@include file="partial/footer.jsp"%>