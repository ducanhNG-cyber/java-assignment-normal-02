/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NguyenDucAnh
 */
public class CarShowRoom implements Builder {

    private CarType carType;
    private int price;
    private SoldTime soldTime;

    @Override
    public void setCarType(CarType type) {
        this.carType = type;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public void setSoldTime(SoldTime soldTime) {
        this.soldTime = soldTime;
    }

}
