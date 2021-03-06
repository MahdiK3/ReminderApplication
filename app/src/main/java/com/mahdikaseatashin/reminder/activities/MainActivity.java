package com.mahdikaseatashin.reminder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mahdikaseatashin.reminder.R;
import com.mahdikaseatashin.reminder.fragments.NavigationDrawerFragment;
import com.mahdikaseatashin.reminder.ui_components.DrawerDivider;
import com.mahdikaseatashin.reminder.ui_components.DrawerEntry;
import com.mahdikaseatashin.reminder.ui_components.DrawerTitle;
import com.mahdikaseatashin.reminder.ui_components.DrawerWithIcon;
import com.mahdikaseatashin.reminder.ui_components.DrawerWithSwitch;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

public class
MainActivity extends AppCompatActivity {

    private NavigationDrawerFragment navDrawerFragment;

    @Override
    public void onBackPressed() {
        if (navDrawerFragment.isNavigationDrawerOpen())
            navDrawerFragment.closeNavigationDrawer();
        else
            super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        initNavigationDrawer();

        FloatingActionButton floatingActionButton = findViewById(R.id.floating_action_button_add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddReminderActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initNavigationDrawer() {
        List<DrawerEntry> drawerEntries = new ArrayList<>();
        drawerEntries.add(new DrawerWithIcon(R.drawable.ic_setting, getString(R.string.setting)));
        drawerEntries.add(new DrawerWithIcon(R.drawable.ic_star, getString(R.string.rate)));
        drawerEntries.add(new DrawerWithIcon(R.drawable.ic_error, getString(R.string.about)));
        drawerEntries.add(new DrawerWithIcon(R.drawable.ic_feedback, getString(R.string.feedback)));
        drawerEntries.add(new DrawerDivider());
        drawerEntries.add(new DrawerTitle(getString(R.string.communicate)));
        drawerEntries.add(new DrawerWithSwitch(R.drawable.ic_moon, getString(R.string.night_mode)));
        drawerEntries.add(new DrawerWithIcon(R.drawable.ic_share, getString(R.string.share)));
        drawerEntries.add(new DrawerWithIcon(R.drawable.ic_exit, getString(R.string.exit)));
        navDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        navDrawerFragment.init((DrawerLayout) findViewById(R.id.drawer_layout), drawerEntries);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_view);
        toolbar.setTitle(getString(R.string.app_name));
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navDrawerFragment.openNavigationDrawer();
            }
        });
    }
}
