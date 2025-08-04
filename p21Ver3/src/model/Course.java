/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model;

/**
 *
 * @author NguyenDucAnh
 */
public enum Course {

    JAVA(1, "Java"),
    CPP(2, "Cpp"),
    PYTHON(3, "Python"),
    NET(4, "Net");

    private int selectionCourseType;
    private String courseStringValue;

    private Course(int selectionCourseType, String courseStringValue) {
        this.selectionCourseType = selectionCourseType;
        this.courseStringValue = courseStringValue;
    }

    public int getSelectionCourseType() {
        return selectionCourseType;
    }

    public String getCourseStringValue() {
        return courseStringValue;
    }

    public static Course fromCouse(int number) {
        for (Course course : Course.values()) {
            if (course.getSelectionCourseType() == number) {
                return course;
            }
        }
        return null;
    }
}
