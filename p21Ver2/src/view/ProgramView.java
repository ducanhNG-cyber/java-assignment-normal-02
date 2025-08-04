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

    private String symbol_01 = " ---------------- ";
    private String symbol_02 = " ================ ";

    public void printTitle(String in) {
        System.out.println(symbol_01 + in + symbol_01);
    }

    private void printTitleMenu(String in) {
        System.out.println(symbol_02 + in + symbol_02);
    }

    public void printMenu() {
        printTitleMenu("welcome to student management".toUpperCase());
        String[] menu = {"Create", "Find and sort",
            "Update/Delete", "Report", "Exit"};
        for (int i = 0; i < menu.length; i++) {
            System.out.println(String.format("%d. %s",
                    i + 1, menu[i]));
        }
    }
    
    public void printView(String format, Object...in){
        System.out.println(String.format(format, in));
    }
}
