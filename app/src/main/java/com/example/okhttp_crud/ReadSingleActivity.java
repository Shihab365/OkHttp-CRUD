package com.example.okhttp_crud;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ReadSingleActivity extends AppCompatActivity {
    EditText editSearchItem;
    Button btnSearch, btnBack;
    TextView textAll;
    //final String urls = "http://10.124.67.241/okhttp_api/text_read/read_single/read_single_data.php";
    final String urls = "http://platinum007.000webhostapp.com/okhttp/LiveVersion/text_read/read_single/read_single_data.php";
    String Product, Company, Address, Email, Phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_single);

        editSearchItem = findViewById(R.id.editReadProNameID);
        btnSearch = findViewById(R.id.readButtonSrcID);
        btnBack = findViewById(R.id.readBackButtonID);
        textAll = findViewById(R.id.txtSingleAllId);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReadSingleActivity.this,TextCrudActivity.class));
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSearch = editSearchItem.getText().toString();
                new getSingleProduct().execute(strSearch);
            }
        });
    }

    public class getSingleProduct extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            String strSearch = strings[0];
            OkHttpClient okHttpClient= new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("Product", strSearch)
                    .build();
            Request request = new Request.Builder()
                    .url(urls)
                    .post(formBody)
                    .build();
            Response response=null;
            try{
                response = okHttpClient.newCall(request).execute();
                if(response.isSuccessful()){
                    String result= response.body().string();
                    JSONObject obj = new JSONObject(result);
                    JSONObject employee = obj.getJSONObject("data");
                    Product = employee.getString("Product");
                    Company = employee.getString("Company");
                    Address = employee.getString("Address");
                    Email = employee.getString("Email");
                    Phone = employee.getString("Phone");

                    textAll.setText("Product: "+Product+"\n\nCompany: "+Company+"\n\nAddress: "+Address+"\n\nEmail: "+Email+"\n\nPhone: "+Phone);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        return null;
        }
    }

}
