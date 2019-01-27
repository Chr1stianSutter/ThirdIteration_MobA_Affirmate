package com.example.christian.thirditeration_moba_affirmate;

import android.app.AlarmManager;
import android.app.PendingIntent;
import  android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

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
    public int onStartCommand(Intent myIntent, int flags, int startId){
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();

        myTinydb = MainActivity.getTinydb();
        //final Affirmation IntentAffirmation = (Affirmation) getIntent().getParcelableExtra("myEditAffirmation");
        data=(Affirmation) myIntent.getExtras().get("key");
        myKey = data.affirmationKeyString;
        myIntent.removeExtra("key");
        Calendar alarmStartTime = Calendar.getInstance();

        Calendar now = Calendar.getInstance();
        extractTime(myTinydb.getObject(myKey, Affirmation.class).firstReminderTime);
        alarmStartTime.set(Calendar.HOUR_OF_DAY, hour);
        alarmStartTime.set(Calendar.MINUTE, minute);

        if (now.after(alarmStartTime)) {
            alarmStartTime.add(Calendar.DATE, 1);
        }
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        extractRecCodeFromKey(myTinydb.getObject(myKey, Affirmation.class).affirmationKeyString);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, keyRecCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmStartTime.getTimeInMillis(), broadcast);

        return START_STICKY;

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
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
}
