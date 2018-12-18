package com.example.christian.thirditeration_moba_affirmate;

import android.support.constraint.ConstraintLayout;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.View;

import java.util.ArrayList;


public class AffirmationCard {

    //private ScrollView cardScrollView;

    //private ConstraintLayout cardConstraintLayout;

    //private EditText affirmation;
    private String affirmation;
    //private View viewDivider;

    //private TextView remindMeLabel;

    //private RadioGroup group;
    //private RadioButton firstRadioButton;
    //private RadioButton secondRadioButton;
    //private RadioButton thirdRadioButton;
    private Boolean onceADay;
    private Boolean twiceADay;
    private Boolean thriceADay;
    //private TextView firstReminderAtLabel;
    //private LinearLayout textAndButtonGroup;
    //private TextView firstReminderTime;
    private String firstReminderTime;
    //private ImageView editButton;

    //private LinearLayout saveAndCancelButtonGroup;
    //private Button cancelButton;
    //private Button saveButton;

    public  AffirmationCard(){

    }

    public AffirmationCard(String affirmation, String firstReminderTime, Boolean onceADay, Boolean twiceADay, Boolean thriceADay){
        this.affirmation = affirmation;
        this.firstReminderTime = firstReminderTime;
        this.onceADay = onceADay;
        this.twiceADay = twiceADay;
        this.thriceADay = thriceADay;

    }

    public String getAffirmation() {
        return affirmation;
    }

    public void setAffirmation(String affirmation) {
        this.affirmation = affirmation;
    }

    public String getFirstReminderTime() {
        return firstReminderTime;
    }

    public void setFirstReminderTime(String firstReminderTime) {
        this.firstReminderTime = firstReminderTime;
    }

    public Boolean getOnceADayStatus() {
        return onceADay;
    }

    public void setOnceADayStatus(Boolean onceADay) {
        this.onceADay = onceADay;
    }

    public Boolean getTwiceADayStatus() {
        return twiceADay;
    }

    public void setTwiceADayStatus(Boolean twiceADay) {
        this.twiceADay = twiceADay;
    }

    public Boolean getThriceADayStatus() {
        return thriceADay;
    }

    public void setThriceADayStatus(Boolean thriceADay) {
        this.thriceADay = thriceADay;
    }


    private ArrayList<AffirmationCard> affirmationCards;
    private  void initializeData(){


        affirmationCards = new ArrayList<>();
        //affirmationCards.add(new AffirmationCard("", "", "", "", ""));
    }
}
