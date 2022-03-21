package com.travelmanagement.model;

import org.omg.CosNaming.NamingContextPackage.NotFound;

import javax.sql.DataSource;
import java.sql.*;

public class PackageServices {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;

    public PackageServices(DataSource dataSource) throws SQLException {
        connection=dataSource.getConnection();
    }

    public boolean createPackage(PackageModel packageModel) throws SQLException {
        String sql = "INSERT INTO travelpackage(name,duration,description,price,discount,user_id) " +
                "VALUES(?,?,?,?,?,?)";
        this.preparedStatement=connection.prepareStatement(sql);
        this.preparedStatement.setString(1,packageModel.getName());
        this.preparedStatement.setString(2,packageModel.getDuration());
        this.preparedStatement.setString(3,packageModel.getDescription());
        this.preparedStatement.setDouble(4,packageModel.getPrice());
        this.preparedStatement.setDouble(5,packageModel.getDiscount());
        this.preparedStatement.setInt(6,packageModel.getUserId());
        int isInsert = this.preparedStatement.executeUpdate();
        if (isInsert==1){
            return true;
        }
        else {
            return false;
        }
    }

    public ResultSet getAllProductsByUserId(int userId) throws SQLException {
        String sql = "SELECT * FROM travelpackage WHERE user_id = ?";
        this.preparedStatement=connection.prepareStatement(sql);
        this.preparedStatement.setInt(1,userId);
        ResultSet resultSet = this.preparedStatement.executeQuery();
        ResultSet products = null;
        if (resultSet.next()){
            products=resultSet;
        }
        return products;
    }

    public ResultSet getAllProducts() throws SQLException{
        String sql = "SELECT * FROM travelpackage";
        statement = connection.createStatement();
        ResultSet allProduct = statement.executeQuery(sql);
        return allProduct;
    }
}
