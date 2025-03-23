package de2.ra.validate;

import de2.ra.entity.Book;
import de2.ra.presentation.BookApplication;

import java.util.Scanner;
import java.util.regex.Pattern;

public class BookValidator {
    public static String validateBookId(Scanner sc, String message) {
        String regex = "^B\\d{5}$";
        System.out.println(message);
        do {
            String inputString = sc.nextLine();
            if (Pattern.matches(regex, inputString)) {
                return inputString;
            }
            System.err.println("Dữ liệu không hợp lệ, vui lòng nhập lại");
        } while (true);
    }

    public static String isBookExist(Scanner sc, String value, String type) {
        do {
            boolean isExist = false;
            switch (type) {
                case "bookId":
                    for (Book book : BookApplication.books) {
                        if (book.getBookId().equals(value)) {
                            isExist = true;
                            break;
                        }
                    }
                    break;
                case "bookTitle":
                    for (Book book : BookApplication.books) {
                        if (book.getBookTitle().equals(value)) {
                            isExist = true;
                            break;
                        }
                    }
                    break;
            }
            if (!isExist) {
                return value;
            }
            System.err.println("Dữ liệu bị trùng lặp, vui lòng nhập lại");
            value = sc.nextLine();
        } while (true);
    }

    public static String validateBookTitle(Scanner sc, String value, Book book) {
        do {
            boolean isExist = false;
            for (Book b : BookApplication.books) {
                if (b.getBookTitle().equalsIgnoreCase(value) && !b.getBookId().equals(book.getBookId())) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                return value;
            }
            System.err.println("Tên sách đã tồn tại, vui lòng nhập lại tên khác:");
            value = sc.nextLine();
        } while (true);
    }

    public static int validateBookPublicationYear(Scanner sc, String message) {
        int year;
        do {
            System.out.print(message);
            while (!sc.hasNextInt()) {
                System.err.println("Vui lòng nhập một số nguyên dương.");
                System.out.print(message);
                sc.next();
            }
            year = sc.nextInt();
            if (year > 0) {
                return year;
            } else {
                System.err.println("Năm xuất bản phải lớn hơn 0.");
            }
        } while (true);
    }

    public static int validateBookQuantity(Scanner sc, String message) {
        int quantity;
        do {
            System.out.print(message);
            while (!sc.hasNextInt()) {
                System.err.println("Vui lòng nhập một số nguyên dương.");
                System.out.print(message);
                sc.next();
            }
            quantity = sc.nextInt();
            if (quantity > 0) {
                return quantity;
            } else {
                System.err.println("Năm xuất bản phải lớn hơn 0.");
            }
        } while (true);
    }
}
