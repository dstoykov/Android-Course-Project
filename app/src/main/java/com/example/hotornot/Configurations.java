package com.example.hotornot;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;

public class Configurations {

    public static void configureTabLayoutTextColors(TabLayout tabLayout, int textColor, int selectedTextColor, Context context) {
        tabLayout.setTabTextColors(ContextCompat.getColor(context, textColor), ContextCompat.getColor(context, selectedTextColor));
    }
}
