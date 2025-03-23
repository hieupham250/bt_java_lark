package OOP_1.ra.validate;

import OOP_1.ra.run.BookImp;

import java.util.Scanner;

public class BookValidator {
    public static String validateBookId(Scanner sc) {
        while (true) {
            String inputString = sc.nextLine();
            if (!inputString.matches("B\\w{3}$")) {
                System.out.println("Không hợp lệ, nhập lại");
                continue;
            }
            boolean check = false;
            for (int i = 0; i < BookImp.books.size(); i++) {
                if (BookImp.books.get(i).getBookId().equals(inputString)) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                return inputString;
            }
            System.out.println("Mã sách đã trùng, nhập lại");
        }
    }

    public static String checkSomeName(Scanner sc, String value) {
        while (true) {
            boolean check = false;
            for (int i = 0; i < BookImp.books.size(); i++) {
                if (BookImp.books.get(i).getBookName().equals(value)) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                return value;
            }
            System.out.println("Tên sách đã trùng, nhập lại");
            value = sc.nextLine();
        }
    }

    public static int validateBookYear(Scanner sc, int min) {
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
}
