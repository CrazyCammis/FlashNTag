package com.example.flashntag;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


public class MainFragment extends Fragment {

    ImageButton openGallery, openCamera, openTagFragment, addImageButton;

    public static  final String SEND_CODE = "mainTrue";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        openGallery = getView().findViewById(R.id.openGallery);
        openGallery.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_viewerFragment));

        /*
        openGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Viewer.class);
                startActivity(intent);
            }
        });*/

        openCamera = getView().findViewById(R.id.btnOpenCamera);

        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //insert open cam intent here
                /*Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);*/
            }
        });

        openTagFragment = getView().findViewById(R.id.openTag);
        openTagFragment.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_tagsFragment));



        addImageButton = getView().findViewById(R.id.btnAddImage);
        addImageButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_mainFragment_to_addImageFragment));
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent    = new Intent (view.getContext(), AddImageActivity.class);
                startActivity(intent);
            }
        });
    }
}
