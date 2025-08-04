/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model;

/**
 *
 * @author NguyenDucAnh
 */
public enum Semester {
    FALL(1, "Fall"),
    SUMMER(2, "Summer"),
    SPRING(3, "Spring");

    private int selectionSemesterType;
    private String semesterStringValue;

    private Semester(int selectionSemesterType, String semesterStringValue) {
        this.selectionSemesterType = selectionSemesterType;
        this.semesterStringValue = semesterStringValue;
    }

    public int getSelectionSemesterType() {
        return selectionSemesterType;
    }

    public String getSemesterStringValue() {
        return semesterStringValue;
    }

    public static Semester fromSemester(int number) {
        for (Semester semester : Semester.values()) {
            if (semester.getSelectionSemesterType() == number) {
                return semester;
            }
        }
        return null;
    }
}
