package com.mahdikaseatashin.reminder.fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.TimePicker;

import com.mahdikaseatashin.reminder.activities.AddReminderActivity;

import androidx.fragment.app.DialogFragment;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        String time = getTag();
        int middle = time.indexOf(":");
        int hour = Integer.parseInt(time.substring(0, middle));
        int minute = Integer.parseInt(time.substring(middle + 1));
        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String m;
        if (minute < 10) {
            m = "0" + minute;
        } else {
            m = "" + minute;
        }
        Log.e("My_Log", "onTimeSet: " + hourOfDay + ":" + m);
        ((AddReminderActivity) getActivity()).setTime(hourOfDay + ":" + m);

    }
}
