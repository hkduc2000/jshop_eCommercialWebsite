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
public class EditProfileServ extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        new CookieProcess().checkUserLogin(request, response);
        request.getRequestDispatcher("edit_user_profile_form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        new CookieProcess().checkUserLogin(request, response);
        String Name = request.getParameter("name");
        String Phone = request.getParameter("phone");
        String Address = request.getParameter("address");
        String Password = request.getParameter("password");
        User user = (User) request.getSession().getAttribute("user");
        user.setName(Name);
        user.setPhone(Phone);
        user.setAddress(Address);
        user.setPassword(Password);
        new UserDAO().updateUser(user);
        new CookieProcess().checkUserLogin(request, response);
        response.sendRedirect(request.getContextPath());
    }
}
