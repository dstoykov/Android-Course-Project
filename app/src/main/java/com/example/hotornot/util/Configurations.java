package com.example.hotornot.util;

import android.content.Context;
import com.google.android.material.tabs.TabLayout;
import androidx.core.content.ContextCompat;

public class Configurations {
    public static void configureTabLayoutTextColors(TabLayout tabLayout, int textColor, int selectedTextColor, Context context) {
        tabLayout.setTabTextColors(ContextCompat.getColor(context, textColor), ContextCompat.getColor(context, selectedTextColor));
    }
}
