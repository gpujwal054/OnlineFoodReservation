package com.example.smartrestro;

public class OnlineBook {
    public String name,address,contact, checkIn_date,checkOut_date,checkIn_time,checkOut_time,no_of_people;

    public OnlineBook(String name, String address, String contact, String checkIn_date,String checkOut_date, String checkIn_time, String checkOut_time, String no_of_people){
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.checkIn_date = checkIn_date;
        this.checkOut_date = checkOut_date;
        this.checkIn_time = checkIn_time;
        this.checkOut_time = checkOut_time;
        this.no_of_people = no_of_people;
    }
}
