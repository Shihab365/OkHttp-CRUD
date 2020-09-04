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

public class TextUpdateActivity extends AppCompatActivity {

    EditText editProName, editProAddress, editProPhone;
    Button updateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_update);

        editProName = findViewById(R.id.editUpdateProNameID);
        editProAddress = findViewById(R.id.editUpdateProAddressID);
        editProPhone = findViewById(R.id.editUpdateProPhoneID);
        updateButton = findViewById(R.id.updateSubmitButtonID);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //new updateProduct().execute("http://10.119.224.247/okhttp_api/text_update/edit_data.php");
                        new updateProduct().execute("http://platinum007.000webhostapp.com/okhttp/LiveVersion/text_update/edit_data.php");
                    }
                }).start();
            }
        });
    }
    public class updateProduct extends AsyncTask<String, Void, String>{
        String strUpPro = editProName.getText().toString();
        String strUpAdd = editProAddress.getText().toString();
        String strUpPhn = editProPhone.getText().toString();
        @Override
        protected String doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();

            RequestBody body = new FormBody.Builder()
                    .add("Product", strUpPro)
                    .add("Address", strUpAdd)
                    .add("Phone", strUpPhn)
                    .build();

            Request request = new Request.Builder().url(strings[0])
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if(response.isSuccessful()){
                    startActivity(new Intent(TextUpdateActivity.this,TextCrudActivity.class));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
