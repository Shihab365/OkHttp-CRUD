package com.example.okhttp_crud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MultiFileActivity extends AppCompatActivity {

    Button btnFileUpload, btnReadPhoto, btnReadPdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_file);

        btnFileUpload = findViewById(R.id.btnMultiFileUploadId);
        btnReadPhoto = findViewById(R.id.btnReadPhotoId);
        btnReadPdf = findViewById(R.id.btnReadPdfId);

        btnFileUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MultiFileActivity.this,MultiFileUploadActivity.class));
            }
        });

        btnReadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MultiFileActivity.this,ReadImageActivity.class));
            }
        });

        btnReadPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MultiFileActivity.this,ReadPdfActivity.class));
            }
        });
    }
}
