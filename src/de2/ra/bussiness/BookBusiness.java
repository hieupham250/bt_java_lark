package de2.ra.bussiness;

import de2.ra.entity.Book;
import de2.ra.presentation.BookApplication;
import de2.ra.validate.BookValidator;
import de2.ra.validate.StringRule;
import de2.ra.validate.Validator;

import java.util.Collections;
import java.util.Comparator;

import java.util.Scanner;

public class BookBusiness {
    public static void displayListBooks() {
        if (BookApplication.books.isEmpty()) {
            System.out.println("Danh sách sách trống!");
            return;
        }
        for (Book book : BookApplication.books) {
            book.displayData();
        }
    }

    public static void addBook(Scanner sc) {
        int numberOfBooks = Validator.validateInputInt(sc, "Nhập số sách cần nhập thông tin");
        for (int i = 0; i < numberOfBooks; i++) {
            System.out.println("Nhập thông tin cho sách thứ " + (i + 1) + ":");
            Book book = new Book();
            book.inputData(sc);
            BookApplication.books.add(book);
        }
        System.out.println("Đã thêm " + numberOfBooks + " cuốn sách vào danh sách.");
    }

    public static Book findBookById(String bookId) {
        for (Book book : BookApplication.books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    public static void updateBook(Scanner sc) {
        Book updatedBook = findBookById(Validator.validateInputString(sc, "Nhập vào mã sách cần cập nhật", new StringRule(0, 5)));
        if (updatedBook == null) {
            System.out.println("Không tìm thấy sách");
            return;
        }
        updatedBook.displayData();
        do {
            System.out.println("1. Cập nhật tiêu đề sách");
            System.out.println("2. cập nhật tác giả");
            System.out.println("3. Cập nhật nhà xuất bản");
            System.out.println("4. Cập nhật năm xuất bản");
            System.out.println("5. Cập nhật thể loại");
            System.out.println("6. Cập nhật giá");
            System.out.println("7. Cập nhật số lượng");
            System.out.println("8. Thoát");
            int choice = Validator.validateInputInt(sc, "Lựa chọn của bạn");
            switch (choice) {
                case 1:
                    String bookTitle = Validator.validateInputString(sc, "Nhập tên sách mới", new StringRule(0, 100));
                    updatedBook.setBookTitle(BookValidator.validateBookTitle(sc, bookTitle, updatedBook));
                    break;
                case 2:
                    updatedBook.setAuthor(Validator.validateInputString(sc, "Nhập tác giả mới", new StringRule(0, 50)));
                    System.out.println("Cập nhật thành công");
                    break;
                case 3:
                    updatedBook.setPublisher(Validator.validateInputString(sc, "Nhập nhà xuất bản mới", new StringRule(0, 100)));
                    System.out.println("Cập nhật thành công");
                    break;
                case 4:
                    updatedBook.setPublicationYear(BookValidator.validateBookPublicationYear(sc, "Nhập năm xuất bản mới"));
                    System.out.println("Cập nhật thành công");
                    break;
                case 5:
                    updatedBook.setCategory(Validator.validateInputString(sc, "Nhập thể loại mới", new StringRule(0, 100)));
                    System.out.println("Cập nhật thành công");
                    break;
                case 6:
                    updatedBook.setPrice(Validator.validateInputDouble(sc, "Nhập giá mới", 0));
                    System.out.println("Cập nhật thành công");
                    break;
                case 7:
                    updatedBook.setQuantity(BookValidator.validateBookQuantity(sc, "Nhập số lượng mới"));
                    System.out.println("Cập nhật thành công");
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Vui lòng chọn từ 1-8");
            }
        } while (true);
    }

    public static void deleteBook(Scanner sc) {
        String bookId = Validator.validateInputString(sc, "Nhập vào mã sách cần xóa", new StringRule(0, 5));
        Book bookToDelete = findBookById(bookId);
        if (bookToDelete == null) {
            System.out.println("Không tìm thấy sách");
            return;
        }
        System.out.println("Thông tin sách cần xóa:");
        bookToDelete.displayData();
        System.out.print("Bạn có chắc chắn muốn xóa cuốn sách này không? (Y/N): ");
        String confirmation = sc.nextLine().trim().toUpperCase();
        if (confirmation.equals("Y")) {
            BookApplication.books.remove(bookToDelete);
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Hủy thao tác xóa sách.");
        }
    }

    public static void searchBook(Scanner sc) {
        do {
            System.out.println("1. Tìm kiếm theo tiêu đề sách");
            System.out.println("2. Tìm kiếm theo thể loại");
            System.out.println("3. Tìm kiếm theo khoảng giá");
            System.out.println("4. Thoát");
            int choice = de1.ra.validate.Validator.validateInputInt(sc, "Lựa chọn của bạn");
            boolean found = false;
            switch (choice) {
                case 1:
                    searchByTitle(sc);
                    break;
                case 2:
                    searchByCategory(sc);
                    break;
                case 3:
                    searchByPriceRange(sc);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Vui lòng chọn từ 1-4");
            }
        } while (true);
    }

    private static void searchByTitle(Scanner sc) {
        String keyword = Validator.validateInputString(sc, "Nhập tiêu đề sách cần tìm", new StringRule(0, 100));
        boolean found = false;

        for (Book book : BookApplication.books) {
            if (book.getBookTitle().toLowerCase().contains(keyword.toLowerCase())) {
                book.displayData();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy kết quả phù hợp.");
        }
    }

    private static void searchByCategory(Scanner sc) {
        String category = Validator.validateInputString(sc, "Nhập thể loại sách cần tìm", new StringRule(0, 100));
        boolean found = false;

        for (Book book : BookApplication.books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                book.displayData();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy kết quả phù hợp.");
        }
    }

    private static void searchByPriceRange(Scanner sc) {
        double minPrice = Validator.validateInputDouble(sc, "Nhập giá tối thiểu", 0);
        double maxPrice = Validator.validateInputDouble(sc, "Nhập giá tối đa", minPrice);
        boolean found = false;

        for (Book book : BookApplication.books) {
            if (book.getPrice() >= minPrice && book.getPrice() <= maxPrice) {
                book.displayData();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy kết quả phù hợp.");
        }
    }

    public static void sortBooks(Scanner sc) {
        do {
            System.out.println("1. Sắp xếp theo tiêu đề (A-Z)");
            System.out.println("2. Sắp xếp theo tiêu đề (Z-A)");
            System.out.println("3. Sắp xếp theo giá tăng dần");
            System.out.println("4. Sắp xếp theo giá giảm dần");
            System.out.println("5. Thoát");

            int choice = Validator.validateInputInt(sc, "Lựa chọn của bạn");

            switch (choice) {
                case 1:
                    Collections.sort(BookApplication.books, Comparator.comparing(Book::getBookTitle));
                    break;
                case 2:
                    Collections.sort(BookApplication.books, Comparator.comparing(Book::getBookTitle).reversed());
                    break;
                case 3:
                    Collections.sort(BookApplication.books, Comparator.comparingDouble(Book::getPrice));
                    break;
                case 4:
                    Collections.sort(BookApplication.books, Comparator.comparingDouble(Book::getPrice).reversed());
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Vui lòng chọn từ 1-5");
            }

            System.out.println("Danh sách sau khi sắp xếp:");
            displayListBooks();

        } while (true);
    }

}
