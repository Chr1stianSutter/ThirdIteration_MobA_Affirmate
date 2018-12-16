package com.example.christian.thirditeration_moba_affirmate;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;




public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    Integer hour;
    Integer minute;
    TextView tvDisplayTime;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
        hour = selectedHour;
        minute = selectedMinute;

        tvDisplayTime.setText(new StringBuilder().append(hour).append(":").append(minute));
    }

}
