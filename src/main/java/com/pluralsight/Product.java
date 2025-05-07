package com.pluralsight;

//Attributes
public class Product {
    private String id;
    private String name;
    private double price;
    //Constructor
    public Product(String name, String id, double price) {
        this.name = name;
        this.id = id;
        this.price = price;

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
}
