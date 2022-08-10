package com.example.wehelpyoubook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

public class List_Restaurent extends AppCompatActivity {

    ArrayList<TrangChu> items=new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_restaurent);

        listView=findViewById(R.id.myList);

        items.add(new TrangChu("VietNam",R.drawable.image1,null));
        items.add(new TrangChu("Singapore",R.drawable.image2,null));
        items.add(new TrangChu("ThaiLand",R.drawable.image3,null));
        items.add(new TrangChu("Canada",R.drawable.image4,null));
        items.add(new TrangChu("Lao",R.drawable.image5,null));

        //items.add(new AndroidVersion("Voucher",R.drawable.avatar_whybook));

        listView.setAdapter(new MyAdapter(List_Restaurent.this,R.layout.my_list_item,items));
    }
    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        temp=pattern.matcher(temp).replaceAll("");
        return temp.replaceAll("đ", "d");
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
                String searchStr=removeAccent(newText.toLowerCase());
                for(TrangChu x: items)
                {
                    if(removeAccent(x.Name.toLowerCase()).contains(searchStr))
                        results.add(x);
                }

                ((MyAdapter)listView.getAdapter()).update(results);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }
}