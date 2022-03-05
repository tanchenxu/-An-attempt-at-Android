package com.akuya.akuya_listview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<Bean> data;
    private Context context;

    public MyAdapter(List<Bean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {

        return data.size();
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
         convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.tv);
        textView.setText(data.get(position).getName());

        Log.e("akuya", "getView: "+ position );
        return convertView;
    }
}
