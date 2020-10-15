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
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.emergencyapp.R;


public class NotificationsFragment extends Fragment {

    private EditText editTextNumber;
    private EditText editTextMessage;
    private EditText editText;
    private NotificationsViewModel notificationsViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission
        .SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);

        Button button = root.findViewById(R.id.button);
        editTextMessage = root.findViewById(R.id.editText);
        editTextNumber = root.findViewById(R.id.editTextNumber);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String message = editTextMessage.getText().toString();
                        String number = editTextNumber.getText().toString();

                        SmsManager mySmsManager = SmsManager.getDefault();
                        mySmsManager.sendTextMessage(number,null,message, null, null);

                    }
                }
        );





        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    public void sendSMS(View view){
        String message = editTextMessage.getText().toString();
        String number = editTextNumber.getText().toString();

        SmsManager mySmsManager = SmsManager.getDefault();
        mySmsManager.sendTextMessage(number,null,message, null, null);
    }
}