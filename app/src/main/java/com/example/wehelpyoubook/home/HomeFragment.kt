package com.example.wehelpyoubook.home

<<<<<<< HEAD
=======
import android.annotation.SuppressLint
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
<<<<<<< HEAD
=======
import com.example.wehelpyoubook.accountcontrol.HomeSignInActivity
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
import com.example.wehelpyoubook.adapter.NearRestaurantAdapter
import com.example.wehelpyoubook.databinding.FragmentHomeBinding
import com.example.wehelpyoubook.model.Restaurant
import com.example.wehelpyoubook.restaurentInterface.ListRestaurantActivity
import com.example.wehelpyoubook.restaurentInterface.RestaurantInterfaceControl
import com.example.wehelpyoubook.scrapingdata.ScrapingData
import com.example.wehelpyoubook.scrapingdata.db
<<<<<<< HEAD
import com.google.firebase.firestore.ktx.toObjects
<<<<<<< HEAD

=======
import com.example.wehelpyoubook.vouchercontroller.VoucherListActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

@SuppressLint("StaticFieldLeak")
val db = Firebase.firestore
private const val linkServer = "https://www.foody.vn/ho-chi-minh/food/dia-diem?q=nha+hang&ss=header_search_form&page="
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
private const val linkServer = "https://www.foody.vn/ho-chi-minh/food/dia-diem?q=nha+hang&ss=header_search_form&page="
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private var resList = listOf<Restaurant>()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.getContext()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

<<<<<<< HEAD
=======
        //Scraping data from foody.vn
//                CoroutineScope(IO).launch {
//        //            val listRes = ScrapingData().restaurantScraping(linkServer)
//        //            ScrapingData().foodScraping(linkServer)
//                    ScrapingData().reviewScraping(linkServer)
//                }
        // Show near restaurant
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
        val resDoc = db.collection("Restaurants")
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            resList = documentSnapshot.toObjects()
            binding.recyclerView.adapter =
                context?.let {
                    NearRestaurantAdapter(it, resList) {
                            res -> val myIntent = Intent(context, RestaurantInterfaceControl::class.java)
                        myIntent.putExtra("resKey",res.resID)
                        startActivity(myIntent)
                    }
                }
        }
        binding.recyclerView.setHasFixedSize(true)

        binding.restaurantSearchboxSearchview.setOnSearchClickListener {
            startActivity(Intent(context,ListRestaurantActivity::class.java))
        }
<<<<<<< HEAD
=======
        binding.voucherButton.setOnClickListener {
            startActivity(Intent(context, VoucherListActivity::class.java))
        }

        binding.userInformationButton.setOnClickListener {
            startActivity(Intent(context,HomeSignInActivity::class.java))
        }
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


