package com.example.androidhdb2.model;

// PAST LAUNCHES //

public class PastBtoFlat extends Flat{
    private String image;
    private int price;
    private String region;

    public PastBtoFlat(String flatID, String location, String flatSize, int price, String image, String region) {
        super(flatID, location, flatSize);
        this.price = price;
        this.image = image;
        this.region = region;
    }

    public String getImage() {
        return image;
    }

    public int getPrice() {
        return price;
    }

    public String getRegion() {
        return region;
    }
}
