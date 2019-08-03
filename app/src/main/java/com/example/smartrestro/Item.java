package com.example.smartrestro;

public class Item {
    public String ItemId;
    public String ItemName;
    public String Status;
    public String Quantity;
    public String Rate;

    public Item() {
    }

    public Item(String itemId, String itemName, String status, String quantity, String rate) {
        ItemId = itemId;
        ItemName = itemName;
        Status = status;
        Quantity = quantity;
        Rate = rate;
    }

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getJsonObject(){
        return "{ItemId:"+ItemId+",ItemName:"+ItemName+",Rate:"+Rate+"}";
    }
}
