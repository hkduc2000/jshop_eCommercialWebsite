/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.StatusDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Status;

/**
 *
 * @author HKDUC
 */
public class SubtracAddQuantityServ extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ItemID = Integer.parseInt(request.getParameter("ItemID"));
        int ConditionNo = Integer.parseInt(request.getParameter("ConditionNo"));
        int InputNum = Integer.parseInt(request.getParameter("InputNum"));
        int Sign = Integer.parseInt(request.getParameter("Sign"));
        new StatusDAO().subtractAndAddQuantity(ItemID, ConditionNo, Sign * InputNum);
        response.sendRedirect("goodsreceipt?itemid=" + ItemID);
    }
}
