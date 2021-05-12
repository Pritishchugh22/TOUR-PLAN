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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private TextView register,login_main;
    private EditText editTextemail_login,editTextpassword_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        login_main= (Button) findViewById(R.id.login_ac);
        login_main.setOnClickListener(this);

        editTextpassword_login=(EditText) findViewById(R.id.password_login);
        editTextemail_login=(EditText) findViewById(R.id.email_login);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login_ac:
                loginUser();
                break;
            case R.id.register:
                startActivity(new Intent(this, registerUser.class));
                break;

        }
    }

    private void loginUser() {
        String email_log=editTextemail_login.getText().toString().trim();
        String password_log=editTextpassword_login.getText().toString().trim();

        if(email_log.isEmpty()){
            editTextemail_login.setError("Email is required!");
            editTextemail_login.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email_log).matches()){
            editTextemail_login.setError("Please provide valid email!");
            editTextemail_login.requestFocus();
            return;
        }
        if(password_log.isEmpty()){
            editTextpassword_login.setError("Password is required!");
            editTextemail_login.requestFocus();
            return;
        }
        if(password_log.length()<6){
            editTextpassword_login.setError("Minimum length 6 required");
            editTextemail_login.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email_log,password_log).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        startActivity(new Intent(MainActivity.this, choose.class));
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this,"Check your email to verify your account",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"Failed to login! Please check your credentials",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}