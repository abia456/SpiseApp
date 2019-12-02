package com.example.spiseapp3;

import java.util.ArrayList;

public class Customer {
    public String FirstName;
    public String LastName;
    public String email;
    public String phone;
    public String address;

    public Customer(String firstName, String lastName, String email, String phone, String address) {



        FirstName = firstName;
        LastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Customer() {
    }
}

