package com.example.flashntag;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddImageActivity extends AppCompatActivity {
    Button addImageButton, addTagBtn, submitBtn;
    EditText editText;

    String[] tagList = new String[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);


        editText = findViewById(R.id.addTagText);

        addImageButton = findViewById(R.id.addImageFromGalleryBtn);
        addTagBtn = findViewById(R.id.addTagButton);
        submitBtn = findViewById(R.id.submitTagButton);

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



    }

    private boolean canBeAddedToTag(String text) {
        if(tagList.length < 20 ) {

            if(tagList == null ){
                return  true;
            }

            for (int i = 0; i < tagList.length; i++) {
                if (tagList[i] == text){
                    return  false ;
                }
            }
            return true;
        }
        return  false;
    }
}