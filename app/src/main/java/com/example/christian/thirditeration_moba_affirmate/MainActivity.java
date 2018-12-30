package com.example.christian.thirditeration_moba_affirmate;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.SubMenu;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;



import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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
    ImageView enabledFlag;
    TinyDB refreshedMyTinyDB;

    //NavigationView menuNavigationViewEnabledCards;
    //NavigationView getMenuNavigationViewDisabledCards;
    //NavigationView navigationView;


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
        enabledFlag = (ImageView) findViewById(R.id.enabledIndicatorImageView);

        //menuNavigationViewEnabledCards = (NavigationView) findViewById(R.id.navigationViewEnabledAffirmations);
        //getMenuNavigationViewDisabledCards = (NavigationView) findViewById(R.id.navigationViewDisabledAffirmations);
        //navigationView = (NavigationView) findViewById(R.id.navigationView);

        //Affirmation newAffirmation = new Affirmation("I am a capable UX-Designer", true, false, false, "8:30 AM", true);
        //initializeData();

        //testList = affirmations;
        //myTinydb.putListObject("savedAffirmations", testList);


        if (getIntent().getParcelableExtra("myNewAffirmation") != null) {
            Affirmation newlyAddedAffirmation = (Affirmation) getIntent().getParcelableExtra("myNewAffirmation");

        //if (newlyAddedAffirmation != null) {
       // if (getIntent().getParcelableExtra("myNewAffirmation") != null) {

            //tvTitle.setText("Title: " + movie.title);
            //tvYear.setText("Year: " + Integer.toString(movie.year));
            //affirmations.add(myTinydb.getObject("testKeyNewCard", Affirmation.class));
            //MakeKey(myTinydb.getListObject("savedAffirmations", ArrayList.class).size());
            //if(getIntent().getParcelableExtra("myEditedAffirmation") != null) {
            MakeKey(myTinydb.getListString("myKeys").size());

            newlyAddedAffirmation.affirmationKeyString = myKey;
            //myKey = newlyAddedAffirmation.affirmationKeyString;
            myTinydb.putObject(myKey, newlyAddedAffirmation);
            //getIntent().removeExtra("myNewAffirmation");
            this.getIntent().removeExtra("myNewAffirmation");
             }
            //affirmations.add(newlyAddedAffirmation);
            //myTinydb.putListObject("savedAffirmations", testList);
            //myTinydb.putListObject("key", ArrayList<Affirmatio  affirmations);
        //}



   // }
        Affirmation newlyEditedAffirmation = (Affirmation) getIntent().getParcelableExtra("myEditedAffirmation");
        //if (newlyEditedAffirmation != null) {
        if (getIntent().getParcelableExtra("myEditedAffirmation") != null){

            //MakeKey(myTinydb.getListString("myKeys").size());
            myKey = newlyEditedAffirmation.affirmationKeyString;
            myTinydb.putObject(myKey, newlyEditedAffirmation);
            this.getIntent().removeExtra("myEditedAffirmation");
            //getIntent().removeExtra("myEditedAffirmation");

        }


        Context context = this;
        myTinydb = new TinyDB(context);
        myTinydb.putListString("myKeys", myKeyList);



        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        //initializeMenu();

        final RVAdapter adapter = new RVAdapter(affirmations);
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new RVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onEnabledDisabledButtonClick(int position) {
                //initializeMenu();
                enableDisableItem(position, enableDisableButton);
                adapter.notifyItemChanged(position);
                //myTinydb = RVAdapter.getRefreshedMyTinyDB();
                initializeMenu();
                //refreshMenu();
                //adapter.notifyItemChanged(position);
                //Toast.makeText(MainActivity.this, affirmations.get(position).isEnabled.toString(), Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onEditButtonClick(int position) {
                /*
                Intent myIntent = new Intent(getBaseContext(), EditCardActivity.class);
                startActivityForResult(myIntent,0);
                */


                //Toast.makeText(MainActivity.this, "EDIT", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(getBaseContext(), EditCardActivity.class);
                myIntent.putExtra("myEditAffirmation", affirmations.get(position));
                myIntent.removeExtra("myNewAffirmation");

                startActivityForResult(myIntent,0);
                //setResult(RESULT_OK,myIntent );
                //startActivity(myIntent);

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

         /*
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
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        initializeMenu();
        /*
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        final Menu menuAffirmations = navigationView.getMenu();
        final SubMenu subMenuEnabled = menuAffirmations.addSubMenu("Enabled Affirmations");
        final SubMenu subMenuDisabled = menuAffirmations.addSubMenu("Disabled Affirmations");

        if(myTinydb.getListString("myKeys").size() > 0) {

            for (int i = 0; i < myTinydb.getListString("myKeys").size(); i++) {

                myKey = myTinydb.getListString("myKeys").get(i);
                //affirmations.add(myTinydb.getObject(myKey, Affirmation.class));
                if(myTinydb.getObject(myKey, Affirmation.class).isEnabled == true) {
                    subMenuEnabled.add(myTinydb.getObject(myKey, Affirmation.class).affirmation);
                }else if(myTinydb.getObject(myKey, Affirmation.class).isEnabled == false){
                    subMenuDisabled.add(myTinydb.getObject(myKey, Affirmation.class).affirmation);
                }
            }

        }
        */
        //initializeData();

    }

    public void enableDisableItem(int position, Button disableButton){
        if(affirmations.get(position).isEnabled == true){

            affirmations.get(position).isEnabled = false;
            //adapter.notifyItemChanged(position);
            //disableButton.findViewById(R.id.disableButtonPressed);
            //disableButton.setText("Enable");
            //Toast.makeText(MainActivity.this, "Disabled", Toast.LENGTH_SHORT).show();

        }else{

            affirmations.get(position).isEnabled = true;
            ///adapter.notifyItemChanged(position);
            //disableButton.findViewById(R.id.disableButtonPressed);
            //disableButton.setText("Disable");
            //Toast.makeText(MainActivity.this, "Enabled", Toast.LENGTH_SHORT).show();

        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //Do your work here in ActivityA
        //initializeData();
        //Loop through all cards in edit pref
        /*
        Affirmation newlyAddedAffirmation = (Affirmation) getIntent().getParcelableExtra("myNewAffirmation");
        if (getIntent().getParcelableExtra("myNewAffirmation") != null) {
            getIntent().removeExtra("myEditedAffirmation");
            //tvTitle.setText("Title: " + movie.title);
            //tvYear.setText("Year: " + Integer.toString(movie.year));
            //affirmations.add(myTinydb.getObject("testKeyNewCard", Affirmation.class));
            //affirmations.add(newlyAddedAffirmation);
        }
        if (getIntent().getParcelableExtra("myEditedAffirmation") != null){
            getIntent().removeExtra("myNewAffirmation");
        }
        */

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

    public void  refreshMyTinyDB(TinyDB db){

    }

    public void initializeMenu(){

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().clear();

        final Menu menuAffirmations = navigationView.getMenu();
        final SubMenu subMenuEnabled = menuAffirmations.addSubMenu("Enabled Affirmations");
        final SubMenu subMenuDisabled = menuAffirmations.addSubMenu("Disabled Affirmations");


        if(myTinydb.getListString("myKeys").size() > 0) {

            for (int i = 0; i < myTinydb.getListString("myKeys").size(); i++) {

                myKey = myTinydb.getListString("myKeys").get(i);
                //affirmations.add(myTinydb.getObject(myKey, Affirmation.class));
                if(myTinydb.getObject(myKey, Affirmation.class).isEnabled == true) {
                    subMenuEnabled.add(myTinydb.getObject(myKey, Affirmation.class).affirmation);
                }else if(myTinydb.getObject(myKey, Affirmation.class).isEnabled == false){
                    subMenuDisabled.add(myTinydb.getObject(myKey, Affirmation.class).affirmation);
                }
            }

        }


    }

    /*
    public void refreshMenu(){
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().clear();

        final Menu menuAffirmations = navigationView.getMenu();
        final SubMenu subMenuEnabled = menuAffirmations.addSubMenu("Enabled Affirmations");
        final SubMenu subMenuDisabled = menuAffirmations.addSubMenu("Disabled Affirmations");


        if(RVAdapter.getRefreshedMyTinyDB().getListString("myKeys").size() > 0) {

            for (int i = 0; i < RVAdapter.getRefreshedMyTinyDB().getListString("myKeys").size(); i++) {

                myKey = RVAdapter.getRefreshedMyTinyDB().getListString("myKeys").get(i);
                //affirmations.add(myTinydb.getObject(myKey, Affirmation.class));
                if(RVAdapter.getRefreshedMyTinyDB().getObject(myKey, Affirmation.class).isEnabled == true) {
                    subMenuEnabled.add(RVAdapter.getRefreshedMyTinyDB().getObject(myKey, Affirmation.class).affirmation);
                }else if(RVAdapter.getRefreshedMyTinyDB().getObject(myKey, Affirmation.class).isEnabled == false){
                    subMenuDisabled.add(RVAdapter.getRefreshedMyTinyDB().getObject(myKey, Affirmation.class).affirmation);
                }
            }

        }
    }
    */

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
        /*
        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        */
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
