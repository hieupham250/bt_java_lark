package OOP_2.ra.entity;

import OOP_2.ra.validate.Validator;

import java.util.Scanner;

public class Product {
    private String productid;
    private String productName;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;
    private String description;
    private boolean status;

    public Product() {}

    public Product(String productid, String productName, float importPrice, float exportPrice, int quantity, String description, boolean status) {
        this.productid = productid;
        this.productName = productName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.description = description;
        this.quantity = quantity;
        this.status = status;
        this.profit = calProfit();
    }

    public String getProductid() {
        return productid;
    }

    public String getProductName() {
        return productName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public float getProfit() {
        return profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public String getDescription() {
        return description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float calProfit() {
        return exportPrice - importPrice;
    }

    public void inputData(Scanner sc, Product[] arrProduct) {
        System.out.println("Nhập mã sản phẩm: ");
        while (true) {
            String productId = Validator.validateString(sc, 4, 4);
            if (productId.isEmpty()) {
                System.out.println("ID không hợp lệ, vui lòng nhập lại!");
                continue;
            }
            boolean isDuplicate = false;
            for (Product product : arrProduct) {
                if (product != null && product.getProductid() != null && product.getProductid().equals(productId)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                this.productid = productId;
                break;
            }
            System.out.println("mã sản phẩm đã tồn tại, nhập lại!");
        }

        System.out.println("Nhập tên sản phẩm (6-50 ký tự): ");
        while (true) {
            String productName = Validator.validateString(sc, 6, 50);
            if (productName.isEmpty()) {
                System.out.println("Tên sản phẩm không hợp lệ, vui lòng nhập lại!");
                continue;
            }
            boolean isDuplicate = false;
            for (Product p : arrProduct) {
                if (p != null && p.getProductName() != null && p.getProductName().equalsIgnoreCase(productName)) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                this.productName = productName;
                break;
            }
            System.out.println("Tên sản phẩm đã tồn tại, nhập lại!");
        }

        System.out.println("Nhập giá nhập: ");
        this.importPrice = Validator.validateFloat(sc, 0);

        System.out.println("Nhập giá xuất (ít nhất 20% lớn hơn giá nhập): ");
        while (true) {
            this.exportPrice = Validator.validateFloat(sc, importPrice * 1.2f);
            if (exportPrice >= importPrice * 1.2f) {
                break;
            }
            System.out.println("Giá xuất phải lớn hơn ít nhất 20% so với giá nhập, nhập lại: ");
        }

        this.profit = calProfit();

        System.out.println("Nhập số lượng: ");
        this.quantity = Validator.validateInt(sc, 1);

        System.out.println("Nhập mô tả sản phẩm: ");
        this.description = sc.nextLine();

        System.out.println("Nhập trạng thái sản phẩm (true - Đang bán, false - Không bán): ");
        while (true) {
            String statusInput = sc.nextLine().toLowerCase();
            if (statusInput.equals("true")) {
                this.status = true;
                break;
            } else if (statusInput.equals("false")) {
                this.status = false;
                break;
            }
            System.out.println("Chỉ được nhập 'true' hoặc 'false', nhập lại:");
        }
    }

    public void displayData() {
        System.out.println("--------------------------------");
        System.out.println("Mã sách: " + productid);
        System.out.println("Tên sách: " + productName);
        System.out.println("Giá nhập: " + importPrice);
        System.out.println("Giá bán: " + exportPrice);
        System.out.println("Lợi nhuận: " + profit);
        System.out.println("Số lượng: " + quantity);
        System.out.println("Mô tả: " + description);
        System.out.println("Trạng thái: " + (status ? "Đang bán" : "Không bán"));
    }
}
