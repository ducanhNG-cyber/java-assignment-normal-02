/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author NguyenDucAnh
 */
public class Binary {

    private String binary;

    public Binary(String binaryValue) {
        this.binary = binaryValue;
    }

    public String binToHex() {
        int digitNumber = 1;
        int sum = 0;
        StringBuilder hex = new StringBuilder();

        for (int i = 0; i < binary.length(); i++) {
            if (digitNumber == 1) {
                sum += Integer.parseInt(binary.charAt(i) + "") * 8;
            } else if (digitNumber == 2) {
                sum += Integer.parseInt(binary.charAt(i) + "") * 4;
            } else if (digitNumber == 3) {
                sum += Integer.parseInt(binary.charAt(i) + "") * 2;
            } else if (digitNumber == 4) {
                sum += Integer.parseInt(binary.charAt(i) + "") * 1;
                digitNumber = 0;

                if (sum < 10) {
                    hex.append(sum);
                } else {
                    hex.append((char) ('A' + (sum - 10)));
                }
                sum = 0;
            }
            digitNumber++;
        }

        // Nếu chưa đủ 4 bit cuối thì vẫn phải xử lý phần còn lại
        if (digitNumber != 1) {
            // Fill các bit còn thiếu bằng 0 để đủ 4 bit
            for (; digitNumber <= 4; digitNumber++) {
                if (digitNumber == 2) {
                    sum += 0 * 4;
                } else if (digitNumber == 3) {
                    sum += 0 * 2;
                } else if (digitNumber == 4) {
                    sum += 0 * 1;
                }
            }

            if (sum < 10) {
                hex.append(sum);
            } else {
                hex.append((char) ('A' + (sum - 10)));
            }
        }

        return hex.toString();
    }

    public int binToDec() {
        int result = 0;
        for (int i = 0; i < binary.length(); i++) {
            char c = binary.charAt(i);
            if (c != '0' && c != '1') {
                throw new IllegalArgumentException("Invalid binary digit: " + c);
            }
            result = result * 2 + (c - '0');
        }
        return result;
    }

}
