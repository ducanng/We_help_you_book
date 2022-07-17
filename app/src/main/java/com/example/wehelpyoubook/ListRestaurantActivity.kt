package com.example.wehelpyoubook

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import com.example.wehelpyoubook.adapter.NearRestaurantAdapter
import com.example.wehelpyoubook.databinding.ActivityListRestaurantBinding
import com.example.wehelpyoubook.model.Restaurant
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

@SuppressLint("StaticFieldLeak")
val db = Firebase.firestore
class ListRestaurantActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListRestaurantBinding
    var resList = listOf<Restaurant>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get restaurant data from firestore
        val resDoc = com.example.wehelpyoubook.scrapingdata.db.collection("Restaurants")
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            resList = documentSnapshot.toObjects()
            binding.recyclerView.adapter =
                NearRestaurantAdapter(this@ListRestaurantActivity, resList) {
                        res -> println(res.name)
                }
        }
        binding.recyclerView.setHasFixedSize(true)

        val searchView = binding.restaurantSearchboxSearchview
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val results: ArrayList<Restaurant> = ArrayList()
                for (x in resList) {
                    if (x.name!!.contains(newText)) results.add(x)
                }
                binding.recyclerView.adapter =
                    NearRestaurantAdapter(this@ListRestaurantActivity, results) {
                            res -> println(res.name)
                    }
                return false
            }
        })
    }
}