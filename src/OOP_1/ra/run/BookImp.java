package OOP_1.ra.run;

import OOP_1.ra.entity.Book;
import OOP_1.ra.validate.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookImp {
    public static final List<Book> books = new ArrayList<Book>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("----------------------------MENU----------------------------");
            System.out.println("1. Nhập thông tin n sách (n nhập từ bàn phím)");
            System.out.println("2. Tính lợi nhuận luận các sách");
            System.out.println("3. Hiển thị thông tin sách");
            System.out.println("4. Sắp xếp sách theo giá bán tăng dần");
            System.out.println("5. Sắp xếp sách theo lợi nhuận giảm dần");
            System.out.println("6. Tìm sách theo tên sách (tên sách nhập từ bàn phím)");
            System.out.println("7. Thống kê số lượng sách theo năm xuất bản");
            System.out.println("8. Thống kê số lượng sách theo tác giả");
            System.out.println("9. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Validator.validateInt(sc);
            switch (choice) {
                case 1:
                    addBook(sc);
                    break;
                case 2:
                    calculateBookInterest();
                    break;
                case 3:
                    printBooks();
                    break;
                case 4:
                    orderByPrice();
                    break;
                case 5:
                    orderByInterest();
                    break;
                case 6:
                    searchName(sc);
                    break;
                case 7:
                    displayBookOfYear();
                    break;
                case 8:
                    displayBookOfAuthor();
                    break;
                case 9:
                    System.out.println("Kết thúc chuơng trình");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nhập từ 1-9");
            }
        } while (true);
    }

    public static void addBook(Scanner sc) {
        System.out.println("Nhập n sách muốn thêm: ");
        int n = Validator.validateInt(sc);
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin cho sách thứ " + (i + 1) + ":");
            Book book = new Book();
            book.addBook(sc);
            books.add(book);
        }
    }

    public static void calculateBookInterest() {
        for (Book book : books) {
            System.out.println("Sách: " + book.getBookName() + " có lợi nhuận là: " + book.calculateInterest());
        }
    }

    public static void printBooks() {
        if (books.isEmpty()) {
            System.out.println("Danh sách rỗng");
            return;
        }
        for (Book book : books) {
            book.displayBooks();
        }
    }

    public static void orderByPrice() {
        if (books.isEmpty()) {
            System.out.println("Danh sách sách rỗng, không thể sắp xếp.");
            return;
        }
        int n = books.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (books.get(j).getExportPrice() > books.get(j + 1).getExportPrice()) {
                    Book temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }
        System.out.println("Sắp xếp sách theo giá bán tăng dần: ");
        printBooks();
    }

    public static void orderByInterest() {
        if (books.isEmpty()) {
            System.out.println("Danh sách sách rỗng, không thể sắp xếp.");
            return;
        }
        int n = books.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (books.get(j).getInterest() < books.get(j + 1).getInterest()) {
                    Book temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }
        System.out.println("Sắp xếp sách theo lợi nhuận giảm dần: ");
        printBooks();
    }

    public static void searchName(Scanner scanner) {
        System.out.println("Nhập tên sách muốn tìm: ");
        String input = scanner.nextLine();
        boolean found = false;
        for (Book book : books) {
            if (book.getBookName().toLowerCase().contains(input.toLowerCase())) {
                book.displayBooks();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sách!");
        }
    }

    public static void displayBookOfYear() {
        ArrayList<Integer> years = new ArrayList<>();
        for (Book book : books) {
            int year = book.getYear();
            if (!years.contains(year)) {
                years.add(year);
            }
        }
        System.out.println("Danh sách thống kê sách theo năm.");
        for (int year : years) {
            int count = 0;
            for (Book book : books) {
                if (book.getYear() == year) {
                    count++;
                }
            }
            System.out.println("Năm " + year + " có " + count + " sách");
        }
    }

    public static void displayBookOfAuthor() {
        ArrayList<String> authors = new ArrayList<>();
        for (Book book : books) {
            String author = book.getAuthor();
            if (!authors.contains(author)) {
                authors.add(author);
            }
        }
        System.out.println("Danh sách thống kê sách theo tác giả.");
        for (String author : authors) {
            int count = 0;
            for (Book book : books) {
                if (book.getAuthor().equals(author)) {
                    count++;
                }
            }
            System.out.println("Tác giả " + author + " có " + count + " sách");
        }
    }

    ;
}
