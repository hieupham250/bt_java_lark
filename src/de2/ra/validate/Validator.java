package de2.ra.validate;

import java.util.Scanner;

public class Validator {
    public static String validateInputString(Scanner sc, String message, StringRule stringRule) {
        System.out.println(message);
        do {
            String inputString = sc.nextLine();
            if (stringRule.isValidString(inputString)) {
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

    public static double validateInputDouble(Scanner sc, String message, double minValue) {
        System.out.println(message);
        do {
            if (!sc.hasNextDouble()) {
                System.err.println("Dữ liệu không hợp lệ, vui lòng nhập lại");
                sc.nextLine();
                continue;
            }
            double number = sc.nextDouble();
            if (number > minValue) {
                return number;
            }
            System.err.printf("Giá trị phải lớn hơn %.2f, vui lòng nhập lại\n", minValue);
        } while (true);
    }
}
