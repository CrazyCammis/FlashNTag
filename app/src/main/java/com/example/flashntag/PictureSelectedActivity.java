package com.example.flashntag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class PictureSelectedActivity extends AppCompatActivity {
    public  static  final String TAG = PictureSelectedActivity.class.getSimpleName();

    private EditText editText;
    private String text;
    private int postionInArray;


    private Picture picture;
    private String[] tagList = {};

    private Button addTagBtn;
    private Button confirmAddTagBtn;
    private Button cancelBtn;
    private Button removeTagBtn;
    private Button confirmDeleteBtn;

    private ImageButton DELETE_PICTURE;
    private EditText inpuText;


    private TextView date;
    private ImageView pictureView;

    private PictureRecycleAdapter pictureRecycleAdapter;
    private RecyclerView tagListRecycleView;
    private TagListRecylerAdapter tagListRecylerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_selected);
        Intent intent = getIntent();


        int position = intent.getIntExtra("PIC_ID", 1);


        //sets the ID for the entire file
        pictureView = findViewById(R.id.selectedPicuture);
        date = findViewById(R.id.dateOfPicture);

        addTagBtn = findViewById(R.id.addTagButton);
        DELETE_PICTURE = findViewById(R.id.deletePicture);
        editText = findViewById(R.id.inputText);
        confirmAddTagBtn = findViewById(R.id.submitTagButton);
        cancelBtn = findViewById(R.id.cancellButton);
        inpuText = findViewById(R.id.inputText);
        removeTagBtn = findViewById(R.id.removeTagButton);
        confirmDeleteBtn = findViewById(R.id.confirmDeleteBtn);

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        DocumentReference documentReference = firebaseFirestore.collection("pictures").document(String.valueOf(position));

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    Picture picture = documentSnapshot.toObject(Picture.class);
                    //picture.setPictureID(documentSnapshot.getId());

                    date.setText(picture.getDate().toString());
                    pictureView.setImageResource(picture.getPictureID());
                    tagList= picture.getTags();
                    setUpRecycleView(tagList);
                }
                else{
                    Log.d(TAG, "Get failed with ", task.getException());
                }
            }
        });


/*
        ArrayList<Picture> dataList;
        dataList = (ArrayList<Picture>) Picture.getData("", "");
        picture = dataList.get(position);
*/


        //returns array that only have the tags, removes any ampty one from the count

        //tagList = sortList(tagList);






        //TODO: DELETE FROM GALLERY SEE RECYCLBE VIEW LECTURE 55.00;
                //TODO: REMOVE TAGS



        addTagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddInput();
            }
        });

        removeTagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            showDeleteTagInput();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideAddInput();
            }
        });

        confirmDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

            }
        });



        confirmAddTagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = editText.getText().toString();
                String[] holder = tagList;



                if(text == null || text.equals("")){
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
                    //TODO FIX THIS
                    //adds to the first empty spot
                    holder= addToTagList(text, holder);
                                    //set the new array as the new one
                    picture.setTags(holder);
                    //tagListRecylerAdapter.notifyDataSetChanged();
                    Toast.makeText(view.getContext(), "Tag added", Toast.LENGTH_SHORT).show();
                }

            }
        });

        DELETE_PICTURE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),
                        "Picture Deleted",
                        Toast.LENGTH_SHORT).show();
                pictureRecycleAdapter.removePicture(position);
            }
        });

    }

    private String[] addToTagList(String text, String[] holder) {
        for(String e: holder){
            if(e.equals("")){
                e= text;
                return holder;
            }
        }
        return holder;
    }


    private boolean checkIfTagExist(String[] holder, String tag) {
        if(holder != null && holder.length == 0) {return false;}

        for(int i = 0; i <holder.length; i++){
            if(holder[i].equals(tag.toLowerCase())){
                postionInArray = i;
                return true;}
        }
        return  false;
    }

    private void showAddInput() {
        editText.setVisibility(View.VISIBLE);
        inpuText.setVisibility(View.VISIBLE);
        confirmAddTagBtn.setVisibility(View.VISIBLE);
        cancelBtn.setVisibility(View.VISIBLE);
        DELETE_PICTURE.setVisibility(View.INVISIBLE);
        addTagBtn.setVisibility(View.INVISIBLE);
        removeTagBtn.setVisibility(View.INVISIBLE);
    }

    private void hideAddInput(){
        editText.setVisibility(View.INVISIBLE);
        inpuText.setVisibility(View.INVISIBLE);
        confirmAddTagBtn.setVisibility(View.INVISIBLE);
        cancelBtn.setVisibility(View.INVISIBLE);
        confirmDeleteBtn.setVisibility(View.INVISIBLE);

        DELETE_PICTURE.setVisibility(View.VISIBLE);
        addTagBtn.setVisibility(View.VISIBLE);
        removeTagBtn.setVisibility(View.VISIBLE);
    }

    private void showDeleteTagInput() {
        inpuText.setVisibility(View.INVISIBLE);
        addTagBtn.setVisibility(View.INVISIBLE);
        removeTagBtn.setVisibility(View.INVISIBLE);
        DELETE_PICTURE.setVisibility(View.INVISIBLE);

        cancelBtn.setVisibility(View.VISIBLE);
        editText.setVisibility(View.VISIBLE);
        confirmDeleteBtn.setVisibility(View.VISIBLE);
    }

    private  void hideDeleteTagInput(){
        editText.setVisibility(View.INVISIBLE);
        cancelBtn.setVisibility(View.INVISIBLE);

        inpuText.setVisibility(View.VISIBLE);
        DELETE_PICTURE.setVisibility(View.VISIBLE);
    }

    private void setUpRecycleView(String[] tagList){
        tagListRecycleView = findViewById(R.id.tagRecyclerView);

        tagListRecycleView.setAdapter(new TagListRecylerAdapter(this, tagList));
        tagListRecycleView.setLayoutManager(new GridLayoutManager(this, 3));
    }


    public String[] sortList(String[] tagList){
        int count = 0;
        for(int i = 0; i < tagList.length; i++){
            if(!tagList[i].equals("")||!(tagList[i] == "")){
                count++;
            }
        }

        String[] holder = new String[count];
        for(int i = 0; i < tagList.length; i++){
            if(!tagList[i].equals("") || !(tagList[i] == "")){
                holder[i] = tagList[i];
            }
        }
        return  holder;
    }
}
