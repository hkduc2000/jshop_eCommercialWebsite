/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.ConditionDAO;
import DAL.OrderDAO;
import DAL.ProcessStepDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Order;

/**
 *
 * @author HKDUC
 */
public class OrderManageServ extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("steps", new ProcessStepDAO().getAll());
        request.setAttribute("orders", new OrderDAO().getAllOrders());
        request.setAttribute("variouscdt", new ConditionDAO().getAll());
        request.getRequestDispatcher("order_manage_list_view.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int OrderID = Integer.parseInt(request.getParameter("orderID"));
        Order order = new OrderDAO().getOrder(OrderID);
        request.setAttribute("steps", new ProcessStepDAO().getAll());
        request.setAttribute("order", order);
        request.setAttribute("variouscdt", new ConditionDAO().getAll());
        request.getRequestDispatcher("order_manage_detail_view.jsp").forward(request, response);
    }
}
