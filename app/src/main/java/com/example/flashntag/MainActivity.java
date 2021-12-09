package com.example.flashntag;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageButton openGallery,  openTagActivity, addImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openGallery = findViewById(R.id.openGallery);
        openGallery.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), Viewer.class);


            intent.putExtra("activity","all");
            intent.putExtra("target","");
            startActivity(intent);
        });


        openTagActivity = findViewById(R.id.openTag);
        openTagActivity.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(),TagsActivity.class );
            startActivity(intent);
        });


    }


}