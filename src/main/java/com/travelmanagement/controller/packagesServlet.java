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
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "packages",urlPatterns = "/packages")
public class packagesServlet extends HttpServlet {
    @Resource(name = "jdbc/travelManagement")
    DataSource dataSource;
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PackageServices packageServices = new PackageServices(dataSource);
            ResultSet resultSet=packageServices.getAllProducts();
            List<PackageModel> packageModelList = new ArrayList<>();
            if (resultSet!=null){
                while (resultSet.next()){
                    PackageModel packageModel = new PackageModel(
                            resultSet.getString("name"),resultSet.getString("duration"),
                            resultSet.getString("description"),resultSet.getDouble("price"),
                            resultSet.getDouble("discount"), resultSet.getInt("user_id")
                    );
                    packageModel.setId(resultSet.getInt("id"));
                    packageModelList.add(packageModel);
                }
            }
            req.setAttribute("allPackage",packageModelList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/packages.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    public void destroy() {

    }
}
