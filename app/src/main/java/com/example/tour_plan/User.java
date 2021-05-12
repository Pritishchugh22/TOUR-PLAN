package com.example.tour_plan;

import java.util.DoubleSummaryStatistics;

public class User {
    public String fullName, email, country, phonenumber;

    public User(){

    }

    public User(String fullName, String email, String phonenb,String country ){
        this.fullName=fullName;
        this.email=email;
        this.country= country;
        this.phonenumber= phonenb;
    }


}
