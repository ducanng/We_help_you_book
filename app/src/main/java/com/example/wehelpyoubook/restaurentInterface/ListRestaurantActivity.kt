package com.example.wehelpyoubook.restaurentInterface

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.wehelpyoubook.adapter.NearRestaurantAdapter
import com.example.wehelpyoubook.databinding.ActivityListRestaurantBinding
import com.example.wehelpyoubook.model.Restaurant
import com.google.firebase.firestore.ktx.toObjects
import java.text.Normalizer
import java.util.*
import java.util.regex.Pattern

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
                        res -> val myIntent = Intent(this, RestaurantInterfaceControl::class.java)
                        myIntent.putExtra("resKey",res.resID)
                        startActivity(myIntent)
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
                val searchStr = removeAccent(newText.lowercase(Locale.getDefault()))
                for (x in resList) {
                    if (removeAccent(x.name?.lowercase()).contains(searchStr)) results.add(x)
                }
                binding.recyclerView.adapter =
                    NearRestaurantAdapter(this@ListRestaurantActivity, results) {
                            res -> println(res.name)
                    }
                return false
            }
        })
    }
    fun removeAccent(s: String?): String {
        var temp = Normalizer.normalize(s, Normalizer.Form.NFD)
        val pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+")
        temp = pattern.matcher(temp).replaceAll("")
        return temp.replace("đ".toRegex(), "d")
    }
}