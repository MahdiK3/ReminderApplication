package com.mahdikaseatashin.reminder.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.mahdikaseatashin.reminder.interfaces.OnNavigationViewClickListener;
import com.mahdikaseatashin.reminder.R;
import com.mahdikaseatashin.reminder.ui_components.DrawerDivider;
import com.mahdikaseatashin.reminder.ui_components.DrawerEntry;
import com.mahdikaseatashin.reminder.ui_components.DrawerTitle;
import com.mahdikaseatashin.reminder.ui_components.DrawerWithIcon;
import com.mahdikaseatashin.reminder.ui_components.DrawerWithSwitch;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NavigationDrawerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DrawerEntry> drawerEntries;
    private LayoutInflater inflater;
    private static final String TAG = "NavigationDrawerAdapter";
    private OnNavigationViewClickListener clickListener;

    public void setClickListener(OnNavigationViewClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemViewType(int position) {
        Log.e(TAG, "getItemViewType: called!");
        if (drawerEntries.get(position) instanceof DrawerDivider) {
            return 0;
        }
        if (drawerEntries.get(position) instanceof DrawerTitle) {
            return 1;
        }
        if (drawerEntries.get(position) instanceof DrawerWithIcon) {
            return 2;
        }
        if (drawerEntries.get(position) instanceof DrawerWithSwitch) {
            return 3;
        }
        Log.e(TAG, "getItemViewType: error!");
        return -1;
    }


    public NavigationDrawerAdapter(List<DrawerEntry> drawerEntries, Context context) {
        this.drawerEntries = drawerEntries;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Log.e(TAG, "onCreateViewHolder: called!");
        View itemLayoutView;
        switch (viewType) {
            case 0:
                itemLayoutView = inflater.inflate(R.layout.item_divider, viewGroup, false);
                ItemDividerVh dividerVh = new ItemDividerVh(itemLayoutView);
                return dividerVh;
            case 1:
                itemLayoutView = inflater.inflate(R.layout.item_title, viewGroup, false);
                ItemTitleVh titleVh = new ItemTitleVh(itemLayoutView);
                return titleVh;
            case 2:
                itemLayoutView = inflater.inflate(R.layout.item_with_icon, viewGroup, false);
                ItemWithIconVh iconVh = new ItemWithIconVh(itemLayoutView);
                return iconVh;
            case 3:
                itemLayoutView = inflater.inflate(R.layout.item_with_swith, viewGroup, false);
                ItemWithSwitchVh switchVh = new ItemWithSwitchVh(itemLayoutView);
                return switchVh;
        }
        Log.e(TAG, "onCreateViewHolder: error!");
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.e(TAG, "onBindViewHolder: called!");
        final DrawerEntry entry = drawerEntries.get(position);
        if (entry instanceof DrawerTitle) {
            final ItemTitleVh vh = (ItemTitleVh) holder;
            vh.mTextView.setText(((DrawerTitle) entry).getTitle());
            vh.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null)
                        clickListener.onItemClick(((DrawerTitle) entry).getTitle());
                }
            });
        }
        if (entry instanceof DrawerWithIcon) {
            final ItemWithIconVh vh = (ItemWithIconVh) holder;
            vh.mImageView.setBackgroundResource(((DrawerWithIcon) entry).getIcon_view());
            vh.mTextView.setText(((DrawerWithIcon) entry).getIcon_name());
            vh.parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null)
                        clickListener.onItemClick(((DrawerWithIcon) entry).getIcon_name());
                }
            });
        }
        if (entry instanceof DrawerWithSwitch) {
            final ItemWithSwitchVh vh = (ItemWithSwitchVh) holder;
            vh.mImageView.setBackgroundResource(((DrawerWithSwitch) entry).getIcon_view());
            vh.mTextView.setText(((DrawerWithSwitch) entry).getIcon_name());
            vh.mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (clickListener != null)
                        clickListener.onSwitchStateChange(isChecked);
                }
            });
        }
        Log.e(TAG, "onBindViewHolder: ended!");
    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "getItemCount: called!");
        return drawerEntries.size();
    }

    public class ItemWithIconVh extends RecyclerView.ViewHolder {
        final LinearLayout parent;
        final TextView mTextView;
        final ImageView mImageView;

        public ItemWithIconVh(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_image_view);
            mTextView = itemView.findViewById(R.id.item_text_view);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    public class ItemWithSwitchVh extends RecyclerView.ViewHolder {
        final TextView mTextView;
        final ImageView mImageView;
        final Switch mSwitch;
        final RelativeLayout parent;

        public ItemWithSwitchVh(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.item_image_view);
            mTextView = itemView.findViewById(R.id.item_text_view);
            mSwitch = itemView.findViewById(R.id.item_switch_views);
            parent = itemView.findViewById(R.id.parent);
        }
    }

    public class ItemDividerVh extends RecyclerView.ViewHolder {
        public ItemDividerVh(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class ItemTitleVh extends RecyclerView.ViewHolder {
        final LinearLayout parent;
        final TextView mTextView;

        public ItemTitleVh(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            mTextView = itemView.findViewById(R.id.item_text_view);
        }
    }

}
