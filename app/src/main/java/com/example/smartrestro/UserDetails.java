package com.example.smartrestro;

public class UserDetails {
    String userId,userName,userAddress,userPhone;

    public UserDetails() {
    }

    public UserDetails(String userId, String userName, String userAddress, String userPhone) {
        this.userId = userId;
        this.userName = userName;
        this.userAddress = userAddress;
        this.userPhone = userPhone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
