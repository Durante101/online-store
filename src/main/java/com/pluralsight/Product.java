package com.pluralsight;

import java.util.ArrayList;

//Attributes
public class Product {
    private String id;
    private String name;
    private double price;
    //Constructor
    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;

    }
    public Product(String id, String name) {
        this.name = name;
        this.id = id;

    }

    //Getters Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {

        return String.format("|-%s-|-%s-|-%.2f-|", id, name, price);
    }
}
