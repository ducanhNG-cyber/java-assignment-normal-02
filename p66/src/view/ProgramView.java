/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author NguyenDucAnh
 */
public class ProgramView {

    private String s1 = " ------------------ ";
    private String s2 = " ================== ";

    public void printTitle(String title) {
        System.out.println(s1 + title + s1);
    }

    public void printMainTitle(String mainTitle) {
        System.out.println(s2 + mainTitle + s2);
    }

}
