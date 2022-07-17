package com.example.wehelpyoubook.homescreen


import android.annotation.SuppressLint
import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.*
import com.example.wehelpyoubook.accountcontrol.HomeSignInActivity
import com.example.wehelpyoubook.accountcontrol.info.UserInformationActivity
import com.example.wehelpyoubook.adapter.NearRestaurantAdapter
import com.example.wehelpyoubook.databinding.ActivityHomeBinding
import com.example.wehelpyoubook.model.Restaurant
import com.example.wehelpyoubook.restaurentInterface.ListRestaurantActivity
import com.example.wehelpyoubook.restaurentInterface.RestaurantInterfaceControl
import com.example.wehelpyoubook.scrapingdata.db
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

@SuppressLint("StaticFieldLeak")
val db = Firebase.firestore
private const val linkServer = "https://www.foody.vn/ho-chi-minh/food/dia-diem?q=nha+hang&ss=header_search_form&page="
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var resList = listOf<Restaurant>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

       //Scraping data from foody.vn
//        CoroutineScope(IO).launch {
////            val listRes = ScrapingData().restaurantScraping(linkServer)
////            ScrapingData().foodScraping(linkServer)
//            ScrapingData().reviewScraping(linkServer)
//        }
        // Show near restaurant
        val resDoc = db.collection("Restaurants")
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            resList = documentSnapshot.toObjects()
            binding.recyclerView.adapter =
                NearRestaurantAdapter(this@HomeActivity, resList) {
                    res -> val myIntent = Intent(this, RestaurantInterfaceControl::class.java)
                            myIntent.putExtra("resKey",res.resID)
                            startActivity(myIntent)
                }
        }

        binding.recyclerView.setHasFixedSize(true)

        binding.restaurantSearchboxSearchview.setOnSearchClickListener {
            startActivity(Intent(this,ListRestaurantActivity::class.java))
        }
        binding.voucherButton.setOnClickListener {
            startActivity(Intent(this, RestaurantInterfaceControl::class.java))
        }

//        binding.restaurantListButton.setOnClickListener {
//            startActivity(Intent(this, TestActivity::class.java))
//        }
        binding.userInformationButton.setOnClickListener {
            startActivity(Intent(this,HomeSignInActivity::class.java))
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
                startActivity(Intent(this, UserInformationActivity::class.java))
            } else {
                startActivity(Intent(this, HomeSignInActivity::class.java))
            }
            return@setOnMenuItemClickListener true
        }
        return true
    }
}