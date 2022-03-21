<%@ page import="java.util.List" %>
<%@ page import="com.travelmanagement.model.PackageModel" %><%--
  Created by IntelliJ IDEA.
  User: saiydur
  Date: 3/21/2022
  Time: 11:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="partial/header.jsp"%>
  <%
    if (request.getSession().getAttribute("userid")!=null){
      int id = (int) request.getSession().getAttribute("userid");
      if (id<0){
        response.sendRedirect("dashboard");
      }
    }

    if (request.getParameter("isDone")!=null){
        if (String.valueOf(request.getAttribute("isDone"))=="1"){
            out.println("Package Added Successfully");
        }
        else {
            out.println("Package Added Successfully");
        }
    }
  %>
  <section class="container">
      <div class="row mt-3">
          <div class="col-7">
              <form method="post" action="addpackages">
                  <fieldset>
                      <legend>
                          Package Details Here
                      </legend>
                      <div class="form-group">
                          <label for="name">Package Name</label>
                          <input type="text" class="form-control" id="name" name="name" placeholder="Dhaka To Bandarban" required>
                      </div>
                      <div class="form-group">
                          <label for="duration">Duration</label>
                          <input type="text" class="form-control" id="duration" name="duration" placeholder="3 Days 2 Nights" required>
                      </div>
                      <div class="form-group">
                          <label for="description">Description</label>
                          <textarea class="form-control" id="description" name="description" required></textarea>
                      </div>
                      <div class="form-group">
                          <label for="price">Price</label>
                          <input type="number" class="form-control" id="price" name="price" required>
                      </div>
                      <div class="form-group">
                          <label for="discount">Discount</label>
                          <input type="number" class="form-control" id="discount" name="discount" required>
                      </div>
                      <button type="submit" class="btn btn-primary mt-2">Create Package</button>
                  </fieldset>
              </form>
          </div>
          <div class="col-5 mt-3">
              <%
                  if (request.getAttribute("packages")!=null){
                      List<PackageModel> packageModels = (List<PackageModel>) request.getAttribute("packages");
                      if(packageModels!=null){
                          out.println("<table class=\"table table-info table-striped\">\n" +
                                  "  <thead>\n" +
                                  "    <tr>\n" +
                                  "      <th scope=\"col\">Package Name</th>\n" +
                                  "      <th scope=\"col\">Description</th>\n" +
                                  "      <th scope=\"col\">Price</th>\n" +
                                  "    </tr>\n" +
                                  "  </thead>\n" +
                                  "  <tbody>\n" );
                          for (PackageModel pkg:
                          packageModels){
                              out.println("<tr>"+
                                      "<td>"+pkg.getName()+"</td>"+
                                      "<td>"+pkg.getDescription()+"</td>"+
                                      "<td>"+pkg.getPrice()+"</td>"+
                                      "</tr>");
                          }
                          out.println(
                                  "  </tbody>\n" +
                                  "</table>");
                      }
                  }
              %>
          </div>
      </div>
  </section>

<%@include file="partial/footer.jsp"%>