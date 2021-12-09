package com.example.flashntag.adapter;

import android.content.Context;
import android.content.Intent;
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

public class PictureRecycleAdapter extends  RecyclerView.Adapter<PictureRecycleAdapter.PictureViewHolder> {
    private static final String TAG = PictureRecycleAdapter.class.getSimpleName();
    public List<Picture> pictureList;
    public LayoutInflater inflater;

    public PictureRecycleAdapter(Context context, List<Picture> pictureList) {
        this.inflater = LayoutInflater.from(context);
        this.pictureList = pictureList;
    }


    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View itemview = inflater.inflate(R.layout.picturelist_xmlfile, parent, false);

        return new PictureViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder viewHolder, int position) {
        Picture pictureToShow = pictureList.get(position);
        viewHolder.setPicture(pictureToShow, position);
    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView typeTextView;
        private ImageView pictureImageView;
        private int position;

        public PictureViewHolder(@NonNull View itemView) {
            super(itemView);

            typeTextView = itemView.findViewById(R.id.dateAddedText);
            pictureImageView = itemView.findViewById(R.id.pictureImageView);
            itemView.setOnClickListener(this);
        }

        public void setPicture(Picture pictureToShow, int position) {
            typeTextView.setText(pictureToShow.getDate().toString());
            pictureImageView.setImageResource(pictureToShow.getPictureID());
            this.position =  position;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), PictureSelectedActivity.class);
          intent.putExtra("PIC_ID", position);
          view.getContext().startActivity(intent);
        }
    }


    public  void removePicture(int id){
        pictureList.remove(id);
        notifyItemRemoved(id);
        notifyItemRangeChanged(id, pictureList.size());
    }

    public void addPicture(int newID, Picture newPicture){
        pictureList.add(newID, newPicture);
        notifyItemRangeChanged(newID, pictureList.size());
    }
}
