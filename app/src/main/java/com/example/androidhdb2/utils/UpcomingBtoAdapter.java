package com.example.androidhdb2.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidhdb2.R;
import com.example.androidhdb2.model.UpcomingBtoFlat;

import java.util.ArrayList;

public class UpcomingBtoAdapter extends RecyclerView.Adapter<UpcomingBtoAdapter.ViewHolder> {

    private ArrayList<UpcomingBtoFlat> btoFlatList;
    private Context mContext;

    public UpcomingBtoAdapter(Context mContext, ArrayList<UpcomingBtoFlat> btoFlatList) {
        this.btoFlatList = btoFlatList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UpcomingBtoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item_upcomingbto, viewGroup, false);
        UpcomingBtoAdapter.ViewHolder vh = new UpcomingBtoAdapter.ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingBtoAdapter.ViewHolder viewHolder, int i) {
        final UpcomingBtoFlat flat = btoFlatList.get(i);

        viewHolder.tvName.setText(flat.getLocation());
        viewHolder.tvTotal.setText(String.valueOf(flat.getTotal()));
        viewHolder.tvDetails.setText(flat.getFlatSize());

        // insert fragment for image here
    }

    @Override
    public int getItemCount() {
        return btoFlatList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvTotal;
        public TextView tvDetails;
        // other variables in the current Activity
        public View mView;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.flatName);
            tvTotal = itemView.findViewById(R.id.flatTotal);
            tvDetails = itemView.findViewById(R.id.flatDetails);
            // casting for other variables
            mView = itemView;
        }
    }
}


