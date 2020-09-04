package com.example.okhttp_crud;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListViewPhotoAdapter extends ArrayAdapter {

    List list=new ArrayList();
    public ListViewPhotoAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void add(PhotoDetailsAll object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row;
        row=convertView;
        final ListViewPhotoAdapter.PhotoHolder photoHolder;
        if(row==null){
            LayoutInflater inflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.all_photo_read_row,parent,false);
            photoHolder=new ListViewPhotoAdapter.PhotoHolder();

            photoHolder.txtName=(TextView)row.findViewById(R.id.rowNameId);
            photoHolder.txtDescr=(TextView)row.findViewById(R.id.rowDescriptionId);
            photoHolder.imageView=(ImageView) row.findViewById(R.id.rowImageId);
            row.setTag(photoHolder);
        }
        else{
            photoHolder=(ListViewPhotoAdapter.PhotoHolder) row.getTag();
        }
        PhotoDetailsAll photoDetailsAll=(PhotoDetailsAll) this.getItem(position);

        photoHolder.txtName.setText(photoDetailsAll.getName());
        photoHolder.txtDescr.setText(photoDetailsAll.getDescription());
        //photoHolder.imageView.setText(photoDetailsAll.getPhoto());

        //String path1 = "http://10.116.146.228/okhttp_api/photo_create/";
        String path1 = "http://platinum007.000webhostapp.com/okhttp/LiveVersion/photo_create/";
        String path2 = photoDetailsAll.getPhoto();
        String imgPath = path1+path2;
        Picasso.with(getContext()).load(imgPath).into(photoHolder.imageView);

        return row;
    }

    static class PhotoHolder{
        ImageView imageView;
        TextView txtName,txtDescr;
    }
}
