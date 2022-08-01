package com.example.wehelpyoubook.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wehelpyoubook.adapter.NearRestaurantAdapter
import com.example.wehelpyoubook.databinding.FragmentHomeBinding
import com.example.wehelpyoubook.model.Restaurant
import com.example.wehelpyoubook.restaurentInterface.ListRestaurantActivity
import com.example.wehelpyoubook.restaurentInterface.RestaurantInterfaceControl
import com.example.wehelpyoubook.scrapingdata.ScrapingData
import com.example.wehelpyoubook.scrapingdata.db
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
private const val linkServer = "https://www.foody.vn/ho-chi-minh/food/dia-diem?q=nha+hang&ss=header_search_form&page="
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
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


