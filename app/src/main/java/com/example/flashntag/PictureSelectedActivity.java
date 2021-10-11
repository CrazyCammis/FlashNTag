package com.example.flashntag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.flashntag.adapter.PictureRecycleAdapter;

public class PictureSelectedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_selected);
        Intent intent = getIntent();

        long position = intent.getLongExtra("PIC_ID", 0);
        int ID = (int) position;

        //TODO: CREATE VIEW FROM DATA BASE WITH SELECTED PICTURE ID
        //TODO: DELETE FROM GALLERY SEE RECYCLBE VIEW LECTURE 55.00;
        //TODO: ADD TAGS
        //TODO: REMOVE TAGS


    }
}