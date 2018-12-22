package com.example.christian.thirditeration_moba_affirmate;

import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Affirmation implements Parcelable{

    String affirmation;
    Boolean remindOnceADay;
    Boolean remindTwiceADay;
    Boolean remindThriceADay;
    String firstReminderTime;
    Boolean isEnabled;

    @Override
    public int describeContents() {
        return 0;
    }

    // Storing the Affirmation data to Parcel object
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(affirmation);
        dest.writeByte((byte) (remindOnceADay ? 1 : 0));
        dest.writeByte((byte) (remindTwiceADay ? 1 : 0));
        dest.writeByte((byte) (remindThriceADay ? 1 : 0));
        dest.writeString(firstReminderTime);
        dest.writeByte((byte) (isEnabled ? 1 : 0));
    }

    Affirmation(String affirmation, Boolean remindOnceADay, Boolean remindTwiceADay, Boolean remindThriceADay, String firstReminderTime, Boolean isEnabled){

        this.affirmation = affirmation;
        this.remindOnceADay = remindOnceADay;
        this.remindTwiceADay = remindTwiceADay;
        this.remindThriceADay = remindThriceADay;
        this.firstReminderTime = firstReminderTime;
        this.isEnabled = isEnabled;
    }

    /**
     * Retrieving Affirmation data from Parcel object
     * This constructor is invoked by the method createFromParcel(Parcel source) of
     * the object CREATOR
     **/
    private Affirmation(Parcel in){
        this.affirmation = in.readString();
        this.remindOnceADay = in.readByte() != 0;
        this.remindTwiceADay = in.readByte() != 0;
        this.remindThriceADay = in.readByte() != 0;
        this.firstReminderTime = in.readString();
        this.isEnabled = in.readByte() != 0;
    }

    // This is to de-serialize the object
    public static final Parcelable.Creator<Affirmation> CREATOR = new Parcelable.Creator<Affirmation>(){
        public Affirmation createFromParcel(Parcel in) {
            return new Affirmation(in);
        }

        public Affirmation[] newArray(int size) {
            return new Affirmation[size];
        }
    };
}


