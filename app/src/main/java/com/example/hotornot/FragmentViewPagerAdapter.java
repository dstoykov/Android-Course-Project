package com.example.hotornot;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> fragmentsTitles;

    public FragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        fragmentsTitles = new ArrayList<>();
    }

    public void addFragment(Fragment fragment, String fragmentTitle) {
        fragments.add(fragment);
        fragmentsTitles.add(fragmentTitle);
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(final int position) {
        return fragmentsTitles.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
