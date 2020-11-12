/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author HKDUC
 */
public class RegisterServ extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        User user = new User(username, password,name, email, phone, address, false);
        String msg = new UserDAO().addUser(user);
        if (msg.equals("Đã tạo tài khoản thành công")) {
            response = CookieProcess.addCookieToResponse(response, "username", username, 3600 * 24 * 15);
            response = CookieProcess.addCookieToResponse(response, "password", password, 3600 * 24 * 15);
            response = CookieProcess.addCookieToResponse(response, "role","user", 3600 * 24 * 15);
        }
        request.setAttribute("msg", msg);
        request.setAttribute("type", "Register");
        request.getRequestDispatcher("login_register_result.jsp").include(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
