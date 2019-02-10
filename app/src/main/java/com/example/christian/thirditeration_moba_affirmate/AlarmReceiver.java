package com.example.christian.thirditeration_moba_affirmate;


import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import org.apache.commons.lang3.SerializationUtils;

public class AlarmReceiver extends BroadcastReceiver {
    private static String CHANNEL_ID;
    public static TinyDB myTinydb;
    public String myKey;
    Affirmation data;




    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "ON RECEIVE WAS CALLED", Toast.LENGTH_SHORT).show();

        myTinydb = MainActivity.getTinydb();

        byte[] dataFromIntent = intent.getByteArrayExtra("key2");
        String dataAffirmationKeyString = (String) SerializationUtils.deserialize(dataFromIntent);

        data = myTinydb.getObject(dataAffirmationKeyString, Affirmation.class);

            if(data.isEnabled == true) {

                CHANNEL_ID = data.affirmationKeyString;

                Intent goToMain = new Intent(context, MainActivity.class);
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                stackBuilder.addParentStack(MainActivity.class);
                stackBuilder.addNextIntent(goToMain);

                int uniqueInt = (int) (System.currentTimeMillis() & 0xfffffff);
                PendingIntent pendingIntent = stackBuilder.getPendingIntent(uniqueInt, PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID);
                Notification notification = mBuilder.setContentTitle("AffirMate")
                        .setAutoCancel(true)
                        .setContentText(data.affirmation)
                        .setTicker("New Message Alert!")
                        .setSmallIcon(R.drawable.ic_affirmate_logo_text_am_black_svg_02)
                        .setContentIntent(pendingIntent).build();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    mBuilder.setChannelId(CHANNEL_ID);
                }

                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(
                            CHANNEL_ID,
                            "Affirmation_Notification", importance
                    );
                    notificationManager.createNotificationChannel(channel);
                }

                notificationManager.notify(0, notification);
            }

        //}
    }



}