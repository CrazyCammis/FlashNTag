package com.example.flashntag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.flashntag.adapter.PictureRecycleAdapter;
import com.example.flashntag.modeller.Picture;

import java.util.ArrayList;

public class PictureSelectedActivity extends AppCompatActivity {
    private EditText text;
    private ImageButton DELETE_PICTURE;
    private int ID;

    PictureRecycleAdapter pictureRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_selected);
        Intent intent = getIntent();

        long position = intent.getLongExtra("PIC_ID", 0);
        //sets the ID for the entire file
         ID = (int) position;

        //TODO: CREATE VIEW FROM DATA BASE WITH SELECTED PICTURE ID
        //TODO: DELETE FROM GALLERY SEE RECYCLBE VIEW LECTURE 55.00;
        //TODO: ADD TAGS
        //TODO: REMOVE TAGS


        ArrayList<Picture> dataList = (ArrayList<Picture>) Picture.getData();

        Picture picture = dataList.get(ID);
/*
        Image image = findViewById(R.id.selectedPicuture);
        text  =findViewById(R.id.date);
*/


        DELETE_PICTURE = findViewById(R.id.btnOpenCamera);
        DELETE_PICTURE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pictureRecycleAdapter.removePicture(ID);
            }
        });



    }
}
