package com.example.okhttp_crud;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SingleDeleteActivity extends AppCompatActivity {
    EditText editPhotoDel;
    Button btnPhotoDel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_delete);
        editPhotoDel = findViewById(R.id.singlePhotoDelId);
        btnPhotoDel = findViewById(R.id.singlePhotoDelBtnId);

        btnPhotoDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //new deletePhoto().execute("http://10.117.44.232/okhttp_api/photo_create/photo_delete.php");
                        new deletePhoto().execute("http://platinum007.000webhostapp.com/okhttp/LiveVersion/photo_create/photo_delete.php");
                    }
                }).start();
            }
        });
    }

    public class deletePhoto extends AsyncTask<String, Void, String> {

        String strDel = editPhotoDel.getText().toString();
        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("Name", strDel)
                    .build();

            Request request = new Request.Builder().url(strings[0])
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if(response.isSuccessful()){
                    startActivity(new Intent(SingleDeleteActivity.this,SingleFileActivity.class));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
