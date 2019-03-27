package com.example.androidhdb2.model;

public class ResaleFlat extends Flat {
    private int remainingLease;
    private String storey;
    private float floorArea;
    private int price;
    private String town;

    public ResaleFlat(String flatID, String location, String flatSize, int price, int remainingLease, String storey, float floorArea) {
        super(flatID, location, flatSize);
        this.price = price;
        this.remainingLease = remainingLease;
        this.storey = storey;
        this.floorArea = floorArea;
    }

    public void setTown(String town) { this.town = town; }

    public String getTown() {return town; }

    public int getPrice() {
        return price;
    }

    public int getRemainingLease() {
        return remainingLease;
    }

    public String getStorey() {
        return storey;
    }

    public float getFloorArea() {
        return floorArea;
    }

    @Override
    public String toString() {
        return "ResaleFlat{" +
                "town="+ getTown() +
                "location=" + getLocation() +
                "remainingLease=" + remainingLease +
                ", storey='" + storey + '\'' +
                ", floorArea=" + floorArea +
                ", price=" + price +
                '}';
    }
}
