package com.git.kysersozelee.part4;

import com.git.kysersozelee.Country;
import lombok.*;

import java.util.UUID;

interface Human{
    String getFirstName();
    String getLastName();
    String setFirstName(String firstName);
    String setLastName(String lastName);
    String getIdentifyNumber();
}

public class Customer implements Human{
    private String firstName;
    private String lastName;
    private String identifyNumber;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identifyNumber = UUID.randomUUID().toString();
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String setFirstName(String firstName) {
        this.firstName = firstName;
        return firstName;
    }

    @Override
    public String setLastName(String lastName) {
        this.lastName = lastName;
        return this.lastName;
    }

    @Override
    public String getIdentifyNumber() {
        return this.identifyNumber;
    }
}
