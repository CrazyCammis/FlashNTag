package com.example.flashntag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toolbar;

import com.example.flashntag.adapter.PictureRecycleAdapter;
import com.example.flashntag.modeller.Picture;
//Temporary holder for recucleviwer
public class Viewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("RecyclerVier");

        //setSupportActionBar(toolbar); GET HELP FROM ASSISTANT


        setUpRecyclerView();

    }

    private void setUpRecyclerView(){
        RecyclerView pictureRecuycleView = findViewById(R.id.recycleViewPage);

        pictureRecuycleView.setAdapter(new PictureRecycleAdapter(this, Picture.getData()));

        pictureRecuycleView.setLayoutManager(new GridLayoutManager(this, 3));

    }
}