package com.example.christian.thirditeration_moba_affirmate;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class EditCardActivity extends AppCompatActivity{

    TextView tvDisplayTime;
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
    String tvDisplayTimeString;
    public static TinyDB tinydb;
    ArrayList myAffirmations;
    ArrayList myKeyListToEdit;
    ConstraintLayout layout;
    Integer keyRecCode;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);



        final Affirmation editAffirmation = (Affirmation) getIntent().getParcelableExtra("myEditAffirmation");

        myKeyListToEdit = new ArrayList<String>();



         tvDisplayTimeString = editAffirmation.firstReminderTime;

        if (editAffirmation != null) {

            affirmationText = editAffirmation.affirmation;

            onceADay = editAffirmation.remindOnceADay;
            twiceADay = editAffirmation.remindTwiceADay;
            thriceADay = editAffirmation.remindThriceADay;

            firstReminderTimeString = editAffirmation.firstReminderTime;
            isEnabled = editAffirmation.isEnabled;
            affirmationKeyString = editAffirmation.affirmationKeyString;

        }
        setContentView(R.layout.activity_edit_card);

        et1 = (EditText) findViewById(R.id.affirmationEditText);
        et1.setText(affirmationText);
        radioButtonOnceADay = (RadioButton) findViewById(R.id.radioButton1);

        radioButtonTwiceADay = (RadioButton) findViewById(R.id.radioButton2);

        radioButtonThriceADay = (RadioButton) findViewById(R.id.radioButton3);
        tvDisplayTime = (TextView) findViewById(R.id.tvTime);
        tvDisplayTime.setText(editAffirmation.firstReminderTime);

        tinydb = MainActivity.getTinydb();
        myAffirmations = MainActivity.getAffirmations();

        layout = (ConstraintLayout) findViewById(R.id.constraintLayoutEditTarget);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideKeyboard(view);
                return false;
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Your Affirmation");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                finish();
            }
        });



        Button deleteButton = (Button) findViewById(R.id.deleteButtonPressed);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDeleteAffirmationDialog(editAffirmation);
//
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

                    affirmationKeyString = editAffirmation.affirmationKeyString;


                    Affirmation editedAffirmation = new Affirmation(affirmationText, onceADay, twiceADay, thriceADay, firstReminderTimeString, isEnabled, affirmationKeyString);


                    Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
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

    public void showDeleteAffirmationDialog(final Affirmation toDelete){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view=inflater.inflate(R.layout.activity_edit_card, null);
                alert.setPositiveButton(R.string.yes_delete_card, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);

                        extractRecCodeFromKey(toDelete.affirmationKeyString);

                        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                        Intent alarmIntent = new Intent(getBaseContext(), AlarmReceiver.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                                getApplicationContext(), keyRecCode, alarmIntent, 0);

                        alarmManager.cancel(pendingIntent);
                        Toast.makeText(getBaseContext(), "AFFIRMATION DELETED", Toast.LENGTH_SHORT).show();

                        myKeyListToEdit= tinydb.getListString("myKeys");
                        myKeyListToEdit.remove(toDelete.affirmationKeyString);
                        tinydb.putListString("myKeys", myKeyListToEdit);

                        tinydb.remove(toDelete.affirmationKeyString);

                        startActivity(myIntent);
                    }
                });
                alert.setNegativeButton(R.string.no_keep_card, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        alert.setMessage("Do you want to DELETE this Affirmation?");
        alert.show();


    }

    public void extractRecCodeFromKey(String keyFromAffirmation){

        keyRecCode = Integer.parseInt(keyFromAffirmation.replaceAll("\\D", ""));

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radioButton1:
                if(checked)

                    onceADay = true;
                twiceADay = false;
                thriceADay = false;
                //Toast.makeText(getApplicationContext(), "1 works", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radioButton2:
                if(checked)

                    onceADay = false;
                twiceADay = true;
                thriceADay = false;
                //Toast.makeText(getApplicationContext(), "2 works", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radioButton3:
                if (checked)

                    onceADay = false;
                twiceADay = false;
                thriceADay = true;
                //Toast.makeText(getApplicationContext(), "3 works", Toast.LENGTH_SHORT).show();
                break;

        }
    }
    /*
    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                    hour = selectedHour;
                    minute = selectedMinute;

                    tvDisplayTime.setText(new StringBuilder().append(hour).append(":").append(minute));
                }
            };
    */
    protected void hideKeyboard(View view)
    {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
