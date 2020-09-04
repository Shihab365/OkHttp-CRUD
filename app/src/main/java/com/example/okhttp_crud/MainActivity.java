package com.example.okhttp_crud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnTextCrud, btnFileCrud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTextCrud = findViewById(R.id.buttonTextCrudId);
        btnFileCrud = findViewById(R.id.buttonFileCrudId);

        btnTextCrud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,TextCrudActivity.class));
            }
        });

        btnFileCrud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,FileCrudActivity.class));
            }
        });
    }
}
