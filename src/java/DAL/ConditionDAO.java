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

/**
 *
 * @author HKDUC
 */
public class ConditionDAO extends BaseDAO{
    public ArrayList<Condition> getAll() {
        ArrayList<Condition> categories = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Condition";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                Condition s = new Condition();
                s.setConditionNo(rs.getInt("ConditionNo"));
                s.setCondition(rs.getString("Condition"));
                categories.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConditionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

}
