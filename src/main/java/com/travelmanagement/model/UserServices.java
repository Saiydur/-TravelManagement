package com.travelmanagement.model;

import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UserServices {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private Statement statement = null;

    public UserServices(DataSource dataSource) throws SQLException {
        connection=dataSource.getConnection();
    }

    public int create(UserModel user) throws SQLException {
        String firstName= user.getFirstName();
        String lastName= user.getLastName();
        String email= user.getEmail();
        String phone= user.getPhoneNumber();
        String username = user.getUsername();
        String password= user.getPassword();
        String roleName= user.getRoleName();

        String sqlRoleId = "SELECT * from userrole where name = ?";
        preparedStatement = connection.prepareStatement(sqlRoleId);
        this.preparedStatement.setString(1,roleName);
        ResultSet result= this.preparedStatement.executeQuery();
        int roleId=1;
        if (result.next()){
            roleId = result.getInt(1);
        }

        String sql = "INSERT INTO USER(username,first_name,last_name,phone_number,email,password,role_id) values(?,?,?,?,?,?,?)";
        this.preparedStatement = connection.prepareStatement(sql);
        this.preparedStatement.setString(1,username);
        this.preparedStatement.setString(2,firstName);
        this.preparedStatement.setString(3,lastName);
        this.preparedStatement.setString(4,phone);
        this.preparedStatement.setString(5,email);
        this.preparedStatement.setString(6,password);
        this.preparedStatement.setInt(7,roleId);
        int response = this.preparedStatement.executeUpdate();
        System.out.println(response);
        return response;
    }

    public Map<Boolean,Integer> isValidUser(UserModel user) throws SQLException {
        String sql = "SELECT * FROM USER where (email = ? or username= ?) and password = ?";
        this.preparedStatement = connection.prepareStatement(sql);
        this.preparedStatement.setString(1,user.getEmail());
        this.preparedStatement.setString(2,user.getUsername());
        this.preparedStatement.setString(3,user.getPassword());
        ResultSet resultSet = this.preparedStatement.executeQuery();
        Map<Boolean,Integer> person = new HashMap<Boolean,Integer>();
        if (resultSet.next()){
            if((user.getEmail().equals(resultSet.getString("email"))||user.getUsername().equals(resultSet.getString("username")))&&user.getPassword().equals(resultSet.getString("password"))){
                person.put(true,resultSet.getInt("id"));
            }
        }
        this.connection.close();
        this.preparedStatement.close();
        return person;
    }

    public ResultSet getUserAllInfo(int userId) throws SQLException {
        String sql = "SELECT * FROM USER WHERE id = ?";
        this.preparedStatement=connection.prepareStatement(sql);
        this.preparedStatement.setInt(1,userId);
        ResultSet resultSet = this.preparedStatement.executeQuery();
        return resultSet;
    }

    public boolean updateUserPassword(int userId,String password) throws SQLException{
        String sql = "UPDATE user SET password = ? WHERE id = ?";
        this.preparedStatement=connection.prepareStatement(sql);
        this.preparedStatement.setString(1,password);
        this.preparedStatement.setInt(2,userId);
        int response = preparedStatement.executeUpdate();
        if (response==1){
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean updateUserInfo(int userId,UserModel userModel) throws SQLException {
        String sql = "UPDATE user SET " +
                "first_name = ? ," +
                "last_name = ? ," +
                "email = ? ," +
                "phone_number = ? " +
                "WHERE id = ?";
        this.preparedStatement=connection.prepareStatement(sql);
        this.preparedStatement.setString(1,userModel.getFirstName());
        this.preparedStatement.setString(2,userModel.getLastName());
        this.preparedStatement.setString(3,userModel.getEmail());
        this.preparedStatement.setString(4,userModel.getPhoneNumber());
        this.preparedStatement.setInt(5,userId);
        int response = preparedStatement.executeUpdate();
        if (response==1){
            return true;
        }
        else
        {
            return false;
        }
    }
}
