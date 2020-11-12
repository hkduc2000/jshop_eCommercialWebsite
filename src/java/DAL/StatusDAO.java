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
import model.Condition;
import model.Status;

/**
 *
 * @author HKDUC
 */
public class StatusDAO extends BaseDAO {

    public ArrayList<Status> getStatusByItemID(int ItemID) {
        ArrayList<Status> statusList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Status WHERE ItemID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ItemID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Status s = new Status();
                s.setItemID(rs.getInt("ItemID"));
                s.setConditionNo(rs.getInt("ConditionNo"));
                s.setPrice(rs.getInt("Price"));
                s.setQuantity(rs.getInt("Quantity"));
                statusList.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statusList;
    }
    
    public Status getStatusByItemID(int ItemID, int ConditionNo) {
        try {
            String sql = "SELECT * FROM Status WHERE ItemID=? AND ConditionNo=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ItemID);
            statement.setInt(2, ConditionNo);
            ResultSet rs = statement.executeQuery();
            rs.next();
            Status s = new Status();
            s.setItemID(rs.getInt("ItemID"));
            s.setConditionNo(rs.getInt("ConditionNo"));
            s.setPrice(rs.getInt("Price"));
            s.setQuantity(rs.getInt("Quantity"));
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addStatus(Status status) {
        try {
            String sql = "INSERT INTO Status VALUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, status.getItemID());
            statement.setInt(2, status.getConditionNo());
            statement.setInt(3, status.getPrice());
            statement.setInt(4, status.getQuantity());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateStatus(Status status) {
        try {
            String sql = "UPDATE Status SET Quantity=?, Price=? WHERE ItemID=? AND ConditionNo=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, status.getQuantity());
            statement.setInt(2, status.getPrice());
            statement.setInt(3, status.getItemID());
            statement.setInt(4, status.getConditionNo());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void subtractAndAddQuantity(int ItemID, int ConditionNo,  int InputNum) {
        StatusDAO db = new StatusDAO();
        Status status = db.getStatusByItemID(ItemID, ConditionNo);
        status.setQuantity(status.getQuantity() + InputNum);
        db.updateStatus(status);
    }
}
