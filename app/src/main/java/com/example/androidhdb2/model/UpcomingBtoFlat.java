package com.example.androidhdb2.model;

// NEW LAUNCHES //

public class UpcomingBtoFlat extends Flat {
    private String image;


    public UpcomingBtoFlat(String flatID, String loc, String flatSize, String image) {
        super(flatID, loc, flatSize);
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
