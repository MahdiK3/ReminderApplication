package com.mahdikaseatashin.reminder.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mahdikaseatashin.reminder.R;
import com.mahdikaseatashin.reminder.adapters.AddReminderAdapter;
import com.mahdikaseatashin.reminder.fragments.AddPhoneNumberDialogFragment;
import com.mahdikaseatashin.reminder.fragments.BirthdayDialogFragment;
import com.mahdikaseatashin.reminder.fragments.DatePickerFragment;
import com.mahdikaseatashin.reminder.fragments.RepeatDialogFragment;
import com.mahdikaseatashin.reminder.fragments.ReportAsDialogFragment;
import com.mahdikaseatashin.reminder.fragments.TimePickerFragment;
import com.mahdikaseatashin.reminder.interfaces.OnAddReminderItemClickListener;
import com.mahdikaseatashin.reminder.ui_components.AddReminderItems;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AddReminderActivity extends AppCompatActivity implements OnAddReminderItemClickListener {

    private RelativeLayout relativeLayout;
    private EditText reminderTitle;
    private ImageView icon_keyboard;
    private AddReminderAdapter adapter;
    private List<AddReminderItems> addReminderItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        reminderTitle = findViewById(R.id.edit_text_add_reminder);
        ImageView icon_phone_number = findViewById(R.id.icon_phone_number);
        ImageView icon_contact = findViewById(R.id.icon_contacts);
        icon_keyboard = findViewById(R.id.icon_keyboard);
        ImageView icon_birthday = findViewById(R.id.icon_birthday);

        icon_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBirthdayNameDialogFragment();
            }
        });

        reminderTitle.requestFocus();
        relativeLayout = findViewById(R.id.parent_add_reminder);
        relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                relativeLayout.getWindowVisibleDisplayFrame(r);
                final int screenHeight = relativeLayout.getRootView().getHeight();
                final int keypadHeight = screenHeight - r.bottom;
                icon_keyboard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (keypadHeight > screenHeight * 0.15) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            assert imm != null;
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        } else {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            assert imm != null;
                            imm.showSoftInput(reminderTitle, InputMethodManager.SHOW_IMPLICIT);
                        }
                    }
                });
            }
        });


        icon_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContactInfo();
            }
        });

        icon_phone_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPhoneNumberDialogFragment();
            }
        });


        initToolbar();

        initAddReminderRecyclerView();

        initFloatingActionButton();

    }

    private void openBirthdayNameDialogFragment() {
        BirthdayDialogFragment fragment = new BirthdayDialogFragment();
        FragmentManager fm = getSupportFragmentManager();
        fragment.show(fm, getString(R.string.birthday_of));
    }

    private void getContactInfo() {
        View parent = findViewById(R.id.parent_add_reminder);
        Snackbar.make(parent, getString(R.string.pro_version), Snackbar.LENGTH_SHORT).show();
    }

    private void openPhoneNumberDialogFragment() {
        AddPhoneNumberDialogFragment dialogFragment = new AddPhoneNumberDialogFragment();
        FragmentManager fm = getSupportFragmentManager();
        dialogFragment.show(fm, getString(R.string.enter_the_phone_number));
    }

    private void initFloatingActionButton() {
        FloatingActionButton floatingBtnSave = findViewById(R.id.floating_action_button_save);
        ImageView floatingBtnCancel = findViewById(R.id.floating_action_button_cancel);
        floatingBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        floatingBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reminderTitle.getText().toString().isEmpty()) {
                    Toast.makeText(AddReminderActivity.this, getString(R.string.enter_title), Toast.LENGTH_SHORT).show();
                } else {

                }
            }
        });
    }

    private void initAddReminderRecyclerView() {
        addReminderItems = new ArrayList<>();
        Date date = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = dateFormat.format(date);
        addReminderItems.add(new AddReminderItems(R.drawable.ic_event, getString(R.string.date), strDate));
        addReminderItems.add(new AddReminderItems(R.drawable.ic_time, getString(R.string.time), getString(R.string.six_am)));
        addReminderItems.add(new AddReminderItems(R.drawable.ic_repeat, getString(R.string.repeat), getString(R.string.once)));
        addReminderItems.add(new AddReminderItems(R.drawable.ic_circle, getString(R.string.marker), getString(R.string.no_marker)));
        addReminderItems.add(new AddReminderItems(R.drawable.ic_notification, getString(R.string.report), getString(R.string.notification)));
        RecyclerView recyclerView = findViewById(R.id.add_reminder_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        recyclerView.setHasFixedSize(true);
        adapter = new AddReminderAdapter(addReminderItems, getBaseContext());
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_view_add_reminder);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onAddReminderItemClick(String item_name) {
        switch (item_name) {
            case "Date":
                String strDate = addReminderItems.get(0).getItem_text();
                DialogFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), strDate);
                break;
            case "Time":
                String strTime = addReminderItems.get(1).getItem_text();
                DialogFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.show(getSupportFragmentManager(), strTime);
                break;
            case "Repeat":
                RepeatDialogFragment fragment = new RepeatDialogFragment();
                FragmentManager fm = getSupportFragmentManager();
                String repeatText = addReminderItems.get(2).getItem_text();
                switch (repeatText) {
                    case "Once":
//                        radioGroup.clearCheck();
//                        radioGroup.check(R.id.radio_button_once);
                        break;
                    case "Hourly":
//                        radioGroup.clearCheck();
//                        radioGroup.check(R.id.radio_button_once);
                        break;
                    case "Daily":
//                        RadioButton daily = findViewById(R.id.radio_button_daily);
//                        radioGroup.check(R.id.radio_button_daily);
//                        ((RadioButton) radioGroup.getChildAt(2)).setChecked(true);
                        break;
                    case "Weekly":
//                        radioGroup.clearCheck();
//                        radioGroup.check(R.id.radio_button_weekly);
                        break;
                    case "Monthly":
//                        radioGroup.clearCheck();
//                        radioGroup.check(R.id.radio_button_monthly);
                        break;
                    case "Yearly":
//                        radioGroup.clearCheck();
//                        radioGroup.check(R.id.radio_button_yearly);
                        break;
                }
                fragment.show(fm, getString(R.string.repeat));
                break;
            case "Marker":
                View parent = findViewById(R.id.parent_add_reminder);
                Snackbar.make(parent, getText(R.string.pro_version), Snackbar.LENGTH_LONG).show();
                break;
            case "Report as":
                ReportAsDialogFragment reportDialogFragment = new ReportAsDialogFragment();
                FragmentManager reportFragmentManager = getSupportFragmentManager();
                reportDialogFragment.show(reportFragmentManager, getString(R.string.report));
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    public void setPhoneNumber(String phone_number) {
        reminderTitle.setText("Call : " + phone_number);
    }

    @SuppressLint("SetTextI18n")
    public void setBirthdayName(String name) {
        reminderTitle.setText("It`s the birthday of : " + name);
    }

    public void setRepeat(String repeat) {
        addReminderItems.get(2).setItem_text(repeat);
        adapter.notifyDataSetChanged();
    }

    public void setReport(String report) {
        addReminderItems.get(4).setItem_text(report);
        adapter.notifyDataSetChanged();
    }

    public void setTime(String time) {
        addReminderItems.get(1).setItem_text(time);
        adapter.notifyDataSetChanged();
    }

    public void setDate(String date) {
        addReminderItems.get(0).setItem_text(date);
        adapter.notifyDataSetChanged();
    }
}
