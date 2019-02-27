package com.example.androidhdb2.model;

public class Flat {

    private String flatID;
    private String location;
    private String flatSize;

    public Flat(){
    };

    public Flat(String flatID, String location, String flatSize) {
        this.flatID = flatID;
        this.location = location;
        this.flatSize = flatSize;
    }

    public String getLocation() {
        return location;
    }

    public String getFlatSize() {
        return flatSize;
    }

    public String getFlatID() {
        return flatID;
    }


}
