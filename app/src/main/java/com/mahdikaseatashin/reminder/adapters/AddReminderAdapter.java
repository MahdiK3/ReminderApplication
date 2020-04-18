package com.mahdikaseatashin.reminder.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mahdikaseatashin.reminder.R;
import com.mahdikaseatashin.reminder.interfaces.OnAddReminderItemClickListener;
import com.mahdikaseatashin.reminder.ui_components.AddReminderItems;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AddReminderAdapter extends RecyclerView.Adapter<AddReminderAdapter.ItemVh> {
    private List<AddReminderItems> reminderItems;
    private LayoutInflater inflater;
    private OnAddReminderItemClickListener clickListener;

    public void setClickListener(OnAddReminderItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public AddReminderAdapter(List<AddReminderItems> reminderItems, Context context) {
        this.reminderItems = reminderItems;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ItemVh onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemLayoutView;
        itemLayoutView = inflater.inflate(R.layout.item_add_reminder, viewGroup, false);
        return new ItemVh(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemVh holder, int position) {
        final AddReminderItems adapter = reminderItems.get(position);
        holder.mImageView.setBackgroundResource(adapter.getItem_view());
        holder.mTitle.setText(adapter.getItem_name());
        holder.mSubTitle.setText(adapter.getItem_text());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null)
                    clickListener.onAddReminderItemClick(adapter.getItem_name());

            }
        });
    }

    @Override
    public int getItemCount() {
        return reminderItems.size();
    }


    class ItemVh extends RecyclerView.ViewHolder {

        final RelativeLayout parent;
        final TextView mTitle;
        final ImageView mImageView;
        final TextView mSubTitle;

        protected ItemVh(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.add_parent);
            mTitle = itemView.findViewById(R.id.add_text_view);
            mImageView = itemView.findViewById(R.id.add_icon);
            mSubTitle = itemView.findViewById(R.id.add_text_view_sub);
        }
    }
}
