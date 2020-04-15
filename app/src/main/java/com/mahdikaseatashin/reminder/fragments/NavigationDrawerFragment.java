package com.mahdikaseatashin.reminder.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mahdikaseatashin.reminder.R;
import com.mahdikaseatashin.reminder.adapters.NavigationDrawerAdapter;
import com.mahdikaseatashin.reminder.ui_components.DrawerEntry;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NavigationDrawerFragment extends Fragment {

    private View root;
    private DrawerLayout drawerLayout;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_nav_drawer, container, false);
        return root;
    }


    public void init(DrawerLayout drawerLayout, List<DrawerEntry> drawerEntries) {
        this.drawerLayout = drawerLayout;

        recyclerView = root.findViewById(R.id.nav_drawer_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        NavigationDrawerAdapter adapter = new NavigationDrawerAdapter(drawerEntries,getActivity());
        recyclerView.setAdapter(adapter);
    }
}
