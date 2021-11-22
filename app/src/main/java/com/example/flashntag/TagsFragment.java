package com.example.flashntag;

import android.content.Intent;
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

import com.example.flashntag.modeller.Picture;

import java.util.ArrayList;

public class TagsFragment extends Fragment {
    public static final String SEND_CODE = "tagTrue";

    public  static final String TAG_TO_SERACH =  "tag";

    private Button searchButton;
    private EditText editText;

    private   String[] tagList ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_tags, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        editText = getView().findViewById(R.id.searchTagsText);

        searchButton = getView().findViewById(R.id.searchButton);
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

    public  boolean checkForTags(String text) {

        ArrayList<String> allTags = Picture.getAllTags();
        if(allTags.contains(text)){return true;}
        else return false;
    }
}
