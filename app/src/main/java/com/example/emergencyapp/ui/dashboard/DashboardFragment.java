package com.example.emergencyapp.ui.dashboard;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.emergencyapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment {
        //Initialize variable
    Button btLocation;
    TextView textView1,textView2,textView3,textView4,textView5;
    FusedLocationProviderClient fusedLocationProviderClient;

    private DashboardViewModel dashboardViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        //assign variables
        btLocation = root.findViewById(R.id.bt_location);
        textView1 = root.findViewById(R.id.text_view1);
        textView2 = root.findViewById(R.id.text_view2);
        textView3 = root.findViewById(R.id.text_view3);
        textView4 = root.findViewById(R.id.text_view4);
        textView5 = root.findViewById(R.id.text_view5);

        //Initialize FusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        btLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check permission
                if (ActivityCompat.checkSelfPermission(getContext()
                ,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    //When permission granted
                    getLocation();
                }else {
                    //When permission denied
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });







        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        //Initialize Location manager
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(
                Context.LOCATION_SERVICE
        );
        //Check condition
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            //When location service is enabled
            //Get last location
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    //initialize location
                    Location location = task.getResult();

                    try {
                        //Initialize geoCoder
                        Geocoder geocoder = new Geocoder(getContext(),
                                Locale.getDefault());
                        //Initialize address list
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        //Set latitude on TextView
                        textView1.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Latitude :</b><br></font>"
                                        + addresses.get(0).getLatitude()
                        ));
                        //Set longitude
                        textView2.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Longitude :</b><br></font>"
                                        + addresses.get(0).getLongitude()
                        ));
                        //Set country name
                        textView3.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Country name :</b><br></font>"
                                        + addresses.get(0).getCountryName()
                        ));
                        //Set locality
                        textView4.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Locality :</b><br></font>"
                                        + addresses.get(0).getLocality()
                        ));
                        //Set address
                        textView5.setText(Html.fromHtml(
                                "<font color='#6200EE'><b>Address :</b><br></font>"
                                        + addresses.get(0).getAddressLine(0)
                        ));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }else  {
            //When location service is not enabled
            //open location setting
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }


}