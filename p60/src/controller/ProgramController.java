/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Bill;
import model.Wallet;
import view.ProgramView;

/**
 *
 * @author NguyenDucAnh
 */
public class ProgramController {

    private ProgramView view = new ProgramView();

    private Scanner scanner = new Scanner(System.in);
    private List<Integer> bills = new ArrayList<>();
    private Bill bill = new Bill();
    
    public void call() {
        view.printTile("Shopping program");
        System.out.print("input number of bill: ");
        int size = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < size; i++) {
            System.out.printf("input value of bill %d: ", i + 1);
            int valueBill = Integer.parseInt(scanner.nextLine());
            bills.add(valueBill);
        }
        bill.setBills(bills);
        System.out.print("input number of wallet: ");
        int walletValue = Integer.parseInt(scanner.nextLine());
        Wallet wallet = new Wallet(walletValue);

       
        compareValue(bill, wallet);
    }

    private void compareValue(Bill bill, Wallet wallet) {
        if (bill.getTotal() <= wallet.getValue()) {
            view.noctice("out", bill);
        } else {
            view.noctice("err", bill);
        }
    }
}
