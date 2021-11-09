package com.example.flashntag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TagsActivity extends AppCompatActivity {
    public static final  String KEY_TEXT =  "EDIT_TEXT";
    public static final  String TYPE =  "tag";

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

                    intent.putExtra(KEY_TEXT, text);
                    intent.putExtra(TYPE, "tag");

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


    public  boolean checkForTags(String text) {


        for (int i = 0; i < tagList.length; i++) {
            if (tagList[i].equals( text) ){
                return true;
            }
        }

        return false;
    }
}