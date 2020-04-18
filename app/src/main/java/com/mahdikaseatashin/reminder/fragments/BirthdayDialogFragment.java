package com.mahdikaseatashin.reminder.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mahdikaseatashin.reminder.R;
import com.mahdikaseatashin.reminder.activities.AddReminderActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class BirthdayDialogFragment extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.birthday_dialog_fragment, container, false);
        TextView btnCancel = root.findViewById(R.id.text_view_cancel_dialog_fragment);
        TextView btnOK = root.findViewById(R.id.text_view_save_dialog_fragment);
        final EditText name = root.findViewById(R.id.edit_text_name_dialog_fragment);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().remove(BirthdayDialogFragment.this).commit();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Enter a valid name", Toast.LENGTH_SHORT).show();
                } else {
                    getFragmentManager().beginTransaction().remove(BirthdayDialogFragment.this).commit();
                    ((AddReminderActivity) getActivity()).setBirthdayName(name.getText().toString());
                }
            }
        });
        return root;
    }
}
