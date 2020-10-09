package com.example.emergencyapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CALL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);





        ImageButton callButton4 = findViewById(R.id.imageButton4);
        ImageButton callButton3 = findViewById(R.id.imageButton3);
        ImageButton callButton2 = findViewById(R.id.imageButton2);
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
        callIntent.setData(Uri.parse("tel:5416569727") );

        if (ActivityCompat.checkSelfPermission( MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{(Manifest.permission.CALL_PHONE)}, REQUEST_CALL);
        } else {
            String dial = "tel:5416569727";
            startActivity( callIntent );
        }
    }
    public void make_Second_Call() {
        Intent callIntent = new Intent( Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:5038698557") );

        if (ActivityCompat.checkSelfPermission( MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{(Manifest.permission.CALL_PHONE)}, REQUEST_CALL);
        } else {
            String dial = "tel:5038698557";
            startActivity( callIntent );
        }
    }
    public void make_Third_Call() {
        Intent callIntent = new Intent( Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:5413719773") );

        if (ActivityCompat.checkSelfPermission( MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{(Manifest.permission.CALL_PHONE)}, REQUEST_CALL);
        } else {
            String dial = "tel:5413719773";
            startActivity( callIntent );
        }
    }
}