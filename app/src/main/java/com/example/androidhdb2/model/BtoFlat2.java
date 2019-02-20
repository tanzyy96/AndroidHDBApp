package com.example.androidhdb2.model;

// PAST LAUNCHES //

public class BtoFlat2 extends Flat{
    private String image;
    private String region;

    public BtoFlat2(String flatID, String location, String flatSize, String housingType, String image, String region) {
        super(flatID, location, flatSize, housingType);
        this.image = image;
        this.region = region;
    }
}
