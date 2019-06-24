package com.example.hotornot;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DetailsFragment extends Fragment {

    private GpsLocation gpsLocation;

    public DetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        gpsLocation = GpsLocation.getInstance(getActivity());
        System.out.println(gpsLocation.getLocation());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println(gpsLocation.getLocation());
    }
}
