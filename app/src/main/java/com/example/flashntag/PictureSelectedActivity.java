package com.example.flashntag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.flashntag.adapter.PictureRecycleAdapter;
import com.example.flashntag.modeller.Picture;

import java.util.ArrayList;

public class PictureSelectedActivity extends AppCompatActivity {
    private EditText editText;
    private ImageButton DELETE_PICTURE;
    private int ID;

    private Picture picture;

    private Button addTag;
    private Button submitButton;
    private Button cancelTag;



    private ImageView pictureView;
    PictureRecycleAdapter pictureRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_selected);
        Intent intent = getIntent();

        long position = intent.getLongExtra("PIC_ID", 0);
        //sets the ID for the entire file
         ID = (int) position;

        ArrayList<Picture> dataList = (ArrayList<Picture>) Picture.getData();
        picture = dataList.get(ID);
        pictureView.setImageResource(picture.getPictureID());

        //TODO: CREATE VIEW FROM DATA BASE WITH SELECTED PICTURE ID
        //TODO: DELETE FROM GALLERY SEE RECYCLBE VIEW LECTURE 55.00;
        //TODO: ADD TAGS
        //TODO: REMOVE TAGS


       // ArrayList<Picture> dataList = (ArrayList<Picture>) Picture.getData();

        //Picture picture = dataList.get(ID);
/*
        Image image = findViewById(R.id.selectedPicuture);
        text  =findViewById(R.id.date);
*/







        addTag = findViewById(R.id.addTagButton);
        DELETE_PICTURE = findViewById(R.id.btnOpenCamera);
        editText = findViewById(R.id.addTagText);
        submitButton = findViewById(R.id.submitTagButton);
        cancelTag = findViewById(R.id.cancellAddButton);

        addTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInput();

            }
        });

        cancelTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideInput();
            }
        });



        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text;
                text = editText.getText().toString();
                String[] holder = picture.getTags();

                if(text == null ){
                    Toast.makeText(view.getContext(), "Error, no tag inputed try again", Toast.LENGTH_SHORT).show();
                }
                else if(holder.length > 20){
                    Toast.makeText(view.getContext(), "Tag list full", Toast.LENGTH_SHORT).show();
                }

                else{
                    //adds to the empty spot
                    holder[holder.length] = text;
                    //set the new array as the new one
                    picture.setTags(holder);

                    Toast.makeText(view.getContext(), "Tag added", Toast.LENGTH_SHORT).show();
                }


            }
        });

        DELETE_PICTURE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),
                        "Tag Deleted",
                        Toast.LENGTH_SHORT).show();
                //pictureRecycleAdapter.removePicture(ID);
            }
        });

}

    private void showInput() {
        editText.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        cancelTag.setVisibility(View.VISIBLE);

        DELETE_PICTURE.setVisibility(View.INVISIBLE);
        addTag.setVisibility(View.INVISIBLE);

    }

    private void hideInput(){
        editText.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.INVISIBLE);

        DELETE_PICTURE.setVisibility(View.VISIBLE);
        addTag.setVisibility(View.VISIBLE);
        cancelTag.setVisibility(View.INVISIBLE);

    }
}
