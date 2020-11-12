/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author HKDUC
 */
public class User {

    String Username;
    String Password;
    String Name;
    String Email;
    String Phone;
    String Address;
    boolean Admin;

    public User(String Username, String Password, String Name, String Email, String Phone, String Address, boolean Admin) {
        this.Username = Username;
        this.Password = Password;
        this.Name = Name;
        this.Email = Email;
        this.Phone = Phone;
        this.Address = Address;
        this.Admin = Admin;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public User() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public boolean isAdmin() {
        return Admin;
    }

    public void setAdmin(boolean Admin) {
        this.Admin = Admin;
    }
}
