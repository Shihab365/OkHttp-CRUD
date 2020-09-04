package com.example.okhttp_crud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SingleFileActivity extends AppCompatActivity {

    Button btnFileCreated, btnFileRead, btnFileUpdate, btnFileDelete;
    String json_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_file);

        btnFileCreated = findViewById(R.id.btnFileCreateId);
        btnFileRead = findViewById(R.id.btnFileReadId);
        btnFileUpdate = findViewById(R.id.btnFileUpdateId);
        btnFileDelete = findViewById(R.id.btnFileDeleteId);

        btnFileCreated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleFileActivity.this,SingleCreatedActivity.class));
            }
        });

        btnFileRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllPhoto();
            }
        });

        btnFileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleFileActivity.this,SingleUpdateActivity.class));
            }
        });

        btnFileDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleFileActivity.this,SingleDeleteActivity.class));
            }
        });
    }

    public void getAllPhoto(){
        //String urls = "http://10.116.146.228/okhttp_api/photo_create/read_all_photo.php";
        String urls = "http://platinum007.000webhostapp.com/okhttp/LiveVersion/photo_create/read_all_photo.php";
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
                    SingleFileActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            json_string=str;
                            if(json_string==null){
                                Toast.makeText(SingleFileActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent intent=new Intent(SingleFileActivity.this,SingleReadActivity.class);
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
