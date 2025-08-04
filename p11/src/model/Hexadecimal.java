/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NguyenDucAnh
 */
public class Hexadecimal {

    private String hex;

    public Hexadecimal(String hexValue) {
        this.hex = hexValue;
    }

    public String hexToBin() {
        String bin = "";
        String binFragment = "";
        int iHex;

        hex = hex.trim(); // Xoá khoảng trắng
        hex = hex.replaceFirst("0x", ""); // Bỏ tiền tố "0x" nếu có

        for (int i = 0; i < hex.length(); i++) {
            iHex = Integer.parseInt("" + hex.charAt(i), 16); // Chuyển ký tự hex về số nguyên
            binFragment = Integer.toBinaryString(iHex); // Chuyển số về nhị phân

            while (binFragment.length() < 4) {
                binFragment = "0" + binFragment; // Bổ sung các số 0 ở đầu nếu cần
            }

            bin += binFragment;
        }

        return bin;
    }

    public int hexToDecimal() {
        String digits = "0123456789ABCDEF";
        hex = hex.toUpperCase();
        int val = 0;
        for (int i = 0; i < hex.length(); i++) {
            char c = hex.charAt(i);
            int d = digits.indexOf(c);
            if (d == -1) {
                throw new IllegalArgumentException("Invalid hex character: " + c);
            }
            val = 16 * val + d;
        }
        return val;
    }

}
