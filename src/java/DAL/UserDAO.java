/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author HKDUC
 */
public class UserDAO extends BaseDAO {

    public ArrayList<User> getAll() {
        ArrayList<User> items = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Users";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User s = new User();
                s.setUsername(rs.getString("Username"));
                s.setPassword(rs.getString("Password"));
                s.setName(rs.getString("Name"));
                s.setEmail(rs.getString("Email"));
                s.setPhone(rs.getString("Phone"));
                s.setAddress(rs.getString("Address"));
                s.setAdmin(rs.getBoolean("isAdmin"));
                items.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }

    public User getUser(String username, String password) {
        try {
            String sql = "SELECT * FROM Users WHERE Username=? AND Password=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User s = new User();
                s.setUsername(rs.getString("Username"));
                s.setPassword(rs.getString("Password"));
                s.setName(rs.getString("Name"));
                s.setEmail(rs.getString("Email"));
                s.setAddress(rs.getString("Address"));
                s.setPhone(rs.getString("Phone"));
                s.setAdmin(rs.getBoolean("isAdmin"));
                return s;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public User getUser(String username) {
        try {
            String sql = "SELECT * FROM Users WHERE Username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User s = new User();
                s.setUsername(rs.getString("Username"));
                s.setPassword(rs.getString("Password"));
                s.setName(rs.getString("Name"));
                s.setEmail(rs.getString("Email"));
                s.setAddress(rs.getString("Address"));
                s.setPhone(rs.getString("Phone"));
                s.setAdmin(rs.getBoolean("isAdmin"));
                return s;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void updateUser(User user){
        try {
            String sql = "UPDATE Users SET Password=?, Name=?, Email=?, Phone=?, Address=?, isAdmin=? WHERE Username=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getAddress());
            statement.setBoolean(6, user.isAdmin());
            statement.setString(7, user.getUsername());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String addUser(User user) {
        try {
            String sql = "INSERT INTO Users VALUES(?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getAddress());
            statement.setBoolean(7, user.isAdmin());
            statement.executeUpdate();
        } catch (SQLException ex) {
            return "Username đã tồn tại";
        }
        return "Đã tạo tài khoản thành công";
    }
}
