package com.example.okhttp_crud;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter {
    List list=new ArrayList();
    public ListViewAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void add(ProductDetails object) {
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
        ProductHolder productHolder;
        if(row==null){
            LayoutInflater inflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.read_all_listview,parent,false);
            productHolder=new ProductHolder();
            productHolder.txtPro=(TextView)row.findViewById(R.id.listviewProductId);
            productHolder.txtCom=(TextView)row.findViewById(R.id.listviewCompanyId);
            productHolder.txtAdd=(TextView)row.findViewById(R.id.listviewAddressId);
            productHolder.txtEml=(TextView)row.findViewById(R.id.listviewEmailId);
            productHolder.txtPhn=(TextView)row.findViewById(R.id.listviewPhoneId);
            row.setTag(productHolder);
        }
        else{
            productHolder=(ProductHolder) row.getTag();
        }
        ProductDetails productDetails=(ProductDetails) this.getItem(position);
        productHolder.txtPro.setText(productDetails.getProduct());
        productHolder.txtCom.setText(productDetails.getCompany());
        productHolder.txtAdd.setText(productDetails.getAddress());
        productHolder.txtEml.setText(productDetails.getEmail());
        productHolder.txtPhn.setText(productDetails.getPhone());
        return row;
    }

    static class ProductHolder{
        TextView txtPro,txtCom,txtAdd,txtEml,txtPhn;
    }
}
