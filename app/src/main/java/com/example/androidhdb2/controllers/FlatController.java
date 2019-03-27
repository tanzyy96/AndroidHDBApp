package com.example.androidhdb2.controllers;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.androidhdb2.model.PastBtoFlat;
import com.example.androidhdb2.model.ResaleFlat;
import com.example.androidhdb2.model.SBFlat;
import com.example.androidhdb2.model.UpcomingBtoFlat;
import com.example.androidhdb2.utils.ResaleAPI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlatController {
    private String TAG = "FLATCONTROLLER";

//
    public List<SBFlat> getSBF(List<SBFlat> flatArrayList, ArrayList results) {
        String TAG = "Flat Controller";
        String flatType;
        String[] priceRange = new String[2];
        String[] flatSupplyRange = new String[2];
        String ethnicGroup="";
        String[] ethnicGroupQuota = new String[2];

        flatType = (String) results.get(0);
        if (results.get(1) != null) {
            priceRange = (String[]) results.get(1);
            Log.d(TAG, priceRange[0]);
        }
        if (results.get(2) != null)
            flatSupplyRange = (String[]) results.get(2);
        if (results.get(3)!=null) {
            ethnicGroup = (String) results.get(3);
            ethnicGroupQuota = (String[]) results.get(4);
        }
        Log.d(TAG, flatType);

        String finalethnicGroup = ethnicGroup;
        String[] finalPriceRange = priceRange;
        Log.d(TAG, "fPR"+finalPriceRange);
        String[] finalFlatSupplyRange = flatSupplyRange;
        String[] finalEthnicGroupQuota = ethnicGroupQuota;

        for (SBFlat flat : flatArrayList) {
            if (!compare_FT(flat, flatType))
                flatArrayList.remove(flat);
            if (finalPriceRange[0] != null) {
                if (!compare_PR(flat, Integer.parseInt(finalPriceRange[0]), Integer.parseInt(finalPriceRange[1])))
                    flatArrayList.remove(flat);
            }
            if (finalFlatSupplyRange[0] != null && !compare_SR(flat, Integer.parseInt(finalFlatSupplyRange[0]), Integer.parseInt(finalFlatSupplyRange[1]))) {
                flatArrayList.remove(flat);
            }
            if (finalethnicGroup != "" && !compare_EG(flat, finalethnicGroup, Integer.parseInt(finalEthnicGroupQuota[0]), Integer.parseInt(finalEthnicGroupQuota[1]))) {
                flatArrayList.remove(flat);
            }
        }
        return flatArrayList;
    }
//
    public ArrayList<ResaleFlat> getResale(String[] flatList, String[] priceList, String[] leaseList, String[] storeyList, String[] areaList,
                                           String flatType, String priceRange, String remainingLeaseRange, String storeyRange, String floorAreaRange) {
//        ResaleAPI api = new ResaleAPI(flatList, priceList, leaseList, storeyList, areaList);
        ArrayList<ResaleFlat> resaleFlatArrayList = ResaleAPI.requestData(flatType, "2019");
        Log.d("Flatcontroller", String.valueOf(resaleFlatArrayList));
        return filterResaleFlats(resaleFlatArrayList, flatList, priceList, leaseList, storeyList, areaList,
                flatType, priceRange, remainingLeaseRange, storeyRange, floorAreaRange);
    }

    public ArrayList<ResaleFlat> filterResaleRegion(ArrayList<ResaleFlat> flatArrayList, String region) {
        Log.d("filterResaleRegion", region);
        ArrayList<ResaleFlat> relevantflats = new ArrayList<ResaleFlat>();
        for (ResaleFlat flat: flatArrayList) {
            if (flat.getTown().contains(region)) {
                relevantflats.add(flat);
            }
        }
        return relevantflats;
    }


    private boolean compare_FT(SBFlat flat, String ft) {
        if (flat.getFlatSize().contains(ft))
            return true;
        return false;
    }

    private boolean compare_SR(SBFlat flat, int min, int max) {
        if (flat.getFlatSupply() > max || flat.getFlatSupply()<min) {
            return false;
        } return true;
    }

    private boolean compare_PR(SBFlat flat, int min, int max) {
        if (flat.getPrice() > max || flat.getPrice() < min)
            return false;
        return true;
    }

    private boolean compare_EG(SBFlat flat, String ethnicGroup, int min, int max) {
        if (flat.getEthnicQuota(ethnicGroup) < min || flat.getEthnicQuota(ethnicGroup) > max)
            return false;
        return true;
    }

    private ArrayList<ResaleFlat> filterResaleFlats(ArrayList<ResaleFlat> flats,
                                                   String[] database_Flat_Type,
                                                   String[] database_Selling_Price_range,
                                                   String[] database_Remaining_Lease_range,
                                                   String[] database_Storey_range,
                                                   String[] database_Floor_Area_range,
                                                   String flat_type,
                                                   String sellingprice,
                                                   String lease_range,
                                                   String storey_range,
                                                   String floor_area_range) {

        String [] Database_Flat_Type = database_Flat_Type;
        String [] Database_Floor_Area_range = database_Floor_Area_range;
        String []Database_Remaining_Lease_range = database_Remaining_Lease_range;
        String [] Database_Selling_Price_range = database_Selling_Price_range;
        String [] Database_Storey_range =  database_Storey_range;

        ArrayList<ResaleFlat> relevantFlats = new ArrayList<ResaleFlat>();
        Log.d("filterflats", flat_type + ' ' + sellingprice + ' ' + lease_range);
        for (int i=0; i<flats.size(); i++) {
            Log.d("RelevantFlats", String.valueOf(compare_type(flats.get(i), flat_type))+ String.valueOf(compare_price(flats.get(i), sellingprice, database_Selling_Price_range))+String.valueOf(compare_lease(flats.get(i), lease_range, database_Remaining_Lease_range)));
            if (compare_type(flats.get(i), flat_type)
                    && compare_price(flats.get(i), sellingprice, database_Selling_Price_range)
                    && compare_lease(flats.get(i), lease_range, database_Remaining_Lease_range)
                    && compare_storey(flats.get(i), storey_range, database_Storey_range)
                    && compare_area(flats.get(i), floor_area_range, database_Floor_Area_range)) {
                relevantFlats.add(flats.get(i));
            }
        }
        Log.d("RelevantFlats", String.valueOf(relevantFlats));
        return relevantFlats;
    }
    private boolean compare_type(ResaleFlat flat, String flat_type_detail) {
        if (flat.getFlatSize().equals(flat_type_detail))
            return true;
        else
            return false;

    }

    private boolean compare_price(ResaleFlat flat, String selling_price_range_detail, String[] Database_Selling_Price_range) {
        int minprice = 0;
        int maxprice = 0;
        if (selling_price_range_detail.equals(Database_Selling_Price_range[0]))
            return true;
        Log.d("ResaleAPI", selling_price_range_detail+Database_Selling_Price_range[0]);
        if (selling_price_range_detail.equals(Database_Selling_Price_range[1])) {
            minprice = 1; maxprice = 200000;}
        else if (selling_price_range_detail.equals(Database_Selling_Price_range[2])) {
            minprice = 200001; maxprice = 400000;}
        else if (selling_price_range_detail.equals(Database_Selling_Price_range[3])) {
            minprice = 400001; maxprice = 600000;}
        else if (selling_price_range_detail.equals(Database_Selling_Price_range[4])) {
            minprice = 600001; maxprice = 800000;}
        else if (selling_price_range_detail.equals(Database_Selling_Price_range[5])) {
            minprice = 800001; maxprice = 1000000;}
        else if (selling_price_range_detail.equals(Database_Selling_Price_range[6])) {
            minprice = 1000001; maxprice = 10000000;}
        else {
            Log.e("ResaleAPI", "Invalid price range: "+selling_price_range_detail+ ' ' + Database_Selling_Price_range[0] + Database_Selling_Price_range[1]);
        }
        if (flat.getPrice() >= minprice && flat.getPrice() < maxprice)
            return true;
        else
            return false;

    }

    private boolean compare_lease(ResaleFlat flat, String remaining_lease_range_detail, String[] Database_Remaining_Lease_range) {
        int minlease = 0;
        int maxlease = 0;

        if (remaining_lease_range_detail.equals(Database_Remaining_Lease_range[0])) {
            return true; }
        Log.d("ResaleAPI", remaining_lease_range_detail+Database_Remaining_Lease_range[0]);
        if (remaining_lease_range_detail.equals(Database_Remaining_Lease_range[1])) {
            minlease = 1; maxlease = 20;}
        else if (remaining_lease_range_detail.equals(Database_Remaining_Lease_range[2])) {
            minlease = 21; maxlease = 40;}
        else if (remaining_lease_range_detail.equals(Database_Remaining_Lease_range[3])) {
            minlease = 41; maxlease = 60;}
        else if (remaining_lease_range_detail.equals(Database_Remaining_Lease_range[4])) {
            minlease = 61; maxlease = 80;}
        else if (remaining_lease_range_detail.equals(Database_Remaining_Lease_range[5])) {
            minlease = 81; maxlease = 99;}
        else {
            Log.e("ResaleAPI", "Invalid lease range"+remaining_lease_range_detail+Database_Remaining_Lease_range[0]);
        }
        if (flat.getRemainingLease() >= minlease && flat.getRemainingLease() < maxlease)
            return true;
        else
            return false;
    }

    private boolean compare_area(ResaleFlat flat, String area_range_detail, String[] Database_Floor_Area_range) {
        int minarea = 0;
        int maxarea = 0;
        if (area_range_detail.equals(Database_Floor_Area_range[0])) {
            return true;}
        if (area_range_detail.equals(Database_Floor_Area_range[1])) {
            minarea = 1; maxarea = 50;}
        else if (area_range_detail.equals(Database_Floor_Area_range[2])) {
            minarea = 51; maxarea = 100;}
        else if (area_range_detail.equals(Database_Floor_Area_range[3])) {
            minarea = 101; maxarea = 150;}
        else if (area_range_detail.equals(Database_Floor_Area_range[4])) {
            minarea = 151; maxarea = 200;}
        else if (area_range_detail.equals(Database_Floor_Area_range[5])) {
            minarea = 201; maxarea = 9999;}
        else {
            Log.e("ResaleAPI", "Invalid area range");
        }
        if (flat.getRemainingLease() >= minarea && flat.getRemainingLease() < maxarea)
            return true;
        else
            return false;
    }

    private boolean compare_storey(ResaleFlat flat, String storey_range_detail, String[] Database_Storey_range) {
        int minstorey = 0;
        int maxstorey = 0;
        if (storey_range_detail.equals(Database_Storey_range[0])) {
            return true;}
        if (storey_range_detail.equals(Database_Storey_range[1])) {
            minstorey = 1; maxstorey = 4;}
        else if (storey_range_detail.equals(Database_Storey_range[2])) {
            minstorey = 5; maxstorey = 10;}
        else if (storey_range_detail.equals(Database_Storey_range[3])) {
            minstorey = 11; maxstorey = 15;}
        else if (storey_range_detail.equals(Database_Storey_range[4])) {
            minstorey = 16; maxstorey = 200;}
        else {
            Log.e("ResaleAPI", "Invalid storey range");
        }
        if (Integer.parseInt(flat.getStorey().substring(0,1)) <= maxstorey && Integer.parseInt(flat.getStorey().substring(6,7)) >= minstorey)
            return true;
        else
            return false;
    }

}
