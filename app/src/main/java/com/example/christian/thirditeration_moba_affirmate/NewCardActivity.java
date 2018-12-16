package com.example.christian.thirditeration_moba_affirmate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NewCardActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Enter Your Affirmation");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });

        Button cancelButton = (Button) findViewById(R.id.cancelButtonPressed);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });

        Button saveButton = (Button) findViewById(R.id.saveButtonPressed);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });
    }

}
