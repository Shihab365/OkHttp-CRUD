package com.example.okhttp_crud;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ReadPdfActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_pdf);

        editText = findViewById(R.id.editPdfId);
        button = findViewById(R.id.buttonPdfId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString();
                Intent intent=new Intent(ReadPdfActivity.this,ViewPdfActivity.class);
                intent.putExtra("pdfUrl",str);
                startActivity(intent);
            }
        });
    }
}
