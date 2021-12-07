package com.example.flashntag;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.flashntag.adapter.PictureRecycleAdapter;
import com.example.flashntag.modeller.Picture;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.List;

//Temporary holder for recucleviwer
public class Viewer extends AppCompatActivity {
    private static final String TAG = Viewer.class.getSimpleName();


    private List<Picture> pictureList;

    private FirebaseFirestore firestoreDb;
    private CollectionReference pictureColReference;
    private ListenerRegistration fireStorelistenerRegistration;


    private PictureRecycleAdapter pictureRecycleAdapter;
    private RecyclerView pictureRecuycleView;
    private String targetSent = "";
    private String typeOfView = "";




    //used
    private String activitySentFrom;
    private String targetFromActivity;
    private List<Picture> picturessToDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);

        Intent intent = getIntent();


        typeOfView = intent.getStringExtra("activity");
        targetSent = intent.getStringExtra("target");


        firestoreDb = FirebaseFirestore.getInstance();
        pictureColReference = firestoreDb.collection("pictures");
        //get the date or create the data


        picturessToDb = Picture.getData("", "");

        //for first time
        /*for(Picture pictures : picturessToDb){
            pictureReference.add(pictures);
        }*/
        setUpRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        createFirestoreReadListener();

        // if(fireStorelistenerRegistration != null){
        //     fireStorelistenerRegistration.remove();
        // }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(fireStorelistenerRegistration != null){

        }
    }

    private void setUpRecyclerView() {
        pictureRecuycleView = findViewById(R.id.recycleViewPage);
        pictureRecycleAdapter = new PictureRecycleAdapter(this, pictureList);


        //gets the lists

        pictureRecuycleView.setAdapter(new PictureRecycleAdapter(this, Picture.getData(typeOfView, targetSent)));

        pictureRecuycleView.setLayoutManager(new GridLayoutManager(this, 3));

    }


    //Down prioritsed
    private void createFirestoreReadListener() {
        pictureColReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        Picture picture = documentSnapshot.toObject(Picture.class);
                        //picture.setPictureID(documentSnapshot.getId());
                        pictureList.add(picture);
                        pictureIDList.add(picture.getPictureID());
                    }
                    pictureRecycleAdapter.notifyDataSetChanged();
                } else {
                    Log.d(TAG, "Error getting documents " + task.getException());
                }
            }
        });

        fireStorelistenerRegistration = pictureColReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot quereySnapshot, @Nullable FirebaseFirestoreException e) {


                if (e != null) {
                    Log.w(TAG, "Listened failed.", e);
                    return;
                }
                for (DocumentChange documentChange : quereySnapshot.getDocumentChanges()) {
                    QueryDocumentSnapshot documentSnapshot = documentChange.getDocument();
                    Picture picture = documentSnapshot.toObject(Picture.class);
                    Integer newid = Integer.valueOf(documentSnapshot.getId());
                    picture.setPictureID(newid);
                    int pos = pictureList.indexOf(picture.getPictureID());

                    switch (documentChange.getType()) {
                        case ADDED:
                            pictureList.add(picture);
                            pictureIDList.add(picture.getPictureID());
                            pictureRecycleAdapter.notifyItemInserted(pictureList.size());
                        case REMOVED:
                            pictureList.remove(pos);
                            pictureIDList.remove(pos);
                            pictureRecycleAdapter.notifyItemRemoved(pos);
                            ;
                        case MODIFIED:
                            pictureList.set(pos, picture);
                            pictureRecycleAdapter.notifyItemChanged(pos);
                            break;
                    }
                }
            }
        });


    }
}

