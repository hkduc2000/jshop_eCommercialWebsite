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
import model.Category;
import model.Item;

/**
 *
 * @author HKDUC
 */
public class CategoryDAO extends BaseDAO{
    public ArrayList<Category> getAll() {
        ArrayList<Category> categories = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Categories";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                Category s = new Category();
                s.setCategoryNo(rs.getInt("CategoryNo"));
                s.setCategory(rs.getString("Category"));
                categories.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }
}
