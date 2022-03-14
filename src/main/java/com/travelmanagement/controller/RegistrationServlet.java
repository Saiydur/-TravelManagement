package com.travelmanagement.controller;

import com.travelmanagement.model.UserModel;
import com.travelmanagement.model.UserServices;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "registration", value = "/registration")

public class RegistrationServlet extends HttpServlet {
    @Resource(name="jdbc/travelManagement")
    private DataSource dataSource;
    public void init() {
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/registration.jsp");
        requestDispatcher.forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String email=request.getParameter("email");
        String phone=request.getParameter("phone");
        String username = request.getParameter("username");
        String password=request.getParameter("password");
        String role=request.getParameter("role");

        UserModel userModel = new UserModel(username,firstName,lastName,phone,email,role,password);
        UserServices userServices = null;
        try {
            userServices = new UserServices(dataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int regDone = 0;
        try {
            regDone=userServices.create(userModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        HttpSession session = request.getSession();
        if (regDone==1) {
            session.setAttribute("success", "Registration Successful");
        }
        else
        {
            session.setAttribute("success", "Registration Failed");
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/registration.jsp");
        requestDispatcher.forward(request,response);

    }

    public void destroy() {
    }
}
