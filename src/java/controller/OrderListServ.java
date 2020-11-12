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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Order;

/**
 *
 * @author HKDUC
 */
public class OrderListServ extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        new CookieProcess().checkUserLogin(request, response);
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        request.setAttribute("orders", new OrderDAO().getAllOrders(username));
        request.setAttribute("variouscdt", new ConditionDAO().getAll());
        request.setAttribute("steps", new ProcessStepDAO().getAll());
        request.getRequestDispatcher("order_list_view.jsp").forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        new CookieProcess().checkUserLogin(request, response);
        int OrderID = Integer.parseInt(request.getParameter("orderID"));
        Order order = new OrderDAO().getOrder(OrderID);
        request.setAttribute("steps", new ProcessStepDAO().getAll());
        request.setAttribute("order", order);
        request.setAttribute("variouscdt", new ConditionDAO().getAll());
        request.getRequestDispatcher("order_detail_view.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
