package com.travelmanagement.controller;

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

@WebServlet(name = "updatePassword",urlPatterns = "/updatePassword")
public class updatePasswordServlet extends HttpServlet {
    @Resource(name = "jdbc/travelManagement")
    DataSource dataSource;
    @Override
    public void init() throws ServletException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("updatePassword.jsp");
        requestDispatcher.forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String mainPassword = (String) session.getAttribute("password");
        int userid = (int) session.getAttribute("userid");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/updatePassword.jsp");
        if (oldPassword.equals(mainPassword)){
            try {
                UserServices userServices = new UserServices(dataSource);
                boolean isUpdatePassword=userServices.updateUserPassword(userid,newPassword);
                if (isUpdatePassword){
                    request.setAttribute("update","Password Updated Successfully");
                }
                else
                {
                    request.setAttribute("update","Password Not Updated");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            request.setAttribute("update","Check Your Old Password");
        }
        requestDispatcher.forward(request,response);

    }

    @Override
    public void destroy() {

    }
}
