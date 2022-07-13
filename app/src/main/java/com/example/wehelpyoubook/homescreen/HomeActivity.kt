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
import com.example.wehelpyoubook.model.Restaurant
import com.example.wehelpyoubook.scrapingdata.db
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

val db = Firebase.firestore
private const val linkServer = "https://www.foody.vn/ho-chi-minh/food/dia-diem?q=nha+hang&ss=header_search_form&page="
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

//    fun eventChangeListener() =
//        runBlocking {
//
//            async {
//                val resList = ArrayList<Restaurant>()
//                db = FirebaseFirestore.getInstance()
//                db.collection("Restaurants")
//                    .addSnapshotListener(object : EventListener<QuerySnapshot> {
//                        override fun onEvent(
//                            value: QuerySnapshot?,
//                            error: FirebaseFirestoreException?
//                        ) {
//                            if (error != null) {
//                                Log.e("FireStore error", error.message.toString())
//                                return
//                            }
//                            for (dc: DocumentChange in value?.documentChanges!!) {
//                                if (dc.type == DocumentChange.Type.ADDED) {
//                                    resList.add(dc.document.toObject(Restaurant::class.java))
//                                }
//                            }
//                        }
//
//                    })
//                resList
//            }.await()
//        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

      // Scraping data from foody.vn
//        CoroutineScope(IO).launch {
//            val listRes = ScrapingData().restaurantScraping(linkServer)
//            ScrapingData().foodScraping(linkServer)
//            ScrapingData().reviewScraping(linkServer)
////            runOnUiThread {
////                binding.recyclerView.adapter = NearRestaurantAdapter(this@HomeActivity, eventChangeListener())
////            }
//        }
        var resDoc = db.collection("Restaurants")
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            val res = documentSnapshot.toObjects<Restaurant>()
            binding.recyclerView.adapter =
                NearRestaurantAdapter(this@HomeActivity, res)
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