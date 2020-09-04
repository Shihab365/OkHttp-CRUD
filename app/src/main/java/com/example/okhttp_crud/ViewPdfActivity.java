package com.example.okhttp_crud;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ViewPdfActivity extends AppCompatActivity {
    PDFView pdfView;
    String sessionId, AllFile;
    //final String urls = "http://10.232.131.247/okhttp_api/multifile/read_pdf_file.php";
    final String urls = "http://platinum007.000webhostapp.com/okhttp/LiveVersion/multifile/read_pdf_file.php";
    InputStream inputStream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);

        pdfView = findViewById(R.id.pdfViewId);
        sessionId = getIntent().getStringExtra("pdfUrl");
        new ReadPdfClass().execute(sessionId);
    }

    public class ReadPdfClass extends AsyncTask<String, Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            String strSearch = strings[0];
            String path3=null;
            OkHttpClient okHttpClient= new OkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("ID", strSearch)
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
                    AllFile = employee.getString("AllFile");

                    //String path1 = "http://10.232.131.247/okhttp_api/multifile/";
                    String path1 = "http://platinum007.000webhostapp.com/okhttp/LiveVersion/multifile/";
                    String path2 = AllFile;
                    path3 = path1+path2;
                    inputStream=new URL(path3).openStream();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream ip3) {
            pdfView.fromStream(ip3).load();
        }
    }
}
