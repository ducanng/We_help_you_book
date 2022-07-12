package com.example.wehelpyoubook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter {

    ArrayList<TrangChu> items;
    public MyAdapter(Context context, int layout, ArrayList<TrangChu> items)
    {
        super(context,layout);
        this.items=items;
    }

    public void update(ArrayList<TrangChu> results)
    {
        items=new ArrayList<>();
        items.addAll(results);
        notifyDataSetChanged();
    }
    public class  ViewHolder{
        TextView textView;
        ImageView imageView;
    }
    @Override
    public int getCount(){
        return items.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row;
        row=convertView;
        ViewHolder viewHolder;
        if(row==null)
        {
            row= LayoutInflater.from(getContext()).inflate(R.layout.my_list_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.imageView=row.findViewById(R.id.androidimage);
            viewHolder.textView=row.findViewById(R.id.Versionname);
            row.setTag(viewHolder);


        }
        else
        {
            viewHolder = (ViewHolder) row.getTag();
        }
        viewHolder.imageView.setImageResource(items.get(position).image);
        viewHolder.textView.setText(items.get(position).Name);
        return row;

    }
}