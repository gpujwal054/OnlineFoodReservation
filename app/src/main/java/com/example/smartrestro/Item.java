package com.example.smartrestro;

import android.net.Uri;

public class Item {
    String item_name,image,price;

    public Item() {
    }

    public Item(String item_name, String image, String price) {
        this.item_name = item_name;
        this.image = image;
        this.price = price;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
