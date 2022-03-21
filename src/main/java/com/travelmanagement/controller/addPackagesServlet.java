package com.travelmanagement.controller;

import com.travelmanagement.model.PackageModel;
import com.travelmanagement.model.PackageServices;

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

@WebServlet(name = "addpackages",urlPatterns = "/addpackages")
public class addPackagesServlet extends HttpServlet {
    @Resource(name = "jdbc/travelManagement")
    DataSource dataSource;
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("userid")!=null){
            int userId = (int) session.getAttribute("userid");
            try {
                PackageServices packageServices = new PackageServices(dataSource);
                ResultSet products=packageServices.getAllProductsByUserId(userId);
                List<PackageModel> productList = new ArrayList<>();
                if (products!=null){
                    while (products.next()){
                        PackageModel packageModel = new PackageModel(
                                products.getString("name"),products.getString("duration"),
                                products.getString("description"),products.getDouble("price"),
                                products.getDouble("discount"), products.getInt("user_id")
                        );
                        packageModel.setId(products.getInt("id"));
                        productList.add(packageModel);
                    }
                    req.setAttribute("packages",productList);
                }
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addpackages.jsp");
                requestDispatcher.forward(req,resp);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            resp.sendRedirect("login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String duration = req.getParameter("duration");
        String description = req.getParameter("description");
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userid");
        double price = Double.parseDouble(req.getParameter("price"));
        double discount = Double.parseDouble(req.getParameter("discount"));
        PackageModel packageModel = new PackageModel(name,duration,description,price,discount,userId);
        try {
            PackageServices packageServices = new PackageServices(dataSource);
            boolean isDone=packageServices.createPackage(packageModel);
            if (isDone){
                req.setAttribute("isDone","1");
            }
            else {
                req.setAttribute("isDone","0");
            }
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addpackages.jsp");
            requestDispatcher.forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
