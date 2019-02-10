package com.example.christian.thirditeration_moba_affirmate;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Toast;
import android.content.Intent;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView rv;
    public static ArrayList<Affirmation> affirmations;
    public static TinyDB myTinydb;
    ArrayList<String> myKeyList;
    String myKey;
    Button enableDisableButton;
    Button editCardButton;
    ImageView enabledFlag;

    String substr;
    String before;
    String after;
    Integer hour;
    Integer minute;
    Integer keyRecCode;

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

        if (getIntent().getParcelableExtra("myNewAffirmation") != null) {
            Affirmation newlyAddedAffirmation = (Affirmation) getIntent().getParcelableExtra("myNewAffirmation");

            MakeKey(myTinydb.getListString("myKeys").size());

            newlyAddedAffirmation.affirmationKeyString = myKey;
            myTinydb.putObject(myKey, newlyAddedAffirmation);
            this.getIntent().removeExtra("myNewAffirmation");
             }

        Affirmation newlyEditedAffirmation = (Affirmation) getIntent().getParcelableExtra("myEditedAffirmation");
        if (getIntent().getParcelableExtra("myEditedAffirmation") != null){

            myKey = newlyEditedAffirmation.affirmationKeyString;
            myTinydb.putObject(myKey, newlyEditedAffirmation);
            this.getIntent().removeExtra("myEditedAffirmation");
        }


        Context context = this;
        myTinydb = new TinyDB(context);

        rv = (RecyclerView)findViewById(R.id.rv);

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

                enableDisableItem(position, enableDisableButton);
                adapter.notifyItemChanged(position);
                adapter.notifyDataSetChanged();

               initializeMenu();
            }
            @Override
            public void onEditButtonClick(int position) {

                Intent myIntent = new Intent(getBaseContext(), EditCardActivity.class);
                myIntent.putExtra("myEditAffirmation", affirmations.get(position));
                myIntent.putExtra("myEditAffirmation", myTinydb.getObject(affirmations.get(position).affirmationKeyString,Affirmation.class));

                startActivity(myIntent);
            }

        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getBaseContext(), NewCardActivity.class);
                startActivityForResult(myIntent,0);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        initializeMenu();
    }





    public void enableDisableItem(int position, Button disableButton){

        if(affirmations.get(position).isEnabled == true){
//
            affirmations.get(position).isEnabled = false;
            myTinydb.putObject(affirmations.get(position).affirmationKeyString, affirmations.get(position));

            extractRecCodeFromKey(affirmations.get(position).affirmationKeyString);

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent myIntent = new Intent(this, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    getApplicationContext(), keyRecCode, myIntent, 0);

            alarmManager.cancel(pendingIntent);
            Toast.makeText(this, "NOTIFICATIONS DISABLED", Toast.LENGTH_SHORT).show();

        }else{

            affirmations.get(position).isEnabled = true;
            myTinydb.putObject(affirmations.get(position).affirmationKeyString, affirmations.get(position));


            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            Calendar alarmStartTime = Calendar.getInstance();

            Calendar now = Calendar.getInstance();
            extractTime(myTinydb.getObject(myKey, Affirmation.class).firstReminderTime);
            alarmStartTime.set(Calendar.HOUR_OF_DAY, hour);
            alarmStartTime.set(Calendar.MINUTE, minute);

            if (now.after(alarmStartTime)) {
                alarmStartTime.add(Calendar.DATE, 1);
            }

            Intent intent = new Intent(this, ServiceClass.class);
            intent.putExtra("key",affirmations.get(position));
            Toast.makeText(this, "NOTIFICATIONS ENABLED", Toast.LENGTH_SHORT).show();
            startService(intent);
       }

    }
    /*
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
    }
    */
    public void initializeData(){

        for(int i = 0; i < myTinydb.getListString("myKeys").size(); i++){
            myKey = myTinydb.getListString("myKeys").get(i);
            affirmations.add(myTinydb.getObject(myKey, Affirmation.class));

            if(myTinydb.getObject(myKey, Affirmation.class).isEnabled == true) {

                Intent intent = new Intent(this, ServiceClass.class);
                intent.putExtra("key",myTinydb.getObject(myKey, Affirmation.class));

                startService(intent);


            }else{
                //stopService

                extractRecCodeFromKey(myTinydb.getObject(myKey, Affirmation.class).affirmationKeyString);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent myIntent = new Intent(this, AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        getApplicationContext(), keyRecCode, myIntent, 0);

                alarmManager.cancel(pendingIntent);
                //Toast.makeText(this, "NOTIFICATION DISABLED", Toast.LENGTH_SHORT).show();
                stopService(myIntent);
            }

        }

        //Toast.makeText(getApplicationContext(), Integer.toString(myTinydb.getListString("myKeys").size()), Toast.LENGTH_SHORT).show();

    }
    public void extractTime(String timeUnformatted){
        substr = ":";
        before = timeUnformatted.substring(0, timeUnformatted.indexOf(substr));
        after = timeUnformatted.substring(timeUnformatted.indexOf(substr) + substr.length());

        hour = Integer.valueOf(before);
        minute = Integer.parseInt(after.replaceAll("\\D", ""));

    }

    public void extractRecCodeFromKey(String keyFromAffirmation){

       keyRecCode = Integer.parseInt(keyFromAffirmation.replaceAll("\\D", ""));

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
                if(myTinydb.getObject(myKey, Affirmation.class).isEnabled == true) {
                    subMenuEnabled.add(myTinydb.getObject(myKey, Affirmation.class).affirmation);
                }else if(myTinydb.getObject(myKey, Affirmation.class).isEnabled == false){
                    subMenuDisabled.add(myTinydb.getObject(myKey, Affirmation.class).affirmation);
                }
            }

        }


    }

    public void MakeKey(int i){
        myKey = "affirmation"+(i);
        myKeyList = myTinydb.getListString("myKeys");
        myKeyList.add(myKey);
        myTinydb.putListString("myKeys", myKeyList);


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

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        for (int i = 0; i < myTinydb.getListString("myKeys").size(); i++){
            myKey = myTinydb.getListString("myKeys").get(i);
            String affirmationText = myTinydb.getObject(myKey, Affirmation.class).affirmation;
            String itemTitle = item.getTitle().toString();

            if( affirmationText.equals(itemTitle)  ) {

                //Toast.makeText(this, "INSIDE IF NOW!", Toast.LENGTH_SHORT).show();
                rv.scrollToPosition(i);
                rv.getLayoutManager().scrollToPosition(i);

            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
