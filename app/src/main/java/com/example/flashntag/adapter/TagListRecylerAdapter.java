package com.example.flashntag.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashntag.R;

public class TagListRecylerAdapter extends RecyclerView.Adapter<TagListRecylerAdapter.TagListViewHolder>{
    private static final String TAG = TagListRecylerAdapter.class.getSimpleName();

    private String[] tagLis = {};
    private LayoutInflater inflater;


    public TagListRecylerAdapter(Context context, String[] tagList){
        this.inflater = LayoutInflater.from(context);
        this.tagLis = tagList;
    }
    @NonNull
    @Override
    public TagListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        Log.d(TAG, "onCreateViewHolder");

        View itemview = inflater.inflate(R.layout.taglist_xml, parent, false);

        return new TagListViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull TagListViewHolder viewHolder, int position) {

        String tagToDisplay = tagLis[position];

        Log.d(TAG, "onBindViewHolder " + tagToDisplay + " "  + position);

        viewHolder.setTag(tagToDisplay, position);
    }

    public void removeTag(int position){
        tagLis[position] = null;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        int size =tagLis.length;

        return size;
    }


    public  class TagListViewHolder extends RecyclerView.ViewHolder {
        private Button tagToShow;
        private int position;

        public TagListViewHolder(@NonNull View itemView) {
            super(itemView);

            tagToShow = itemView.findViewById(R.id.tagShownButton);


        }

        public  void setTag(String tag, int position){
            tagToShow.setText(tag);
            this.position = position;

        }


    }
}
