<%@ page import="com.travelmanagement.model.PackageModel" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: saiydur
  Date: 3/8/2022
  Time: 9:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="partial/header.jsp"%>

    <section class="container">
        <table class="table table-success table-striped">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">Description</th>
                <th scope="col">Total Day</th>
                <th scope="col">Price</th>
                <th scope="col" colspan="2">Action</th>
            </tr>
            </thead>
            <tbody>
                <%
                    if (request.getAttribute("allPackage")!=null){
                        List<PackageModel> packageModels = (List<PackageModel>) request.getAttribute("allPackage");
                        if (packageModels!=null){
                            for (PackageModel pkg :
                                    packageModels) {
                                out.println("<tr>\n" +
                                        "      <th scope=\"row\">"+pkg.getName()+"</th>\n" +
                                        "      <td>"+pkg.getDescription()+"</td>\n" +
                                        "      <td>"+pkg.getDuration()+"</td>\n" +
                                        "      <td>"+pkg.getPrice()+"</td>\n" +
                                        "      <td><a href=\""+pkg.getId()+"\">Purchase Now</a></td>\n" +
                                        "      <td><a href=\""+pkg.getId()+"\">See Details</td>\n" +
                                        "    </tr>");
                            }
                        }
                    }
                %>
            </tbody>
        </table>
    </section>

<%@include file="partial/footer.jsp"%>
