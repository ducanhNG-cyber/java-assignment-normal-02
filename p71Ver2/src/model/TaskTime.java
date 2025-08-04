/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author NguyenDucAnh
 */
public class TaskTime {

    private Date date;
    private double timeFrom;
    private double timeTo;
    private String nameTask;
    private String asginee;

    public TaskTime(Date date, double timeFrom, double timeTo, String nameTask, String asginee) {
        this.date = date;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.nameTask = nameTask;
        this.asginee = asginee;
    }

    public Date getDate() {
        return date;
    }

    public double getTimeFrom() {
        return timeFrom;
    }

    public double getTimeTo() {
        return timeTo;
    }

    public String getNameTask() {
        return nameTask;
    }

    public String getAsginee() {
        return asginee;
    }

}
