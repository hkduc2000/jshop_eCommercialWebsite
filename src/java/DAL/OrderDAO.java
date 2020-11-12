/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;

/**
 *
 * @author HKDUC
 */
public class OrderDAO extends BaseDAO {

    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Orders WHERE ProcessStepNo>=2 ORDER BY OrderID DESC";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order s = new Order();
                s.setOrderID(rs.getInt("OrderID"));
                s.setUsername(rs.getString("Username"));
                s.setTotal_price(rs.getInt("Total_price"));
                s.setCreated_date(rs.getDate("CreatedDate"));
                s.setRecipientName(rs.getString("RecipientName"));
                s.setRecipientPhone(rs.getString("RecipientPhone"));
                s.setAddress(rs.getString("Address"));
                s.setProcessStepNo(rs.getInt("ProcessStepNo"));
                s.setOrderInfo(new ItemsInOrderDAO().getOrderInfo(s.getOrderID()));
                orders.add(s);
            }
            return orders;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<Order> getAllOrders(String username) {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Orders WHERE Username=? AND ProcessStepNo>=2 ORDER BY OrderID DESC";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order s = new Order();
                s.setOrderID(rs.getInt("OrderID"));
                s.setUsername(rs.getString("Username"));
                s.setTotal_price(rs.getInt("Total_price"));
                s.setCreated_date(rs.getDate("CreatedDate"));
                s.setRecipientName(rs.getString("RecipientName"));
                s.setRecipientPhone(rs.getString("RecipientPhone"));
                s.setAddress(rs.getString("Address"));
                s.setProcessStepNo(rs.getInt("ProcessStepNo"));
                s.setOrderInfo(new ItemsInOrderDAO().getOrderInfo(s.getOrderID()));
                orders.add(s);
            }
            return orders;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void createEmptyCart(String username) {
        try {
            String sql = "INSERT INTO Orders VALUES(?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setInt(2, 0);
            statement.setDate(3, null);
            statement.setString(4, null);
            statement.setString(5, null);
            statement.setString(6, null);
            statement.setInt(7, 1);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Order getOrder(int OrderID) {
        try {
            String sql = "SELECT * FROM Orders WHERE OrderID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, OrderID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Order s = new Order();
                s.setOrderID(rs.getInt("OrderID"));
                s.setUsername(rs.getString("Username"));
                s.setTotal_price(rs.getInt("Total_price"));
                s.setCreated_date(rs.getDate("CreatedDate"));
                s.setRecipientName(rs.getString("RecipientName"));
                s.setRecipientPhone(rs.getString("RecipientPhone"));
                s.setAddress(rs.getString("Address"));
                s.setProcessStepNo(rs.getInt("ProcessStepNo"));
                s.setOrderInfo(new ItemsInOrderDAO().getOrderInfo(s.getOrderID()));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Order getCart(String username) {
        try {
            String sql = "SELECT * FROM Orders WHERE Username=? AND ProcessStepNo=1";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            Order s = new Order();
            if (rs.next()) {
                s.setOrderID(rs.getInt("OrderID"));
                s.setUsername(rs.getString("Username"));
                s.setTotal_price(rs.getInt("OrderID"));
                s.setCreated_date(rs.getDate("CreatedDate"));
                s.setRecipientName(rs.getString("RecipientName"));
                s.setRecipientPhone(rs.getString("RecipientPhone"));
                s.setAddress(rs.getString("Address"));
                s.setProcessStepNo(rs.getInt("ProcessStepNo"));
                s.setOrderInfo(new ItemsInOrderDAO().getOrderInfo(s.getOrderID()));
            } else {
                createEmptyCart(username);
                return getCart(username);
            }
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateOrder(Order order) {
        try {
            String sql = "UPDATE Orders "
                    + "SET Total_price =?, CreatedDate=?, RecipientName=?, "
                    + "RecipientPhone=?, Address=?, ProcessStepNo=? WHERE OrderID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getTotal_price());
            statement.setDate(2, new java.sql.Date(order.getCreated_date().getTime()));
            statement.setString(3, order.getRecipientName());
            statement.setString(4, order.getRecipientPhone());
            statement.setString(5, order.getAddress());
            statement.setInt(6, order.getProcessStepNo());
            statement.setInt(7, order.getOrderID());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
