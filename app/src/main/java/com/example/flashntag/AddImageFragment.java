package com.example.flashntag;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddImageFragment extends Fragment {

    Button addImageButton, addTagBtn, submitBtn;
    EditText editText;

    String[] tagList = new String[20];

    AddImageFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_image, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText = getView().findViewById(R.id.addTagText);

        addImageButton = getView().findViewById(R.id.addImageFromGalleryBtn);
        addTagBtn = getView().findViewById(R.id.addTagButton);
        submitBtn = getView().findViewById(R.id.submitTagButton);

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "CLICKED YOU DID IT", Toast.LENGTH_SHORT).show();
                //TODO ADD METHOD TO SEND IT TO OUR STORAGE
                //ADD ADD STRINGS METHOD
            }
        });



        addTagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text;
                text = editText.getText().toString();

                if(canBeAddedToTag(text)){
                    tagList[tagList.length] = text;
                }
            }
        });


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Picture picture = new Picture()
                Toast.makeText(view.getContext(), "Picture added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean canBeAddedToTag(String text) {
        if(tagList.length < 20 ) {

            if(tagList == null ){
                return  true;
            }

            for (int i = 0; i < tagList.length; i++) {
                if (tagList[i].equals(text)){
                    return  false ;
                }
            }
            return true;
        }
        return  false;
    }

}
