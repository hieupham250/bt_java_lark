package de1.ra.bussiness;

import de1.ra.entity.Student;
import de1.ra.presentation.StudentApplication;
import de1.ra.validate.ByteRange;
import de1.ra.validate.FloatRange;
import de1.ra.validate.StudentValidator;
import de1.ra.validate.Validator;

import java.util.Scanner;

public class StudentBusiness {
    public static void displayListStudents() {
        if (StudentApplication.currentStudentIndex == 0) {
            System.out.println("Danh sách sinh viên trống");
            return;
        }
        for (int i = 0; i < StudentApplication.currentStudentIndex; i++) {
            StudentApplication.arrStudents[i].displayData();
        }
    }

    public static void addStudent(Scanner sc) {
        int numberOfStudents = Validator.validateInputInt(sc, "Nhập số học sinh cần nhập thông tin");
        if(numberOfStudents + StudentApplication.currentStudentIndex > StudentApplication.arrStudents.length) {
            System.out.println("Danh sách sản phẩm đầy");
            return;
        }
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("Nhập thông tin cho sinh viên thứ " + (i + 1) + ":");
            StudentApplication.arrStudents[StudentApplication.currentStudentIndex] = new Student();
            StudentApplication.arrStudents[StudentApplication.currentStudentIndex].inputData(sc);
            StudentApplication.currentStudentIndex++;
        }
        System.out.println("Đã thêm " + numberOfStudents + " sinh viên vào danh sách.");
    }

    public static int findIndexById(String studentId) {
        for (int i = 0; i < StudentApplication.currentStudentIndex; i++) {
            if (StudentApplication.arrStudents[i].getStudentId().equals(studentId)) {
                return i;
            }
        }
        return -1;
    }

    public static void updateStudent(Scanner sc) {
        String studentId = Validator.validateInputString(sc, "Nhập vào mã sinh viên cần cập nhật");
        int indexUpdate = findIndexById(studentId);
        if (indexUpdate == -1) {
            System.out.println("Không tìm thấy sinh viên");
            return;
        }
        StudentApplication.arrStudents[indexUpdate].displayData();
        do {
            System.out.println("1. Cập nhật tên sinh viên");
            System.out.println("2. Cập nhật ngày sinh");
            System.out.println("3. Cập nhật giới tính");
            System.out.println("4. Cập nhật số điện thoại");
            System.out.println("5. Cập nhật tên lớp");
            System.out.println("6. Cập nhật gpa");
            System.out.println("7. Cập nhật trạng thái");
            System.out.println("8. Thoát");
            int choice = Validator.validateInputInt(sc, "Lựa chọn của bạn");
            switch (choice) {
                case 1:
                    StudentApplication.arrStudents[indexUpdate].setStudentName(Validator.validateInputString(sc, "Nhập tên sinh viên mới"));
                    System.out.println("Cập nhật thành công");
                    break;
                case 2:
                    StudentApplication.arrStudents[indexUpdate].setBirthday(StudentValidator.validateBirthday(sc, "Nhập ngày sinh mới"));
                    System.out.println("Cập nhật thành công");
                    break;
                case 3:
                    StudentApplication.arrStudents[indexUpdate].setSex(Validator.validateInputBoolean(sc, "Nhập giới tính mới (true - Nam , false - Nữ)"));
                    System.out.println("Cập nhật thành công");
                    break;
                case 4:
                    StudentApplication.arrStudents[indexUpdate].setPhoneNumber(StudentValidator.validatePhoneNumber(sc, "Nhập số điện thoại mới"));
                    System.out.println("Cập nhật thành công");
                    break;
                case 5:
                    StudentApplication.arrStudents[indexUpdate].setClassName(Validator.validateInputString(sc, "Nhập tên lớp mới"));
                    System.out.println("Cập nhật thành công");
                    break;
                case 6:
                    StudentApplication.arrStudents[indexUpdate].setGpa(Validator.validateInputFloat(sc, "Nhập gpa mới", new FloatRange(0, 10)));
                    System.out.println("Cập nhật thành công");
                    break;
                case 7:
                    StudentApplication.arrStudents[indexUpdate].setStatus(Validator.validateInputByte(sc, "Nhập trạng thái mới (1: Đang học, 2: Bảo lưu, 3: Đã nghỉ):", new ByteRange((byte) 1, (byte) 3)));
                    System.out.println("Cập nhật thành công");
                    break;
                case 8:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1-7");
            }
        } while (true);
    }

    public static void deleteStudent(Scanner sc) {
        String studentId = Validator.validateInputString(sc, "Nhập mã sinh viên cần xóa");
        int indexDelete = findIndexById(studentId);
        if (indexDelete == -1) {
            System.err.println("Không tìm thấy sinh viên");
            return;
        }
        for (int i = indexDelete; i < StudentApplication.currentStudentIndex; i++) {
            StudentApplication.arrStudents[i] = StudentApplication.arrStudents[i + 1];
        }
        StudentApplication.currentStudentIndex--;
        System.out.println("Xóa sinh viên thành công");
    }

    public static void searchStudent(Scanner sc) {
        do {
            System.out.println("1. Tìm kiếm theo tên sinh viên");
            System.out.println("2. Tìm kiếm theo lớp học");
            System.out.println("3. Tìm kiếm theo khoảng điểm tích lũy");
            System.out.println("4. Thoát");
            int choice = Validator.validateInputInt(sc, "Lựa chọn của bạn");
            boolean found = false;
            switch (choice) {
                case 1:
                    searchByName(sc);
                    break;
                case 2:
                    searchByClass(sc);
                    break;
                case 3:
                    searchByGpa(sc);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Vui lòng chọn từ 1-4");
            }
        } while (true);
    }

    private static void searchByName(Scanner sc) {
        String searchStudentName = Validator.validateInputString(sc, "Nhập tên sinh viên:");
        boolean found = false;

        for (int i = 0; i < StudentApplication.currentStudentIndex; i++) {
            if (StudentApplication.arrStudents[i].getStudentName().equalsIgnoreCase(searchStudentName)) {
                found = true;
                StudentApplication.arrStudents[i].displayData();
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy kết quả phù hợp.");
        }
    }

    private static void searchByClass(Scanner sc) {
        String searchClassName = Validator.validateInputString(sc, "Nhập tên lớp học:");
        boolean found = false;

        for (int i = 0; i < StudentApplication.currentStudentIndex; i++) {
            if (StudentApplication.arrStudents[i].getClassName().equalsIgnoreCase(searchClassName)) {
                found = true;
                StudentApplication.arrStudents[i].displayData();
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy kết quả phù hợp.");
        }
    }

    private static void searchByGpa(Scanner sc) {
        float minGpa = Validator.validateInputFloat(sc, "Nhập điểm GPA tối thiểu:", new FloatRange(0, 10));
        float maxGpa = Validator.validateInputFloat(sc, "Nhập điểm GPA tối đa:", new FloatRange(minGpa, 10));
        boolean found = false;

        for (int i = 0; i < StudentApplication.currentStudentIndex; i++) {
            float gpa = StudentApplication.arrStudents[i].getGpa();
            if (gpa >= minGpa && gpa <= maxGpa) {
                found = true;
                StudentApplication.arrStudents[i].displayData();
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy kết quả phù hợp.");
        }
    }

    public static void sortStudents(Scanner sc) {
        System.out.println("1. Sắp xếp theo tên (A-Z)");
        System.out.println("2. Sắp xếp theo tên (Z-A)");
        System.out.println("3. Sắp xếp theo GPA tăng dần");
        System.out.println("4. Sắp xếp theo GPA giảm dần");
        int choice = Validator.validateInputInt(sc, "Lựa chọn của bạn");

        switch (choice) {
            case 1:
                bubbleSortByName(true);
                break;
            case 2:
                bubbleSortByName(false);
                break;
            case 3:
                bubbleSortByGpa(true);
                break;
            case 4:
                bubbleSortByGpa(false);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ");
                return;
        }
        displayListStudents();
    }

    private static void bubbleSortByName(boolean ascending) {
        for (int i = 0; i < StudentApplication.currentStudentIndex - 1; i++) {
            for (int j = 0; j < StudentApplication.currentStudentIndex - i - 1; j++) {
                if ((ascending && StudentApplication.arrStudents[j].getStudentName().compareToIgnoreCase(StudentApplication.arrStudents[j + 1].getStudentName()) > 0) ||
                        (!ascending && StudentApplication.arrStudents[j].getStudentName().compareToIgnoreCase(StudentApplication.arrStudents[j + 1].getStudentName()) < 0)) {
                    Student temp = StudentApplication.arrStudents[j];
                    StudentApplication.arrStudents[j] = StudentApplication.arrStudents[j + 1];
                    StudentApplication.arrStudents[j + 1] = temp;
                }
            }
        }
    }

    private static void bubbleSortByGpa(boolean ascending) {
        for (int i = 0; i < StudentApplication.currentStudentIndex - 1; i++) {
            for (int j = 0; j < StudentApplication.currentStudentIndex - i - 1; j++) {
                if ((ascending && StudentApplication.arrStudents[j].getGpa() > StudentApplication.arrStudents[j + 1].getGpa()) ||
                        (!ascending && StudentApplication.arrStudents[j].getGpa() < StudentApplication.arrStudents[j + 1].getGpa())) {
                    Student temp = StudentApplication.arrStudents[j];
                    StudentApplication.arrStudents[j] = StudentApplication.arrStudents[j + 1];
                    StudentApplication.arrStudents[j + 1] = temp;
                }
            }
        }
    }
}
