package com.studios.lucian.excelfile;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

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
            list = new ArrayList<>();
            File file = new File(file_path);
            FileInputStream fileInputStream = new FileInputStream(file);

            Workbook workbook = Workbook.getWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(0);

            int row = sheet.getRows();
            String listItem;
            for (int i = 8; i < 127; i++) {
                listItem = "";
                Cell cell = sheet.getCell(1, i);
                listItem += cell.getContents();

                list.add(listItem);
            }
        } catch (BiffException | IOException e) {
            new AlertDialog.Builder(this).setTitle(e.getMessage()).setPositiveButton("OK", null).show();
        }
    }
}
