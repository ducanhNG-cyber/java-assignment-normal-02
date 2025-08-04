/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import controller.FruitController;

/**
 *
 * @author NguyenDucAnh
 */
public class Main {

    private FruitController fruitController = new FruitController();

    private void run() {
        fruitController.setupData();
        while (true) {
            fruitController.getMainMenuView();
            int choice = fruitController.getUserChoice("> Your choice: ", 1, 5);

            switch (choice) {
                case 1: // Create
                    fruitController.createFruit();
                    break;
                case 2: // View Order
                    fruitController.viewOrder();
                    break;
                case 3: // Shopping for buyer
                    fruitController.Shopping();
                    break;
                case 4: // exit
                    System.out.println("Exiting...");
                    return;
                case 5:
                    fruitController.display();
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
