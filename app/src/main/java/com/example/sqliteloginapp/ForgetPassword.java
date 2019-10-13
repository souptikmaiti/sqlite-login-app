package com.example.sqliteloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ForgetPassword extends AppCompatActivity {

    DatabaseHelper helper =new DatabaseHelper(this);
    private EditText etEmail, etPhone, etNewPass, etConNewPass;
    private Button btnUpdatePass, btnLogin, btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etNewPass = findViewById(R.id.etNewPass);
        etConNewPass = findViewById(R.id.etConNewPass);
        btnUpdatePass = findViewById(R.id.btnUpdatePass);
    }

    public void onUpdatePassword(View view){
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String newPassword = etNewPass.getText().toString();
        String conNewPassword = etConNewPass.getText().toString();

        if(email.isEmpty() || phone.isEmpty() || newPassword.isEmpty() || conNewPassword.isEmpty()){
            Toast.makeText(getApplicationContext(),"all fields must be filled",Toast.LENGTH_LONG).show();
        }
        else{
            if(!newPassword.equals(conNewPassword)){
                Toast.makeText(getApplicationContext(),"Confirm Password does not match",Toast.LENGTH_LONG).show();
            }
            else{
                ArrayList<String> userCred = helper.checkUser(email,phone);
                if(userCred.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Not a user. Please Register",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(ForgetPassword.this, Registration.class));
                }
                else if(userCred.get(1).equals(newPassword)){
                    Toast.makeText(getApplicationContext(),"New password must be different from old one",Toast.LENGTH_LONG).show();
                }
                else{
                    helper.updatePassword(userCred.get(0),newPassword);
                    Toast.makeText(getApplicationContext(),"password updated",Toast.LENGTH_LONG).show();
                }
            }

        }
    }

    public void onLogin(View v){
        startActivity(new Intent(ForgetPassword.this, MainActivity.class));
    }

    public void onReg(View v){
        startActivity(new Intent(ForgetPassword.this, Registration.class));
    }
}
