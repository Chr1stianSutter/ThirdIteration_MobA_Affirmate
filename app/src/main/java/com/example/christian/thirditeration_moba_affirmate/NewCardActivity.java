package com.example.christian.thirditeration_moba_affirmate;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.app.Dialog;
import android.text.format.DateFormat;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class NewCardActivity extends AppCompatActivity {

    TextView tvDisplayTime;
    Integer hour;
    Integer minute;
    SharedPreferences prefs;
    SharedPreferences.Editor prefsEditor;
    String affirmationText;
    EditText et1;
    RadioButton radioButtonOnceADay;
    RadioButton radioButtonTwiceADay;
    RadioButton radioButtonThriceADay;
    Boolean onceADay;
    Boolean twiceADay;
    Boolean thriceADay;
    String firstReminderTimeString;
    //public static ArrayList affirmationsList;
    ArrayList myAffirmationsList;
    Integer listLengthPlusOne;
    String affirmationsKey;
    public static Integer listLength;
    public static TinyDB tinydb;
    ArrayList myAffirmations;
    //String masterKey;
    //public static Integer myOldKey;


    //TextView firstReminderTimeTextView;
    //StringBuilder

    /*
    final String KEYAffirmationText = "keyAffirmationText";

    final String KEYRadioOnce = "keyRadioOnce";
    final String KEYRadioTwice = "keyRadioTwice";
    final String KEYRadioThrice = "keyRadioThrice";

    final String KEYFirstReminderTime = "keyFirstReminderTime";
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);

        et1 = (EditText) findViewById(R.id.affirmationEditText);
        radioButtonOnceADay = (RadioButton) findViewById(R.id.radioButton);
        radioButtonTwiceADay = (RadioButton) findViewById(R.id.radioButton2);
        radioButtonThriceADay = (RadioButton) findViewById(R.id.radioButton3);
        tvDisplayTime = (TextView) findViewById(R.id.tvTime);

        //Context context = this;
        //tinydb = new TinyDB(context);
        tinydb = MainActivity.getTinydb();
        /*
        if(myOldKey == null){
            myOldKey = 0;
        }
        */
        myAffirmations = MainActivity.getAffirmations();
        //affirmationsList = new ArrayList<Affirmation>();
        //myAffirmationsList = NewCardActivity.getAffirmationList();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //prefs = this.getSharedPreferences("prefsFile1", MODE_PRIVATE);
        //prefsEditor = prefs.edit();

        getSupportActionBar().setTitle("Enter Your Affirmation");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });

        //TextView textView = (TextView) findViewById(R.id.tvTime);
        //textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_editable, 0, 0, 0);


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

                if (et1.getText().length() > 0){
                    //speichern
                    affirmationText = et1.getText().toString();


                    //if(onceADay == null){
                     //   onceADay = false;
                    //}else {
                        onceADay = radioButtonOnceADay.isChecked();

                    //}
                    //if(twiceADay == null){
                     //   twiceADay = false;
                    //}else {
                        twiceADay = radioButtonTwiceADay.isChecked();
                    //}

                    //if (thriceADay == null){
                     //   thriceADay = false;
                    //}else {
                        thriceADay = radioButtonThriceADay.isChecked();

                    //}
                    firstReminderTimeString = tvDisplayTime.getText().toString();


                    //if(myOldKey == null){
                     //   myOldKey = 0;
                   // }

                    //makeAffirmationsListKey();
                    /*
                    makeAffirmationsMasterKey(myOldKey);
                    tinydb.putInt("myOldKey", myOldKey);
                    tinydb.putString("MasterKey", masterKey);
                    */

                    /*
                    Affirmation newAffirmation = new Affirmation(affirmationText, onceADay, twiceADay, thriceADay, firstReminderTimeString);
                    tinydb.putObject(tinydb.getString("MasterKey"), newAffirmation);
                    */

                    /*
                    myAffirmations = MainActivity.getAffirmations();
                    //myAffirmations.add(new Affirmation(affirmationText, onceADay, twiceADay, thriceADay, firstReminderTimeString));
                    myAffirmations.add(tinydb.getObject(tinydb.getString("MasterKey"), Affirmation.class));
                    tinydb.putListObject("myAffirmationsListKey", myAffirmations);
                    */


                    Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                    //myIntent.putExtra("listLength", listLength);
                    //myIntent.putExtra("myAffirmationsNewList", myAffirmations);
                    setResult(RESULT_OK,myIntent );
                    //finish();
                    startActivity(myIntent);


                }else{
                    Toast.makeText(getApplicationContext(), "Please enter an affirmation", Toast.LENGTH_SHORT).show();
                }




            }
        });
    }
/*
    public void setCurrentTimeOnView(){
        tvDisplayTime = (TextView) findViewById(R.id.tvTime);

        final Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        tvDisplayTime.setText(new StringBuilder().append(hour)
        .append(":").append(minute));
    }
*/
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                    hour = selectedHour;
                    minute = selectedMinute;

                    tvDisplayTime.setText(new StringBuilder().append(hour).append(":").append(minute));
                }
            };
/*
    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Person("Emma Wilson", "23 years old", R.drawable.emma));
        persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.lavery));
        persons.add(new Person("Lillie Watts", "35 years old", R.drawable.lillie));
    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);
    }
*/
/*
    private void makeAffirmationsMasterKey(int oldKey){

        if(masterKey == null){
            masterKey = "masterKey"+0;
        }

        masterKey = "masterkey"+(oldKey+1);
        myOldKey++;
    }
*/
    private void makeAffirmationsListKey(){
        listLength = myAffirmations.size();
        listLengthPlusOne = (listLength +1);
        affirmationsKey = ("affirmation"+ listLengthPlusOne);
    }

    /*
    public static TinyDB getTinydb() {
        return tinydb;
    }
    */

    public static  Integer getListLength() {
        return  listLength;
    }
    /*
    public static  Integer getMyOldKey() {

        if(myOldKey == null){
            myOldKey = 0;
        }
        return  myOldKey;
    }
    */
    /*
    public static String getAffirmationsListKey(){
        return affirmationsKey;
    }
    */
    /*
    public static ArrayList getAffirmationList(){
        return affirmationsList;
    }
    */
}
