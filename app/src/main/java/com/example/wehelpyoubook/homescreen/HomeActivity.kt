package com.example.wehelpyoubook.homescreen


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.wehelpyoubook.Feedback
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.TestActivity
import com.example.wehelpyoubook.accountcontrol.HomeSignInActivity
import com.example.wehelpyoubook.adapter.NearRestaurantAdapter

import com.example.wehelpyoubook.databinding.ActivityHomeBinding
import com.example.wehelpyoubook.interfacecontrol.DataCenter
import com.example.wehelpyoubook.model.Food
import com.example.wehelpyoubook.model.Restaurant
import com.example.wehelpyoubook.scrapingdata.ScrapingData
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.util.*
import kotlin.collections.ArrayList


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val myDataset = NearRestaurantData().loadNearRestaurant().toList()
//
//        binding.recyclerView.adapter = NearRestaurantAdapter(this,myDataset)
//        var listRes = ArrayList<Restaurant>()
//        // Scraping data from foody.vn

        CoroutineScope(IO).launch {
            var listRes =
                ScrapingData().restaurantScraping("https://www.foody.vn/ho-chi-minh/food/dia-diem?q=nha+hang&ss=header_search_form&page=")
            println(listRes.size)
            runOnUiThread(java.lang.Runnable {
                binding.recyclerView.adapter = NearRestaurantAdapter(this@HomeActivity, listRes)
            })
        }

        binding.recyclerView.setHasFixedSize(true)
        binding.restaurantListButton.setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
        }
        binding.userInformationButton.setOnClickListener {
            startActivity(Intent(this,HomeSignInActivity::class.java))
        }
        binding.feedbackButton.setOnClickListener {
            startActivity(Intent(this, Feedback::class.java))
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_home, menu)
        val homeLoginItem = menu.findItem(R.id.menu_home_login_item)

        homeLoginItem.setOnMenuItemClickListener {
            startActivity(Intent(this, HomeSignInActivity::class.java))
            return@setOnMenuItemClickListener true
        }
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return true when (item.itemId){
//            R.id.menu_home_item ->{}
//        }
//        return super.onOptionsItemSelected(item)
//    }
}