package com.example.oroni.cambioclient.model;

public class User {
    int customer_id;
    String email;
    String password;

    public User(int customer_id, String email, String password) {
        this.customer_id = customer_id;
        this.email = email;
        this.password = password;
    }



    public int getCustomer_id() {
        return customer_id;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
