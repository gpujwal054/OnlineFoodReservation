package com.example.smartrestro;


public class User {
    public String name, email, address, contact,url;

    public User(String name,String email,String address, String contact,String url){
        this.name = name;
        this.email = email;
        this.address = address;
        this.contact = contact;
        this.url= url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
