<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Travel Management</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="${pageContext.request.contextPath}" class="nav-link px-2 text-secondary">Home</a></li>
                <li><a href="aboutus" class="nav-link px-2 text-secondary">About Us</a></li>
                <%
                    if(request.getSession().getAttribute("userRole")!=null){
                        if ((int)request.getSession().getAttribute("userRole")==3){
                            out.println("<li><a href=\"packages\" class=\"nav-link px-2 text-secondary\">See Packages</a></li>");
                            out.println("<li><a href=\"postBlog\" class=\"nav-link px-2 text-secondary\">Post Blog</a></li>");
                            out.println("<li><a href=\"dashboard\" class=\"nav-link px-2 text-secondary\">View Profile</a></li>");
                        }
                        else if ((int)request.getSession().getAttribute("userRole")==2){
                            out.println("<li><a href=\"addpackages\" class=\"nav-link px-2 text-secondary\">Add Packages</a></li>");
                            out.println("<li><a href=\"reports\" class=\"nav-link px-2 text-secondary\">Reports</a></li>");
                            out.println("<li><a href=\"postBlog\" class=\"nav-link px-2 text-secondary\">Post Blog</a></li>");
                            out.println("<li><a href=\"dashboard\" class=\"nav-link px-2 text-secondary\">View Profile</a></li>");
                        }
                        else if ((int)request.getSession().getAttribute("userRole")==1){
                            out.println("<li><a href=\"adminPanel\" class=\"nav-link px-2 text-secondary\">Manage User</a></li>");
                            out.println("<li><a href=\"postBlog\" class=\"nav-link px-2 text-secondary\">Post Blog</a></li>");
                            out.println("<li><a href=\"dashboard\" class=\"nav-link px-2 text-secondary\">View Profile</a></li>");
                        }
                    }
                %>
            </ul>

            <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3">
                <input type="search" class="form-control form-control-dark" placeholder="Search..." aria-label="Search">
            </form>
            <%
                if (request.getSession().getAttribute("userid")==null){
            %>
            <div class="text-end">
                <a href="login" type="button" class="btn btn-outline-light me-2">Login</a>
                <a href="registration" type="button" class="btn btn-warning">Sign-up</a>
            </div>
            <%
                }else
                {
            %>
            <div class="text-end">
                <a href="logout" type="button" class="btn btn-outline-light me-2">Logout</a>
            </div>
            <%
                }
            %>
        </div>
    </div>
</header>