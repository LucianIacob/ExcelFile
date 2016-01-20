package com.studios.lucian.excelfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class ReadSelectedFile extends AppCompatActivity {

    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_selected_file);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        Intent intent = getIntent();
        String file_path = intent.getStringExtra(getString(R.string.file_absolute_path));
        parseExcelFile(file_path);
        displayStudents();
    }

    private void displayStudents() {
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, R.layout.row, list);
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(stringArrayAdapter);
    }

    private void parseExcelFile(String file_path) {
        try {
            File file = new File(file_path);
            FileInputStream fileInputStream = new FileInputStream(file);

            list = new ArrayList<>();
            list.add("Toni");
            list.add("Alex");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
