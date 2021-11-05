package com.example.flashntag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toolbar;

import com.example.flashntag.adapter.PictureRecycleAdapter;
import com.example.flashntag.modeller.Picture;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

import


import java.util.List;

//Temporary holder for recucleviwer
public class Viewer extends AppCompatActivity {
    private List<Picture> pictureList;
    private List<Integer> pictureIDList;

    private List<Picture> holder;

    private  FirebaseFirestore firestoreDb;
    private CollectionReference pictureReference;
    private ListenerRegistration fireStorelistenerRegistration;


    private PictureRecycleAdapter pictureRecycleAdapter;
    private  RecyclerView pictureRecuycleView;
    private String targetSent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);

      // pictureList = new ArrayList<>();
       // pictureIDList = new ArrayList<Integer>();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("RecyclerVier");

        //setSupportActionBar(toolbar); GET HELP FROM ASSISTANT


        firestoreDb = FirebaseFirestore.getInstance();

        pictureReference = firestoreDb.collection("pictures");

        //get the date or create the data




        setUpRecyclerView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //createFirestoreReadListener();

        if(fireStorelistenerRegistration != null){
            fireStorelistenerRegistration.remove();
        }
    }
/*
    private void createFirestoreReadListener() {/*
        pictureReference.get().addOnCompleteListener(@NonNull Task<QuerySnapshot> task){
            if (task.isSucssesful()){
                for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                    Picture picture =  documentSnapshot.toObject(Picture.class);
                    picture.setPictureID(documentSnapshot.getId());
                    pictureList.add(picture);
                    pictureIDList.add(picture.getPictureID());
                }
                pictureRecycleAdapter.notifyDataSetChanged();
            }
            else{
                Log.d(TAG, "Error getting documents " + task.getException);
            }
        });*/
/*
       fireStorelistenerRegistration =  pictureReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot quereySnapshot, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    Log.w(TAG, "Listened failed.", e);
                    return;
                }
                for(DocumentChange documentChange : quereySnapshot.getDocumentChanges()){
                    QueryDocumentSnapshot documentSnapshot = documentChange.getDocument();
                    Picture picture = documentSnapshot.toObject(Picture.class);
                    Integer newid = Integer.valueOf(documentSnapshot.getId());
                    picture.setPictureID(newid);
                    int pos = pictureList.indexOf(picture.getPictureID());

                    switch (documentChange.getType()){
                        case ADDED:
                            pictureList.add(picture);
                            pictureIDList.add(picture.getPictureID());
                            pictureRecycleAdapter.notifyItemInserted(pictureList.size());
                        case REMOVED:
                            pictureList.remove(pos);
                            pictureIDList.remove(pos);
                            pictureRecycleAdapter.notifyItemRemoved(pos);;
                        case MODIFIED:
                            pictureList.set(pos, picture);
                            pictureRecycleAdapter.notifyItemChanged(pos);
                            break;
                    }
                }
            }
        }

        );
    }
*/


    //For when we getting a dynamic one
    private void createSpesificList(String type){
        for(int i = 0; i <pictureList.size(); i ++){
            Picture picture = pictureList.get(i);
            String[] tags = picture.getTags();

            for(int i2 = 0; i <tags.length; i2++) {
                if (tags[i2] == type){
                    holder.add(picture);
                }
            }
        }
        pictureList = holder;
    }


    //method for creating a list based on date
    private void createDatelist(String dateSent ) {
        for(int i = 0; i <pictureList.size(); i ++){
            Picture picture = pictureList.get(i);

            if(picture.getDate() = dateSent){
                holder.add(picture)
            }
        }
        pictureList = holder;
    }

    private  void setTypeDisplay(String type){
        switch(type){
            case "tag":
                createSpesificList(targetSent);
                break;


                //favorited should be a reserved tag
            case "favorite" :
                createSpesificList("favorited");
                break;

            case "date" :
                createDatelist(targetSent);
                break;



            default:break;
        }
    }



    private void setUpRecyclerView(){
         pictureRecuycleView = findViewById(R.id.recycleViewPage);
        pictureRecycleAdapter = new PictureRecycleAdapter(this, pictureList );

        pictureRecuycleView.setAdapter(new PictureRecycleAdapter(this, Picture.getData()));

        pictureRecuycleView.setLayoutManager(new GridLayoutManager(this, 3));

    }
}