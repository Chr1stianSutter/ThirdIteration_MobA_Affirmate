package com.example.christian.thirditeration_moba_affirmate;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import java.lang.*;


public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    Integer hour;
    Integer minute;


    private static String  pad(int c)
    {
        return c>=10 ? ""+c : "0"+c;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);



/*
        //Take Hour:Minute from last input value (8:30)
        TextView tvDisplayTime = (TextView) getActivity().findViewById(R.id.tvTime);

        String tvDisplayTimeString = tvDisplayTime.toString();

        String substr = ":";
        String before = tvDisplayTimeString.substring(0, tvDisplayTimeString.indexOf(substr));
        String after = tvDisplayTimeString.substring(tvDisplayTimeString.indexOf(substr) + substr.length());

        int hour = Integer.valueOf(before);
        int minute = Integer.valueOf(after);

*/

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));



    }




    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {

        TextView tvDisplayTime = (TextView) getActivity().findViewById(R.id.tvTime);
        hour = selectedHour;
        minute = selectedMinute;

        String AM_PM;
        if (selectedHour >=0 && selectedHour < 12){
            AM_PM = " AM";
        } else {
            AM_PM = " PM";
        }

        tvDisplayTime.setText(new StringBuilder().append(hour).append(":").append(pad(minute)).append(AM_PM));



    }

}
