/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author NguyenDucAnh
 */
public class ProgramView {

    private String s1 = " ============ ";
    private String s2 = " ------------ ";
    private List<String> listForS1 = Arrays.asList("Menu", "Login Program");
    private List<String> listForS2 = Arrays.asList("Vietnamese", "English","Display");

    private boolean checkPrintMenu(String getValue) {
        for (String l : listForS1) {
            if (l.equalsIgnoreCase(getValue)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkPrintTitle(String getValue) {
        for (String l : listForS2) {
            if (l.equalsIgnoreCase(getValue)) {
                return true;
            }
        }
        return false;
    }

    public void printView(String format, Object... in) {
        System.out.println(String.format(format, in));
    }

    public void printTitle(int type, String title) {
        if (type == 0 && checkPrintMenu(title)) {
            printView("%-15s%-15s%-15s", s1, title, s1);
        }

        if (type == 1 && checkPrintTitle(title)) {
            printView("%-15s%-15s%-15s", s2, title, s2);
        }
    }

    public void printMenu() {
        String menu[] = {"Vietnamese", "English", "Exit"};
        for (int i = 0; i < menu.length; i++) {
            System.out.println(String.format("%d. %s", i + 1, menu[i]));
        }
    }
}
