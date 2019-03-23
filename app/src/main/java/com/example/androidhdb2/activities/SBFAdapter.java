package com.example.androidhdb2.activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidhdb2.R;
import com.example.androidhdb2.model.SBFlat;
import com.example.androidhdb2.utils.BtoAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SBFAdapter extends RecyclerView.Adapter<SBFAdapter.ViewHolder> {
    private List<SBFlat> sbFlatList;
    private Context mContext;

    public SBFAdapter(Context applicationContext, List<SBFlat> flatArrayList) {
        this.mContext = applicationContext;
        if (flatArrayList.size() == 0){
            SBFlat flat = new SBFlat("x", "NO FLAT FOUND", "", 0, 0, new HashMap<String, Integer>(),"NO FLAT FOUND");
            List<SBFlat> l = new ArrayList<SBFlat>();
            l.add(flat);
            this.sbFlatList = l;
        }
        else {
            this.sbFlatList = flatArrayList;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item, viewGroup, false);
        SBFAdapter.ViewHolder vh = new SBFAdapter.ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final SBFlat flat = sbFlatList.get(i);

        viewHolder.tvName.setText(flat.getRegion() + "\n" + flat.getFlatSize());
        viewHolder.tvPrice.setText("Minimum Price:"+'\n' + "$" + flat.getPrice());
        viewHolder.tvDetails.setText("Flat Supply: " + flat.getFlatSupply() + '\n' +
                "Ethnic Quota: " + flat.getEthnicQuota());

    }

    @Override
    public int getItemCount() {
        return sbFlatList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName;
        public TextView tvPrice;
        public TextView tvDetails;
        public View mView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.itemName);
            tvPrice = itemView.findViewById(R.id.itemPrice);
            tvDetails = itemView.findViewById(R.id.itemDetails);
            // casting for other variables
            mView = itemView;
        }
    }
}
