package com.example.wehelpyoubook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView=findViewById(R.id.myList);
        ArrayList<TrangChu> items=new ArrayList<>();
        items.add(new TrangChu("Restaurent",R.drawable.image1));
        items.add(new TrangChu("Voucher",R.drawable.avatar_whybook));

        listView.setAdapter(new MyAdapter(MainActivity.this,R.layout.my_list_item,items));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    Intent intent=new Intent();

                    intent.setClass(MainActivity.this,List_Restaurent.class);
                    startActivity(intent);
                }
                if(position==1)
                {
                    Intent intent=new Intent();

                    intent.setClass(MainActivity.this,List_Voucher.class);
                    startActivity(intent);
                }
            }
        });


    }
}