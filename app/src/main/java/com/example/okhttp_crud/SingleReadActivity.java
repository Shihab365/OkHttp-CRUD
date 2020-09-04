package com.example.okhttp_crud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SingleReadActivity extends AppCompatActivity {

    ListView listView;
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ListViewPhotoAdapter listViewPhotoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_read);
        listViewPhotoAdapter=new ListViewPhotoAdapter(this,R.layout.all_photo_read_row);
        listView = findViewById(R.id.listviewPhotoId);
        listView.setAdapter(listViewPhotoAdapter);
        json_string=getIntent().getExtras().getString("json_data");
        try {
            jsonObject=new JSONObject(json_string);
            jsonArray=jsonObject.getJSONArray("data");
            int count=0;
            String Name, Description, Photo;
            while (count<jsonArray.length()){
                JSONObject jo=jsonArray.getJSONObject(count);
                Name = jo.getString("Name");
                Description = jo.getString("Description");
                Photo = jo.getString("Photo");
                PhotoDetailsAll photoDetailsAll=new PhotoDetailsAll(Name, Description, Photo);
                listViewPhotoAdapter.add(photoDetailsAll);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
