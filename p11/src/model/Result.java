/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NguyenDucAnh
 */
public class Result {

    private String[] result;
    private Class classValue;

    public Result(String a, String b, Class classValue) {
        this.result = new String[2];
        this.result[0] = a;
        this.result[1] = b;
        this.classValue = classValue;
    }

    public void re() {

        if (classValue.equals(Binary.class)) {
            System.out.println("Bin to Dec: " + result[0]);
            System.out.println("Bin to Hex: " + result[1]);
            
        }
        if (classValue.equals(Decimal.class)) {
            System.out.println("Dec to Bin: " + result[0]);
            System.out.println("Dec to Hex: " + result[1]);
        }
        if (classValue.equals(Hexadecimal.class)) {
            System.out.println("Hex to Bin: " + result[0]);
            System.out.println("Hex to Dec: " + result[1]);
        }

    }
}
