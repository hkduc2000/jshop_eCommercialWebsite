/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.ItemsInOrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ItemsInOrder;

/**
 *
 * @author HKDUC
 */
@WebServlet(name = "DeleteItemsInCartServ", urlPatterns = {"/delete_items_in_cart"})
public class DeleteItemsInCartServ extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DeleteItemsInCartServ</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteItemsInCartServ at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (((String) session.getAttribute("username")) == null) {
            response.getWriter().print("Access denied");
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (((String) session.getAttribute("username")) == null) {
            response.getWriter().print("Access denied");
            return;
        }
        ItemsInOrder s = new ItemsInOrder();
        s.setOrderID(Integer.parseInt(request.getParameter("OrderID")));
        s.setItemID(Integer.parseInt(request.getParameter("ItemID")));
        s.setConditionNo(Integer.parseInt(request.getParameter("ConditionNo")));
        new ItemsInOrderDAO().deleteItemsInOrder(s);
        response.sendRedirect("cart");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
