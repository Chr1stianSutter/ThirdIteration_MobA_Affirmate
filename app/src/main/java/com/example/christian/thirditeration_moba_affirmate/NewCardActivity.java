package com.example.christian.thirditeration_moba_affirmate;

import android.app.TimePickerDialog;
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
    List affirmationsList;
    //TextView firstReminderTimeTextView;
    //StringBuilder


    final String KEYAffirmationText = "keyAffirmationText";

    final String KEYRadioOnce = "keyRadioOnce";
    final String KEYRadioTwice = "keyRadioTwice";
    final String KEYRadioThrice = "keyRadioThrice";

    final String KEYFirstReminderTime = "keyFirstReminderTime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_card);

        et1 = (EditText) findViewById(R.id.affirmationEditText);
        radioButtonOnceADay = (RadioButton) findViewById(R.id.radioButton);
        radioButtonTwiceADay = (RadioButton) findViewById(R.id.radioButton2);
        radioButtonThriceADay = (RadioButton) findViewById(R.id.radioButton3);
        tvDisplayTime = (TextView) findViewById(R.id.tvTime);
        //affirmationsList = (List)

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        prefs = this.getSharedPreferences("prefsFile1", MODE_PRIVATE);
        prefsEditor = prefs.edit();

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
                    prefsEditor.putString(KEYAffirmationText, affirmationText);
                    //prefsEditor.commit();

                    onceADay = radioButtonOnceADay.isChecked();
                    prefsEditor.putBoolean(KEYRadioOnce, onceADay);
                    //prefsEditor.commit();

                    twiceADay = radioButtonTwiceADay.isChecked();
                    prefsEditor.putBoolean(KEYRadioTwice, twiceADay);
                    //prefsEditor.commit();

                    thriceADay = radioButtonThriceADay.isChecked();
                    prefsEditor.putBoolean(KEYRadioThrice, thriceADay);
                    //prefsEditor.commit();

                    firstReminderTimeString = tvDisplayTime.getText().toString();
                    prefsEditor.putString(KEYFirstReminderTime, firstReminderTimeString);
                    prefsEditor.commit();

                    Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
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
}
