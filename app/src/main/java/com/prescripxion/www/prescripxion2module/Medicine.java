package com.prescripxion.www.prescripxion2module;

/**
 * Created by Tasin Ishmam on 12/1/2017.
 */
public class Medicine {

    private String Name;
    private double Price;


    public void setName(String name) {
        Name = name;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getName() {

        return Name;
    }

    public double getPrice() {
        return Price;
    }

    public Medicine(String name, double price) {

        Name = name;

        Price = price;
    }
}
