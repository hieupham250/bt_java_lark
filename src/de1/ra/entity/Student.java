package de1.ra.entity;

import de1.ra.validate.ByteRange;
import de1.ra.validate.FloatRange;
import de1.ra.validate.StudentValidator;
import de1.ra.validate.Validator;

import java.util.Scanner;

public class Student implements IApp {
    private String studentId;
    private String studentName;
    private String birthday;
    private String phoneNumber;
    private boolean sex;
    private String email;
    private String major;
    private String className;
    private float gpa;
    private byte status  = 1;

    public Student() {}

    public Student(String studentId, String studentName, String birthday, String phoneNumber, boolean sex, String email, String major, String className, float gpa, byte status) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
        this.email = email;
        this.major = major;
        this.className = className;
        this.gpa = gpa;
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isSex() {
        return sex;
    }

    public String getEmail() {
        return email;
    }

    public String getMajor() {
        return major;
    }

    public String getClassName() {
        return className;
    }

    public float getGpa() {
        return gpa;
    }

    public byte getStatus() {
        return status;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public void inputData(Scanner sc) {
        this.studentId = inputStudentId(sc);
        this.studentName = Validator.validateInputString(sc, "Nhập tên sinh viên");
        this.birthday = StudentValidator.validateBirthday(sc, "Nhập ngày sinh");
        this.phoneNumber = StudentValidator.validatePhoneNumber(sc, "Nhập số điện thoại");
        this.sex = Validator.validateInputBoolean(sc, "Nhập giới tính (true - Nam , false - Nữ)");
        this.email = StudentValidator.validateEmail(sc, "Nhập email");
        this.major = Validator.validateInputString(sc, "Ngành học");
        this.className = Validator.validateInputString(sc, "tên lớp học");
        this.gpa = Validator.validateInputFloat(sc, "Nhập điểm gpa", new FloatRange(0, 10));
        this.status = Validator.validateInputByte(sc, "Nhập trạng thái (1- đang theo học, 2 - bảo lưu, 3 - đã nghỉ học)",new ByteRange((byte) 1, (byte) 3));
    }

    @Override
    public void displayData() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("Mã sinh viên: " + studentId);
        System.out.println("Tên sinh viên: " + studentName);
        System.out.println("Ngày sinh: " + birthday);
        System.out.println("Giới tính: " + (sex ? "Nam" : "Nữ"));
        System.out.println("Số điện thoại: " + phoneNumber);
        System.out.println("Tên lớp: " + className);
        System.out.println("Trạng thái: " + (status == 1 ? "Đang theo học" : status == 2 ? "Bảo lưu" : "Đã nghỉ học"));
    }

    public String inputStudentId(Scanner sc) {
        String studentId = StudentValidator.validateStudentId(sc, "Nhập mã sinh viên");
        return StudentValidator.isStudentExist(sc, studentId);
    }
}
