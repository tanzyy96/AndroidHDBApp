package com.example.androidhdb2.model;

public class Flat {

    private String flatID;
    private String location;
    private String flatSize;
    // private float price;
    //private String priceRange;
    private String housingType;

    public Flat(){
    };

    public Flat(String flatID, String location, String flatSize, String housingType) {
        this.flatID = flatID;
        this.location = location;
        this.flatSize = flatSize;
        this.housingType = housingType;
    }

    public String getLocation() {
        return location;
    }

    public String getFlatSize() {
        return flatSize;
    }

//    public float getPrice() {
//        return price;
//    }

    public String getFlatID() {
        return flatID;
    }

//    public String getPriceRange() {
//        return priceRange;
//    }

    public String getHousingType() {
        return housingType;
    }
}
