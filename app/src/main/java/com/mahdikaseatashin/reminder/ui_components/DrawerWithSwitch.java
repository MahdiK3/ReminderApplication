package com.mahdikaseatashin.reminder.ui_components;

public class DrawerWithSwitch extends DrawerEntry {
    private int icon_view;
    private String icon_name;
    private boolean checked;

    public int getIcon_view() {
        return icon_view;
    }

    public void setIcon_view(int icon_view) {
        this.icon_view = icon_view;
    }

    public String getIcon_name() {
        return icon_name;
    }

    public void setIcon_name(String icon_name) {
        this.icon_name = icon_name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public DrawerWithSwitch(int icon_view, String icon_name) {
        this.icon_view = icon_view;
        this.icon_name = icon_name;
    }
}
