package com.mahdikaseatashin.reminder.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import com.mahdikaseatashin.reminder.activities.AddReminderActivity;

import androidx.fragment.app.DialogFragment;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        String date = getTag();
        int firstMiddle = date.indexOf("/");
        int secondMiddle = date.indexOf("/", 5);
        int year = Integer.parseInt(date.substring(0, firstMiddle));
        int month = Integer.parseInt(date.substring(firstMiddle + 1, secondMiddle));
        int day = Integer.parseInt(date.substring(secondMiddle + 1));
        Log.e("My_Log", "Test: " + date + "and " + firstMiddle + " second " + secondMiddle);
        Log.e("My_Log", "K3: " + year + "/" + month + "/" + day);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String m;
        String d;
        if (month < 10) {
            m = "0" + month;
        } else {
            m = "" + month;
        }
        if (day < 10) {
            d = "0" + day;
        } else {
            d = "" + day;
        }
        String date = year + "/" + m + "/" + d;

        ((AddReminderActivity) getActivity()).setDate(date);

    }
}
