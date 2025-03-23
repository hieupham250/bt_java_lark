package de1.ra.presentation;

import de1.ra.bussiness.StudentBusiness;
import de1.ra.entity.Student;
import de1.ra.validate.Validator;

import java.util.Scanner;

public class StudentApplication {
    public static final Student[] arrStudents = new Student[100];
    public static int currentStudentIndex = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        displayStudentMenu(sc);
    }

    public static void displayStudentMenu(Scanner sc) {
        do {
            System.out.println("----------------------------Student Menu----------------------------");
            System.out.println("1. Hiển thị danh sách sinh viên");
            System.out.println("2. Thêm mới sinh viên");
            System.out.println("3. Chỉnh sửa thông tin sinh viên");
            System.out.println("4. Xóa sinh viên");
            System.out.println("5. Tìm kiếm sinh viên");
            System.out.println("6. Sắp xếp");
            System.out.println("0. Thoát chương trình");
            int choice = Validator.validateInputInt(sc, "Lựa chọn của bạn");
            switch (choice) {
                case 0:
                    System.out.println("Kết thúc chương trình");
                    sc.close();
                    System.exit(0);
                case 1:
                    StudentBusiness.displayListStudents();
                    break;
                case 2:
                    StudentBusiness.addStudent(sc);
                    break;
                case 3:
                    StudentBusiness.updateStudent(sc);
                    break;
                case 4:
                    StudentBusiness.deleteStudent(sc);
                    break;
                case 5:
                    StudentBusiness.searchStudent(sc);
                    break;
                case 6:
                    StudentBusiness.sortStudents(sc);
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 0-6");
            }
        } while (true);
    }
}
