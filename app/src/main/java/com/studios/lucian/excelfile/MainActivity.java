package com.studios.lucian.excelfile;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    private List<String> item = null;
    private List<String> path = null;
    private String root;
    private TextView myPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            myPath = (TextView) findViewById(R.id.path);
            root = Environment.getExternalStorageDirectory().getPath();
            getDir(root);
        } catch (Exception ex) {
            new AlertDialog.Builder(this).setTitle(ex.getMessage()).setPositiveButton("OK", null).show();
        }
    }

    private void getDir(String dirPath) {
        myPath.setText("Location: " + dirPath);
        item = new ArrayList<>();
        path = new ArrayList<>();
        File file = new File(dirPath);
        File[] files = file.listFiles();

        if (!dirPath.equals(root)) {
            item.add(root);
            path.add(root);
            item.add("../");
            path.add(file.getParent());
        }

        for (File file1 : files) {
            if (!file1.isHidden() && file1.canRead()) {
                path.add(file1.getPath());
                if (file1.isDirectory()) {
                    item.add(file1.getName() + "/");
                } else {
                    item.add(file1.getName());
                }
            }
        }
        ArrayAdapter<String> fileList = new ArrayAdapter<>(this, R.layout.row, item);
        setListAdapter(fileList);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        File file = new File(path.get(position));

        if (file.isDirectory()) {
            if (file.canRead()) {
                getDir(path.get(position));
            } else {
                new AlertDialog.Builder(this).setTitle("[" + file.getName() + "] folder can't be read!").setPositiveButton("OK", null).show();
            }
        } else {
            //Intent intent = new Intent(this, ReadSelectedFile.class);
            //intent.putExtra(getString(R.string.file_absolute_path), file.getAbsolutePath());
            //startActivity(intent);
            //new AlertDialog.Builder(this).setTitle(file.getName()).setPositiveButton("OK", null).show();
        }
    }
}
