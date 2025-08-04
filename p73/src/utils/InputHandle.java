/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author NguyenDucAnh
 */
public class InputHandle {

    private Scanner scanner = new Scanner(System.in);
    private Date v1;
    private int dayGet;
    private int monthGet;

    public void setV1(Date date) {
        this.v1 = date;
    }

    public Date getV1() {
        return this.v1;
    }

    public void setDayGet(int d) {
        this.dayGet = d;
    }

    public int getDayGet() {
        return this.dayGet;
    }

    public void setMonthGet(int m) {
        this.monthGet = m;
    }

    public int getMonthGet() {
        return this.monthGet;
    }

    public int getUserNumberLimit(String msg, int min, int max) {
        while (true) {
            System.out.print(msg);
            try {
                String value = scanner.nextLine().trim();

                if (value.isEmpty()) {
                    System.err.println("This value cannot be empty!");
                    continue;
                }

                int number = Integer.parseInt(value);

                if (number <= 0) {
                    System.err.println("This value must > 0!");
                    continue;
                }

                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.err.println(String.format("please input from %d to %d", min, max));
                }

            } catch (NumberFormatException e) {
                System.err.println("This value must be a integer!");
            }
        }
    }

    public int getAmountValue(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                String value = scanner.nextLine().trim();

                if (value.isEmpty()) {
                    System.err.println("This amount cannot be empty!");
                    continue;
                }

                int amountNumber = Integer.parseInt(value);

                if (amountNumber < 0) {
                    System.err.println("This value must >= 0!");
                    continue;
                }

                return amountNumber;

            } catch (NumberFormatException e) {
                System.err.println("Invalid! please input integer!");
            }
        }
    }

    public String getContentValue(String msg) {
        while (true) {
            System.out.print(msg);
            String valueString = scanner.nextLine().trim();

            if (valueString.isEmpty()) {
                System.err.println("This content cannot be empty!");
                continue;
            } else {
                return valueString;
            }

        }
    }

    public Date getDateValue(String msg) {
        String regex = "^\\d{2}-(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)-\\d{4}$";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        dateFormat.setLenient(false);

        while (true) {
            System.out.print(msg);
            String valueString = scanner.nextLine().trim();

            if (valueString.isEmpty()) {
                System.err.println("This date cannot be empty!");
                continue;
            }

            if (!Pattern.matches(regex, valueString)) {
                System.err.println("Date must be in format dd-MMM-yyyy (e.g. 11-Apr-2009)!");
                continue;
            }

            try {
                // Parse chuỗi ngày
                Date date = dateFormat.parse(valueString);
                setV1(date);

                Calendar inputDate = Calendar.getInstance();
                inputDate.setTime(date);

                int maxDay = inputDate.getActualMaximum(Calendar.DAY_OF_MONTH);
                int monthInputed = inputDate.get(Calendar.MONTH) + 1;

                setDayGet(maxDay);
                setMonthGet(monthInputed);

                // Kiểm tra không nhập ngày tương lai
                Date currentDate = new Date();
                if (date.after(currentDate)) {
                    Calendar now = Calendar.getInstance();
                    System.err.printf("Your day input must be <= current day [%02d-%02d-%04d]%n",
                            now.get(Calendar.DAY_OF_MONTH),
                            now.get(Calendar.MONTH) + 1,
                            now.get(Calendar.YEAR));
                    continue;
                }

                return date;

            } catch (ParseException e) {
                try {
                    // Tách chuỗi để tìm tháng và năm
                    String[] parts = valueString.split("-");
                    String monthStr = parts[1];
                    int year = Integer.parseInt(parts[2]);

                    Date tempMonth = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(monthStr);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(tempMonth);
                    int month = cal.get(Calendar.MONTH);

                    Calendar maxDayCal = Calendar.getInstance();
                    maxDayCal.set(Calendar.MONTH, month);
                    maxDayCal.set(Calendar.YEAR, year);
                    int maxDay = maxDayCal.getActualMaximum(Calendar.DAY_OF_MONTH);

                    System.err.printf("Invalid date! Month %02d only has up to %02d days.%n", month + 1, maxDay);
                } catch (Exception ex) {
                    System.err.println("Invalid date format!");
                }
            }
        }
    }

}
