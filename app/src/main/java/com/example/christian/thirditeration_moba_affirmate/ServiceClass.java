package com.example.christian.thirditeration_moba_affirmate;

import android.app.AlarmManager;
import android.app.PendingIntent;
import  android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

import org.apache.commons.lang3.SerializationUtils;

import java.util.Calendar;

public class ServiceClass extends Service{

    public static TinyDB myTinydb;
    Integer keyRecCode;
    String myKey;
    String substr;
    String before;
    String after;
    Integer hour;
    Integer minute;
    Affirmation data;

    @Override
    public IBinder onBind(Intent myIntent){


        return null;
    }

    @Override
    public int onStartCommand(Intent myIntent, int flags, int startId) {


        myTinydb = MainActivity.getTinydb();
        //final Affirmation IntentAffirmation = (Affirmation) getIntent().getParcelableExtra("myEditAffirmation");
        data = (Affirmation) myIntent.getExtras().get("key");
        myKey = data.affirmationKeyString;
        Toast.makeText(this, "Service Started" + data.affirmationKeyString, Toast.LENGTH_SHORT).show();

        if (data.remindOnceADay == true && data.isEnabled == true) {
            setAlarm(data.firstReminderTime, 0);

            //return START_STICKY;

        } else if (data.remindTwiceADay == true && data.isEnabled == true) {
            setAlarm(data.firstReminderTime, 0);
            setAlarm(data.firstReminderTime, 3);
            //setAlarm(data.firstReminderTime, 6);

            //return START_STICKY;

        } else if (data.remindThriceADay == true && data.isEnabled == true) {
            setAlarm(data.firstReminderTime, 0);
            setAlarm(data.firstReminderTime, 2);
            setAlarm(data.firstReminderTime, 4);

            //return START_STICKY;

        }

        return START_STICKY;


    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(this, "Service Stopped" + data.affirmationKeyString, Toast.LENGTH_SHORT).show();
    }
    public void extractRecCodeFromKey(String keyFromAffirmation){

        keyRecCode = Integer.parseInt(keyFromAffirmation.replaceAll("\\D", ""));

    }
    public void extractTime(String timeUnformatted){
        substr = ":";
        before = timeUnformatted.substring(0, timeUnformatted.indexOf(substr));
        after = timeUnformatted.substring(timeUnformatted.indexOf(substr) + substr.length());

        hour = Integer.valueOf(before);
        //minute = Integer.valueOf(after);
        minute = Integer.parseInt(after.replaceAll("\\D", ""));

    }
    public void setAlarm(String firstReminderTime, Integer additionalHours){
        Calendar alarmStartTime = Calendar.getInstance();

        Calendar now = Calendar.getInstance();
        extractTime(myTinydb.getObject(myKey, Affirmation.class).firstReminderTime);
        alarmStartTime.set(Calendar.HOUR_OF_DAY, hour);
        alarmStartTime.set(Calendar.MINUTE, minute+additionalHours);

        if (now.after(alarmStartTime)) {
            alarmStartTime.add(Calendar.DATE, 1);
        }
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        //Bundle bundle = new Bundle();
        //Affirmation objectForBundle = new Affirmation(data.affirmation, data.remindOnceADay, data.remindTwiceADay, data.remindThriceADay, data.firstReminderTime, data.isEnabled, data.affirmationKeyString);

        byte[] data2 = SerializationUtils.serialize(data.affirmationKeyString);


        //bundle.putSerializable("Affirmation", data);
        //intent.putExtra("key2", data2);
        intent.putExtra("key2", data2);
        //intent.putExtra("key2" ,bundle);

        /*
        intent.putExtra("key2",data);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setAction("android.media.action.DISPLAY_NOTIFICATION");
        */
        //myIntent.removeExtra("key");
        extractRecCodeFromKey(myTinydb.getObject(myKey, Affirmation.class).affirmationKeyString);
        int uniqueInt = (int) (System.currentTimeMillis() & 0xfffffff);
        PendingIntent broadcast = PendingIntent.getBroadcast(getApplicationContext(), keyRecCode+additionalHours, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //PendingIntent broadcast = PendingIntent.getBroadcast(getApplicationContext(), uniqueInt, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmStartTime.getTimeInMillis(), broadcast);




    }

}
