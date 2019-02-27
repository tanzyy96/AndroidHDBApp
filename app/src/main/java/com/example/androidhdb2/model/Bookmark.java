package com.example.androidhdb2.model;

public class Bookmark {
    private Flat flat;
//    private String userID;

    public Bookmark(Flat flat) {
        this.flat = flat;
//        this.userID = userID;
    }

    public Flat getFlat() {
        return flat;
    }

//    public String getUserID() {
//        return userID;
//    }
}
