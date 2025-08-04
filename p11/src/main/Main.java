/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import controller.ProgramController;
import java.util.Scanner;

/**
 *
 * @author NguyenDucAnh
 */
public class Main {

    private ProgramController programController = new ProgramController();
    private Scanner scanner = new Scanner(System.in);

    private void run() {
        while (true) {
            System.out.println(" =============== MENU =============== ");
            programController.getMenu();
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    programController.convertFromBinary();
                    break;
                case 2:
                    programController.convertFromDecimal();
                    break;
                case 3:
                    programController.convertFromHexadecimal();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
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
