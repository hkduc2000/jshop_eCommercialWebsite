/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAL.CategoryDAO;
import DAL.ConditionDAO;
import DAL.ItemDAO;
import DAL.ItemImgDAO;
import DAL.StatusDAO;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Condition;
import model.Item;
import model.ItemImg;
import model.Status;

/**
 *
 * @author HKDUC
 */
@MultipartConfig
public class addItemServ extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("variouscdt", new ConditionDAO().getAll());
        request.setAttribute("categories", new CategoryDAO().getAll());
        request.getRequestDispatcher("add_items_form.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Save item 
        request.setCharacterEncoding("UTF-8");
        String ItemName = request.getParameter("ItemName");
        String Description = request.getParameter("Description");
        int CategoryNo = Integer.parseInt(request.getParameter("CategoryNo"));
        Item item = new Item(ItemName, Description, CategoryNo);
        ItemDAO DB = new ItemDAO();
        DB.addItem(item);
        //Download thumbnail
        String rootPath = getServletContext().getRealPath("/");
        Part thumbnailPart = request.getPart("ItemThubnail");
        String imgPath = "static/img/" + DB.getIdentCur() + "_thumbnail.jpg";
        InputStream is = thumbnailPart.getInputStream();
        Files.copy(is, Paths.get(rootPath + imgPath),
                StandardCopyOption.REPLACE_EXISTING);
        ItemImg img = new ItemImg(DB.getIdentCur(), imgPath, true);
        ItemImgDAO imgDB = new ItemImgDAO();
        imgDB.addItemImage(img);
        //Download images
        ArrayList<Part> fileParts = (ArrayList<Part>) request.getParts();
        int cnt = 1;
        for (Part imgPart : fileParts) {
            if (imgPart.getName().equals("ItemImage")) {
                imgPath = "static/img/" + DB.getIdentCur() + "_" + cnt + ".jpg";
                cnt++;
                is = imgPart.getInputStream();
                Files.copy(is, Paths.get(rootPath + imgPath),
                        StandardCopyOption.REPLACE_EXISTING);
                img = new ItemImg(DB.getIdentCur(), imgPath, false);
                imgDB.addItemImage(img);
            }
        }
        //add status
        ArrayList<Condition> cdts = new ConditionDAO().getAll();
        for (Condition cdt:cdts){
            Status status = new Status();
            status.setItemID(DB.getIdentCur());
            status.setConditionNo(cdt.getConditionNo());
            int price = Integer.parseInt(request.getParameter("price" + cdt.getConditionNo()));
            status.setPrice(price);
            status.setQuantity(0);
            new StatusDAO().addStatus(status);
        }
        response.sendRedirect("list");
    }
}
