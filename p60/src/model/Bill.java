/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NguyenDucAnh
 */
public class Bill {

    private int value;
    private List<Integer> bills;

    public Bill() {
    }

    public Bill(int value) {
        this.value = value;
    }

    public void setBills(List<Integer> bills) {
        this.bills = bills;
    }

    public int getTotal() {
        int sum = 0;
        for (Integer i : bills) {
            sum += i;
        }
        return sum;
    }
}
