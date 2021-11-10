package com.example.emergencyapp.ui.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.emergencyapp.MainActivity;
import com.example.emergencyapp.R;

public class HomeFragment extends Fragment {
    private static final int REQUEST_CALL = 1;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);



                ImageButton callButton4 = getActivity().findViewById(R.id.imageButton3);
                ImageButton callButton3 = getActivity().findViewById(R.id.imageButton);
                ImageButton callButton2 = getActivity().findViewById(R.id.imageButton2);
                ImageButton callButton5 = getActivity().findViewById(R.id.imageButton4);
                ImageButton callButton6 = getActivity().findViewById(R.id.imageButton5);

                callButton6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        make_Fifth_Call();
                    }

                });

                callButton5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        make_Fourth_Call();
                    }

                });

                callButton4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        makeCall();
                    }

                });
                callButton3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        make_Second_Call();
                    }

                });
                callButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        make_Third_Call();
                    }

                });
            }
            public void makeCall() {
                Intent callIntent = new Intent( Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:911") );

                if (ActivityCompat.checkSelfPermission( getContext(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{(Manifest.permission.CALL_PHONE)}, REQUEST_CALL);
                } else {
                    String dial = "tel:911";
                    startActivity( callIntent );
                }
            }
            public void make_Second_Call() {
                Intent callIntent = new Intent( Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:541-567-8822") );

                if (ActivityCompat.checkSelfPermission( getContext(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{(Manifest.permission.CALL_PHONE)}, REQUEST_CALL);
                } else {
                    String dial = "tel:541-567-8822";
                    startActivity( callIntent );
                }
            }
            public void make_Third_Call() {
                Intent callIntent = new Intent( Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:800-273-8255") );

                if (ActivityCompat.checkSelfPermission( getContext(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{(Manifest.permission.CALL_PHONE)}, REQUEST_CALL);
                } else {
                    String dial = "tel:800-273-8255";
                    startActivity( callIntent );
                }
            }
            public void make_Fourth_Call() {
                Intent callIntent = new Intent( Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:800-222-1222") );

                if (ActivityCompat.checkSelfPermission( getContext(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{(Manifest.permission.CALL_PHONE)}, REQUEST_CALL);
                } else {
                    String dial = "tel:800-222-1222";
                    startActivity( callIntent );
                }
            }


            public void make_Fifth_Call() {
                Intent callIntent = new Intent( Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:844-698-2411") );

                if (ActivityCompat.checkSelfPermission( getContext(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{(Manifest.permission.CALL_PHONE)}, REQUEST_CALL);
                } else {
                    String dial = "tel:844-698-2411";
                    startActivity( callIntent );
                }
            }
        });
        return root;




    }
















    }
