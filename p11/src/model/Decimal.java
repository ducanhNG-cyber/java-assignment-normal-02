/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NguyenDucAnh
 */
public class Decimal {

    private int decimal;

    public Decimal(int decimalValue) {
        this.decimal = decimalValue;
    }

    public String decToHex() {
        if (decimal == 0) {
            return "0"; // Xử lý trường hợp đặc biệt
        }
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'};
        String hex = "";
        while (decimal != 0) {
            int rem = decimal % 16;
            hex = hexDigits[rem] + hex;
            decimal = decimal / 16;
        }
        return hex;
    }

    public String decToBin() {

        String binString = "";
        while (decimal != 0) {
            int rem = decimal % 2;
            decimal /= 2;
            binString = binString + rem;
        }
        return binString;
    }
}
