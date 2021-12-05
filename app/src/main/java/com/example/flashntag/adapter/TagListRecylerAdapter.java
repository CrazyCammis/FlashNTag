package com.example.flashntag.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashntag.R;
import com.example.flashntag.Viewer;

public class TagListRecylerAdapter extends RecyclerView.Adapter<TagListRecylerAdapter.TagListViewHolder>{
    private static final String TAG = TagListRecylerAdapter.class.getSimpleName();

    private String[] tagLis = {};
    private LayoutInflater inflater;

    private String tag;


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


    public  class TagListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Button tagToShow;
        private int position;

        public TagListViewHolder(@NonNull View itemView) {
            super(itemView);

            tagToShow = itemView.findViewById(R.id.tagShownButton);

            tagToShow.setOnClickListener(this);

        }

        public  void setTag(String tag, int position){
            tagToShow.setText(tag);
            this.position = position;

        }


        @Override
        public void onClick(View view) {
            String send = tagLis[position];
            Intent intent = new Intent(view.getContext(), Viewer.class);

            intent.putExtra("activity","tag");
            intent.putExtra("target",send);
            view.getContext().startActivity(intent);
        }
    }
}
