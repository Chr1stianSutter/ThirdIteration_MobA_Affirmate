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
        radioButtonOnceADay = (RadioButton) findViewById(R.id.radioButton1);
        //radioButtonOnceADay.setChecked(true);
        radioButtonTwiceADay = (RadioButton) findViewById(R.id.radioButton2);
        //radioButtonTwiceADay.setChecked(false);
        radioButtonThriceADay = (RadioButton) findViewById(R.id.radioButton3);
        //radioButtonThriceADay.setChecked(false);
        tvDisplayTime = (TextView) findViewById(R.id.tvTime);
        onceADay = true;
        twiceADay = false;
        thriceADay = false;

        tinydb = MainActivity.getTinydb();

        myAffirmations = MainActivity.getAffirmations();


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

                if (et1.getText().length() > 0){
                    //speichern
                    affirmationText = et1.getText().toString();


                        radioButtonOnceADay = (RadioButton) findViewById(R.id.radioButton1);
                        onceADay = radioButtonOnceADay.isChecked();

                        twiceADay = radioButtonTwiceADay.isChecked();
                        radioButtonTwiceADay = (RadioButton) findViewById(R.id.radioButton2);


                        radioButtonThriceADay = (RadioButton) findViewById(R.id.radioButton3);
                        thriceADay = radioButtonThriceADay.isChecked();


                    firstReminderTimeString = tvDisplayTime.getText().toString();


                    Intent myIntent = new Intent(getBaseContext(), MainActivity.class);

                    setResult(RESULT_OK,myIntent );

                    startActivity(myIntent);


                }else{
                    Toast.makeText(getApplicationContext(), "Please enter an affirmation", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radioButton1:
                if(checked)

                onceADay = true;
                twiceADay = false;
                thriceADay = false;
                Toast.makeText(getApplicationContext(), "1 works", Toast.LENGTH_SHORT).show();
                    break;
            case R.id.radioButton2:
                if(checked)

                onceADay = false;
                twiceADay = true;
                thriceADay = false;
                Toast.makeText(getApplicationContext(), "2 works", Toast.LENGTH_SHORT).show();
                    break;
            case R.id.radioButton3:
                if (checked)

                onceADay = false;
                twiceADay = false;
                thriceADay = true;
                Toast.makeText(getApplicationContext(), "3 works", Toast.LENGTH_SHORT).show();
                    break;

        }
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

    private void makeAffirmationsListKey(){
        listLength = myAffirmations.size();
        listLengthPlusOne = (listLength +1);
        affirmationsKey = ("affirmation"+ listLengthPlusOne);
    }


    public static  Integer getListLength() {
        return  listLength;
    }

}
