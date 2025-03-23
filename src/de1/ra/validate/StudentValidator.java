package de1.ra.validate;

import de1.ra.presentation.StudentApplication;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentValidator {
    public static String validateStudentId(Scanner sc, String message) {
        String regex = "^SV\\d{5}$";
        System.out.println(message);
        do {
            String inputString = sc.nextLine();
            if (Pattern.matches(regex, inputString)) {
                return inputString;
            }
            System.err.println("Dữ liệu không hợp lệ, vui lòng nhập lại");
        } while (true);
    }

    public static String isStudentExist(Scanner sc, String value) {
        do {
            boolean isExist = false;
            for (int i = 0; i < StudentApplication.currentStudentIndex; i++) {
                if (StudentApplication.arrStudents[i].getStudentId().equals(value)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                return value;
            }
            System.err.println("Dữ liệu bị trùng lặp, vui lòng nhập lại");
            value = sc.nextLine();
        } while (true);
    }

    public static String validateBirthday(Scanner sc, String message) {
        String regex = "^([0-2][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Không cho phép ngày không hợp lệ như 30/02/2023
        System.out.println(message);
        do {
            String inputString = sc.nextLine();
            if (Pattern.matches(regex, inputString)) {
                try {
                    Date date = sdf.parse(inputString);
                    return inputString;
                } catch (Exception e) {
                    System.err.println("Ngày sinh không hợp lệ. Vui lòng nhập lại (định dạng dd/MM/yyyy).");
                }
            } else {
                System.err.println("Định dạng ngày sinh không hợp lệ. Vui lòng nhập lại (dd/MM/yyyy).");
            }
        } while (true);
    }

    public static String validatePhoneNumber(Scanner sc, String message) {
        String regex = "^(0[2-9][0-9]{8})$";
        System.out.println(message);
        do {
            String inputString = sc.nextLine();
            if (Pattern.matches(regex, inputString)) {
                return inputString;
            }
            System.err.println("Số điện thoại không hợp lệ. Vui lòng nhập lại");
        } while (true);
    }

    public static String validateEmail(Scanner sc, String message) {
        String regex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        System.out.println(message);
        do {
            String inputString = sc.nextLine();
            if (Pattern.matches(regex, inputString)) {
                return inputString;
            }
            System.err.println("Định dạng email không hợp lệ. Vui lòng nhập lại");
        } while (true);
    }
}
