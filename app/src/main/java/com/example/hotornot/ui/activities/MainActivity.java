package com.example.hotornot.ui.activities;

import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.hotornot.db.DBController;
import com.example.hotornot.ui.fragments.DetailsFragment;
import com.example.hotornot.ui.fragments.FragmentViewPagerAdapter;
import com.example.hotornot.ui.fragments.OverallFragment;
import com.example.hotornot.R;
import com.example.hotornot.databinding.ActivityMainBinding;
import com.example.hotornot.gps.GpsLocation;
import com.example.hotornot.util.Configurations;

public class MainActivity extends AppCompatActivity {
    private static final String OVERALL_TAB_TITLE = "Overall";
    private static final String DETAILS_TAB_TITLE = "Details";
    private static final long DELAY_MILLIS = 1000L;

    private ActivityMainBinding binding;
    private FragmentViewPagerAdapter fragmentViewPagerAdapter;
    private DBController dbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initScreen();
    }

    private void initScreen() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.progressBar.setVisibility(View.INVISIBLE);
        dbController = DBController.getInstance(this, this);
        setSupportActionBar((Toolbar) binding.toolbar);
        Configurations.configureTabLayoutTextColors(binding.tabLayout, R.color.lightGrey, R.color.white, this);
        initFragments();
        askForLocationPermission();
    }

    private void initFragments() {
        fragmentViewPagerAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
        fragmentViewPagerAdapter.addFragment(new OverallFragment(), OVERALL_TAB_TITLE);
        fragmentViewPagerAdapter.addFragment(new DetailsFragment(), DETAILS_TAB_TITLE);
        binding.contentViewPager.setAdapter(fragmentViewPagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.contentViewPager);
    }

    private void askForLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    GpsLocation.LOCATION_REQUEST_CODE);
        }
    }

    private void refreshForecast() {
        binding.progressBar.bringToFront();
        binding.progressBar.setVisibility(View.VISIBLE);
        dbController.updateDb();
        new Handler().postDelayed(() -> initFragments(), DELAY_MILLIS);
        new Handler().postDelayed(() -> binding.progressBar.setVisibility(View.INVISIBLE), DELAY_MILLIS);
    }

    private void refreshForecastByTown(String town) {
        binding.progressBar.bringToFront();
        binding.progressBar.setVisibility(View.VISIBLE);
        dbController.updateDbByTown(town);
        new Handler().postDelayed(() -> initFragments(), DELAY_MILLIS);
        new Handler().postDelayed(() -> binding.progressBar.setVisibility(View.INVISIBLE), DELAY_MILLIS);
    }

    private void initSearchViewListeners(MenuItem item, SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!query.isEmpty()) {
                    refreshForecastByTown(query);
                }
                searchView.clearFocus();
                item.collapseActionView();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        initSearchViewListeners(item, searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            refreshForecast();
        }
        return true;
    }
}
