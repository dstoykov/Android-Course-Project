package com.example.hotornot.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hotornot.R;
import com.example.hotornot.databinding.FragmentDetailsBinding;
import com.example.hotornot.db.DBController;
import com.example.hotornot.db.Forecast;
import com.example.hotornot.db.RoomInstance;
import com.example.hotornot.ui.recyclerview.DetailsAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsFragment extends Fragment {
    private RoomInstance room;
    private FragmentDetailsBinding binding;
    private DBController dbController;

    public DetailsFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        room = RoomInstance.getInstance(getContext());
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        dbController = DBController.getInstance(getContext(), getActivity());
        initViews();
        loadDataFromDb();
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initViews() {
//        binding.detailsSwipeRefresh.setOnRefreshListener(() -> {
//            dbController.updateDb();
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    loadDataFromDb();
//                }
//            }, 1000);
//        });
    }

    private void loadDataFromDb() {
        room.getHourlyForecast(data -> setupRecyclerView(data));
    }

    private void setupRecyclerView(List<Forecast> forecasts) {
        DetailsAdapter adapter = new DetailsAdapter(forecasts);
        binding.recViewDetails.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recViewDetails.setAdapter(adapter);
    }
}
