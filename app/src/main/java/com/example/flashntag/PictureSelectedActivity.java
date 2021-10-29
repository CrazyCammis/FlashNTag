package com.example.flashntag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.flashntag.adapter.PictureRecycleAdapter;
import com.example.flashntag.modeller.Picture;

public class PictureSelectedActivity extends AppCompatActivity {

    private Picture picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_selected);
        Intent intent = getIntent();

        //Gets the ID sent from last intent, based on the name of the sent one
        long position = intent.getLongExtra("PIC_ID", 0);
        //converts into an interger so we can use it later.
        int ID = (int) position;

        String[] HOLDER = {"Ani", "Sam", " Joe"};


        //HOLDER before we get the data from somewhee

         picture = new Picture(2,"https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fyt3.ggpht.com%2Fa%2FAATXAJwG-Z01rJUziDMRH1jPTVcn9dKdeizUmoTRTA%3Ds900-c-k-c0xffffffff-no-rj-mo&f=1&nofb=1",HOLDER);



        //TODO: CREATE VIEW FROM DATA BASE WITH SELECTED PICTURE ID
        //TODO: DELETE FROM GALLERY SEE RECYCLBE VIEW LECTURE 55.00;
        //TODO: ADD TAGS
        //TODO: REMOVE TAG


    }




}