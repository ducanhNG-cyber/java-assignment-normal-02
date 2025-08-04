/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NguyenDucAnh
 */
public class Wallet {

    private int total;

    public Wallet(int value) {
        if (value >= 0) {
            this.total = value;
        } else {
            System.err.println("Your input must >= 0!");
        }
    }

    public int getValue() {
        return total;
    }
}
