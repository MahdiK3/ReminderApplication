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

public class AddPhoneNumberDialogFragment extends DialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.phone_number_dialog_fragment, container, false);
        TextView btnCancel = root.findViewById(R.id.btn_cancel_dialog_fragment);
        TextView btnOK = root.findViewById(R.id.btn_save_dialog_fragment);
        final EditText phoneNumber = root.findViewById(R.id.edit_text_phone_number_dialog_fragment);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().remove(AddPhoneNumberDialogFragment.this).commit();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phoneNumber.getText().toString().isEmpty() || phoneNumber.getText().toString().length() < 6) {
                    Toast.makeText(getActivity(), "Enter a valid phone number", Toast.LENGTH_SHORT).show();
                } else {
                    getFragmentManager().beginTransaction().remove(AddPhoneNumberDialogFragment.this).commit();
                    ((AddReminderActivity) getActivity()).setPhoneNumber(phoneNumber.getText().toString());
                }
            }
        });
        return root;
    }
}
