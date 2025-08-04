/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author NguyenDucAnh
 */
public class InputHandle {

    private Scanner scanner = new Scanner(System.in);

    public String getString(String msg) {
        while (true) {
            System.out.print(msg);
            String regex = "[a-zA-Z ]+";
            String value = scanner.nextLine().trim();

            if (value.isEmpty()) {
                System.err.println("This value cannot be EMPTY!");
                continue;
            }

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(value);

            if (matcher.matches()) {
                return value;
            } else {
                System.err.println("Invalid Format! please input string!");
            }

        }
    }

    public double getDouble(String msg, double min, double max) {
        while (true) {
            System.out.print(msg);
            String value = scanner.nextLine().trim();

            if (value.isEmpty()) {
                System.err.println("This value cannot be EMPTY!");
                continue;
            }

            try {
                double number = Double.parseDouble(value);

                if (number <= 0) {
                    System.err.println("This number must > 0!");
                    continue;
                }

                if (number < min || number > max) {
                    System.err.println(String.format("Please input between %.2f and %.2f",
                            min, max));
                    continue;
                }

                return number;

            } catch (NumberFormatException e) {
                System.err.println("This value must double/integer number");
            }
        }
    }

    public int getUserLimitChoice(String msg, int min, int max) {
        while (true) {
            System.out.print(msg);
            String value = scanner.nextLine().trim();

            if (value.isEmpty()) {
                System.err.println("This value cannot be EMPTY!");
                continue;
            }

            try {
                int number = Integer.parseInt(value);

                if (number <= 0) {
                    System.err.println("This number must > 0!");
                    continue;
                }

                if (number < min || number > max) {
                    System.err.println(String.format("Please input between %d and %d",
                            min, max));
                    continue;
                }

                return number;

            } catch (NumberFormatException e) {
                System.err.println("This value must integer number");
            }
        }
    }

    public Date getDate(String msg) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);

        while (true) {
            System.out.print(msg);
            String value = scanner.nextLine().trim();

            if (value.isEmpty()) {
                System.err.println("This value cannot be EMPTY!");
                continue;
            }

            // Kiểm tra định dạng dd-MM-yyyy bằng regex
            if (!value.matches("\\d{2}-\\d{2}-\\d{4}")) {
                System.err.println("Invalid format! You must enter date as dd-MM-yyyy (with 4-digit year).");
                continue;
            }

            try {
                Date date = dateFormat.parse(value);
                Calendar inputDate = Calendar.getInstance();
                inputDate.setTime(date);

                Calendar now = Calendar.getInstance();

                int inputDay = inputDate.get(Calendar.DAY_OF_MONTH);
                int inputMonth = inputDate.get(Calendar.MONTH) + 1;
                int inputYear = inputDate.get(Calendar.YEAR);

                int currentDay = now.get(Calendar.DAY_OF_MONTH);
                int currentMonth = now.get(Calendar.MONTH) + 1;
                int currentYear = now.get(Calendar.YEAR);

                if (inputYear > currentYear) {
                    System.err.println("Invalid year: year must be <= current year (" + currentYear + ").");
                    continue;
                }

                if (inputYear == currentYear && inputMonth > currentMonth) {
                    System.err.println("Invalid month: month must be <= current month (" + currentMonth + ") in current year.");
                    continue;
                }

                if (inputYear == currentYear && inputMonth == currentMonth && inputDay > currentDay) {
                    System.err.println("Invalid day: day must be <= today (" + currentDay + ").");
                    continue;
                }

                return date;
            } catch (ParseException e) {
                System.err.println("Invalid date! Please make sure it is a real date (e.g., 30-02-2024 is invalid).");
            }
        }
    }

    public int getInt(String msg) {
        while (true) {
            System.out.print(msg);
            String value = scanner.nextLine().trim();

            if (value.isEmpty()) {
                System.err.println("This value cannot be EMPTY!");
                continue;
            }

            try {
                int number = Integer.parseInt(value);

                if (number <= 0) {
                    System.err.println("This number must > 0!");
                    continue;
                }

                if (number > Integer.MAX_VALUE) {
                    System.err.println("Please input in limit of integer!");
                    continue;
                }

                if (number > 0 && number < Integer.MAX_VALUE) {
                    return number;
                }

            } catch (NumberFormatException e) {
                System.err.println("This value must be integer!");
            }
        }
    }

    public boolean checkYN(String msg) {
        while (true) {
            System.out.print(msg);
            String value = scanner.nextLine().trim();

            if (value.isEmpty()) {
                System.err.println("This value cannot be EMPTY!");
                continue;
            }

            if (value.equalsIgnoreCase("y")) {
                return true;
            }

            if (value.equalsIgnoreCase("n")) {
                return false;
            }

            System.err.println("Invalid Value! Please input Yes[Y] or No[N]:");
        }
    }

}
