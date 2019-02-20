package com.example.androidhdb2.model;

// NEW LAUNCHES //

public class BtoFlat extends Flat {
    private String image;

    private String flatID;
    private String location;
    private String flatSize;
    // private float price;
    //private String priceRange;
    private String housingType;

    public BtoFlat(String flatID, String loc, String flatSize, String housingType, String image) {
        super(flatID, loc, flatSize, housingType);
        this.image = image;
    }
}
