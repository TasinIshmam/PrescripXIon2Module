package com.prescripxion.www.prescripxion2module;

/**
 * Created by Tasin Ishmam on 12/1/2017.
 */
public class Medicine {

    private String Name;
    private String Manufacturer;
    private int Price;


    public void setName(String name) {
        Name = name;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getName() {

        return Name;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public int getPrice() {
        return Price;
    }

    public Medicine(String name, String manufacturer, int price) {

        Name = name;
        Manufacturer = manufacturer;
        Price = price;
    }
}
