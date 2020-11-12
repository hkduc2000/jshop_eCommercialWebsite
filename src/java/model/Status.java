/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author HKDUC
 */
public class Status {
    int ItemID;
    int ConditionNo;
    int Price;
    int Quantity;

    public Status(int ItemID, int ConditionNo, int Quantity) {
        this.ItemID = ItemID;
        this.ConditionNo = ConditionNo;
        this.Quantity = Quantity;
    }

    public Status(int ItemID, int ConditionNo, int Price, int Quantity) {
        this.ItemID = ItemID;
        this.ConditionNo = ConditionNo;
        this.Price = Price;
        this.Quantity = Quantity;
    }

    public Status() {
    }
    
    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int ItemID) {
        this.ItemID = ItemID;
    }

    public int getConditionNo() {
        return ConditionNo;
    }

    public void setConditionNo(int ConditionNo) {
        this.ConditionNo = ConditionNo;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
}
