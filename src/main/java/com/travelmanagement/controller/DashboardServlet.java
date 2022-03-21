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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "dashboard",urlPatterns = "/dashboard")
public class DashboardServlet extends HttpServlet {
    @Resource(name = "jdbc/travelManagement")
    private DataSource dataSource;
    @Override
    public void init() throws ServletException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        fetchUserData(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        int userId=(int)session.getAttribute("userid");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        UserModel userModel = new UserModel();
        userModel.setEmail(email);
        userModel.setLastName(lastName);
        userModel.setFirstName(firstName);
        userModel.setPhoneNumber(phoneNumber);

        try {
            UserServices userServices = new UserServices(dataSource);
            boolean isUpdate=userServices.updateUserInfo(userId,userModel);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/dashboard.jsp");
            if (isUpdate){
                request.setAttribute("isDone",true);
            }
            else {
                request.setAttribute("isDone",false);
            }
            fetchUserData(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchUserData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int userId=(int)session.getAttribute("userid");
        List<UserModel> users = new ArrayList<>();
        try {
            UserServices userServices = new UserServices(dataSource);
            ResultSet resultSet=userServices.getUserAllInfo(userId);
            while (resultSet.next()){
                UserModel userModel = new UserModel();
                userModel.setFirstName(resultSet.getString("first_name"));
                userModel.setLastName(resultSet.getString("last_name"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setUsername(resultSet.getString("username"));
                userModel.setPhoneNumber(resultSet.getString("phone_number"));
                userModel.setPassword(resultSet.getString("password"));
                session.setAttribute("userRole",resultSet.getInt("role_id"));
                users.add(userModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("users",users);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/dashboard.jsp");
        requestDispatcher.forward(request,response);
    }

    public void destroy(){

    }
}
