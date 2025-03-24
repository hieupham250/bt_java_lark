package OOP_2.ra.imp;

import OOP_2.ra.entity.Product;
import OOP_2.ra.validate.Validator;

import java.util.Scanner;

public class ProductImp {
    public static Product[] products = new Product[100];
    public static int currentProductIndex = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("----------------------------MENU----------------------------");
            System.out.println("1. Nhập thông tin n sản phẩm (n nhập từ bàn phím)");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Tính lợi nhuận các sản phẩm");
            System.out.println("4. Sắp xếp các sản phẩm theo lợi nhuận giảm dần");
            System.out.println("5. Thống kê các sản phẩm theo giá");
            System.out.println("6. Tìm các sản phẩm theo tên sản phẩm");
            System.out.println("7. Nhập sản phẩm");
            System.out.println("8. Bán sản phẩm");
            System.out.println("9. Cập nhật trạng thái sản phẩm");
            System.out.println("10. Thoát");
            System.out.print("Lựa chọn của bạn: ");
            int choice = Validator.validateInt(sc, 0);
            switch (choice) {
                case 1:
                    addProduct(sc);
                    break;
                case 2:
                    displayProducts();
                    break;
                case 3:
                    calculateProductInterest();
                    break;
                case 4:
                    orderByInterest();
                    break;
                case 5:
                    displayProducOfPrice(sc);
                    break;
                case 6:
                    searchName(sc);
                    break;
                case 7:
                    addQuantity(sc);
                    break;
                case 8:
                    exportProduct(sc);
                    break;
                case 9:
                    updateStatus(sc);
                    break;
                case 10:
                    System.out.println("Kết thúc chuơng trình");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Vui lòng chọn từ 1-10");
            }
        } while (true);
    }

    public static void addProduct(Scanner sc) {
        System.out.println("Nhập vào n sản phẩm muốn thêm: ");
        int n = Validator.validateInt(sc, 1);
        if (n + currentProductIndex > products.length) {
            System.out.println("Danh sách sản phẩm đầy");
            return;
        }
        for (int i = 0; i < n; i++) {
            products[currentProductIndex] = new Product();
            products[currentProductIndex].inputData(sc, products);
            currentProductIndex++;
        }
    }

    public static void displayProducts() {
        if (currentProductIndex == 0) {
            System.out.println("danh sách rỗng");
            return;
        }
        for (int i = 0; i < currentProductIndex; i++) {
            products[i].displayData();
        }
    }

    public static void calculateProductInterest() {
        if (currentProductIndex == 0) {
            System.out.println("Danh sách rỗng");
            return;
        }
        for (int i = 0; i < currentProductIndex; i++) {
            System.out.println("Sản phẩm " + products[i].getProductName() + " có doanh thu là: " + products[i].getProfit());
        }
    }


    public static void orderByInterest() {
        int n = currentProductIndex;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (products[j].getProfit() < products[j + 1].getProfit()) {
                    Product temp = products[j];
                    products[j] = products[j + 1];
                    products[j + 1] = temp;
                }
            }
        }
        System.out.println("Sắp xếp sách theo lợi nhuận giảm dần: ");
        displayProducts();
    }

    public static void displayProducOfPrice(Scanner sc) {
        System.out.println("Nhập số tiền bắt đầu tìm: ");
        int startNumber = Validator.validateInt(sc, 0);
        System.out.println("Nhập số tiền kết thúc tìm: ");
        int endNumber = Validator.validateInt(sc, startNumber);
        boolean flag = false;
        for (int i = 0; i < currentProductIndex; i++) {
            if (products[i] != null && products[i].getExportPrice() >= startNumber && products[i].getExportPrice() <= endNumber) {
                flag = true;
                products[i].displayData();
            }
        }
        if (!flag) {
            System.out.println("Không tìm thấy giá muốn tìm");
        }
    }

    public static void searchName(Scanner sc) {
        System.out.println("Nhập tên sản phẩm muốn tìm: ");
        String name = Validator.validateString(sc, 1, 10000);
        boolean flag = false;
        for (int i = 0; i < currentProductIndex; i++) {
            if (products[i] != null && products[i].getProductName().toLowerCase().contains(name.toLowerCase().trim())) {
                flag = true;
                products[i].displayData();
            }
        }
        if (!flag) {
            System.out.println("Không tìm thấy tên muốn tìm");
        }
    }

    public static int FindIndex(String id) {
        for (int i = 0; i < currentProductIndex; i++) {
            if (products[i].getProductid().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public static void addQuantity(Scanner sc) {
        System.out.println("nhập mã sản phẩm: ");
        String productId = Validator.validateString(sc, 1, 10);
        int index = FindIndex(productId);
        if (index == -1) {
            System.out.println("không tìm thấy mã sản phẩm");
            return;
        }
        System.out.println("nhập số lượng muốn thêm: ");
        int quantity = Validator.validateInt(sc, 1);
        products[index].setQuantity(quantity + products[index].getQuantity());
        products[index].displayData();
    }

    public static void exportProduct(Scanner sc) {
        System.out.println("nhập vào mã sản phẩm muốn bán: ");
        String productId = Validator.validateString(sc, 1, 10);
        int index = FindIndex(productId);
        if (index == -1) {
            System.out.println("không tìm thấy mã sản phẩm");
            return;
        }
        System.out.println("nhập số lượng muốn bán: ");
        int quantity = Validator.validateInt(sc, 1);
        if (products[index].getQuantity() < quantity) {
            System.out.println("Số lượng trong kho không đủ");
            return;
        }
        products[index].setQuantity(products[index].getQuantity() - quantity);
        products[index].displayData();
    }

    public static void updateStatus(Scanner sc) {
        System.out.println("nhập vào mã sản phẩm muốn đổi trạng thái: ");
        String productId = Validator.validateString(sc, 1, 10);
        int index = FindIndex(productId);
        if(index == -1) {
            System.out.println("không tìm thấy mã sản phẩm");
            return;
        }
        products[index].setStatus(!products[index].isStatus());
        System.out.println("Cập nhật trạng thái thành công");
    }
}
