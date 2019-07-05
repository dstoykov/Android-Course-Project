package com.example.hotornot.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hotornot.R;
import com.example.hotornot.databinding.FragmentOverallBinding;
import com.example.hotornot.db.DBController;
import com.example.hotornot.db.RoomInstance;
import com.example.hotornot.util.CardFiller;

public class OverallFragment extends Fragment {
    private RoomInstance room;
    private FragmentOverallBinding binding;
    private DBController dbController;

    public OverallFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        room = RoomInstance.getInstance(getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_overall, container, false);
        dbController = DBController.getInstance(getContext(), getActivity());
        initViews();
        loadForecast();
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initViews() {
        binding.overallSwipeRefresh.setOnRefreshListener(() -> {
            updateForecast();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadForecast();
                    binding.overallSwipeRefresh.setRefreshing(false);
                }
            }, 3000);
        });
    }

    private void loadForecast() {
        loadTodayDataFromDb();
        loadTomorrowDataFromDb();
    }

    private void loadTodayDataFromDb() {
        room.getTodayForecast(data -> CardFiller.fillTodayCardView(data, binding, getActivity()));
    }

    private void loadTomorrowDataFromDb() {
        room.getTomorrowForecast(data -> CardFiller.fillTomorrowCardView(data, binding, getActivity()));
    }

    private void updateForecast() {
        dbController.updateTodayAndTomorrowForecast();
    }
}
