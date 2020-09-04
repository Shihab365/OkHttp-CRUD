package com.example.okhttp_crud;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.json.JSONObject;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ReadImageActivity extends AppCompatActivity {
    EditText editSrcPhoto;
    Button btnSearch, btnBack;
    TextView textPrevSrc;
    ImageView photoResult;
    String Photo, Name;
    //final String urls = "http://10.119.149.153/okhttp_api/photo_create/single_photo_read.php";
    final String urls = "http://platinum007.000webhostapp.com/okhttp/LiveVersion/photo_create/single_photo_read.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_image);

        editSrcPhoto = findViewById(R.id.srcPhotoId);
        btnSearch = findViewById(R.id.btnSrcPhotoId);
        btnBack = findViewById(R.id.btnSrcPhotoBackId);
        textPrevSrc = findViewById(R.id.textYouSrcId);
        photoResult = findViewById(R.id.imgSrsResultId);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSearch = editSrcPhoto.getText().toString();
                new getSinglePhoto().execute(strSearch);
            }
        });
    }

    public class getSinglePhoto extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String strSearch = strings[0];
            OkHttpClient okHttpClient= new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("Name", strSearch)
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
                    Name = employee.getString("Name");
                    Photo = employee.getString("Photo");

                    //String path1 = "http://10.119.149.153/okhttp_api/photo_create/";
                    String path1 = "http://platinum007.000webhostapp.com/okhttp/LiveVersion/photo_create/";
                    final String path2 = path1+Photo;
                    Handler uiHandler = new Handler(Looper.getMainLooper());
                    uiHandler.post(new Runnable(){
                        @Override
                        public void run() {
                            Picasso.with(ReadImageActivity.this).load(path2).into(photoResult);
                            textPrevSrc.setText("You Search: "+Name);
                        }
                    });
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
