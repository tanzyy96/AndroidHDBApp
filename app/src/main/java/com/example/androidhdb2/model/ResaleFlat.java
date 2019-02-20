package com.example.androidhdb2.model;

public class ResaleFlat extends Flat {
    private int remainingLease;
    private int storey;
    private float floorArea;

    public ResaleFlat(String flatID, String location, String flatSize, String housingType, int remainingLease, int storey, float floorArea) {
        super(flatID, location, flatSize, housingType);
        this.remainingLease = remainingLease;
        this.storey = storey;
        this.floorArea = floorArea;
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
