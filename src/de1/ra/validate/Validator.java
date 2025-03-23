package de1.ra.validate;

import de1.ra.entity.IApp;

import java.util.Scanner;

public class Validator {
    public static String validateInputString(Scanner sc, String message) {
        System.out.println(message);
        do {
            String inputString = sc.nextLine();
            if (!inputString.isEmpty()) {
                return inputString;
            }
            System.err.println("Dữ liệu không hợp lệ, vui lòng nhập lại");
        } while (true);
    }

    public static int validateInputInt(Scanner sc, String message) {
        System.out.println(message);
        do {
            if (!sc.hasNextInt()) {
                System.err.println("Dữ liệu không phải số nguyên, vui lòng nhập lại");
                sc.nextLine();
                continue;
            }
            return Integer.parseInt(sc.nextLine());
        } while (true);
    }

    public static boolean validateInputBoolean(Scanner sc, String message) {
        System.out.println(message);
        do {
            String inputString = sc.nextLine();
            if (inputString.equalsIgnoreCase("true") || inputString.equalsIgnoreCase("false")) {
                return Boolean.parseBoolean(inputString);
            }
            System.err.println("Kiểu dữ liệu không hợp lệ, vui lòng nhập lại");
        } while (true);
    }

    public static float validateInputFloat(Scanner sc, String message, FloatRange range) {
        System.out.println(message);
        do {
            if (!sc.hasNextFloat()) {
                System.err.println("Dữ liệu không hợp lệ, vui lòng nhập lại");
                sc.nextLine();
                continue;
            }
            float number = Float.parseFloat(sc.nextLine());
            if (range.isWithinRange(number)) {
                return number;
            }
            System.err.printf("Giá trị phải nằm trong khoảng %.2f - %.2f, vui lòng nhập lại\n", range.getMin(), range.getMax());
        } while (true);
    }

    public static byte validateInputByte(Scanner sc, String message, ByteRange range) {
        System.out.println(message);
        do {
            if (!sc.hasNextByte()) {
                System.err.println("Dữ liệu không hợp lệ, vui lòng nhập lại số nguyên trong khoảng " + range.getMin() + " - " + range.getMax());
                sc.nextLine();
                continue;
            }
            byte number = sc.nextByte();
            sc.nextLine();
            if (range.isWithinRange(number)) {
                return number;
            }
            System.err.println("Giá trị phải nằm trong khoảng " + range.getMin() + " - " + range.getMax() + ", vui lòng nhập lại.");
        } while (true);
    }
}
