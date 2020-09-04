package com.example.okhttp_crud;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MultiFileUploadActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int ALL_FILE_REQUEST = 102;
    private static final int CAMERA_REQUEST = 103;
    private static final int GALLERY_REQUESR = 104;
    Button btnFromGallery, btnFromCamera, btnFromAllFile, btnUploadAll;
    TextView txtGallery, txtCamera, txtAllFile;
    ProgressBar progressBar;
    ImageView galleryImgPrev, cameraImgPrev;
    int method=0;

    String gallery_file_path, camera_file_path, all_file_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_file_upload);

        progressBar = findViewById(R.id.all_progressbar_id);
        galleryImgPrev = findViewById(R.id.galleryImage_id);
        cameraImgPrev = findViewById(R.id.cameraImage_id);

        btnFromGallery = findViewById(R.id.fromGallery_id);
        btnFromCamera = findViewById(R.id.fromCamera_id);
        btnFromAllFile = findViewById(R.id.fromAllFile_id);
        btnUploadAll = findViewById(R.id.uploadAllBtn_id);

        txtGallery = findViewById(R.id.txt_gallery_id);
        txtCamera = findViewById(R.id.txt_camera_id);
        txtAllFile = findViewById(R.id.txt_allfile_id);


        btnFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                method=2;
                if(Build.VERSION.SDK_INT>=23){
                    if(checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                        filePicker(2);
                    }
                    else{
                        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    }
                }
                else{
                    filePicker(2);
                }
            }
        });

        btnFromCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                method=1;
                if(Build.VERSION.SDK_INT>=23){
                    if(checkPermission(Manifest.permission.CAMERA)){
                        filePicker(1);
                    }
                    else{
                        requestPermission(Manifest.permission.CAMERA);
                    }
                }
                else{
                    filePicker(1);
                }
            }
        });

        btnFromAllFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                method=0;
                if(Build.VERSION.SDK_INT>=23){
                    if(checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                        filePicker(0);
                    }
                    else{
                        requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                    }
                }
                else{
                    filePicker(0);
                }
            }
        });

        btnUploadAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gallery_file_path==null){
                    Toast.makeText(MultiFileUploadActivity.this, "Gallery File Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(camera_file_path==null){
                    Toast.makeText(MultiFileUploadActivity.this, "Camera File Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(all_file_path==null){
                    Toast.makeText(MultiFileUploadActivity.this, "All File Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                UploadTasks uploadTasks=new UploadTasks();
                uploadTasks.execute(new String[]{gallery_file_path,camera_file_path,all_file_path});
            }
        });
    }

    private void filePicker(int i) {
        if(i==0){
            Intent intent=new Intent();
            intent.setType("*/*");
            intent.setAction(Intent.ACTION_PICK);
            startActivityForResult(Intent.createChooser(intent, "Choose File to Upload"),ALL_FILE_REQUEST);
        }
        if(i==1){
            Intent intent=new Intent();
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,CAMERA_REQUEST);
        }
        if(i==2){
            Intent intent=new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_PICK);
            startActivityForResult(intent,GALLERY_REQUESR);
        }
    }

    private boolean checkPermission(String permission){
        int result = ContextCompat.checkSelfPermission(MultiFileUploadActivity.this,permission);
        if(result== PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else{
            return false;
        }
    }

    private void requestPermission(String permission){
        if(ActivityCompat.shouldShowRequestPermissionRationale(MultiFileUploadActivity.this,permission)){
            Toast.makeText(this, "Please Allow Permission", Toast.LENGTH_SHORT).show();
        }
        else{
            ActivityCompat.requestPermissions(MultiFileUploadActivity.this,new String[]{permission},PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PERMISSION_REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission Successfully", Toast.LENGTH_SHORT).show();
                    filePicker(method);
                }
                else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            if(requestCode==GALLERY_REQUESR){
                if(data==null){
                    return;
                }
                Uri uri=data.getData();
                String selectedPath=FilePath.getFilePath(MultiFileUploadActivity.this,uri);
                Log.d("File path: "," "+selectedPath);
                if(selectedPath!=null){
                    txtGallery.setText(""+new File(selectedPath).getName());
                }
                Bitmap bitmap= BitmapFactory.decodeFile(selectedPath);
                galleryImgPrev.setImageBitmap(bitmap);
                gallery_file_path=selectedPath;
            }
            if(requestCode==CAMERA_REQUEST){
                if(data==null){
                    return;
                }
                //Save file to temp location
                Bitmap thumb=(Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes=new ByteArrayOutputStream();
                thumb.compress(Bitmap.CompressFormat.JPEG,100, bytes);
                File destination=new File(Environment.getExternalStorageDirectory(),"photo.jpg");
                if(destination.exists()){
                    destination.delete();
                }
                FileOutputStream out;
                try{
                    out=new FileOutputStream(destination);
                    out.write(bytes.toByteArray());
                    out.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
                Log.d("File path:"," "+destination.getPath());
                if(destination!=null){
                    txtCamera.setText(""+destination.getName());
                }
                cameraImgPrev.setImageBitmap(thumb);
                camera_file_path=destination.getPath();
            }

            if(requestCode==ALL_FILE_REQUEST){
                if(data==null){
                    return;
                }
                Uri uri=data.getData();
                String paths=FilePath.getFilePath(MultiFileUploadActivity.this,uri);
                Log.d("File path:"," "+paths);

                if(paths!=null){
                    txtAllFile.setText(""+new File(paths).getName());
                }
                all_file_path=paths;
            }
        }
    }
    public class UploadTasks extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s!=null){
                Toast.makeText(MultiFileUploadActivity.this, "File Uploaded", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MultiFileUploadActivity.this, "File Uploaded Failed", Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.GONE);
        }

        @Override
        protected String doInBackground(String... strings) {
            File file1=new File(strings[0]);
            File file2=new File(strings[1]);
            File file3=new File(strings[2]);

            try{
                RequestBody requestBody=new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("files1",file1.getName(),RequestBody.create(MediaType.parse("*/*"),file1))
                        .addFormDataPart("files2",file2.getName(),RequestBody.create(MediaType.parse("*/*"),file2))
                        .addFormDataPart("files3",file3.getName(),RequestBody.create(MediaType.parse("*/*"),file3))
                        .addFormDataPart("some_key","some_value")
                        .addFormDataPart("submit","submit")
                        .build();
                Request request2=new Request.Builder()
                        //.url("http://10.202.138.254/okhttp_api/multifile/multifile.php")
                        .url("http://platinum007.000webhostapp.com/okhttp/LiveVersion/multifile/multifile.php")
                        .post(requestBody)
                        .build();

                OkHttpClient okClient=new OkHttpClient();
                Response response2=okClient.newCall(request2).execute();
                if(response2!=null && response2.isSuccessful()){
                    return response2.body().string();
                }
                else{
                    return null;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
