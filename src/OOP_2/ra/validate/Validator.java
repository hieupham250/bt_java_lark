package OOP_2.ra.validate;

import java.util.Scanner;

public class Validator {
    public static String validateString(Scanner sc, int min, int max) {
        while (true) {
            String input = sc.nextLine();
            if (input.length() >= min && input.length() <= max) {
                return input;
            }
            System.out.println("Dữ liệu nhập không hợp lệ, nhập lại");
        }
    }

    public static int validateInt(Scanner sc, int min) {
        while (true) {
            if (!sc.hasNextInt()) {
                System.out.println("Không phải số, nhập lại:");
                sc.next();
                continue;
            }
            int number = sc.nextInt();
            sc.nextLine();
            if (number < min) {
                System.out.println("Không được nhỏ hơn " + min);
                continue;
            }
            return number;
        }
    }

    public static float validateFloat(Scanner scanner, float min) {
        while (true) {
            if (!scanner.hasNextFloat()) {
                System.out.println("Không phải số, nhập lại:");
                scanner.next();
                continue;
            }
            float number = scanner.nextFloat();
            scanner.nextLine();
            if (number <= min) {
                System.out.println("Không được nhỏ hơn " + min);
                continue;
            }
            return number;
        }
    }
}
