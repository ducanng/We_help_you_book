package com.example.wehelpyoubook.homescreen


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.Feedback
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.TestActivity
import com.example.wehelpyoubook.accountcontrol.HomeSignInActivity
import com.example.wehelpyoubook.adapter.NearRestaurantAdapter
import com.example.wehelpyoubook.databinding.ActivityHomeBinding
import com.example.wehelpyoubook.scrapingdata.ScrapingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

private const val linkServer = "https://www.foody.vn/ho-chi-minh/food/dia-diem?q=nha+hang&ss=header_search_form&page="
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      // Scraping data from foody.vn
        CoroutineScope(IO).launch {
            val listRes = ScrapingData()
                .restaurantScraping(linkServer)
            ScrapingData().foodScraping(linkServer)
            ScrapingData().reviewScraping(linkServer)
            runOnUiThread {
                binding.recyclerView.adapter = NearRestaurantAdapter(this@HomeActivity, listRes)
            }
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
    
}