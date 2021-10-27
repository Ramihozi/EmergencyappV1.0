package com.example.emergencyapp.ui.notifications;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.emergencyapp.R;


public class NotificationsFragment extends Fragment {

    private EditText editTextNumber;
    private EditText editTextMessage;

    public NotificationsFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        NotificationsViewModel notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = rootView.findViewById(R.id.text_notifications);
        ActivityCompat.requestPermissions(requireActivity(),new String[]{Manifest.permission
        .SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);

        Button button = rootView.findViewById(R.id.button);
        editTextMessage = rootView.findViewById(R.id.editText);
        editTextNumber = rootView.findViewById(R.id.editTextNumber);
        button.setOnClickListener(
                v -> {
                    String message = editTextMessage.getText().toString();
                    String number = editTextNumber.getText().toString();

                    SmsManager mySmsManager = SmsManager.getDefault();
                    mySmsManager.sendTextMessage(number,null,message, null, null);

                }
        );

        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return rootView;
    }

}