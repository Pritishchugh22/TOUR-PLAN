package com.example.tour_plan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class choose extends AppCompatActivity implements View.OnClickListener {
    private TextView add, register_tours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        add = (Button) findViewById(R.id.newtour);
        add.setOnClickListener(this);

        register_tours = (Button) findViewById(R.id.tour_view);
        register_tours.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newtour:
                startActivity(new Intent(this, add.class));
                break;
            case R.id.tour_view:
                startActivity(new Intent(this, schedules.class));
                break;

        }
    };
}