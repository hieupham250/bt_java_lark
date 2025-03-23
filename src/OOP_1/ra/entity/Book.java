package OOP_1.ra.entity;

import OOP_1.ra.validate.BookValidator;
import OOP_1.ra.validate.Validator;

import java.util.Scanner;

public class Book {
    private String bookId;
    private String bookName;
    private float importPrice;
    private float exportPrice;
    private String author;
    private float interest;
    private int year;

    public Book() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getInterest() {
        return calculateInterest();
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Book(String bookId, String bookName, String author, float importPrice, float exportPrice, int year) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.interest = calculateInterest();
        this.year = year;
    }

    public float calculateInterest() {
        return exportPrice - importPrice;
    }

    public void addBook(Scanner sc) {
        System.out.println("Nhập vào id sách: ");
        this.bookId = BookValidator.validateBookId(sc);
        System.out.println("Nhập vào tên sách: ");
        String input = Validator.validateString(sc, 1, 1000);
        this.bookName = BookValidator.checkSomeName(sc, input);
        System.out.println("Nhập vào giá nhập sách: ");
        this.importPrice = Validator.validateFloat(sc, 0);
        System.out.println("Nhập vào giá bán sách: ");
        this.exportPrice = Validator.validateFloat(sc, importPrice * 1.3f);
        System.out.println("Nhập vào tác giả sách: ");
        this.author = Validator.validateString(sc, 6, 50);
        System.out.println("Nhập vào năm sản xuất: ");
        this.year = BookValidator.validateBookYear(sc, 2000);
    }

    public void displayBooks() {
        System.out.println("-------------------------");
        System.out.println("Mã sách: " + bookId);
        System.out.println("tên sách: " + bookName);
        System.out.println("Giá nhập: " + importPrice);
        System.out.println("Giá bán: " + exportPrice);
        System.out.println("Tác giả: " + author);
        System.out.println("Lợi nhuận: " + interest);
        System.out.println("năm: " + year);
    }
}