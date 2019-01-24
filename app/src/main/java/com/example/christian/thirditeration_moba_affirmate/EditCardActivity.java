package com.example.christian.thirditeration_moba_affirmate;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

public class EditCardActivity extends AppCompatActivity{

    TextView tvDisplayTime;
    Integer hour;
    Integer minute;

    String affirmationText;
    EditText et1;
    RadioButton radioButtonOnceADay;
    RadioButton radioButtonTwiceADay;
    RadioButton radioButtonThriceADay;
    Boolean onceADay;
    Boolean twiceADay;
    Boolean thriceADay;
    String firstReminderTimeString;
    Boolean isEnabled;
    String affirmationKeyString;
    String substr;
    String before;
    String after;
    String tvDisplayTimeString;
    public static TinyDB tinydb;
    ArrayList myAffirmations;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);



        final Affirmation EditAffirmation = (Affirmation) getIntent().getParcelableExtra("myEditAffirmation");
        //
         tvDisplayTimeString = EditAffirmation.firstReminderTime;

         substr = ":";
         before = tvDisplayTimeString.substring(0, tvDisplayTimeString.indexOf(substr));
         after = tvDisplayTimeString.substring(tvDisplayTimeString.indexOf(substr) + substr.length());

         hour = Integer.valueOf(before);
         //minute = Integer.valueOf(after);
        minute = Integer.parseInt(after.replaceAll("\\D", ""));
        //

        //

        if (EditAffirmation != null) {
            //affirmations.add(myTinydb.getObject("testKeyNewCard", Affirmation.class));
            //affirmations.add(newlyAddedAffirmation);

            affirmationText = EditAffirmation.affirmation;

            onceADay = EditAffirmation.remindOnceADay;
            twiceADay = EditAffirmation.remindTwiceADay;
            thriceADay = EditAffirmation.remindThriceADay;

            firstReminderTimeString = EditAffirmation.firstReminderTime;
            isEnabled = EditAffirmation.isEnabled;
            affirmationKeyString = EditAffirmation.affirmationKeyString;
            /*
            if(EditAffirmation.firstReminderTime != null) {
                //tvDisplayTime.setText(firstReminderTimeString);
                tvDisplayTime.setText(tvDisplayTimeString);
            }else{
                tvDisplayTime.setText("FRT == NULL");
            }
            //tvDisplayTime.setText(firstReminderTimeString);
            */
        }
        setContentView(R.layout.activity_edit_card);

        et1 = (EditText) findViewById(R.id.affirmationEditText);
        et1.setText(affirmationText);
        radioButtonOnceADay = (RadioButton) findViewById(R.id.radioButton1);

        radioButtonTwiceADay = (RadioButton) findViewById(R.id.radioButton2);

        radioButtonThriceADay = (RadioButton) findViewById(R.id.radioButton3);
        tvDisplayTime = (TextView) findViewById(R.id.tvTime);
        tvDisplayTime.setText(EditAffirmation.firstReminderTime);
        //super.onActivityResult(savedInstanceState);


        //onceADay = true;
        //twiceADay = false;
        //thriceADay = false;

        tinydb = MainActivity.getTinydb();
        myAffirmations = MainActivity.getAffirmations();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Your Affirmation");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                //Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                //setResult(RESULT_OK,myIntent);
                //startActivity(myIntent);
                finish();
            }
        });



        Button cancelButton = (Button) findViewById(R.id.cancelButtonPressed);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
//                //setResult(RESULT_OK,myIntent);
//                startActivity(myIntent);
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

                    isEnabled = true;

                    affirmationKeyString = EditAffirmation.affirmationKeyString;


                    Affirmation editedAffirmation = new Affirmation(affirmationText, onceADay, twiceADay, thriceADay, firstReminderTimeString, isEnabled, affirmationKeyString);
                    //tinydb.putObject("testKeyNewCard", newAffirmation);

                    Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
                    //getIntent().removeExtra("myNewAffirmation");
                    myIntent.removeExtra("myNewAffirmation");
                    myIntent.putExtra("myEditedAffirmation", editedAffirmation );
                    myIntent.replaceExtras(myIntent.putExtra("myEditedAffirmation", editedAffirmation ));

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

    //protected void onActivityResult(int requestCode, int resultCode, Intent data)
    //{
        //super.onActivityResult(requestCode, resultCode, data);

        //Get Data of affirmation to edit

    //}

}
