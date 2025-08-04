/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
import model.User;
import model.UserData;
import utils.InputHandle;
import view.ProgramView;

/**
 *
 * @author NguyenDucAnh
 */
public class LoginController {

    private ProgramView view = new ProgramView();
    private UserData userData = new UserData();
    private List<User> data = new ArrayList<>();
    private InputHandle inputHandle = new InputHandle();

    private final int sizeCaptcha = 5;
    private final String pathName = "ghiFileDemo.txt";

    // -------------------------------------- DATA BASE --------------------------------------
    public void generateDB() {
        data.add(new User("0123456789", "ducAnh123"));
        data.add(new User("0321323423", "anime2wibu"));
        data.add(new User("1234567890", "wibu3rach"));

    }

    // -------------------------------------- VIEW --------------------------------------
    public void getMenu() {
        view.printTitle(0, "Login program");
        view.printMenu();
    }

    public void getRunChildMenu(Locale locale) {
        view.printMenuChild(locale);
    }

    public void getTitle(String msg) {
        view.printTitle(1, msg);
    }

    public static String getWordLanguage(Locale currentLocale, String key) {
        ResourceBundle languges = ResourceBundle.getBundle("model/languages/" + currentLocale, currentLocale);
        String value = languges.getString(key);
        return value + " ";
    }

    public void setupdata() {
        System.out.println("Thuc hien doc file");
        readData(pathName);
    }

    // -------------------------------------- PROGRESS --------------------------------------
    public void create(Locale locale) {

        String userAccount = getUserAccount(getWordLanguage(locale, "enterAccountNumber"), locale);
        String password = getPassword(getWordLanguage(locale, "enterPassword"), locale);

        userData.setUserList(data);

        if (userData.isDuplicatedAccountName(userAccount)) {
            System.err.println("new user account is duplicated");
            return;
        }

        User user = new User(userAccount, password);
        addController(user);
    }

    private void addController(User user) {
        data.add(user);

        //User getUser = data.get(data.size()-1);
        
        int count = 0;
        for (int i = 0; i < data.size(); i++) {
            if (count == data.size() - 1) {
                writeDataToFile(pathName, user.toString());
            }
            count++;
        }

    }

    public void login(Locale locale) {
        userData.setUserList(data);
        String userAccount = getUserAccount(getWordLanguage(locale, "enterAccountNumber"), locale);
        String password = getPassword(getWordLanguage(locale, "enterPassword"), locale);
        String captcha = generateCaptcha(sizeCaptcha);
        view.printView("%s: %s", "Captcha", captcha);
        String userCaptcha = getCaptcha(getWordLanguage(locale, "enterCaptcha"));

        User user = new User(userAccount, password);
        if (checkCaptcha(captcha, userCaptcha)) { // Kiem tra captcha da random va captcha nhap vao
            if (userData.loginAccout(user)) {
                System.out.println("Login Success!");
            } else {
                System.err.println("Login Fail!");
            }
        } else {
            System.out.println(getWordLanguage(locale, "errorCaptcha"));
        }

    }

    // Handle file: write data to file
    public void writeDataToFile(String pathName, String data) {
        try {

            try (FileWriter writer = new FileWriter(pathName, true)) {
                writer.write(data);
                writer.append("\n");
            }
            System.out.println("Successfully to wrote text to file!");
        } catch (IOException e) {
            System.err.println("Error! Wrote data to file not working!");
            e.printStackTrace();
        }
    }

    // Handle data: read data from file 
    public void readData(String pathName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(pathName))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] m = currentLine.split(",");
                if (m.length >= 2) {
                    String userAccount = m[0].trim();
                    String password = m[1].trim();
                    data.add(new User(userAccount, password));
                }
            }
        } catch (IOException e) {
            System.err.println("Error! Read data from file not working!");
            e.printStackTrace();
        }
    }

    // -------------------------------------- SUPPORT --------------------------------------
    public void display() {
        if (data.isEmpty()) {
            setupdata();
        }
        for (User data : data) {
            System.out.println(data.toString());
        }
    }

    private String generateCaptcha(int size) {
        Random rand = new Random(); // Tạo số ngẫu nhiên không seed để đảm bảo tính ngẫu nhiên thực sự

        String chrs = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder captcha = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            int index = rand.nextInt(chrs.length()); // Sinh số ngẫu nhiên từ 0 đến độ dài của chuỗi
            captcha.append(chrs.charAt(index));
        }

        return captcha.toString();
    }

    private boolean checkCaptcha(String captcha, String userCaptcha) {
        return captcha.equals(userCaptcha);
    }

    // -------------------------------------- INPUT --------------------------------------
    public int getUserChoice(String msg) {
        return inputHandle.getUserLimitChoice(msg, 1, 10);
    }

    private String getUserAccount(String msg, Locale locale) {
        return inputHandle.getUserAccount(msg, locale);
    }

    private String getPassword(String msg, Locale locale) {
        return inputHandle.getPassword(msg, locale);
    }

    private String getCaptcha(String msg) {
        return inputHandle.getcaptcha(msg);
    }

    public void clearDataBase() {
        data.clear();
    }
}
