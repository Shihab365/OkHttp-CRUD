package com.example.okhttp_crud;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TextDeleteActivity extends AppCompatActivity {

    EditText editDelProName;
    Button deleteButton, backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_delete);

        editDelProName = findViewById(R.id.editDelProNameID);
        deleteButton = findViewById(R.id.deleteSubmitButtonID);
        backButton = findViewById(R.id.btnDelBackId);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TextDeleteActivity.this,TextCrudActivity.class));
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //new deleteProduct().execute("http://10.119.224.247/okhttp_api/text_delete/delete_data.php");
                        new deleteProduct().execute("http://platinum007.000webhostapp.com/okhttp/LiveVersion/text_delete/delete_data.php");
                    }
                }).start();
            }
        });
    }

    public class deleteProduct extends AsyncTask<String, Void, String>{

        String strDel = editDelProName.getText().toString();
        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();

            RequestBody body = new FormBody.Builder()
                    .add("Product", strDel)
                    .build();

            Request request = new Request.Builder().url(strings[0])
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if(response.isSuccessful()){
                    startActivity(new Intent(TextDeleteActivity.this,TextCrudActivity.class));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
