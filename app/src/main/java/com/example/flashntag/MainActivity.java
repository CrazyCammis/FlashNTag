package com.example.flashntag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton openGallery;
    ImageButton openCamera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openGallery = findViewById(R.id.openGallery);
        openGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Viewer.class);
                startActivity(intent);
            }
        });

        openCamera = findViewById(R.id.btnOpenCamera);
        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //insert open cam intent here
                /*Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);*/
            }
        });




    }

    public void onGalleryBtnClick (View view) {
        Intent openGallery = new Intent(this, tagList.class);
        startActivity(openGallery);
    }

    public void onCameraBtnClick (View view) {

    }
}