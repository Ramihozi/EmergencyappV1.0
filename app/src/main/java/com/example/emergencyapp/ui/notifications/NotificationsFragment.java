package com.example.emergencyapp.ui.notifications;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emergencyapp.R;

import java.util.ArrayList;


public class NotificationsFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<ContactModel> arrayList = new ArrayList<>();
    MainAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        //Assign variable
        recyclerView = root.findViewById(R.id.recycler_view);

        //Check permission
        checkPermission();





        return root;



    }

    private void checkPermission() {
        //Check condition
        if (ContextCompat.checkSelfPermission(getContext()
                , Manifest.permission.READ_CONTACTS)
        != PackageManager.PERMISSION_GRANTED){
            //When permission is not granted
            //Request permission
            ActivityCompat.requestPermissions(getActivity()
            ,new String[]{Manifest.permission.READ_CONTACTS}, 100);
            
        }else {
            //When permission is granted
            //Create method
            getContactList();
        }
    }

    private void getContactList() {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        //Sort by ascending
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC";
        //Initialize cursor
        Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(
                uri, null, null, null, sort
        );
        //Check condition
        if(cursor.getCount() > 0){
            //When count is greater than 0
            //Use While loop
            while (cursor.moveToNext()){
                //Cursor move to text
                //Get contact id
                @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.Contacts._ID
                ));
                //Get contract name
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME
                ));
                //Initialize phone uri
                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                //Initialize selection
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                        +" =?";
                //Initialize Phone cursor
                Cursor phoneCursor = getActivity().getApplicationContext().getContentResolver().query(
                        uriPhone,null, selection
                        , new String[]{id}, null
                );
                //Check condition
                if (phoneCursor.moveToNext()){
                    //WHen phone cursor move to next
                    @SuppressLint("Range") String number = phoneCursor.getString(phoneCursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER
                    ));
                    //Initialize contact model
                    ContactModel model = new ContactModel();
                    //Set name
                    model.setName(name);
                    //Set Number
                    model.setNumber(number);
                    //Add model in array list
                    arrayList.add(model);
                    //Close phone Cursor
                    phoneCursor.close();
                }
            }
            //Close cursor
            cursor.close();
        }
        //Set layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //Initialize adapter
        adapter = new MainAdapter(getActivity(),arrayList);
        //Set adapter
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //Check condition
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0]
                == PackageManager.PERMISSION_GRANTED){
            //When permission is granted
            //Call method
            getContactList();
        }else {
            //When permission is denied
            //Display toast
            Toast.makeText(getContext(), "Permission Denied.",
                    Toast.LENGTH_SHORT).show();
            //Call check permission method
            checkPermission();
        }
    }

}

