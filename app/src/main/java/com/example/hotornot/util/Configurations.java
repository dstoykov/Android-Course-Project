package com.example.hotornot.util;

import android.app.Activity;
import android.content.Context;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Configurations {
    private Configurations() {}

    public static void configureTabLayoutTextColors(TabLayout tabLayout, int textColor, int selectedTextColor, Context context) {
        tabLayout.setTabTextColors(ContextCompat.getColor(context, textColor), ContextCompat.getColor(context, selectedTextColor));
    }

    public static void setToolbarTitle(String title, Activity activity) {
        ((AppCompatActivity)activity).getSupportActionBar().setTitle(title);
    }
}
