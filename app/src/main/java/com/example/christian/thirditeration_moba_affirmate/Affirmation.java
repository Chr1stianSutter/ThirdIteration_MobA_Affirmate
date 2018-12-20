package com.example.christian.thirditeration_moba_affirmate;

import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.List;

public class Affirmation {
    /*


private List<Person> persons;

// This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
private void initializeData(){
    persons = new ArrayList<>();
    persons.add(new Person("Emma Wilson", "23 years old", R.drawable.emma));
    persons.add(new Person("Lavery Maiss", "25 years old", R.drawable.lavery));
    persons.add(new Person("Lillie Watts", "35 years old", R.drawable.lillie));
}
*/

    String affirmation;
    Boolean remindOnceADay;
    Boolean remindTwiceADay;
    Boolean remindThriceADay;
    String firstReminderTime;



    Affirmation(String affirmation, Boolean remindOnceADay, Boolean remindTwiceADay, Boolean remindThriceADay, String firstReminderTime){

        this.affirmation = affirmation;
        this.remindOnceADay = remindOnceADay;
        this.remindTwiceADay = remindTwiceADay;
        this.remindThriceADay = remindThriceADay;
        this.firstReminderTime = firstReminderTime;
    }
}

/*
    private List<Affirmation> affirmations;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData(){
        affirmations = new ArrayList<>();
        //affirmations.add(new Affirmation("", "", "", "", ""));
        //affirmations.add(new Affirmation("", "", "", "", ""));
        //affirmations.add(new Affirmation("", "", "", "", ""));
    }
*/