package com.example.emergencyapp.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;




import com.example.emergencyapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DashboardFragment extends Fragment {
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

                //initialize map fragment
                SupportMapFragment supportMapFragment = (SupportMapFragment)
                        getChildFragmentManager().findFragmentById((R.id.google_map));
                //Async map
                supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(final GoogleMap googleMap) {
                        //when map is loaded
                        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                            @Override
                            public void onMapClick(LatLng latLng) {
                                //when clicked on map
                                //initialize mrker options
                                MarkerOptions markerOptions = new MarkerOptions();
                                //Set position of marker
                                markerOptions.position(latLng);
                                //Set title of marker
                                markerOptions.title(latLng.latitude + "" + latLng.longitude);
                                //Remove all marker
                                googleMap.clear();
                                //Animating to zoom the marker
                                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                        latLng, 10
                                ));
                                //Add marker on map
                                googleMap.addMarker(markerOptions);
                            }
                        });
                    }
                });
            }
        });
        return root;
    }
}