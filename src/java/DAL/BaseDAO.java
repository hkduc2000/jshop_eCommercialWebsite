/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HKDUC
 */
public class BaseDAO {
    protected Connection connection;
    public BaseDAO(){
        try {
            String user = "admin";
            String pass = "20001998";
            String url = "jdbc:sqlserver://jshop.crnsmb2nnxcf.us-east-2.rds.amazonaws.com:1433;databaseName=jshop";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BaseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
