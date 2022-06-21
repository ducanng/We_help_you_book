package com.example.wehelpyoubook

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class SearchableHomeActivity : AppCompatActivity() {
    private var LOG : String = "searchable_home"
    private fun doMySearch(query : String ){
        Log.v(LOG,query)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        if (Intent.ACTION_SEARCH == intent.action){
            intent.getStringExtra(SearchManager.QUERY)?.also{
                    query -> doMySearch(query)}
        }
    }
}