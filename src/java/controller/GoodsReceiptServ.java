/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.CategoryDAO;
import DAL.ConditionDAO;
import DAL.ItemDAO;
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
public class GoodsReceiptServ extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ItemID = Integer.parseInt(request.getParameter("itemid"));
        request.setAttribute("item", new ItemDAO().getItemByID(ItemID));
        request.setAttribute("variouscdt", new ConditionDAO().getAll());
        request.getRequestDispatcher("goods_receipt_form.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
