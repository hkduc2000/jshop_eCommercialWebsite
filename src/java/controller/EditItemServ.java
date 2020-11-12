/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.CategoryDAO;
import DAL.ConditionDAO;
import DAL.ItemDAO;
import DAL.StatusDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Condition;
import model.Item;
import model.Status;

/**
 *
 * @author HKDUC
 */
public class EditItemServ extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("variouscdt", new ConditionDAO().getAll());
        request.setAttribute("categories", new CategoryDAO().getAll());
        int ItemID = Integer.parseInt(request.getParameter("ItemID"));
        request.setAttribute("item", new ItemDAO().getItemByID(ItemID));
        request.getRequestDispatcher("edit_item_form.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int CategoryNo = Integer.parseInt(request.getParameter("CategoryNo"));
        int ItemID = Integer.parseInt(request.getParameter("ItemID"));
        Item item = new Item();
        item.setItemID(ItemID);
        item.setItemName(request.getParameter("ItemName"));
        item.setDescription(request.getParameter("Description"));
        item.setCategoryNo(CategoryNo);
        new ItemDAO().updateItem(item);
        
        //update prices
        ArrayList<Condition> cdts = new ConditionDAO().getAll();
        for (Condition cdt:cdts){
            Status status = new StatusDAO().getStatusByItemID(ItemID, cdt.getConditionNo());
            int price = Integer.parseInt(request.getParameter("price" + cdt.getConditionNo()));
            status.setPrice(price);
            new StatusDAO().updateStatus(status);
        }
        response.sendRedirect("goodsreceipt?itemid=" + ItemID);
    }
}
