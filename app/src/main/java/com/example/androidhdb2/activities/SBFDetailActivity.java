package com.example.androidhdb2.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.androidhdb2.R;
import com.example.androidhdb2.model.SBFlat;
import com.example.androidhdb2.utils.SBFAdapter;
import com.example.androidhdb2.utils.SBFilter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SBFDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBar;
    private TextView progressText;
    private final String TAG = "SBFDETAIL";
    private FirebaseFirestore db;
    private String userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbfdetail);
        Intent intent = getIntent();
        userid = intent.getStringExtra("UserID");
        String[] detail = intent.getStringArrayExtra("FLAT DETAILS");
        ArrayList results = SBFilter.filterFlats(detail);
        Log.d(TAG, String.valueOf(results));

        String flatType;
        String[] priceRange;
        String[] flatSupplyRange;
        String ethnicGroup;
        String[] ethnicGroupQuota;


        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressText);
        recyclerView = findViewById(R.id.rV);
        db = FirebaseFirestore.getInstance();

        // Setup Recycler View
        recyclerView.setVisibility(View.GONE);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        progressBar.setVisibility(View.VISIBLE);
        progressText.setVisibility(View.VISIBLE);
        getSBFlats(results);
    }

    private void getSBFlats(ArrayList results) {

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

        progressBar.setVisibility(View.VISIBLE);
        List<SBFlat> flatArrayList = new CopyOnWriteArrayList<SBFlat>();
        CollectionReference colRef = db.collection("SBFlat");
//        Query query = colRef.whereEqualTo("flatSize",flatType);
//        if (priceRange!=null && !TextUtils.isEmpty(priceRange[0]) && TextUtils.isDigitsOnly(priceRange[0])) {
//            query = query.whereGreaterThanOrEqualTo("price",Integer.parseInt(priceRange[0]));
//            if (priceRange[1]!=null) {
//                query = query.whereLessThanOrEqualTo("price",Integer.parseInt(priceRange[1]));
//            }
//        }

        String finalethnicGroup = ethnicGroup;
        String[] finalPriceRange = priceRange;
        Log.d(TAG, "fPR"+finalPriceRange);
        String[] finalFlatSupplyRange = flatSupplyRange;
        String[] finalEthnicGroupQuota = ethnicGroupQuota;
        colRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        SBFlat flat = document.toObject(SBFlat.class);
//                        Log.d(TAG, flat.toString());
                        flatArrayList.add(flat);
                    }
                }
                Log.d(TAG, String.valueOf(flatArrayList)+flatArrayList.size());
                for (SBFlat flat : flatArrayList) {
                    if (!compare_FT(flat, flatType))
                        flatArrayList.remove(flat);
                    if (finalPriceRange[0] !=null){
                        if(!compare_PR(flat,Integer.parseInt(finalPriceRange[0]), Integer.parseInt(finalPriceRange[1])))
                            flatArrayList.remove(flat);
                    }
                    if (finalFlatSupplyRange[0] != null && !compare_SR(flat,Integer.parseInt(finalFlatSupplyRange[0]), Integer.parseInt(finalFlatSupplyRange[1]))){
                            flatArrayList.remove(flat);
                    }
                    if (finalethnicGroup!="" && !compare_EG(flat, finalethnicGroup, Integer.parseInt(finalEthnicGroupQuota[0]), Integer.parseInt(finalEthnicGroupQuota[1]))) {
                            flatArrayList.remove(flat);
                    }
                }
                Log.d(TAG, String.valueOf(flatArrayList)+flatArrayList.size());

                progressBar.setVisibility(View.GONE);
                progressText.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                mAdapter = new SBFAdapter(getApplicationContext(), flatArrayList , userid);
                recyclerView.setAdapter(mAdapter);
            }
        });
    }

    public boolean compare_FT(SBFlat flat, String ft) {
        if (flat.getFlatSize().contains(ft))
            return true;
        return false;
    }

    public boolean compare_SR(SBFlat flat, int min, int max) {
        if (flat.getFlatSupply() > max || flat.getFlatSupply()<min) {
            return false;
        } return true;
    }

    public boolean compare_PR(SBFlat flat, int min, int max) {
        if (flat.getPrice() > max || flat.getPrice() < min)
            return false;
        return true;
    }

    public boolean compare_EG(SBFlat flat, String ethnicGroup, int min, int max) {
        if (flat.getEthnicQuota(ethnicGroup) < min || flat.getEthnicQuota(ethnicGroup) > max)
            return false;
        return true;
    }


}
