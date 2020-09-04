package com.example.okhttp_crud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FileCrudActivity extends AppCompatActivity {

    Button btnSingleFile, btnMultiFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_crud);

        btnSingleFile = findViewById(R.id.btnSingleFileId);
        btnMultiFile = findViewById(R.id.btnMultiFileId);

        btnSingleFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FileCrudActivity.this,SingleFileActivity.class));
            }
        });

        btnMultiFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FileCrudActivity.this,MultiFileActivity.class));
            }
        });
    }
}
