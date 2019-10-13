package com.example.sqliteloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    private TextView welcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcome = findViewById(R.id.tvWelcome);
        String msg = "Welcome " + getIntent().getExtras().getString("EMAIL").toString();
        welcome.setText(msg);
    }
}
