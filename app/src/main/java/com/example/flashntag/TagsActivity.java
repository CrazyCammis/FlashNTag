package com.example.flashntag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.flashntag.modeller.Picture;

import java.util.ArrayList;

public class TagsActivity extends AppCompatActivity {
    public static final String SEND_CODE = "tagTrue";

    public  static final String TAG_TO_SERACH =  "tag";

    private Button searchButton;
    private EditText editText;

    private   String[] tagList ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);


        editText = findViewById(R.id.searchTagsText);

        searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Viewer.class);

                String text;
                text = editText.getText().toString();
                tagList = new String[]{"a", "b"};


                //Makes sure to see if we can find the targeted tag in the list, if not we wont be able start the intent
                    //HOLDER
                if(checkForTags(text))/*FINDS THE TAG IN THE LIST"*/ {

/*
                    intent.putExtra(SEND_CODE, "tagTrue");
                    intent.putExtra(TAG_TO_SERACH, text);
*/
                    startActivity(intent);
                }

                else{

                    Toast.makeText(view.getContext(),
                            "Tag " + text + " not found, try again",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    //PROBLEM HERE IN CHECK FOR TAGS

    public  boolean checkForTags(String text) {


        boolean holder = false;
                if(holder = true){
                    return false;
                }
/*
        ArrayList<String> allTags = Picture.getAllTags();

        if(allTags.contains(text)){return true;}*/


        else
            return false;
    }
}