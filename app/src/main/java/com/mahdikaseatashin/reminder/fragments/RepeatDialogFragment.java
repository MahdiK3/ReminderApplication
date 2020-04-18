package com.mahdikaseatashin.reminder.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mahdikaseatashin.reminder.R;
import com.mahdikaseatashin.reminder.activities.AddReminderActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class RepeatDialogFragment extends DialogFragment {

    private View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.repeat_dialog_fragment, container, false);
        TextView ok = root.findViewById(R.id.text_view_save_repeat_dialog_fragment);
        TextView cancel = root.findViewById(R.id.text_view_cancel_repeat_dialog_fragment);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton once = root.findViewById(R.id.radio_button_once);
                RadioButton hourly = root.findViewById(R.id.radio_button_hourly);
                RadioButton daily = root.findViewById(R.id.radio_button_daily);
                RadioButton weekly = root.findViewById(R.id.radio_button_weekly);
                RadioButton monthly = root.findViewById(R.id.radio_button_monthly);
                RadioButton yearly = root.findViewById(R.id.radio_button_yearly);
                RadioGroup radioGroup = root.findViewById(R.id.radio_group_repeat);
                if (once.isChecked()) {
                    ((AddReminderActivity) getActivity()).setRepeat(getString(R.string.once));
                } else if (hourly.isChecked()) {
                    ((AddReminderActivity) getActivity()).setRepeat(getString(R.string.hourly));
                } else if (daily.isChecked()) {
                    ((AddReminderActivity) getActivity()).setRepeat(getString(R.string.daily));
                } else if (weekly.isChecked()) {
                    ((AddReminderActivity) getActivity()).setRepeat(getString(R.string.weekly));
                } else if (monthly.isChecked()) {
                    ((AddReminderActivity) getActivity()).setRepeat(getString(R.string.monthly));
                } else if (yearly.isChecked()) {
                    ((AddReminderActivity) getActivity()).setRepeat(getString(R.string.yearly));
                }
                getFragmentManager().beginTransaction().remove(RepeatDialogFragment.this).commit();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().remove(RepeatDialogFragment.this).commit();
            }
        });
        return root;
    }
}
