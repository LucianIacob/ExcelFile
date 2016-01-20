package com.studios.lucian.excelfile;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.studios.lucian.excelfile.Model.Student;
import com.studios.lucian.excelfile.Utils.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadSelectedFile extends AppCompatActivity {

    private ArrayList<Student> studentList;

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
        ArrayList<String> listToDisplay = new ArrayList<>();
        for (Student student : studentList) {
            String studentToDisplay = Constants.NAME + student.getName() + Constants.SECTION +
                    student.getFaculty() + Constants.AVERAGE + student.getAverage();
            listToDisplay.add(studentToDisplay);
        }
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, R.layout.displayed_student, listToDisplay);
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(stringArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), SelectedStudent.class);
                intent.putExtra("crt", studentList.get(position).getCrt());
                intent.putExtra("name", studentList.get(position).getName());
                intent.putExtra("faculty", studentList.get(position).getFaculty());
                intent.putExtra("grade", studentList.get(position).getAverage());
                intent.putExtra("year", studentList.get(position).getYearOfStudy());
                startActivity(intent);
            }
        });
    }

    private void parseExcelFile(String file_path) {
        try {
            studentList = new ArrayList<>();
            File file = new File(file_path);
            FileInputStream fileInputStream = new FileInputStream(file);

            Workbook workbook = Workbook.getWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(0);

            int numRows = sheet.getRows();
            Student student;

            for (int i = 8; i < numRows - 12; i++) {
                student = new Student();
                student.setCrt(sheet.getCell(0, i).getContents());
                student.setName(sheet.getCell(1, i).getContents());
                student.setYearOfStudy(sheet.getCell(2, i).getContents());
                student.setFaculty(sheet.getCell(3, i).getContents());
                student.setAverage(sheet.getCell(5, i).getContents());
                studentList.add(student);
            }
        } catch (BiffException | IOException e) {
            new AlertDialog.Builder(this).setTitle(e.getMessage()).setPositiveButton("OK", null).show();
        }
    }
}
