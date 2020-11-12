/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.ItemsInOrderDAO;
import DAL.OrderDAO;
import DAL.StatusDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Item;
import model.ItemsInOrder;
import model.Order;
import model.Status;

/**
 *
 * @author HKDUC
 */
public class OrderProcessServ extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //new CookieProcess().checkUserLogin(request, response);
        int OrderID = Integer.parseInt(request.getParameter("OrderID"));
        int step = Integer.parseInt(request.getParameter("Step"));
        Order order = new OrderDAO().getOrder(OrderID);
        order.setProcessStepNo(step);
        new OrderDAO().updateOrder(order);
        
        ArrayList<ItemsInOrder> orderInfo = order.getOrderInfo();
        ArrayList<Item> items = order.getItems();
        ItemsInOrderDAO db = new ItemsInOrderDAO();
        for (int i=0;i<items.size();i++){
            ArrayList<Status> statusList = items.get(i).getStatusList();
            ItemsInOrder inf = orderInfo.get(i);
            Status status = statusList.get(inf.getConditionNo()-1);
            status.setQuantity(status.getQuantity()+inf.getQuantity());
            new StatusDAO().updateStatus(status);
        }
        if (request.getSession().getAttribute("role").equals("user")) {
            response.sendRedirect("order_list");
        } else {
            response.sendRedirect("order_manage");
        }

    }
}
