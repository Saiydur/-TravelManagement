package com.travelmanagement.controller;

import com.travelmanagement.model.UserModel;
import com.travelmanagement.model.UserServices;

import java.io.*;
import java.sql.*;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {

    @Resource(name = "jdbc/travelManagement")
    private DataSource dataSource;

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request,response);
    }


    public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        UserModel userModel = new UserModel(request.getParameter("emailorusername"),request.getParameter("emailorusername"),request.getParameter("password"));
        try {
            UserServices userServices = new UserServices(dataSource);
            Map<Boolean,Integer> person =userServices.isValidUser(userModel);
            if (!person.isEmpty()){
                for (Integer key : person.values()){
                    HttpSession session = request.getSession();
                    session.setAttribute("userid",key);
                    response.sendRedirect("dashboard");
                }
            }
            else {
                request.setAttribute("login","Check username or password");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.jsp");
                requestDispatcher.forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
    }

}