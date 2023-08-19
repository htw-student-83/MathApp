package com.example.mathapp.login;

public class DB_Daten {
    private int pin;
    private String firstname;
    private String lastname;
    private String mobile_number;

    public DB_Daten(int pin, String firstname, String lastname, String mobile_number){
        this.pin = pin;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile_number = mobile_number;
    }

    public  DB_Daten(){}


    public int getPin() {
        return pin;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMobile_number() {
        return mobile_number;
    }
}
