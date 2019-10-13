package com.example.sqliteloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowRecords extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    TableLayout tbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_records);

        tbl = findViewById(R.id.tbl);
        tbl.setColumnStretchable(0,true);
        tbl.setColumnStretchable(1,true);
        tbl.setColumnStretchable(2,true);
        tbl.setColumnStretchable(3,true);
        tbl.setColumnStretchable(4,true);
        tbl.setColumnStretchable(5,true);

        ArrayList<ArrayList<String>> allRec = helper.getAllRecords();
        DynamicRowCreate(allRec);
    }

    public void DynamicRowCreate(ArrayList<ArrayList<String>> recs){

        for(int i=0; i<recs.size();i++){
            TableRow tr = new TableRow(this);
            TextView id = new TextView(this);
            TextView fn = new TextView(this);
            TextView ln = new TextView(this);
            TextView em = new TextView(this);
            TextView ph = new TextView(this);
            TextView ps = new TextView(this);

            id.setText(recs.get(i).get(0));
            fn.setText(recs.get(i).get(1));
            ln.setText(recs.get(i).get(2));
            em.setText(recs.get(i).get(3));
            ph.setText(recs.get(i).get(4));
            ps.setText(recs.get(i).get(5));

            id.setGravity(Gravity.CENTER);
            fn.setGravity(Gravity.CENTER);
            ln.setGravity(Gravity.CENTER);
            em.setGravity(Gravity.CENTER);
            ph.setGravity(Gravity.CENTER);
            ps.setGravity(Gravity.CENTER);

            tr.addView(id);
            tr.addView(fn);
            tr.addView(ln);
            tr.addView(em);
            tr.addView(ph);
            tr.addView(ps);

            tbl.addView(tr);

        }

    }
}
