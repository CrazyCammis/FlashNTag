package com.example.flashntag;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashntag.adapter.PictureRecycleAdapter;
import com.example.flashntag.modeller.Picture;

import java.util.List;

//Temporary holder for recucleviwer
public class Viewer extends AppCompatActivity {
    private List<Picture> pictureList;

    private PictureRecycleAdapter pictureRecycleAdapter;
    private  RecyclerView pictureRecuycleView;
    private String targetSent = "";
    private String typeOfView  = "";

    private String activitySentFrom;
    private String targetFromActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);

        Intent intent =getIntent();
        typeOfView = intent.getStringExtra("activity");
        targetSent = intent.getStringExtra("target");

        setUpRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private void setUpRecyclerView(){
        pictureRecuycleView = findViewById(R.id.recycleViewPage);
        pictureRecycleAdapter = new PictureRecycleAdapter(this, pictureList );

        pictureRecuycleView.setAdapter(new PictureRecycleAdapter(this, Picture.getData(typeOfView, targetSent)));
        pictureRecuycleView.setLayoutManager(new GridLayoutManager(this, 3));
    }
}

