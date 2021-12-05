package com.example.flashntag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.flashntag.adapter.TagListRecylerAdapter;
import com.example.flashntag.modeller.Picture;

import java.util.ArrayList;

public class TagsActivity extends AppCompatActivity {
    public static final String SEND_CODE = "tagTrue";

    public  static final String TAG_TO_SERACH =  "tag";

    private Button searchButton;
    private EditText editText;
    private String text;


    private   String[] tagList ={} ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tags);


        editText = findViewById(R.id.searchTagsText);

        searchButton = findViewById(R.id.searchButton);


        tagList = Picture.getAllTags().toArray(new String[0]);

        if(tagList.length > 21) {setmaxPreview(tagList);}
        setUpRecycleView(tagList);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                text = editText.getText().toString();



                //Makes sure to see if we can find the targeted tag in the list, if not we wont be able start the intent
                    //HOLDER
                if(!checkForTags(text))/*FINDS THE TAG IN THE LIST"*/ {
                    Toast.makeText(view.getContext(),
                            "Tag " + text + " not found, try again",
                            Toast.LENGTH_SHORT).show();
                }

                else if (checkForTags(text)){
                    Intent intent = new Intent(view.getContext(), Viewer.class);
                    Toast.makeText(view.getContext(),
                            "Tag " + text + " FOUND",
                            Toast.LENGTH_SHORT).show();


                    intent.putExtra("activity", "tag");
                    intent.putExtra("target", text);

                }






            }
        });

    }


    //PROBLEM HERE IN CHECK FOR TAGS
    public String[] setmaxPreview(String[] tagListtoReduce){
        String[] holder = new String[20];

        for (int i = 0; i < 20; i++) {
            holder[i] = tagListtoReduce[i];
            i++;
        }
        return holder;
    }


    public  boolean checkForTags(String text) {

        ArrayList<String> allTags = Picture.getAllTags();
        if(allTags.contains(text)){return true;}
        else return false;
    }


    private void setUpRecycleView(String[] tagList){
        RecyclerView tagListRecycleView = findViewById(R.id.allTagsRecycleView);

        tagListRecycleView.setAdapter(new TagListRecylerAdapter(this, tagList));
        tagListRecycleView.setLayoutManager(new GridLayoutManager(this, 3));
    }

}