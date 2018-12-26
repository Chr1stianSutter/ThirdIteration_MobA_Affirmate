package com.example.christian.thirditeration_moba_affirmate;

import android.content.Context;
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
    public static ArrayList<Affirmation> affirmations;
    public static TinyDB myTinydb;
    String myKeyString;
    String myAffirmationsKey;
    public static Integer listCount;
    //ArrayList<Affirmation> affirmationsInPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        affirmations = new ArrayList<>();

        initializeData();

        listCount = getIntent().getIntExtra("listCounter", 0);

        Affirmation newlyAddedAffirmation = (Affirmation) getIntent().getParcelableExtra("myNewAffirmation");
        if (newlyAddedAffirmation != null) {
            //listCount ++;
            //affirmations.add(myTinydb.getObject("testKeyNewCard", Affirmation.class));
            makeAffirmationsListKey();
            //myTinydb.putObject(myAffirmationsKey, newlyAddedAffirmation);
            //myTinydb.putObject(getIntent().getStringExtra("key"), newlyAddedAffirmation);
            //affirmations.add(newlyAddedAffirmation);

            //affirmations.add(myTinydb.getObject("affirmation3", Affirmation.class));

            if (listCount < 11){
                int size = listCount;
                Toast.makeText(getApplicationContext(), Integer.toString(listCount), Toast.LENGTH_SHORT).show();

                if(size == 1){
                    //key = "affirmation0";
                    //myTinydb.putObject("affirmation0", newlyAddedAffirmation);
                    myTinydb.putObject("affirmation1", newlyAddedAffirmation);
                    affirmations.add(myTinydb.getObject("affirmation1", Affirmation.class));
                }else if(size == 2){
                    //key = "affirmation1";
                    myTinydb.putObject("affirmation2", newlyAddedAffirmation);
                    affirmations.add(myTinydb.getObject("affirmation1", Affirmation.class));
                    affirmations.add(myTinydb.getObject("affirmation2", Affirmation.class));
                }else if(size == 3){
                    //key = "affirmation2";
                    myTinydb.putObject("affirmation3", newlyAddedAffirmation);
                    affirmations.add(myTinydb.getObject("affirmation1", Affirmation.class));
                    affirmations.add(myTinydb.getObject("affirmation2", Affirmation.class));
                    affirmations.add(myTinydb.getObject("affirmation3", Affirmation.class));
                }else if(size == 3){
                    //key = "affirmation3";
                    myTinydb.putObject("affirmation4", newlyAddedAffirmation);
                }else if(size == 4){
                    //key = "affirmation4";
                    myTinydb.putObject("affirmation5", newlyAddedAffirmation);
                }else if(size == 5){
                    //key = "affirmation5";
                    myTinydb.putObject("affirmation6", newlyAddedAffirmation);
                }else if(size == 6){
                    //key = "affirmation6";
                    myTinydb.putObject("affirmation7", newlyAddedAffirmation);
                }else if(size == 7){
                    //key = "affirmation7";
                    myTinydb.putObject("affirmation8", newlyAddedAffirmation);
                }else if(size == 8){
                    //key = "affirmation8";
                    myTinydb.putObject("affirmation9", newlyAddedAffirmation);
                }else if(size == 9){
                    //key = "affirmation9";
                    myTinydb.putObject("affirmation10", newlyAddedAffirmation);
                }else if(size == 10){
                    //key = "affirmation10";

                }
            }
            Toast.makeText(getApplicationContext(), Integer.toString(affirmations.size()), Toast.LENGTH_SHORT).show();




            /*
            //if(affirmations.size() != 0) {
                for (int i = 0; i < affirmations.size(); i++) {
                    MakeKeyString(i);
                    Toast.makeText(getApplicationContext(), myKeyString, Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(), Integer.toString(affirmations.size()), Toast.LENGTH_SHORT).show();
                    affirmations.add(myTinydb.getObject(myKeyString, Affirmation.class));
                }
            //}
            */
        }
        Integer myListLength = getIntent().getIntExtra("listLength", 5);




        Context context = this;
        myTinydb = new TinyDB(context);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        //initializeData();
        RVAdapter adapter = new RVAdapter(affirmations);
        rv.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), NewCardActivity.class);
                myIntent.putExtra("myListLengthValue", affirmations.size());
                startActivityForResult(myIntent,0);
                //startActivity(myIntent);
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

    private void makeAffirmationsListKey(){
        //myAffirmations = MainActivity.getAffirmations();
        //listLength = myAffirmations.size();
        //listLengthMinusOne = (listLength);
        myAffirmationsKey = "affirmation"+affirmations.size();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        listCount = getIntent().getIntExtra("listCounter", 0);
        //Do your work here in ActivityA

        //Loop through all cards in edit pref
        /*
        Affirmation newlyAddedAffirmation = (Affirmation) getIntent().getParcelableExtra("myNewAffirmation");
        if (newlyAddedAffirmation != null) {

            //affirmations.add(myTinydb.getObject("testKeyNewCard", Affirmation.class));
            affirmations.add(newlyAddedAffirmation);
        }
        */

        initializeData();
/*
        if (affirmations.size() < 11){
            int size = affirmations.size();
            Toast.makeText(getApplicationContext(), Integer.toString(affirmations.size()), Toast.LENGTH_SHORT).show();

            if(size == 0){
                //key = "affirmation0";
                //myTinydb.putObject("affirmation0", newlyAddedAffirmation);
                //affirmations.add(myTinydb.getObject("affirmation0", Affirmation.class));
            }else if(size == 1){
                //key = "affirmation1";
                //myTinydb.putObject("affirmation1", newlyAddedAffirmation);
                affirmations.add(myTinydb.getObject("affirmation1", Affirmation.class));
            }else if(size == 2){
                //key = "affirmation2";
                //myTinydb.putObject("affirmation2", newlyAddedAffirmation);
                affirmations.add(myTinydb.getObject("affirmation1", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation2", Affirmation.class));
            }else if(size == 3){
                //key = "affirmation3";
                //myTinydb.putObject("affirmation3", newlyAddedAffirmation);
                affirmations.add(myTinydb.getObject("affirmation1", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation2", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation3", Affirmation.class));
            }else if(size == 4){
                //key = "affirmation4";
                //myTinydb.putObject("affirmation4", newlyAddedAffirmation);
                affirmations.add(myTinydb.getObject("affirmation1", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation2", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation3", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation4", Affirmation.class));
            }else if(size == 5){
                //key = "affirmation5";
                //myTinydb.putObject("affirmation5", newlyAddedAffirmation);
                affirmations.add(myTinydb.getObject("affirmation1", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation2", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation3", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation4", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation5", Affirmation.class));
            }else if(size == 6){
                affirmations.add(myTinydb.getObject("affirmation1", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation2", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation3", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation4", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation5", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation6", Affirmation.class));
                //key = "affirmation6";
                //myTinydb.putObject("affirmation6", newlyAddedAffirmation);
            }else if(size == 7){
                affirmations.add(myTinydb.getObject("affirmation1", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation2", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation3", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation4", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation5", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation6", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation7", Affirmation.class));
                //key = "affirmation7";
                //myTinydb.putObject("affirmation7", newlyAddedAffirmation);
            }else if(size == 8){
                //key = "affirmation8";
                //myTinydb.putObject("affirmation8", newlyAddedAffirmation);
                affirmations.add(myTinydb.getObject("affirmation1", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation2", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation3", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation4", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation5", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation6", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation7", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation8", Affirmation.class));
            }else if(size == 9){
                //key = "affirmation9";
                //myTinydb.putObject("affirmation9", newlyAddedAffirmation);
                affirmations.add(myTinydb.getObject("affirmation1", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation2", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation3", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation4", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation5", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation6", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation7", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation8", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation9", Affirmation.class));
            }else if(size == 10){
                //key = "affirmation9";
                //myTinydb.putObject("affirmation9", newlyAddedAffirmation);
                affirmations.add(myTinydb.getObject("affirmation1", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation2", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation3", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation4", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation5", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation6", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation7", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation8", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation9", Affirmation.class));
                affirmations.add(myTinydb.getObject("affirmation10", Affirmation.class));
            }
        }
        */
    }



    public void initializeData(){
        /*
        Affirmation newAffirmation = new Affirmation("I am a capable UX-Designer", true, false, false, "8:30 AM", true);
        myTinydb.putObject("testKey", newAffirmation);
        affirmations.add(myTinydb.getObject("testKey", Affirmation.class));

        if(myTinydb.getObject("affirmation1", Affirmation.class) != null){
            affirmations.add(myTinydb.getObject("affirmation1", Affirmation.class));
        }
        if(myTinydb.getObject("affirmation2", Affirmation.class) != null){
            affirmations.add(myTinydb.getObject("affirmation2", Affirmation.class));
        }
        if(myTinydb.getObject("affirmation2", Affirmation.class) != null){
            affirmations.add(myTinydb.getObject("affirmation3", Affirmation.class));
        }
        if(myTinydb.getObject("affirmation4", Affirmation.class) != null){
            affirmations.add(myTinydb.getObject("affirmation4", Affirmation.class));
        }
        if(myTinydb.getObject("affirmation5", Affirmation.class) != null){
            affirmations.add(myTinydb.getObject("affirmation5", Affirmation.class));
        }
        if(myTinydb.getObject("affirmation5", Affirmation.class) != null){
            affirmations.add(myTinydb.getObject("affirmation6", Affirmation.class));
        }
        if(myTinydb.getObject("affirmation7", Affirmation.class) != null){
            affirmations.add(myTinydb.getObject("affirmation7", Affirmation.class));
        }
        if(myTinydb.getObject("affirmation8", Affirmation.class) != null){
            affirmations.add(myTinydb.getObject("affirmation8", Affirmation.class));
        }
        if(myTinydb.getObject("affirmation9", Affirmation.class) != null){
            affirmations.add(myTinydb.getObject("affirmation9", Affirmation.class));
        }
        if(myTinydb.getObject("affirmation10", Affirmation.class) != null){
            affirmations.add(myTinydb.getObject("affirmation10", Affirmation.class));
        }
*/





    }

    public void MakeKeyString(int i){
        myKeyString = ("affirmation"+i);
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(affirmations);
        rv.setAdapter(adapter);

    }



    public void fabButtonOnClick(View v){

        if(affirmations.size() < 10) {
            Intent myIntent = new Intent(getBaseContext(), NewCardActivity.class);
            myIntent.putExtra("ListCountInteger", listCount);
            startActivity(myIntent);
        }else{
            Toast.makeText(getApplicationContext(), "No more than 10 affirmations can be stored, please delete one before you add another", Toast.LENGTH_SHORT).show();
        }
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

    public static TinyDB getTinydb() {
        return myTinydb;
    }
    public static ArrayList getAffirmations() {
        return affirmations;
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
