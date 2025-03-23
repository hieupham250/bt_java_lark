package de2.ra.presentation;

import de1.ra.validate.Validator;
import de2.ra.bussiness.BookBusiness;
import de2.ra.entity.Book;

import java.util.ArrayList;
import java.util.Scanner;

public class BookApplication {
    public static final ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        displayBookMenu(sc);
    }

    public static void displayBookMenu(Scanner sc) {
        System.out.println("----------------------------Book Menu----------------------------");
        System.out.println("1. Hiển thị danh sách các cuốn sách");
        System.out.println("2. Thêm mới sách");
        System.out.println("3. Chỉnh sửa thông tin sách");
        System.out.println("4. Xóa sách");
        System.out.println("5. Tìm kiếm sách");
        System.out.println("6. Sắp xếp");
        System.out.println("0. Thoát chương trình");
        int choice = Validator.validateInputInt(sc, "Lựa chọn của bạn");
        switch (choice) {
            case 0:
                System.out.println("Kết thúc chương trình");
                sc.close();
                System.exit(0);
                break;
            case 1:
                BookBusiness.displayListBooks();
                break;
            case 2:
                BookBusiness.addBook(sc);
                break;
            case 3:
                BookBusiness.updateBook(sc);
                break;
            case 4:
                BookBusiness.deleteBook(sc);
                break;
            case 5:
                BookBusiness.searchBook(sc);
                break;
            case 6:
                BookBusiness.sortBooks(sc);
                break;
            default:
                System.err.println("Vui lòng chọn từ 0-6");
        }
    }
}
