package com.example.flashntag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flashntag.adapter.PictureRecycleAdapter;
import com.example.flashntag.adapter.TagListRecylerAdapter;
import com.example.flashntag.modeller.Picture;

import java.util.ArrayList;

public class PictureSelectedActivity extends AppCompatActivity {
    private EditText editText;
    private String text;


    private Picture picture;
    private String[] tagList = {};

    private Button addTagBtn;
    private Button confirmAddTagBtn;
    private Button cancelBtn;
    private Button removeTagBtn;
    private Button confirmDeleteBtn;

    private ImageButton DELETE_PICTURE;



    private PictureRecycleAdapter pictureRecycleAdapter;
    private TagListRecylerAdapter tagListRecylerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_selected);
        Intent intent = getIntent();


        int position = intent.getIntExtra("PIC_ID", 1);
        //sets the ID for the entire file
        ImageView pictureView = findViewById(R.id.selectedPicuture);
        TextView date = findViewById(R.id.dateOfPicture);



        ArrayList<Picture> dataList;
        dataList = (ArrayList<Picture>) Picture.getData("", "");
        picture = dataList.get(position);



        //returns array that only have the tags, removes any ampty one from the count
        tagList= picture.getTags();
        //tagList = sortList(tagList);



        date.setText(picture.getDate().toString());
        pictureView.setImageResource(picture.getPictureID());

        setUpRecycleView(tagList);
        //TODO: DELETE FROM GALLERY SEE RECYCLBE VIEW LECTURE 55.00;
                //TODO: REMOVE TAGS


        addTagBtn = findViewById(R.id.addTagButton);
        DELETE_PICTURE = findViewById(R.id.deletePicture);
        editText = findViewById(R.id.inputText);
        confirmAddTagBtn = findViewById(R.id.submitTagButton);
        cancelBtn = findViewById(R.id.cancellButton);
        removeTagBtn = findViewById(R.id.removeTagButton);
        confirmDeleteBtn = findViewById(R.id.confirmDeleteBtn);

        addTagBtn.setOnClickListener(view -> showAddInput());

        removeTagBtn.setOnClickListener(view -> showDeleteTagInput());

        cancelBtn.setOnClickListener(view -> hideAddInput());

        confirmDeleteBtn.setOnClickListener(view -> {
            text = editText.getText().toString();
            String[] holder = tagList;

            if(text.equals("")){
                Toast.makeText(view.getContext(), "Error, no tag inputed try again",Toast.LENGTH_SHORT).show();

            }
            else if(checkIfTagExist(holder, text)){
                Toast.makeText(view.getContext(), "Tag " + text + " removed :D" ,Toast.LENGTH_SHORT).show();
                //TODO ADD REMOVE FUNCITON
            }

            else{
                Toast.makeText(view.getContext(), "Tag " + text + " not found, try again D:" ,Toast.LENGTH_SHORT).show();

            }

        });



        confirmAddTagBtn.setOnClickListener(view -> {
            text = editText.getText().toString();
            String[] holder = tagList;



            if(text.equals("")){
                Toast.makeText(view.getContext(), "Error, no tag inputed try again", Toast.LENGTH_SHORT).show();
            }


            else if(holder.length > 20){
                Toast.makeText(view.getContext(), "Tag list full", Toast.LENGTH_SHORT).show();
            }
            else if(checkIfTagExist(holder, text)){
                Toast.makeText(view.getContext(), "Error, tag alreadt in list", Toast.LENGTH_SHORT).show();
            }



            //ads to it
            else{

                holder= addToTagList(text, holder);

                picture.setTags(holder);
                tagListRecylerAdapter.notifyDataSetChanged();
                Toast.makeText(view.getContext(), "Tag added", Toast.LENGTH_SHORT).show();
            }

        });

        DELETE_PICTURE.setOnClickListener(view -> {
            Toast.makeText(view.getContext(),
                    "Picture Deleted",
                    Toast.LENGTH_SHORT).show();

            Intent intentb = new Intent(view.getContext(), Viewer.class);


            intentb.putExtra("activity","all");
            intentb.putExtra("target","");
            startActivity(intentb);

            //pictureRecycleAdapter.removePicture(position);
        });

    }

    private String[] addToTagList(String text, String[] holder) {
        for(String tagToAdd: holder){
            if(tagToAdd.equals("")){
                tagToAdd= text;
                return holder;
            }
        }
        return holder;
    }


    private boolean checkIfTagExist(String[] holder, String tag) {
        if(holder != null && holder.length == 0) {return false;}

        assert holder != null;
        for (String s : holder) {
            if (s.equals(tag.toLowerCase())) {
                return true;
            }
        }
        return  false;
    }

    private void showAddInput() {
        editText.setVisibility(View.VISIBLE);
        confirmAddTagBtn.setVisibility(View.VISIBLE);
        cancelBtn.setVisibility(View.VISIBLE);
        DELETE_PICTURE.setVisibility(View.INVISIBLE);
        addTagBtn.setVisibility(View.INVISIBLE);
        removeTagBtn.setVisibility(View.INVISIBLE);
    }

    private void hideAddInput(){
        editText.setVisibility(View.INVISIBLE);

        confirmAddTagBtn.setVisibility(View.INVISIBLE);
        cancelBtn.setVisibility(View.INVISIBLE);
        confirmDeleteBtn.setVisibility(View.INVISIBLE);

        DELETE_PICTURE.setVisibility(View.VISIBLE);
        addTagBtn.setVisibility(View.VISIBLE);
        removeTagBtn.setVisibility(View.VISIBLE);
    }

    private void showDeleteTagInput() {
        addTagBtn.setVisibility(View.INVISIBLE);
        removeTagBtn.setVisibility(View.INVISIBLE);
        DELETE_PICTURE.setVisibility(View.INVISIBLE);

        cancelBtn.setVisibility(View.VISIBLE);
        editText.setVisibility(View.VISIBLE);
        confirmDeleteBtn.setVisibility(View.VISIBLE);
    }

    private void setUpRecycleView(String[] tagList){
        RecyclerView tagListRecycleView = findViewById(R.id.tagRecyclerView);

        tagListRecycleView.setAdapter(new TagListRecylerAdapter(this, tagList));
        tagListRecycleView.setLayoutManager(new GridLayoutManager(this, 3));
    }


    public String[] sortList(String[] tagList){
        int count = 0;
        for (String s : tagList) {
            if (!s.equals("") ) {
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
