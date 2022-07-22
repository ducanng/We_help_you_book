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

public class List_Voucher extends AppCompatActivity {

    ArrayList<TrangChu> items=new ArrayList<>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_voucher);

        listView=findViewById(R.id.myList);

        items.add(new TrangChu("Voucher giảm 500k",R.drawable.avatar_whybook,"500"));
        items.add(new TrangChu("Voucher giảm 200k",R.drawable.avatar_whybook,"200"));
        items.add(new TrangChu("Voucher giảm 100k",R.drawable.avatar_whybook,"100"));
        items.add(new TrangChu("Voucher giảm 300k",R.drawable.avatar_whybook,"300"));
        items.add(new TrangChu("Voucher giảm 400k",R.drawable.avatar_whybook,"400"));

        //items.add(new AndroidVersion("Voucher",R.drawable.avatar_whybook));

        listView.setAdapter(new MyAdapter(List_Voucher.this,R.layout.my_list_item,items));
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                if(position==2)
                {
                    Intent intent=new Intent();

                    intent.setClass(MainActivity.this,List_Voucher.class);
                    startActivity(intent);
                }
                if(position==3)
                {
                    Intent intent=new Intent();

                    intent.setClass(MainActivity.this,List_Voucher.class);
                    startActivity(intent);
                }
                if(position==4)
                {
                    Intent intent=new Intent();

                    intent.setClass(MainActivity.this,List_Voucher.class);
                    startActivity(intent);
                }

            }
        });
        */
    }

    /*public static String removeAccent(String s) {
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
    */

}