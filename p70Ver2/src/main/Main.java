/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import controller.LoginController;
import java.util.Locale;

/**
 *
 * @author NguyenDucAnh
 */
public class Main {

    private LoginController loginController = new LoginController();

    private void run() {
        loginController.generateDB();
        while (true) {
            loginController.getMenu();
            Locale locale;
            int choice = loginController.getUserChoice("Your choice: ");
            switch (choice) {
                case 1: // Vietnamese
                    loginController.getTitle("Vietnamese");
                    locale = new Locale("vi");
                    loginController.login(locale);
                    break;
                case 2: // English            
                    loginController.getTitle("English");
                    locale = new Locale("en");
                    loginController.login(locale);
                    break;
                case 3:

                    System.out.println("Exiting...");
                    return;
                case 4:
                    loginController.getTitle("Display");
                    loginController.display();
                    break;
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new Main().run();
    }

}
