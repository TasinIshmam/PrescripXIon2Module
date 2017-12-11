package com.prescripxion.www.prescripxion2module;

/**
 * Created by Tasin Ishmam on 12/11/2017.
 */

public class Medicine {
    public String Name;
   public String Details;
   public Double Price;

    public Medicine(String name, String details, Double price) {
        Name = name;
        Details = details;
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }
}
