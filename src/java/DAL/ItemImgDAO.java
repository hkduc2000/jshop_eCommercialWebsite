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
import model.ItemImg;

/**
 *
 * @author HKDUC
 */
public class ItemImgDAO extends BaseDAO {
    public ItemImgDAO() {
    }

    public ArrayList<String> getImgPathsByItemID(int ItemID) {
        ArrayList<String> imgPaths = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ItemImages WHERE ItemID=? AND isThumbnail=0";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ItemID);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                imgPaths.add(rs.getString("ImagePath"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imgPaths;
    }

    public String getThumbnailPathByItemID(int ItemID) {
        ArrayList<ItemImg> items = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ItemImages WHERE ItemID=? AND isThumbnail=1";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ItemID);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getString("ImagePath");
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addItemImage(ItemImg img) {
        try {
            String sql = "INSERT INTO ItemImages VALUES(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, img.getItemID());
            statement.setString(2, img.getImagePath());
            statement.setBoolean(3, img.isThumbnail());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
