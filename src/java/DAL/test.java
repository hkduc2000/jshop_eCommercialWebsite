/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.Category;
import model.Status;
import model.Item;
import model.ItemImg;
import model.Order;
import model.ProcessStep;
import model.User;

/**
 *
 * @author HKDUC
 */
public class test {

    public static void main(String[] args) {
        for (ProcessStep step: new ProcessStepDAO().getAll()){
            System.out.println(step.getProcessStep());
        }
    }
}
