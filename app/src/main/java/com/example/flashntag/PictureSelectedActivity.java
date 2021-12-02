package com.example.flashntag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flashntag.adapter.PictureRecycleAdapter;
import com.example.flashntag.adapter.TagListRecylerAdapter;
import com.example.flashntag.modeller.Picture;

import java.util.ArrayList;

public class PictureSelectedActivity extends AppCompatActivity {
    private EditText editText;


    private Picture picture;
    private String[] tagList = {"pasta", "Pregnant Mario", "Pregnant Luigi", "Plane", "","Pregnant Sonic", "Pregnant Donald Duck"};

    private Button addTag;
    private Button submitButton;
    private Button cancelTag;
    private ImageButton DELETE_PICTURE;
    private EditText addTagText;

    private TextView date;

    private ImageView pictureView;

    PictureRecycleAdapter pictureRecycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_selected);
        Intent intent = getIntent();


        int position = intent.getIntExtra("PIC_ID", 1);
        //sets the ID for the entire file
        pictureView = findViewById(R.id.selectedPicuture);
        date = findViewById(R.id.dateOfPicture);



        ArrayList<Picture> dataList;
        dataList = (ArrayList<Picture>) Picture.getData("", "");
        picture = dataList.get(position);



        //returns array that only have the tags, removes any ampty one from the count
        tagList= picture.getTags();
        //tagList = sortList(tagList);





        date.setText(picture.getDate().toString());
        pictureView.setImageResource(picture.getPictureID());

        setUpRecycleView(tagList);
        //TODO: CREATE VIEW FROM DATA BASE WITH SELECTED PICTURE ID
        //TODO: DELETE FROM GALLERY SEE RECYCLBE VIEW LECTURE 55.00;
                //TODO: REMOVE TAGS




        addTag = findViewById(R.id.addTagButton);
        DELETE_PICTURE = findViewById(R.id.deletePicture);
        editText = findViewById(R.id.addTagText);
        submitButton = findViewById(R.id.submitTagButton);
        cancelTag = findViewById(R.id.cancellAddButton);
        addTagText = findViewById(R.id.addTagText);

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
            public void onClick(View view) {/*
                String text;
                text = editText.getText().toString();
                String[] holder = tagList;

                if(text == null ){
                    Toast.makeText(view.getContext(), "Error, no tag inputed try again", Toast.LENGTH_SHORT).show();
                }

                else if(checkIfTagExist(holder, text)){
                    Toast.makeText(view.getContext(), "Error, tag alreadt in list", Toast.LENGTH_SHORT).show();
                }

                else if(holder.length > 20){
                    Toast.makeText(view.getContext(), "Tag list full", Toast.LENGTH_SHORT).show();
                }

                //ads to it
                else{
                    //adds to the empty spot
                    holder[holder.length] = text;
                    //set the new array as the new one
                    picture.setTags(holder);
                    Toast.makeText(view.getContext(), "Tag added", Toast.LENGTH_SHORT).show();
                }*/
                Toast.makeText(view.getContext(), "Tag added", Toast.LENGTH_SHORT).show();
            }
        });

        DELETE_PICTURE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),
                        "Tag Deleted",
                        Toast.LENGTH_SHORT).show();
             //   pictureRecycleAdapter.removePicture(position);
            }
        });

    }

    private boolean checkIfTagExist(String[] holder, String tag) {
        if(holder != null && holder.length > 0) {return false;}

        for(int i = 0; i <holder.length; i++){
            if(holder[i].equals( tag)){return true;}
        }
        return  false;
    }

    private void showInput() {
        editText.setVisibility(View.VISIBLE);
        addTagText.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        cancelTag.setVisibility(View.VISIBLE);
       DELETE_PICTURE.setVisibility(View.INVISIBLE);
        addTag.setVisibility(View.INVISIBLE);
    }

    private void hideInput(){
        editText.setVisibility(View.INVISIBLE);
        addTagText.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.INVISIBLE);
        DELETE_PICTURE.setVisibility(View.VISIBLE);
        addTag.setVisibility(View.VISIBLE);
        cancelTag.setVisibility(View.INVISIBLE);
    }

    private void setUpRecycleView(String[] tagList){
        RecyclerView tagListRecycleView = findViewById(R.id.tagRecyclerView);

        tagListRecycleView.setAdapter(new TagListRecylerAdapter(this, tagList));
        tagListRecycleView.setLayoutManager(new GridLayoutManager(this, 3));
    }


    public String[] sortList(String[] tagList){
        int count = 0;
        for(int i = 0; i < tagList.length; i++){
            if(!tagList[i].equals("")){
                count++;
            }
        }

        String[] holder = new String[count];
        for(int i = 0; i < tagList.length; i++){
            if(!tagList[i].equals("")){
                holder[i] = tagList[i];
            }
        }
        return  holder;
    }
}
