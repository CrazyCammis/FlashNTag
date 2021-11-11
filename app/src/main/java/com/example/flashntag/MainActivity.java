package com.example.flashntag;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    //ImageButton openGallery, openCamera, openTagActivity, addImageButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavController controller = Navigation.findNavController(this, R.id.NavHostFragment);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        NavigationUI.setupWithNavController(bottomNavigation, controller);
    }


    /*
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
                startActivity(intent);
            }
        });

        openTagActivity = findViewById(R.id.openTag);
        openTagActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),TagsActivity.class );
                startActivity(intent);
            }
        });


        addImageButton = findViewById(R.id.btnAddImage);
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent    = new Intent (view.getContext(), AddImageActivity.class);
                startActivity(intent);
            }
        });






    }

    public void onGalleryBtnClick (View view) {
        Intent openGallery = new Intent(this, TagsActivity.class);
        startActivity(openGallery);
    }

    public void onCameraBtnClick (View view) {

    }
    */
}