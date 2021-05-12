package com.example.tour_plan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context; ArrayList<data> list;

    public MyAdapter(Context context, ArrayList<data> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        data data = list.get(position);
        holder.FROM.setText(data.getFrom());
        holder.DESTINATION.setText(data.getDestination());
        holder.DATE.setText(data.getDate());
        holder.NAME.setText(data.getName());
        holder.PHNO.setText(data.getPhonenumber());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView FROM, DESTINATION, DATE, NAME , PHNO;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            FROM = itemView.findViewById(R.id.from_item);
            DESTINATION = itemView.findViewById(R.id.destination_item);
            DATE = itemView.findViewById(R.id.date_item);
            NAME = itemView.findViewById(R.id.name_item);
            PHNO = itemView.findViewById(R.id.phn_no_item);

        }
    }
}
