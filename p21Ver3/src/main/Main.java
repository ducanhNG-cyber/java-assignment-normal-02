/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import controller.StudentController;

/**
 *
 * @author NguyenDucAnh
 */
public class Main {

    private StudentController studentController = new StudentController();

    private void run() {
        studentController.generateDB();
        while (true) {
            studentController.getMenu();
            int choice = studentController.getUserChoiceLimit("> Your choice: ", 1, 5);
            switch (choice) {
                case 1: // creat
                    studentController.createStudentTask();
                    break;
                case 2: // find and sort
                    studentController.findAndSortByStudentName();
                    break;
                case 3: // update or delete
                    studentController.updateORdeleteTask();
                    break;
                case 4: // report
                    break;
                case 5:
                    System.out.println("Exit...");
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
