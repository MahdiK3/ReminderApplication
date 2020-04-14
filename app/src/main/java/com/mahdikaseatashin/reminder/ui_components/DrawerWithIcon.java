package com.mahdikaseatashin.reminder.ui_components;

public class DrawerWithIcon extends DrawerEntry {
    private int icon_view;
    private String icon_name;

    public DrawerWithIcon(int icon_view, String icon_name) {
        this.icon_view = icon_view;
        this.icon_name = icon_name;
    }

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
}
