package com.example.okhttp_crud;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SingleCreatedActivity extends AppCompatActivity {
    EditText editPhotoName, editPhotoDescr;
    Button btnSelectPhoto ,btnSubmit;
    TextView photoPrev;
    ProgressBar progressBar;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int REQUEST_GALLERY = 200;
    String file_path = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_created);

        editPhotoName = findViewById(R.id.etPhotoNameId);
        editPhotoDescr = findViewById(R.id.etPhotoDescrId);
        btnSubmit = findViewById(R.id.btnPhotoSubmitId);
        btnSelectPhoto = findViewById(R.id.btnSinglePhotoId);
        progressBar = findViewById(R.id.progressbarID);
        photoPrev = findViewById(R.id.textPhotoPrevId);

        btnSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=23){
                    if(checkPermission()){
                        filePicker();
                    }
                    else{
                        requestPermission();
                    }
                }
                else{
                    filePicker();
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(file_path!=null){
                    UploadFile();
                }
                else{
                    Toast.makeText(SingleCreatedActivity.this, "Please Select Photo First", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void UploadFile() {
        UploadTask uploadTask=new UploadTask();
        uploadTask.execute(new String[]{file_path});
    }

    private void filePicker(){
        //Toast.makeText(this, "File picker called", Toast.LENGTH_SHORT).show();
        Intent openGallery = new Intent(Intent.ACTION_PICK);
        openGallery.setType("image/*");
        startActivityForResult(openGallery, REQUEST_GALLERY);
    }

    private void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(SingleCreatedActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            Toast.makeText(this, "Please Give Permission to Upload File.", Toast.LENGTH_SHORT).show();
        }
        else{
            ActivityCompat.requestPermissions(SingleCreatedActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(SingleCreatedActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE);
        if(result== PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission Successful", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "Permission Failed", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_GALLERY && resultCode== Activity.RESULT_OK){
            String filePath = getRealPathFromUri(data.getData(), SingleCreatedActivity.this);
            this.file_path=filePath;
            File file=new File(filePath);
            photoPrev.setText(file.getName());
        }
    }

    private String getRealPathFromUri(Uri uri, Activity activity){
        Cursor cursor = activity.getContentResolver().query(uri,null,null,null,null);
        if(cursor==null){
            return  uri.getPath();
        }
        else{
            cursor.moveToNext();
            int id = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(id);
        }
    }
    public class UploadTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);
            if(s.equalsIgnoreCase("true")){
                Toast.makeText(SingleCreatedActivity.this, "File Uploaded", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(SingleCreatedActivity.this, "File Uploaded Failed", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            if(uploadFile(strings[0])){
                return "true";
            }
            else{
                return "failed";
            }
        }

        private boolean uploadFile(String path){
            String strName = editPhotoName.getText().toString();
            String strDescr = editPhotoDescr.getText().toString();
            File file=new File(path);
            try{
                RequestBody requestBody=new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("files",file.getName(),RequestBody.create(MediaType.parse("image/*"),file))
                        .addFormDataPart("some key","some value")
                        .addFormDataPart("submit","submit")
                        .addFormDataPart("Name", strName)
                        .addFormDataPart("Description", strDescr)
                        .build();
                Request request=new Request.Builder()
                        //.url("http://10.124.81.77/okhttp_api/photo_create/image_insert.php")
                        .url("http://platinum007.000webhostapp.com/okhttp/LiveVersion/photo_create/image_insert.php")
                        .post(requestBody)
                        .build();
                OkHttpClient client=new OkHttpClient();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                    }
                });
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
    }
}
