package com.example.christian.thirditeration_moba_affirmate;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;



import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView myTextView;
    Typeface montserratFont;
    int count = 0;
    private RecyclerView rv;
    private List<Affirmation> affirmations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        RVAdapter adapter = new RVAdapter(affirmations);
        rv.setAdapter(adapter);

        /*
        myTextView = findViewById(R.id.TextViewMakeBold);
        montserratFont = Typeface.createFromAsset(this.getAssets(), "fonts/Montserrat-ExtraBold.ttf");
        myTextView.setTypeface(montserratFont);
        */

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), NewCardActivity.class);
                startActivity(myIntent);
            }
        });

        Button editButton = (Button) findViewById(R.id.editButtonPressed);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Edit", Toast.LENGTH_SHORT).show();
            }
        });

        final Button disableButton = (Button) findViewById(R.id.disableButtonPressed);
        disableButton.setOnClickListener(new View.OnClickListener() {
            @Override

            // int count = 0;
            public void onClick(View view) {
                if(count%2 == 1 ){
                    disableButton.setText("Disable");
                    Toast.makeText(MainActivity.this, "Enabled", Toast.LENGTH_SHORT).show();

                }else{
                    disableButton.setText("Enable");
                    Toast.makeText(MainActivity.this, "Disabled", Toast.LENGTH_SHORT).show();
                }

                count++;
                if(count == 10){count = 0;}


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void initializeData(){
        affirmations = new ArrayList<>();
        affirmations.add(new Affirmation("I am a capable UX-Designer", true, false, false, "8:30 AM"));
        affirmations.add(new Affirmation("Test Affirmation", false, true, false, "9:30 AM"));

        //affirmations.add(new Affirmation("", "", "", "", ""));
        //affirmations.add(new Affirmation("", "", "", "", ""));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(affirmations);
        rv.setAdapter(adapter);


    }

    public void fabButtonOnClick(View v){
        Intent myIntent = new Intent(getBaseContext(), NewCardActivity.class);
        startActivity(myIntent);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
