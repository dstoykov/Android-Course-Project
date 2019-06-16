package com.example.hotornot;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.hotornot.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    private ActivityMainBinding binding;
    private FragmentViewPagerAdapter fragmentViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initScreen();
    }

    private void initScreen() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar((Toolbar) binding.toolbar);
        Configurations.configureTabLayoutTextColors(binding.tabLayout, R.color.lightGrey, R.color.white, this);
        initFragments();
    }

    private void initFragments() {
        fragmentViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
        fragmentViewPagerAdapter.addFragment(new OverallFragment(), "Overall");
        fragmentViewPagerAdapter.addFragment(new DetailsFragment(), "Details");
        binding.contentViewPager.setAdapter(fragmentViewPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.contentViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
}
