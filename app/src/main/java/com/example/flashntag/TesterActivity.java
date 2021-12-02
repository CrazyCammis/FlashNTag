package com.example.flashntag;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TesterActivity extends AppCompatActivity {
    private String activitySentFrom;
    private String targetFromActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tester);

        Intent intent = getIntent();





        int holder = intent.getIntExtra("activity", 1);
       activitySentFrom = Integer.toString(holder);
        targetFromActivity = intent.getStringExtra("target");





        TextView viewa= findViewById(R.id.targetText);
        TextView viewb= findViewById(R.id.activitySentFromText);

        viewb.setText(activitySentFrom);
        viewa.setText(targetFromActivity);
    }
}
