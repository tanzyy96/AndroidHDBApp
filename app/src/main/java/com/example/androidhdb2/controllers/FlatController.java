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
import java.util.concurrent.TimeUnit;

public class FlatController {
    private String TAG = "FLATCONTROLLER";

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

//    private class AsyncList extends AsyncTask<Void, Void, ArrayList<PastBtoFlat>> {
//        private static final String TAG = "AsyncTask" ;
//        ArrayList<PastBtoFlat> btoFlatArrayList = new ArrayList<PastBtoFlat>();
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//
//        @Override
//        protected ArrayList<PastBtoFlat> doInBackground(Void... voids) {
//
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<PastBtoFlat> pastBtoFlats) {
//            flatArrayList = pastBtoFlats;
//        }
//    }

}
