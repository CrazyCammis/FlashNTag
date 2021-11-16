package com.example.flashntag.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashntag.PictureSelectedActivity;
import com.example.flashntag.R;
import com.example.flashntag.modeller.Picture;

import java.util.List;

public class PictureRecycleAdapter extends  RecyclerView.Adapter<PictureRecycleAdapter.PictureViewHolder>{
    private  static final String TAG = PictureRecycleAdapter.class.getSimpleName();
    public  List<Picture> pictureList;
    public LayoutInflater inflater;


    //ID to be gotten from delete on picture selected acitivyt
    private int ID;


    //Picturelist er generert fra getData() i Picture klassen
    public PictureRecycleAdapter(Context context, List<Picture> pictureList){
       this.inflater = LayoutInflater.from(context);
       this.pictureList = pictureList;

    }



    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        Log.d(TAG, "onCreateViewHolder");
//Reference to the XML File
        View itemview = inflater.inflate(R.layout.picturelist_xmlfile, parent, false);


        return new PictureViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder viewHolder, int position) {
        //hva skal vi vise
        Picture pictureToShow = pictureList.get(position);

        Log.d(TAG, "onBindViewHolde" + pictureToShow.getFileName() + " - " + position);

        viewHolder.setPicture(pictureToShow);

    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        //The  references to picture and text
        private TextView typeTextView;
        private ImageView pictureImageView;
        private  int position;

        public PictureViewHolder(@NonNull View itemView) {
            //viewet vi skal sette i
            super(itemView);

            typeTextView = itemView.findViewById(R.id.dateAddedText);
            pictureImageView = itemView.findViewById(R.id.pictureImageView);


//sets on click
            itemView.setOnClickListener(this);

        }



        //Sets the data to show the picture
        public  void setPicture(Picture pictureToShow){
            //gets the date to display
            typeTextView.setText(pictureToShow.getDate().toString());
            //sets the image to get
            pictureImageView.setImageResource(pictureToShow.getPictureID());

            //gets the position in the list
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            Intent intent   = new Intent(view.getContext(), PictureSelectedActivity.class);
            view.getContext().startActivity(intent);

            //should get the ID from he currently selected
            intent.putExtra("PIC_ID", position);
            view.getContext().startActivity(intent);
        }
    }

//put here cause it handeled by the adapter
    public  void removePicture(int id){
        pictureList.remove(id);
        notifyItemRemoved(id);
        notifyItemRangeChanged(id, pictureList.size());
    }

//FOR WHEN WE ADD PICTURES USE THIS
    public void addPicture(int newID, Picture newPicture){
        pictureList.add(newID, newPicture);
        notifyItemRangeChanged(newID, pictureList.size());
    }









}
