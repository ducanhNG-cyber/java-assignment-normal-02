/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import controller.LoginController;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author NguyenDucAnh
 */
public class InputHandle {

    private Scanner scanner = new Scanner(System.in);

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

    public String getUserAccount(String msg, Locale locale) {
        while (true) {
            System.out.print(msg);
            String regex = "\\d{10}$";
            String value = scanner.nextLine().trim();

            if (value.isEmpty()) {
                System.err.println("This value cannot be EMPTY!");
                continue;
            }

            if (value.length() != 10) {
                System.err.println(LoginController.getWordLanguage(locale, "errorAccountNumber"));
                continue;
            }

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(value);

            if (matcher.matches()) {
                return value;
            } else {
                System.err.println(LoginController.getWordLanguage(locale, "errorAccountNumber"));
            }

        }
    }

    private boolean checkLengthPassword(String value, int min, int max) {
        return value.length() < min || value.length() > max;
    }

    public String getPassword(String msg, Locale locale) {
        while (true) {
            System.out.print(msg);
            String regex = "[a-zA-Z0-9]+";
            String value = scanner.nextLine().trim();

            if (value.isEmpty()) {
                System.err.println("This value cannot be EMPTY!");
                continue;
            }

            if (checkLengthPassword(value, 8, 31)) {
                System.err.println(LoginController.getWordLanguage(locale, "errorPassword"));
                continue;
            }

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(value);

            if (matcher.matches()) {
                return value;
            } else {
                System.err.println(LoginController.getWordLanguage(locale, "errorPassword"));
            }

        }
    }

    public String getcaptcha(String msg) {
        while (true) {
            System.out.print(msg);
            String regex = "[a-zA-Z0-9]+";
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

}
