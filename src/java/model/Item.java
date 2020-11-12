/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author HKDUC
 */
public class Item {
    int ItemID;
    String ItemName;
    String Description;
    int CategoryNo;
    String ThumbnailPath;
    ArrayList<String> ImgPaths;
    ArrayList<Status> statusList;

    public ArrayList<Status> getStatusList() {
        return statusList;
    }

    public void setStatusList(ArrayList<Status> statusList) {
        this.statusList = statusList;
    }

    public Item() {
    }

    public Item(String ItemName, String Description, int CategoryNo) {
        this.ItemName = ItemName;
        this.Description = Description;
        this.CategoryNo = CategoryNo;
    }
    
    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int ItemID) {
        this.ItemID = ItemID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getCategoryNo() {
        return CategoryNo;
    }

    public void setCategoryNo(int CategoryNo) {
        this.CategoryNo = CategoryNo;
    }

    public String getThumbnailPath() {
        return ThumbnailPath;
    }

    public void setThumbnailPath(String ThumbnailPath) {
        this.ThumbnailPath = ThumbnailPath;
    }

    public ArrayList<String> getImgPaths() {
        return ImgPaths;
    }

    public void setImgPaths(ArrayList<String> ImgPaths) {
        this.ImgPaths = ImgPaths;
    }

    @Override
    public String toString() {
        return "Item{" + "ItemID=" + ItemID + ", ItemName=" + ItemName + ", Discription=" + Description + ", CategoryNo=" + CategoryNo + '}';
    }
    
}
