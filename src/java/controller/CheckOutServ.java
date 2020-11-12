/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.ItemsInOrderDAO;
import DAL.OrderDAO;
import DAL.StatusDAO;
import DAL.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Item;
import model.ItemsInOrder;
import model.Order;
import model.Status;
import model.User;

/**
 *
 * @author HKDUC
 */
public class CheckOutServ extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        new CookieProcess().checkUserLogin(request, response);
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        User user = new UserDAO().getUser(username);
        request.setAttribute("user", user);
        request.getRequestDispatcher("checkout.jsp").forward(request,response);
    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        new CookieProcess().checkUserLogin(request, response);
        HttpSession session = request.getSession();
        Order cart = new OrderDAO().getCart((String) session.getAttribute("username"));
        //Update price to itemsInOrder
        ArrayList<ItemsInOrder> cartInfo = cart.getOrderInfo();
        ArrayList<Item> items = cart.getItems();
        ItemsInOrderDAO db = new ItemsInOrderDAO();
        int sum = 0;
        for (int i=0;i<items.size();i++){
            ArrayList<Status> statusList = items.get(i).getStatusList();
            ItemsInOrder inf = cartInfo.get(i);
            Status status = statusList.get(inf.getConditionNo()-1);
            inf.setPrice(status.getPrice());
            db.updateItemsInCart(inf);
            sum += (inf.getPrice() * inf.getQuantity());
            status.setQuantity(status.getQuantity()-inf.getQuantity());
            new StatusDAO().updateStatus(status);
        }
        //set Attribute for order
        cart.setTotal_price(sum);
        cart.setProcessStepNo(2);
        cart.setCreated_date(new java.util.Date());
        cart.setRecipientName(request.getParameter("receipientName"));
        cart.setAddress(request.getParameter("receipientAddress"));
        cart.setRecipientPhone(request.getParameter("receipientPhone"));
        new OrderDAO().updateOrder(cart);
        response.sendRedirect("list");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
