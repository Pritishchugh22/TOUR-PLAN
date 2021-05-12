package com.example.tour_plan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class schedules extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("DATA");
    private DatabaseReference datachild;
    MyAdapter myAdapter;
    ArrayList<data> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedules);

        recyclerView = findViewById(R.id.dataList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter= new MyAdapter(this, list);
        recyclerView.setAdapter(myAdapter);


        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot :  dataSnapshot.getChildren()){
                    //data data = snapshot.getValue(data.class);
                    //list.add(data);
                    for( DataSnapshot childsnap : snapshot.getChildren()){
                        data data = childsnap.getValue(data.class);
                        list.add(data);
                    }
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}