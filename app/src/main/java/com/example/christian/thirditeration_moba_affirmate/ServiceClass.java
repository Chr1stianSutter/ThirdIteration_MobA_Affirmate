package com.example.christian.thirditeration_moba_affirmate;

import android.app.AlarmManager;
import android.app.PendingIntent;
import  android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import org.apache.commons.lang3.SerializationUtils;
import java.util.Calendar;

public class ServiceClass extends Service {

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
    public IBinder onBind(Intent myIntent) {


        return null;
    }

    @Override
    public int onStartCommand(Intent myIntent, int flags, int startId) {


        myTinydb = MainActivity.getTinydb();
        data = (Affirmation) myIntent.getExtras().get("key");
        myKey = data.affirmationKeyString;
        //Toast.makeText(this, "Service Started" + data.affirmationKeyString, Toast.LENGTH_SHORT).show();


        if (data.remindOnceADay == true && data.isEnabled == true) {
            setAlarm(data.firstReminderTime, 0);

        } else if (data.remindTwiceADay == true && data.isEnabled == true) {
            setAlarm(data.firstReminderTime, 0);
            setAlarm(data.firstReminderTime, 3);

        } else if (data.remindThriceADay == true && data.isEnabled == true) {
            setAlarm(data.firstReminderTime, 0);
            setAlarm(data.firstReminderTime, 2);
            setAlarm(data.firstReminderTime, 4);

        }

        return START_STICKY;


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myTinydb = MainActivity.getTinydb();
        for(int i = 0; i < myTinydb.getListString("myKeys").size(); i++) {
            myKey = myTinydb.getListString("myKeys").get(i);

            if (myTinydb.getObject(myKey, Affirmation.class).remindOnceADay == true && myTinydb.getObject(myKey, Affirmation.class).isEnabled == true) {
                setAlarm(myTinydb.getObject(myKey, Affirmation.class).firstReminderTime, 0);

            } else if (myTinydb.getObject(myKey, Affirmation.class).remindTwiceADay == true && myTinydb.getObject(myKey, Affirmation.class).isEnabled == true) {
                setAlarm(myTinydb.getObject(myKey, Affirmation.class).firstReminderTime, 0);
                setAlarm(myTinydb.getObject(myKey, Affirmation.class).firstReminderTime, 3);

            } else if (myTinydb.getObject(myKey, Affirmation.class).remindThriceADay == true && myTinydb.getObject(myKey, Affirmation.class).isEnabled == true) {
                setAlarm(myTinydb.getObject(myKey, Affirmation.class).firstReminderTime, 0);
                setAlarm(myTinydb.getObject(myKey, Affirmation.class).firstReminderTime, 2);
                setAlarm(myTinydb.getObject(myKey, Affirmation.class).firstReminderTime, 4);

            }
        }

            //Toast.makeText(this, "Service Stopped" + data.affirmationKeyString, Toast.LENGTH_SHORT).show();
    }

    public void extractRecCodeFromKey(String keyFromAffirmation) {

        keyRecCode = Integer.parseInt(keyFromAffirmation.replaceAll("\\D", ""));

    }

    public void extractTime(String timeUnformatted) {
        substr = ":";
        before = timeUnformatted.substring(0, timeUnformatted.indexOf(substr));
        after = timeUnformatted.substring(timeUnformatted.indexOf(substr) + substr.length());

        hour = Integer.valueOf(before);
        minute = Integer.parseInt(after.replaceAll("\\D", ""));

    }

    public void setAlarm(String firstReminderTime, Integer additionalHours) {
        Calendar alarmStartTime = Calendar.getInstance();

        Calendar now = Calendar.getInstance();
        extractTime(myTinydb.getObject(myKey, Affirmation.class).firstReminderTime);
        alarmStartTime.set(Calendar.HOUR_OF_DAY, hour + additionalHours);
        alarmStartTime.set(Calendar.MINUTE, minute );

        if (now.after(alarmStartTime)) {
            alarmStartTime.add(Calendar.DATE, 1);
        }
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        byte[] data2 = SerializationUtils.serialize(data.affirmationKeyString);

        intent.putExtra("key2", data2);

        extractRecCodeFromKey(myTinydb.getObject(myKey, Affirmation.class).affirmationKeyString);
        PendingIntent broadcast = PendingIntent.getBroadcast(getApplicationContext(), keyRecCode + additionalHours, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmStartTime.getTimeInMillis(), broadcast);


    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        /*
        for(int i = 0; i < myTinydb.getListString("myKeys").size(); i++){
            myKey = myTinydb.getListString("myKeys").get(i);

            if (myTinydb.getObject(myKey, Affirmation.class).remindOnceADay == true && myTinydb.getObject(myKey, Affirmation.class).isEnabled == true) {
                setAlarm(myTinydb.getObject(myKey, Affirmation.class).firstReminderTime, 0);

            } else if (myTinydb.getObject(myKey, Affirmation.class).remindTwiceADay == true && myTinydb.getObject(myKey, Affirmation.class).isEnabled == true) {
                setAlarm(myTinydb.getObject(myKey, Affirmation.class).firstReminderTime, 0);
                setAlarm(myTinydb.getObject(myKey, Affirmation.class).firstReminderTime, 3);

            } else if (myTinydb.getObject(myKey, Affirmation.class).remindThriceADay == true && myTinydb.getObject(myKey, Affirmation.class).isEnabled == true) {
                setAlarm(myTinydb.getObject(myKey, Affirmation.class).firstReminderTime, 0);
                setAlarm(myTinydb.getObject(myKey, Affirmation.class).firstReminderTime, 2);
                setAlarm(myTinydb.getObject(myKey, Affirmation.class).firstReminderTime, 4);

            }


        }

        */
        }

}
