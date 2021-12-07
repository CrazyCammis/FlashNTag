package com.example.flashntag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageButton openGallery, openCamera, openTagActivity, addImageButton;

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private  final int RC_SIGN_IN = 1;

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                @Override
                public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    onSignInResult(result);
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = findViewById(R.id.signOutButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthUI.getInstance().signOut(getApplicationContext())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Signed signed out", Toast.LENGTH_LONG);
                    }
                });
            }
        });

        auth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser currentUser = auth.getCurrentUser();
                if (currentUser == null) {
                    List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.EmailBuilder().build());


                    signInLauncher.launch(AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build());
                }
                else {
                    Toast.makeText(getApplicationContext(), "Signed in as " + currentUser.getDisplayName(), Toast.LENGTH_LONG).show();
                }
            }
        };


        openGallery = findViewById(R.id.openGallery);
        openGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Viewer.class);


                intent.putExtra("activity","all");
                intent.putExtra("target","");
                startActivity(intent);
            }
        });


        openTagActivity = findViewById(R.id.openTag);
        openTagActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),TagsActivity.class );
                startActivity(intent);
            }
        });

    }



    @Override
    protected void onResume() {
        super.onResume();
        auth.addAuthStateListener(authStateListener);
       //createFirestoreReadListener();

        // if(fireStorelistenerRegistration != null){
        //     fireStorelistenerRegistration.remove();
        // }
    }

    @Override
    protected void onPause() {
        super.onPause();
        auth.removeAuthStateListener(authStateListener);
        /*if (fireStorelistenerRegistration != null) {

        }*/
    }


    protected void onSignInResult(FirebaseAuthUIAuthenticationResult result) {

        if (result.getResultCode() == RESULT_OK) {
            FirebaseUser currentUser = auth.getCurrentUser();
            Toast.makeText(getApplicationContext(), "Signed in as " + currentUser.getDisplayName(), Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "Signed in cancelled", Toast.LENGTH_LONG).show();
        }
    }

}