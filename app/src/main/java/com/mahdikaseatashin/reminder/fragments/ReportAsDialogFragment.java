package com.mahdikaseatashin.reminder.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mahdikaseatashin.reminder.R;
import com.mahdikaseatashin.reminder.activities.AddReminderActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ReportAsDialogFragment extends DialogFragment {
    private View root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.report_as_dialog_fragment, container, false);
        TextView ok = root.findViewById(R.id.text_view_save_report_dialog_fragment);
        TextView cancel = root.findViewById(R.id.text_view_cancel_report_dialog_fragment);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton notification = root.findViewById(R.id.radio_button_notification);
                RadioButton alarm = root.findViewById(R.id.radio_button_alarm);
                if (notification.isChecked()){
                    ((AddReminderActivity) getActivity()).setReport(getString(R.string.notification));
                }else if (alarm.isChecked()){
                    ((AddReminderActivity) getActivity()).setReport(getString(R.string.alarm));
                }

                getFragmentManager().beginTransaction().remove(ReportAsDialogFragment.this).commit();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().remove(ReportAsDialogFragment.this).commit();
            }
        });
        return root;
    }
}
