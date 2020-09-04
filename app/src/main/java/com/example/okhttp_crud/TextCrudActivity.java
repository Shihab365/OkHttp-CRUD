package com.example.okhttp_crud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TextCrudActivity extends AppCompatActivity {

    Button btnCreate, btnRead, btnUpdate, btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_crud);

        btnCreate = findViewById(R.id.btnTextCreateId);
        btnRead = findViewById(R.id.btnTextReadId);
        btnUpdate = findViewById(R.id.btnTextUpdateId);
        btnDelete = findViewById(R.id.btnTextDeleteId);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TextCrudActivity.this,TextCreateActivity.class));
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TextCrudActivity.this,TextReadActivity.class));
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TextCrudActivity.this,TextUpdateActivity.class));
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TextCrudActivity.this,TextDeleteActivity.class));
            }
        });
    }
}
