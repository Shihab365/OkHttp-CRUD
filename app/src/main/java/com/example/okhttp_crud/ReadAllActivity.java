package com.example.okhttp_crud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadAllActivity extends AppCompatActivity {
    ListView listView;
    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ListViewAdapter listViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_all);
        listViewAdapter=new ListViewAdapter(this,R.layout.read_all_listview);
        listView = findViewById(R.id.listviewId);
        listView.setAdapter(listViewAdapter);
        json_string=getIntent().getExtras().getString("json_data");
        try {
            jsonObject=new JSONObject(json_string);
            jsonArray=jsonObject.getJSONArray("data");
            int count=0;
            String Product, Company, Address, Email, Phone;
            while (count<jsonArray.length()){
                JSONObject jo=jsonArray.getJSONObject(count);
                Product = jo.getString("Product");
                Company = jo.getString("Company");
                Address = jo.getString("Address");
                Email = jo.getString("Email");
                Phone = jo.getString("Phone");
                ProductDetails productDetails=new ProductDetails(Product,Company,Address,Email,Phone);
                listViewAdapter.add(productDetails);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
