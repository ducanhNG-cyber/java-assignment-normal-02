/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author NguyenDucAnh
 */
public class ProgramView {

    public void menu() {
        String[] menu = {"Convert From Binary", "Convert From Decimal",
            "Convert From Hexadecimal", "Exit"};

        for (int i = 0; i < menu.length; i++) {
            System.out.println(String.format("%d. %s", i + 1, menu[i]));
        }
    }

}
