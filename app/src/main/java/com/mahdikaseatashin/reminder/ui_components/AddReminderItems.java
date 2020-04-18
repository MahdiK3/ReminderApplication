package com.mahdikaseatashin.reminder.ui_components;

public class AddReminderItems {
    private int item_view;
    private String item_title;
    private String item_text;

    public AddReminderItems(int item_view, String item_title, String item_text) {
        this.item_view = item_view;
        this.item_title = item_title;
        this.item_text = item_text;
    }

    public String getItem_text() {
        return item_text;
    }

    public void setItem_text(String item_text) {
        this.item_text = item_text;
    }

    public int getItem_view() {
        return item_view;
    }

    public void setItem_view(int item_view) {
        this.item_view = item_view;
    }

    public String getItem_name() {
        return item_title;
    }

    public void setItem_name(String item_name) {
        this.item_title = item_name;
    }
}
