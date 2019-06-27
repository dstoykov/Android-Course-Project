package com.example.hotornot;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hotornot.databinding.FragmentDetailsBinding;
import com.example.hotornot.retrofit.RetrofitInstance;

public class DetailsFragment extends Fragment {
    private GpsLocation gpsLocation;
    private RetrofitInstance retrofit;
    private FragmentDetailsBinding binding;

    public DetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gpsLocation = GpsLocation.getInstance(getActivity());
        retrofit = RetrofitInstance.getInstance();
    }
}
