package com.example.smartrestro;

public class Menu {
    String productId,productPrice,productName,productImage;
    public Menu() {
    }

    public Menu(String productId, String productPrice, String productName, String productImage) {
        this.productId = productId;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productImage = productImage;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
