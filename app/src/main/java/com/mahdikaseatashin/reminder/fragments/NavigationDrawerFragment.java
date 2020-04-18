package com.mahdikaseatashin.reminder.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mahdikaseatashin.reminder.BuildConfig;
import com.mahdikaseatashin.reminder.R;
import com.mahdikaseatashin.reminder.activities.AboutActivity;
import com.mahdikaseatashin.reminder.activities.FeedbackActivity;
import com.mahdikaseatashin.reminder.adapters.NavigationDrawerAdapter;
import com.mahdikaseatashin.reminder.interfaces.NavigationController;
import com.mahdikaseatashin.reminder.interfaces.OnNavigationViewClickListener;
import com.mahdikaseatashin.reminder.ui_components.DrawerEntry;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NavigationDrawerFragment extends Fragment implements OnNavigationViewClickListener, NavigationController {

    private View root;
    private DrawerLayout drawerLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_nav_drawer, container, false);
        return root;
    }


    public void init(DrawerLayout drawerLayout, List<DrawerEntry> drawerEntries) {
        this.drawerLayout = drawerLayout;

        RecyclerView recyclerView = root.findViewById(R.id.nav_drawer_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        NavigationDrawerAdapter adapter = new NavigationDrawerAdapter(drawerEntries, getActivity());
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void onItemClick(String title) {
        Intent intent;
        switch (title) {
            case "Setting":

                break;
            case "About":
                intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
                break;
            case "Feedback":
                intent = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(intent);
                break;
            case "Rate":

                break;
            case "Share":
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "This is an amazing app for scheduling your time of the week!");
                    String shareMessage = "\nLet me recommend you this application\n\n";
//                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareMessage = shareMessage + "App has not been officially released yet!\n build config is -> " + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    e.toString();
                }
                break;
            case "Exit":
                Objects.requireNonNull(getActivity()).finish();
                break;
        }
    }

    @Override
    public void onSwitchStateChange(Boolean status) {
        Toast.makeText(getActivity(), status.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openNavigationDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void closeNavigationDrawer() {
        drawerLayout.closeDrawers();
    }

    @Override
    public boolean isNavigationDrawerOpen() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            return true;
        return false;
    }
}
