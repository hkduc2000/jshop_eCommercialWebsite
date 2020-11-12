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
import model.ItemsInOrder;

/**
 *
 * @author HKDUC
 */
public class ItemsInOrderDAO extends BaseDAO {
    
    public ArrayList<ItemsInOrder> getOrderInfo(int OrderID){
        ArrayList<ItemsInOrder> orderInfo = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ItemsInOrder WHERE OrderID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, OrderID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                ItemsInOrder s = new ItemsInOrder();
                s.setOrderID(rs.getInt("OrderID"));
                s.setItemID(rs.getInt("ItemID"));
                s.setConditionNo(rs.getInt("ConditionNo"));
                s.setPrice(rs.getInt("Price"));
                s.setQuantity(rs.getInt("Quantity"));
                orderInfo.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderInfo;
    }

    public void addItemsToCart(ItemsInOrder inf) {
        try {
            String sql = "INSERT INTO ItemsInOrder VALUES(?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, inf.getOrderID());
            statement.setInt(2, inf.getItemID());
            statement.setInt(3, inf.getConditionNo());
            statement.setInt(4, 0);
            statement.setInt(5, inf.getQuantity());
            statement.executeUpdate();
        } catch (SQLException ex) {
            try {
                addDuplicateItemsInCart(inf);
            } catch (Exception ex1){
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void addDuplicateItemsInCart(ItemsInOrder inf) {
        try {
            String sql = "UPDATE ItemsInOrder SET Quantity=Quantity+" + inf.getQuantity()
                    + " WHERE OrderID=? AND ItemID=? AND ConditionNo=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, inf.getOrderID());
            statement.setInt(2, inf.getItemID());
            statement.setInt(3, inf.getConditionNo());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateItemsInCart(ItemsInOrder inf){
        try {
            String sql = "UPDATE ItemsInOrder SET Quantity=?, Price=? "
                    + "WHERE OrderID=? AND ItemID=? AND ConditionNo=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, inf.getQuantity());
            statement.setInt(2, inf.getPrice());
            statement.setInt(3, inf.getOrderID());
            statement.setInt(4, inf.getItemID());
            statement.setInt(5, inf.getConditionNo());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteItemsInOrder(ItemsInOrder inf){
        try {
            String sql = "DELETE FROM ItemsInOrder WHERE OrderID=? AND ItemID=? AND ConditionNo=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, inf.getOrderID());
            statement.setInt(2, inf.getItemID());
            statement.setInt(3, inf.getConditionNo());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
