package com.example.androidhdb2.controllers;

import android.content.Context;
import android.util.Log;

import com.example.androidhdb2.model.PastBtoFlat;
import com.example.androidhdb2.model.ResaleFlat;
import com.example.androidhdb2.model.SBFlat;
import com.example.androidhdb2.model.UpcomingBtoFlat;
import com.example.androidhdb2.utils.ResaleAPI;

import java.util.ArrayList;

public class FlatController {

//    public ArrayList<PastBtoFlat> getPastBTO(String flatType, int priceRange, String region){
//
//    }
//
//    public ArrayList<SBFlat> getSBF(String flatType, int priceRange, String flatSupplyRange, String ethnicity, String region) {
//
//    }
//
    public ArrayList<ResaleFlat> getResale(String[] flatList, String[] priceList, String[] leaseList, String[] storeyList, String[] areaList,
                                           String flatType, String priceRange, String remainingLeaseRange, String storeyRange, String floorAreaRange, String region) {
        ResaleAPI api = new ResaleAPI(flatList, priceList, leaseList, storeyList, areaList);
        ArrayList<ResaleFlat> resaleFlatArrayListList = api.requestData(region, flatType, "2019");
        Log.d("Flatcontroller", String.valueOf(resaleFlatArrayListList));
        return api.filterFlats(resaleFlatArrayListList, flatType, priceRange, remainingLeaseRange, storeyRange, floorAreaRange);
    }
//
//    public ArrayList<UpcomingBtoFlat> getUpcomingBTO() {
//
//    }

}
