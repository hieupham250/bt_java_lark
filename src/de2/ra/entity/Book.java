package de2.ra.entity;

import de2.ra.validate.BookValidator;
import de2.ra.validate.StringRule;
import de2.ra.validate.Validator;

import java.util.Scanner;

public class Book implements IApp{
    private String bookId;
    private String bookTitle;
    private String author;
    private String publisher;
    private int publicationYear;
    private String category;
    private double price;
    private int quantity;

    public Book () {}

    public Book (String bookId, String bookTitle, String author, String publisher, int publicationYear, String category, double price, int quantity) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public String getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void inputData(Scanner sc) {
        this.bookId = inputBookId(sc);
        this.bookTitle = inputBookTitle(sc);
        this.author = Validator.validateInputString(sc, "Nhập tác giả", new StringRule(0, 50));
        this.publisher = Validator.validateInputString(sc, "Nhập nhà xuất bản", new StringRule(0, 100));
        this.publicationYear = BookValidator.validateBookPublicationYear(sc, "Nhập năm xuất bản");
        this.category = Validator.validateInputString(sc, "Nhập tác giả", new StringRule(0, 100));
        this.price = Validator.validateInputDouble(sc, "Nhập giá sách", 0);
        this.quantity = BookValidator.validateBookQuantity(sc, "Nhập số lượng");
    }

    @Override
    public void displayData() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("Mã sách: " + bookId);
        System.out.println("Tiêu đề: " + bookTitle);
        System.out.println("Tác giả: " + author);
        System.out.println("Nhà xuất bản: " + publisher);
        System.out.println("Năm xuất bản: " + publicationYear);
        System.out.println("Thể loại: " + category);
        System.out.println("Giá: " + price);
        System.out.println("Số lượng: " + quantity);
    }

    public String inputBookId(Scanner sc) {
        String bookId = BookValidator.validateBookId(sc, "Nhập mã sách");
        return BookValidator.isBookExist(sc, bookId, "bookId");
    }

    public String inputBookTitle(Scanner sc) {
        String bookTitle = Validator.validateInputString(sc, "Nhập tiêu đề sách", new StringRule(0, 100));
        return BookValidator.isBookExist(sc, bookTitle, "bookTitle");
    }
}
