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
    ArrayList<String> myKeyList;
    String myKey;
    Button enableDisableButton;
    Button editCardButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        affirmations = new ArrayList<Affirmation>();
        myKeyList = new ArrayList<String>();
        enableDisableButton = (Button) findViewById(R.id.disableButtonPressed);
        editCardButton = (Button) findViewById(R.id.editButtonPressed);

        //Affirmation newAffirmation = new Affirmation("I am a capable UX-Designer", true, false, false, "8:30 AM", true);
        //initializeData();

        //testList = affirmations;
        //myTinydb.putListObject("savedAffirmations", testList);

        Affirmation newlyAddedAffirmation = (Affirmation) getIntent().getParcelableExtra("myNewAffirmation");
        if (newlyAddedAffirmation != null) {
            //tvTitle.setText("Title: " + movie.title);
            //tvYear.setText("Year: " + Integer.toString(movie.year));
            //affirmations.add(myTinydb.getObject("testKeyNewCard", Affirmation.class));

            //MakeKey(myTinydb.getListObject("savedAffirmations", ArrayList.class).size());
            MakeKey(myTinydb.getListString("myKeys").size());
            myTinydb.putObject(myKey, newlyAddedAffirmation);
            //affirmations.add(newlyAddedAffirmation);
            //myTinydb.putListObject("savedAffirmations", testList);

            //myTinydb.putListObject("key", ArrayList<Affirmatio  affirmations);

        }
        


        Context context = this;
        myTinydb = new TinyDB(context);
        myTinydb.putListString("myKeys", myKeyList);



        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        final RVAdapter adapter = new RVAdapter(affirmations);
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new RVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onEnabledDisabledButtonClick(int position) {
                enableDisableItem(position);
                Toast.makeText(MainActivity.this, affirmations.get(position).isEnabled.toString(), Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onEditButtonClick(int position) {
                /*
                Intent myIntent = new Intent(getBaseContext(), EditCardActivity.class);
                startActivityForResult(myIntent,0);
                */
                Toast.makeText(MainActivity.this, "EDIT", Toast.LENGTH_SHORT).show();
            }
        });

        /*
        enableDisableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int position = Integer.parseInt(editTextRemove.getText().toString());
                //removeItem(position);

                //int position = adapter.get
                //int position = Integer.parseInt(())


                //enableDisableItem(position);

                //Toast.makeText(MainActivity.this, affirmations.get(position).isEnabled.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        */


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), NewCardActivity.class);
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

        //initializeData();

    }

    public void enableDisableItem(int position){
        if(affirmations.get(position).isEnabled == true){
            affirmations.get(position).isEnabled = false;
        }else{
            affirmations.get(position).isEnabled = true;
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //Do your work here in ActivityA

        //Loop through all cards in edit pref

        Affirmation newlyAddedAffirmation = (Affirmation) getIntent().getParcelableExtra("myNewAffirmation");
        if (newlyAddedAffirmation != null) {
            //tvTitle.setText("Title: " + movie.title);
            //tvYear.setText("Year: " + Integer.toString(movie.year));
            //affirmations.add(myTinydb.getObject("testKeyNewCard", Affirmation.class));
            //affirmations.add(newlyAddedAffirmation);
        }


        //initializeData();


    }



    public void initializeData(){


        /*
        Affirmation newAffirmation = new Affirmation("I am a capable UX-Designer", true, false, false, "8:30 AM", true);
        myTinydb.putObject("affirmation0", newAffirmation);
        myKeyList.add("affirmation0");
        myTinydb.putListString("myKeys", myKeyList);
        */
        //affirmations.add(myTinydb.getObject("affirmation0", Affirmation.class));

        /*
        for(int i = 0; i < myTinydb.getListObject("savedAffirmations", ArrayList.class).size(); i++){
            MakeKey(i);
            affirmations.add(myTinydb.getObject(myKey, Affirmation.class));
            */

        for(int i = 0; i < myTinydb.getListString("myKeys").size(); i++){
            myKey = myTinydb.getListString("myKeys").get(i);
            affirmations.add(myTinydb.getObject(myKey, Affirmation.class));
        }

        Toast.makeText(getApplicationContext(), Integer.toString(myTinydb.getListString("myKeys").size()), Toast.LENGTH_SHORT).show();


    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(affirmations);
        rv.setAdapter(adapter);

    }

    public void MakeKey(int i){
        myKey = "affirmation"+(i);
        myKeyList = myTinydb.getListString("myKeys");
        myKeyList.add(myKey);
        myTinydb.putListString("myKeys", myKeyList);

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
