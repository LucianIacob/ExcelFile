package com.studios.lucian.excelfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class SelectedStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_student);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String crt = intent.getStringExtra("crt");
        String year = intent.getStringExtra("year");
        String name = intent.getStringExtra("name");
        String grade = intent.getStringExtra("grade");
        String faculty = intent.getStringExtra("faculty");

        setTitle(name);

        TextView textVewCrt = (TextView) findViewById(R.id.textDisplayCRT);
        TextView textViewGrade = (TextView) findViewById(R.id.textDisplayGrade);
        TextView textViewYear = (TextView) findViewById(R.id.textDisplayYear);
        TextView textViewFaculty = (TextView) findViewById(R.id.textDisplayFaculty);
        textVewCrt.setText(crt);
        textViewGrade.setText(grade);
        textViewYear.setText(year);
        textViewFaculty.setText(faculty);
    }
}
