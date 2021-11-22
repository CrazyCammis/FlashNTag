package com.example.flashntag;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.flashntag.adapter.PictureRecycleAdapter;
import com.example.flashntag.modeller.Picture;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;




import java.util.List;

//Temporary holder for recucleviwer
public class Viewer extends Fragment {
    private static final String TYPE_OF_VIEW = "all";
    private List<Picture> pictureList;
    private List<Integer> pictureIDList;

    private List<Picture> holder;

    private FirebaseFirestore firestoreDb;
    private CollectionReference pictureReference;
    private ListenerRegistration fireStorelistenerRegistration;


    private PictureRecycleAdapter pictureRecycleAdapter;
    private RecyclerView pictureRecuycleView;
    private String targetSent = "";
    private String typeOfView;


    private String mainActivityExtra;
    private String favoritedActivityExtra;

    private String tagsActivityExtra;
    private String tagsToSearchFor;

    private String dateSearchExtra;
    private String dateToSearch;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_viewer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Intent intent = getActivity().getIntent();



        mainActivityExtra = intent.getStringExtra(MainFragment.SEND_CODE);

        tagsActivityExtra = intent.getStringExtra(TagsActivity.SEND_CODE);
        tagsToSearchFor = intent.getStringExtra(TagsActivity.TAG_TO_SERACH);

        favoritedActivityExtra = intent.getStringExtra(MainFragment.SEND_CODE);
        mainActivityExtra = intent.getStringExtra(MainFragment.SEND_CODE);

        /*
        firestoreDb = FirebaseFirestore.getInstance();
        pictureReference = firestoreDb.collection("pictures");
        //get the date or create the data*/



        setUpRecyclerView();
    }

    @Override
    public void onResume() {
        super.onResume();
        //createFirestoreReadListener();

       // if(fireStorelistenerRegistration != null){
       //     fireStorelistenerRegistration.remove();
       // }
    }




    private void setDefault() {
    if (mainActivityExtra.equals("mainTrue")) {
        typeOfView = "all";
        targetSent = "";
    }


    else if (favoritedActivityExtra.equals("favoriteTrue")) {
            typeOfView = "favorite";
            targetSent = "";
    }

    else if (tagsActivityExtra.equals("tagTrue")) {
        typeOfView = "tag";
        targetSent = tagsToSearchFor;
    }

    else if (dateSearchExtra.equals("dateTrue")) {
        typeOfView = "date";
        targetSent = dateToSearch;
    }


}



    private void setUpRecyclerView(){
         pictureRecuycleView = getView().findViewById(R.id.recycleViewPage);
        pictureRecycleAdapter = new PictureRecycleAdapter(getActivity(), pictureList );
        //Had to convert 'this' into getActivity(). hopefully same function


        //gets the lists

        pictureRecuycleView.setAdapter(new PictureRecycleAdapter(getActivity(), Picture.getData(typeOfView, targetSent)));

        pictureRecuycleView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

    }
}


/*
    private void createFirestoreReadListener() {
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
        });

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
