package com.example.flashntag.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashntag.R;
import com.example.flashntag.modeller.Picture;

import java.util.List;

public class PictureRecycleAdapter extends  RecyclerView.Adapter<PictureRecycleAdapter.PictureViewHolder>{
    private  static final String TAG = PictureRecycleAdapter.class.getSimpleName();
    public List<Picture> pictureList;
    public LayoutInflater inflater;



    //Picturelist er generert fra getData() i Picture klassen
    public PictureRecycleAdapter(Context context, List<Picture> pictureList){
       this.inflater = LayoutInflater.from(context);
       this.pictureList = pictureList;

    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        Log.d(TAG, "onCreateViewHolder");

        View itemview = inflater.inflate(R.layout.picturelist, parent, false);


        return new PictureViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder viewHolder, int position) {
        Picture pictureToShow = pictureList.get(position);

        Log.d(TAG, "onBindViewHolde" + pictureToShow.getFileName() + " - " + position);

        viewHolder.setPicture(pictureToShow);

    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder{
        private TextView typeTextView;
        private ImageView pictureImageView;

        public PictureViewHolder(@NonNull View itemView) {
            super(itemView);

            typeTextView = itemView.findViewById(R.id.pictureImageView);

        }


        public  void setPicture(Picture pictureToShow){
            typeTextView.setText(pictureToShow.getFileName());
            pictureImageView.setImageResource(pictureToShow.getPictureID());
        }
    }


}
