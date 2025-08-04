/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author NguyenDucAnh
 */
public class MarkData {

    List<Student> studentMarks;

    public MarkData(List<Student> studentMarks) {
        this.studentMarks = studentMarks;
    }

    public List<Student> getStudentMarks() {
        return studentMarks;
    }

    public void setStudentMarks(List<Student> studentMarks) {
        this.studentMarks = studentMarks;
    }

}
