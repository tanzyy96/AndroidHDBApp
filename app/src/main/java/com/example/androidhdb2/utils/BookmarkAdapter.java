package com.example.androidhdb2.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidhdb2.R;
import com.example.androidhdb2.controllers.UserController;
import com.example.androidhdb2.model.Bookmark;
import com.example.androidhdb2.model.Flat;
import com.example.androidhdb2.model.PastBtoFlat;
import com.example.androidhdb2.model.ResaleFlat;
import com.example.androidhdb2.model.SBFlat;
import com.example.androidhdb2.model.UpcomingBtoFlat;

import java.util.ArrayList;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {
    private ArrayList<Bookmark> bookmarks;
    private Context mContext;
    private String userid;

    public BookmarkAdapter(Context c, ArrayList<Bookmark> l , String userid) {
        this.mContext = c;
        this.bookmarks = l;
        this.userid = userid;
    }


    @NonNull
    @Override
    public BookmarkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_bookmark, viewGroup, false);
        BookmarkAdapter.ViewHolder vh = new BookmarkAdapter.ViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkAdapter.ViewHolder viewHolder, int i) {
        Bookmark bookmark = bookmarks.get(i);
        Flat flat = bookmark.getFlat();

        viewHolder.tvName.setText(flat.getLocation());
        if (flat instanceof SBFlat){
            viewHolder.tvDetails.setText(flat.getFlatSize() + "\n" + "Flat Type: Sale of Balance Flat");
        }
        else if (flat instanceof PastBtoFlat){
            viewHolder.tvDetails.setText(flat.getFlatSize() + "\n" + "Flat Type: Past-launch BTO Flat");
        }
        else if (flat instanceof UpcomingBtoFlat){
            viewHolder.tvDetails.setText(flat.getFlatSize() + "\n" + "Flat Type: Upcoming-launch BTO Flat");
        }
        else if (flat instanceof ResaleFlat){
            viewHolder.tvDetails.setText(flat.getFlatSize() + "\n" + "Flat Type: Resale Flat");
        }


        viewHolder.mView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                UserController.removeUserBookmark(mContext, mContext.getFilesDir(), userid, flat);
                bookmarks.remove(bookmark);
                notifyItemRemoved(i);
                Toast.makeText(mContext,"Bookmark removed",Toast.LENGTH_SHORT).show();
                return false;
            }
        })

        ;


        // insert fragment for image here
    }

    @Override
    public int getItemCount() {
        return bookmarks.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvDetails;
        // other variables in the current Activity
        public View mView;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.bookmarkName);
            tvDetails = itemView.findViewById(R.id.bookmarkDetails);
            // casting for other variables
            mView = itemView;
        }
    }
}
