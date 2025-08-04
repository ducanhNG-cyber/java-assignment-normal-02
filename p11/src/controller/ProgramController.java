/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Scanner;
import model.Binary;
import model.Decimal;
import model.Hexadecimal;
import model.Result;
import view.ProgramView;

/**
 *
 * @author NguyenDucAnh
 */
public class ProgramController {

    private Scanner scanner = new Scanner(System.in);
    private ProgramView view = new ProgramView();

    // get menu
    public void getMenu() {
        view.menu();
    }

    // 1. Convert From Binary
    public void convertFromBinary(){
        System.out.print("Enter binary value: ");
        String binaryValue = scanner.nextLine().trim();
        Binary binary = new Binary(binaryValue);
        String result01 = String.valueOf(binary.binToDec());
        String result02 = binary.binToHex();
        Result out = new Result(result01, result02, Binary.class);
        out.re();
    }

    // 2. Convert From Deciaml
    public void convertFromDecimal() {
        System.out.print("Enter decimal value: ");
        int decimalValue = Integer.parseInt(scanner.nextLine());
        Decimal decimal = new Decimal(decimalValue);
        String result01 = decimal.decToBin();
        String result02 = decimal.decToHex();
        Result out = new Result(result01, result02, Decimal.class);
        out.re();
    }

    // 3. Convert From Hexadecimal
    public void convertFromHexadecimal() {
        System.out.print("Enter hexadecimal value: ");
        String hexValue = scanner.nextLine().trim();
        Hexadecimal hexadecimal = new Hexadecimal(hexValue);
        String result01 = hexadecimal.hexToBin();
        String result02 = String.valueOf(hexadecimal.hexToDecimal());
        Result out = new Result(result01, result02, Hexadecimal.class);
        out.re();
    }
}
