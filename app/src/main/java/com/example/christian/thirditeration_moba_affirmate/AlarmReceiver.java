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
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    private static String CHANNEL_ID;
    public static TinyDB myTinydb;
    public String myKey;


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "ON RECEIVE WAS CALLED", Toast.LENGTH_SHORT).show();

        myTinydb = MainActivity.getTinydb();

        for (int i = 0; i < myTinydb.getListString("myKeys").size(); i++) {
            myKey = myTinydb.getListString("myKeys").get(i);

            CHANNEL_ID = myKey;

            //createNotificationChannel(CHANNEL_ID);
            Intent goToMain = new Intent(context, MainActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(goToMain);

            PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context,CHANNEL_ID);
            Notification notification = mBuilder.setContentTitle("AffirMate")
                    .setContentText(myTinydb.getObject(myKey, Affirmation.class).affirmation)
                    .setTicker("New Message Alert!")
                    .setSmallIcon(R.drawable.ic_affirmate_logo_text_am_black_svg_02)
                    .setContentIntent(pendingIntent).build();
                    /*
                    .setSmallIcon(R.drawable.ic_affirmate_logo_text_am_black_svg_02)
                    .setContentTitle("AffirMate")
                    .setContentText(myTinydb.getObject(myKey, Affirmation.class).affirmation)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(myTinydb.getObject(myKey, Affirmation.class).affirmation))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    */
            //Intent notificationIntent = new Intent(context, NotificationActivity.class);


            //stackBuilder.addParentStack(NotificationActivity.class);
            //stackBuilder.addNextIntent(notificationIntent);



            /*
            Notification.Builder builder = new Notification.Builder(context);

            Notification notification = builder.setContentTitle("Demo App Notification")
                    .setContentText("New Notification From Demo App..")
                    .setTicker("New Message Alert!")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pendingIntent).build();
            */
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
    }

}