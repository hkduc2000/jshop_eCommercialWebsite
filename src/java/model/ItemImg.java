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
public class ItemImg {
    int ItemID;
    String ImagePath;
    boolean thumbnail;

    public ItemImg(int ItemID, String ImagePath, boolean isThumbnail) {
        this.ItemID = ItemID;
        this.ImagePath = ImagePath;
        this.thumbnail = isThumbnail;
    }

    public ItemImg() {
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int ItemID) {
        this.ItemID = ItemID;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
    }

    public boolean isThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(boolean isThumbnail) {
        this.thumbnail = isThumbnail;
    }
}
