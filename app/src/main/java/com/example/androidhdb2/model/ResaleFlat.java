package com.example.androidhdb2.model;

public class ResaleFlat extends Flat {
    private int remainingLease;
    private int storey;
    private float floorArea;
    private int price;

    public ResaleFlat(String flatID, String location, String flatSize, int price, int remainingLease, int storey, float floorArea) {
        super(flatID, location, flatSize);
        this.price = price;
        this.remainingLease = remainingLease;
        this.storey = storey;
        this.floorArea = floorArea;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRemainingLease() {
        return remainingLease;
    }

    public int getStorey() {
        return storey;
    }

    public float getFloorArea() {
        return floorArea;
    }
}
