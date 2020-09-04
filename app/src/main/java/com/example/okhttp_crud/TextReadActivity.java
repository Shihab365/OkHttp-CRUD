package com.example.okhttp_crud;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TextReadActivity extends AppCompatActivity {

    Button btnAll, btnSingle;
    String json_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_read);

        btnAll = findViewById(R.id.btnReadAllID);
        btnSingle = findViewById(R.id.btnReadSingleID);

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllProduct();
            }
        });

        btnSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TextReadActivity.this,ReadSingleActivity.class));
            }
        });
    }

    public void getAllProduct(){
        //String urls = "http://10.124.67.241/okhttp_api/text_read/read_all/read_all_data.php";
        String urls = "http://platinum007.000webhostapp.com/okhttp/LiveVersion/text_read/read_all/read_all_data.php";
        OkHttpClient client = new OkHttpClient();
        Request request=new Request.Builder().url(urls).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    final String str = response.body().string();
                    TextReadActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            json_string=str;
                            if(json_string==null){
                                Toast.makeText(TextReadActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent intent=new Intent(TextReadActivity.this,ReadAllActivity.class);
                                intent.putExtra("json_data",json_string);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });
    }
}
