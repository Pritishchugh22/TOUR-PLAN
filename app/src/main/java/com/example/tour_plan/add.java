package com.example.tour_plan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add extends AppCompatActivity implements View.OnClickListener {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference root = database.getReference();
    private FirebaseAuth mAuth;
    private TextView addtour;
    private EditText editTextpickup,editTextdestination,editTextdate, editTextphonenum, editTextname;
    public Integer cnt=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mAuth = FirebaseAuth.getInstance();

        addtour= (Button) findViewById(R.id.add_button);
        addtour.setOnClickListener(this);


        editTextpickup=(EditText) findViewById(R.id.from);
        editTextdestination=(EditText) findViewById(R.id.destination);
        editTextdate=(EditText) findViewById(R.id.date);
        editTextphonenum=(EditText) findViewById(R.id.number);
        editTextname=(EditText) findViewById(R.id.name);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_button:
                add_tour();
                cnt++;
                break;
        }

    }

    private void add_tour() {

        String from=editTextpickup.getText().toString().trim();
        String destination=editTextdestination.getText().toString().trim();
        String date=editTextdate.getText().toString().trim();
        String phonenum=editTextphonenum.getText().toString().trim();
        String name=editTextname.getText().toString().trim();
        String count=cnt.toString().trim();

        if(from.isEmpty()){
            editTextpickup.setError("Pick-up location is required!");
            editTextpickup.requestFocus();
            return;
        }
        if(destination.isEmpty()){
            editTextdestination.setError("Destination is required!");
            editTextdestination.requestFocus();
            return;
        }

        if(date.isEmpty()){
            editTextdate.setError("date is required!");
            editTextdate.requestFocus();
            return;
        }
        if(name.isEmpty()){
            editTextname.setError("Name is required!");
            editTextname.requestFocus();
            return;
        }
        if(phonenum.isEmpty()){
            editTextphonenum.setError("Phone number is required!");
            editTextphonenum.requestFocus();
            return;
        }

        Add_data data = new Add_data(from,destination,phonenum,date,name);

        FirebaseDatabase.getInstance().getReference("DATA")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child(count)
                .setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(add.this,"Your tour has been registered! Go to View tours",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(add.this,"Failed to enter the tour Try again!",Toast.LENGTH_LONG).show();
                }
            }
        });






    }

}