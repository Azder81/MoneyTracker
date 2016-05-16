package com.example.agogolev.moneytracker.models;

/**
 * Created by AGogolev on 21.04.2016.
 */
public class Expense {
    public String description;
    public String price;

    public Expense(String description, String price){
        this.description = description;
        this.price = price;

    }
}
