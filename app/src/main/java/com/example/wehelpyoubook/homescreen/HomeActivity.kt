package com.example.wehelpyoubook.homescreen


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.Feedback
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.TestActivity
import com.example.wehelpyoubook.accountcontrol.HomeSignInActivity
import com.example.wehelpyoubook.accountcontrol.info.UserInformationActivity
import com.example.wehelpyoubook.databinding.ActivityHomeBinding
import com.example.wehelpyoubook.scrapingdata.ScrapingData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    //An
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var user: FirebaseUser? = auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val myDataset = NearRestaurantData().loadNearRestaurant().toList()
//
//        binding.recyclerView.adapter = NearRestaurantAdapter(this,myDataset)

        // Scraping data from foody.vn
        val listRes = ScrapingData().restaurantScraping("https://www.foody.vn/ho-chi-minh/food/dia-diem?q=nha+hang&ss=header_search_form&page=")
        println( listRes.size)
        //An
        //get status user
        //auth = FirebaseAuth.getInstance()
        binding.recyclerView.setHasFixedSize(true)
        binding.restaurantListButton.setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
        }
        binding.userInformationButton.setOnClickListener {
            //An
            if (user != null) {
                startActivity(Intent(this,UserInformationActivity::class.java))
            } else {
                startActivity(Intent(this,HomeSignInActivity::class.java))
            }
        }
        binding.feedbackButton.setOnClickListener {
            startActivity(Intent(this, Feedback::class.java))
        }
    }
    @SuppressLint("NewApi")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_home, menu)
        val homeLoginItem = menu.findItem(R.id.menu_home_login_item)
        //An
        val homeLogoutItem = menu.findItem(R.id.menu_home_logout_item)
        val homeInfoItem = menu.findItem(R.id.menu_home_userinfo_item)
        val user = Firebase.auth.currentUser
        if (user != null) {
            homeLoginItem.isVisible = false
        } else  {
            homeLogoutItem.isVisible = false
        }
        homeLoginItem.setOnMenuItemClickListener {
            startActivity(Intent(this, HomeSignInActivity::class.java))
            return@setOnMenuItemClickListener true
        }
        //An
        homeLogoutItem.setOnMenuItemClickListener {
            Firebase.auth.signOut()
            this.recreate()
            return@setOnMenuItemClickListener true
        }
        homeInfoItem.setOnMenuItemClickListener {
            if (user != null) {
                startActivity(Intent(this,UserInformationActivity::class.java))
            } else {
                startActivity(Intent(this, HomeSignInActivity::class.java))
            }
            return@setOnMenuItemClickListener true
        }
//        val da  = DataCenter(Food("2","2",1,"2"))
//        val food = Food("2","2",1,"2")
//        food.Name
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return true when (item.itemId){
//            R.id.menu_home_item ->{}
//        }
//        return super.onOptionsItemSelected(item)
//    }
}