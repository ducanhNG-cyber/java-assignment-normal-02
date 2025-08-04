/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import model.Bill;

/**
 *
 * @author NguyenDucAnh
 */
public class ProgramView {

    private String s2 = " ============== ";

    public void printTile(String title) {
        System.out.println(s2 + title + s2);
    }

    public void noctice(String type, Bill bill) {
        String in = "This is total of bill: ";
        if (type.equalsIgnoreCase("out")) {
            System.out.println(in + bill.getTotal());
            System.out.println("You can buy it.");
        }
        if(type.equalsIgnoreCase("err")){
            System.out.println(in+bill.getTotal());
            System.out.println("You can't buy it.");
        }
    }
}
