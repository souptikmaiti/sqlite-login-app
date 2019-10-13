package com.example.sqliteloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Registration extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    private EditText etFName;
    private EditText etLName;
    private EditText etEmail;
    private EditText etPhone;
    private EditText etPass;
    private EditText etConPass;
    private Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etFName = findViewById(R.id.etFName);
        etLName = findViewById(R.id.etLName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPass = findViewById(R.id.etPass);
        etConPass = findViewById(R.id.etConPass);
        btnReg = findViewById(R.id.btnReg);

        btnReg.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                insertNewUser();
            }
        });

    }

    public void insertNewUser(){
        String fName = etFName.getText().toString();
        String lName = etLName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String password = etPass.getText().toString();
        String conPass = etConPass.getText().toString();

        if(fName.isEmpty() || lName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty() || conPass.isEmpty()){
            Toast.makeText(getApplicationContext(),"no fields should be kept blank",Toast.LENGTH_LONG).show();
        }
        else{
            if(!password.equals(conPass)){
                Toast.makeText(getApplicationContext(),"confirm password does not match",Toast.LENGTH_LONG).show();
            }
            else{
                ArrayList<String> userCred = helper.checkUser(email,phone);
                if(userCred.isEmpty()){
                    User u = new User();
                    u.setfName(fName);
                    u.setlName(lName);
                    u.setEmail(email);
                    u.setPhone(phone);
                    u.setPassword(password);
                    helper.insertUser(u);
                    startActivity(new Intent(Registration.this,MainActivity.class));
                }
                else{
                    Toast.makeText(getApplicationContext(),"User Already Exists",Toast.LENGTH_LONG).show();
                }

            }
        }

    }
}
