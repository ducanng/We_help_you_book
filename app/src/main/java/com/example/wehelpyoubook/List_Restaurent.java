package com.example.wehelpyoubook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class List_Restaurent extends AppCompatActivity {

    ArrayList<TrangChu> items=new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_restaurent);

        listView=findViewById(R.id.myList);

        items.add(new TrangChu("VietNam",R.drawable.image1));
        items.add(new TrangChu("Singapore",R.drawable.image2));
        items.add(new TrangChu("ThaiLand",R.drawable.image3));
        items.add(new TrangChu("Canada",R.drawable.image4));
        items.add(new TrangChu("Lao",R.drawable.image5));

        //items.add(new AndroidVersion("Voucher",R.drawable.avatar_whybook));

        listView.setAdapter(new MyAdapter(List_Restaurent.this,R.layout.my_list_item,items));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        MenuItem menuItem=menu.findItem(R.id.searchMenu);
        SearchView searchView= (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<TrangChu> results=new ArrayList<>();
                for(TrangChu x: items)
                {
                    if(x.Name.contains(newText))
                        results.add(x);
                }

                ((MyAdapter)listView.getAdapter()).update(results);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}