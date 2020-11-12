/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DAL.ItemDAO;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author HKDUC
 */
public class Order {

    int OrderID;
    String Username;
    int Total_price;
    Date Created_date;
    String RecipientName;
    String RecipientPhone;
    String Address;
    int ProcessStepNo;
    ArrayList<ItemsInOrder> OrderInfo;
    ArrayList<Item> Items;

    public ArrayList<ItemsInOrder> getOrderInfo() {
        return OrderInfo;
    }

    public ArrayList<Item> getItems() {
        return Items;
    }
    
    public void setOrderInfo(ArrayList<ItemsInOrder> OrderInfo) {
        Items = new ArrayList<Item>();
        this.OrderInfo = OrderInfo;
        ItemDAO db = new ItemDAO();
        for (ItemsInOrder inf:OrderInfo){
            Items.add(new ItemDAO().getItemByID(inf.getItemID()));
        }
    }

    public Order(int OrderID, String Username, int Total_price, Date Created_date, String RecipientName, String RecipientPhone, String Address, int ProcessStepNo) {
        this.OrderID = OrderID;
        this.Username = Username;
        this.Total_price = Total_price;
        this.Created_date = Created_date;
        this.RecipientName = RecipientName;
        this.RecipientPhone = RecipientPhone;
        this.Address = Address;
        this.ProcessStepNo = ProcessStepNo;
    }

    public Order(int OrderID, String Username, String RecipientName, String RecipientPhone, String Address, int ProcessStepNo) {
        this.OrderID = OrderID;
        this.Username = Username;
        this.RecipientName = RecipientName;
        this.RecipientPhone = RecipientPhone;
        this.Address = Address;
        this.ProcessStepNo = ProcessStepNo;
    }
    
    
    public Order() {
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public int getTotal_price() {
        return Total_price;
    }

    public void setTotal_price(int Total_price) {
        this.Total_price = Total_price;
    }

    public Date getCreated_date() {
        return Created_date;
    }

    public void setCreated_date(Date Created_date) {
        this.Created_date = Created_date;
    }

    public String getRecipientName() {
        return RecipientName;
    }

    public void setRecipientName(String RecipientName) {
        this.RecipientName = RecipientName;
    }

    public String getRecipientPhone() {
        return RecipientPhone;
    }

    public void setRecipientPhone(String RecipientPhone) {
        this.RecipientPhone = RecipientPhone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getProcessStepNo() {
        return ProcessStepNo;
    }

    public void setProcessStepNo(int ProcessStep) {
        this.ProcessStepNo = ProcessStep;
    }
}
