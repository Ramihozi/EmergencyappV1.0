package com.example.emergencyapp.ui.notifications;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emergencyapp.R;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    //Initialize variable
    Activity activity;
    ArrayList<ContactModel> arrayList;

    //Create constructor
    public MainAdapter(Activity activity, ArrayList<ContactModel> arrayList){
        this.activity = activity;
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Initialize view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact,parent,false);

        //Return view
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Initialize Contact model
        ContactModel model = arrayList.get(position);

        //Set name
        holder.tvName.setText(model.getName());
        //Set number
        holder.tvNumber.setText(model.getNumber());
        //Set call number
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Initialize variable
        TextView tvName,tvNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variable
            tvName = itemView.findViewById(R.id.tv_name);
            tvNumber = itemView.findViewById(R.id.tv_number);
        }
    }
}
