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
import com.google.firebase.database.FirebaseDatabase;

public class registerUser extends AppCompatActivity implements View.OnClickListener{
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth;
    private TextView Login,register;
    private EditText editTextfullname,editTextemail,editTextpassword, editTextphonenb, editTextcountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        mAuth = FirebaseAuth.getInstance();

        Login = (TextView) findViewById(R.id.Login);
        Login.setOnClickListener(this);

        register= (Button) findViewById(R.id.register_btn);
        register.setOnClickListener(this);


        editTextfullname=(EditText) findViewById(R.id.fullname);
        editTextpassword=(EditText) findViewById(R.id.password_register);
        editTextemail=(EditText) findViewById(R.id.email);
        editTextphonenb=(EditText) findViewById(R.id.PhoneNumber);
        editTextcountry=(EditText) findViewById(R.id.country);




    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.register_btn:
                registerUser();
                break;

            case R.id.Login:
                startActivity(new Intent(this, MainActivity.class));
                break;


        }
    }
    private void registerUser(){
        String email=editTextemail.getText().toString().trim();
        String fullname=editTextfullname.getText().toString().trim();
        String password=editTextpassword.getText().toString().trim();
        String phonenb=editTextphonenb.getText().toString().trim();
        String country=editTextcountry.getText().toString().trim();

        if(fullname.isEmpty()){
            editTextfullname.setError("Full name is required!");
            editTextfullname.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editTextemail.setError("Email is required!");
            editTextemail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextemail.setError("Please provide valid email!");
            editTextemail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextpassword.setError("Password is required!");
            editTextemail.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextpassword.setError("Minimum length 6 required");
            editTextemail.requestFocus();
            return;
        }
        if(phonenb.isEmpty()){
            editTextphonenb.setError("Phone number is required!");
            editTextphonenb.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if (task.isSuccessful()){
                            User user = new User(fullname,email,phonenb,country);

                            FirebaseDatabase.getInstance().getReference("User")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(registerUser.this,"User has been registered",Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(registerUser.this,"Failed to register Try again!",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });


                        }
                        else{
                            Toast.makeText(registerUser.this,"Failed to register Try again!",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}