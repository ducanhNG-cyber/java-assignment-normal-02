/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NguyenDucAnh
 */
public class Order {

    private String customerID;
    private int[] fruitID;

    public Order(String customerID, int[] fruitID) {
        this.customerID = customerID;
        this.fruitID = fruitID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int[] getFruitID() {
        return fruitID;
    }

    public void setFruitID(int[] fruitID) {
        this.fruitID = fruitID;
    }

}
