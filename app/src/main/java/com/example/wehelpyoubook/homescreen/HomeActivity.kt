package com.example.wehelpyoubook.homescreen


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.TestActivity
import com.example.wehelpyoubook.accountcontrol.HomeSignInActivity
import com.example.wehelpyoubook.accountcontrol.LoginActivity
import com.example.wehelpyoubook.adapter.NearRestaurantAdapter
import com.example.wehelpyoubook.data.NearRestaurantData
import com.example.wehelpyoubook.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myDataset = NearRestaurantData().loadNearRestaurant()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter = NearRestaurantAdapter(this,myDataset)
        recyclerView.setHasFixedSize(true)
        binding.restaurantListButton.setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
        }
        binding.userInformationButton.setOnClickListener {
            startActivity(Intent(this,HomeSignInActivity::class.java))
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