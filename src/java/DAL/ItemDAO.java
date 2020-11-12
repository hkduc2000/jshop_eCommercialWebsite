/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import com.sun.imageio.plugins.common.I18N;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Item;
import model.ItemImg;

/**
 *
 * @author HKDUC
 */
public class ItemDAO extends BaseDAO {
    
    public ArrayList<Item> getAll() {
        ArrayList<Item> items = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Items";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Item s = new Item();
                s.setItemID(rs.getInt("ItemID"));
                s.setItemName(rs.getString("ItemName"));
                s.setDescription(rs.getString("Description"));
                s.setCategoryNo(rs.getInt("CategoryNo"));
                s.setImgPaths(new ItemImgDAO().getImgPathsByItemID(s.getItemID()));
                s.setThumbnailPath(new ItemImgDAO().getThumbnailPathByItemID(s.getItemID()));
                s.setStatusList(new StatusDAO().getStatusByItemID(s.getItemID()));
                items.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return items;
    }

    public void addItem(Item item) {
        try {
            String sql = "INSERT INTO Items VALUES(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, item.getItemName());
            statement.setString(2, item.getDescription());
            statement.setInt(3, item.getCategoryNo());
            statement.executeUpdate();
        } catch (SQLException ex) {
            //Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getIdentCur() {
        try {
            String sql = "SELECT IDENT_CURRENT('Items')";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt("");
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Item getItemByID(int ItemID) {
        try {
            String sql = "SELECT * FROM Items WHERE ItemID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ItemID);
            ResultSet rs = statement.executeQuery();
            rs.next();
            Item s = new Item();
            s.setItemID(rs.getInt("ItemID"));
            s.setItemName(rs.getString("ItemName"));
            s.setDescription(rs.getString("Description"));
            s.setCategoryNo(rs.getInt("CategoryNo"));
            s.setImgPaths(new ItemImgDAO().getImgPathsByItemID(s.getItemID()));
            s.setThumbnailPath(new ItemImgDAO().getThumbnailPathByItemID(s.getItemID()));
            s.setStatusList(new StatusDAO().getStatusByItemID(s.getItemID()));
            return s;
        } catch (SQLException ex) {
            //Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void updateItem(Item item){
        try {
            String sql = "UPDATE Items SET ItemName=?, Description=?, CategoryNo=? WHERE ItemID=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, item.getItemName());
            statement.setString(2, item.getDescription());
            statement.setInt(3, item.getCategoryNo());
            statement.setInt(4, item.getItemID());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
