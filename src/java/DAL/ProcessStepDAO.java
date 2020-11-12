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
import model.ProcessStep;

/**
 *
 * @author HKDUC
 */
public class ProcessStepDAO extends BaseDAO{
    public ArrayList<ProcessStep> getAll() {
        ArrayList<ProcessStep> steps = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ProcessStep";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                ProcessStep s = new ProcessStep();
                s.setProcessStepNo(rs.getInt("ProcessStepNo"));
                s.setProcessStep(rs.getString("ProcessStep"));
                steps.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProcessStepDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return steps;
    }
}
