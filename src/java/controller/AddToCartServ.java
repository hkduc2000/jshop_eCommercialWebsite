/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.ItemsInOrderDAO;
import DAL.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.smartcardio.Card;
import model.ItemsInOrder;
import model.Order;

/**
 *
 * @author HKDUC
 */
public class AddToCartServ extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.getWriter().print("Access denied");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    
        HttpSession session = request.getSession();
        if (((String) session.getAttribute("username")) == null) {
            response.getWriter().print("Access denied");
            return;
        }
        int ItemID = Integer.parseInt(request.getParameter("itemID"));
        int Quantity = Integer.parseInt(request.getParameter("buyQuantity"));
        int ConditionNo = Integer.parseInt(request.getParameter("conditionNo"));
        String username = (String)session.getAttribute("username");
        Order cart = new OrderDAO().getCart(username);
        int cartID = cart.getOrderID();
        ItemsInOrder inf = new ItemsInOrder(cartID, ItemID, ConditionNo, Quantity);
        new ItemsInOrderDAO().addItemsToCart(inf);
        request.getRequestDispatcher("add_to_cart_result.jsp").forward(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
