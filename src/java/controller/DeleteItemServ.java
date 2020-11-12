/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.CustomDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HKDUC
 */
public class DeleteItemServ extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ItemID = Integer.parseInt(request.getParameter("ItemID"));
        new CustomDAO().executeSQL("DELETE FROM Status WHERE ItemID=" + ItemID);
        new CustomDAO().executeSQL("DELETE FROM ItemImages WHERE ItemID=" + ItemID);
        new CustomDAO().executeSQL("DELETE FROM ItemsInOrder WHERE ItemID=" + ItemID);
        new CustomDAO().executeSQL("DELETE FROM Items WHERE ItemID=" + ItemID);
        response.sendRedirect("list");
    }
}
