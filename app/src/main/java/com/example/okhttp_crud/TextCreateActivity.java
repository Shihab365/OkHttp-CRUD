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

public class TextCreateActivity extends AppCompatActivity {

    EditText editProName, editComName, editComAddress, editComEmail, editComPhone;
    Button createButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_create);

        editProName = findViewById(R.id.editProNameID);
        editComName = findViewById(R.id.editComNameID);
        editComAddress = findViewById(R.id.editComAddressID);
        editComEmail = findViewById(R.id.editComEmailID);
        editComPhone = findViewById(R.id.editComPhoneID);
        createButton = findViewById(R.id.createSubmitButtonID);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //new orderProduct().execute("http://10.117.136.164/okhttp_api/text_create/insert_data.php");
                        new orderProduct().execute("http://platinum007.000webhostapp.com/okhttp/LiveVersion/text_create/insert_data.php");
                    }
                }).start();
            }
        });
    }
    private class orderProduct extends AsyncTask<String, Void, String> {

        String strProName = editProName.getText().toString();
        String strComName = editComName.getText().toString();
        String strComAddress = editComAddress.getText().toString();
        String strComEmail = editComEmail.getText().toString();
        String strComPhone = editComPhone.getText().toString();

        @Override
        protected String doInBackground(String... strings) {

            OkHttpClient client = new OkHttpClient();

            RequestBody body = new FormBody.Builder()
                    .add("Product", strProName)
                    .add("Company", strComName)
                    .add("Address", strComAddress)
                    .add("Email", strComEmail)
                    .add("Phone", strComPhone)
                    .build();

            Request request = new Request.Builder().url(strings[0])
                    .post(body)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if(response.isSuccessful()){
                    startActivity(new Intent(TextCreateActivity.this,TextCrudActivity.class));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
