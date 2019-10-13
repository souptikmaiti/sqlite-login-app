package com.example.sqliteloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    private EditText etEmail;
    private EditText etPassword;
    private Button btLogin;
    private TextView tvRegister;
    private TextView tvForgetPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail =  findViewById(R.id.etEmail);
        etPassword =  findViewById(R.id.etPass);
        btLogin =  findViewById(R.id.btnLogin);
        tvRegister =  findViewById(R.id.tvReg);
        tvForgetPass = findViewById(R.id.tvForgetPass);

    }

    public void onLogin(View view){
        String email = etEmail.getText().toString();
        String pass = etPassword.getText().toString();
        if(email.isEmpty() || pass.isEmpty()){
            Toast.makeText(getApplicationContext(),"email and password can't be blank",Toast.LENGTH_LONG).show();
        }
        else{
            String password = helper.searchUser(email);
            if(pass.equals(password)){
                Intent intent = new Intent(MainActivity.this, Welcome.class);
                intent.putExtra("EMAIL",email);
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(), "email and password don't match", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onRegister(View view){
        Intent intent = new Intent(MainActivity.this, Registration.class);
        startActivity(intent);
    }

    public void onForgetPassword(View view){
        startActivity(new Intent(MainActivity.this, ForgetPassword.class));
    }

    public void onShow(View v){
        startActivity(new Intent(MainActivity.this,ShowRecords.class));
    }

}
