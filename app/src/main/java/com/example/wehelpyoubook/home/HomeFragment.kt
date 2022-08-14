package com.example.wehelpyoubook.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.example.wehelpyoubook.adapter.NearRestaurantAdapter
import com.example.wehelpyoubook.databinding.FragmentHomeBinding
import com.example.wehelpyoubook.model.Restaurant
import com.example.wehelpyoubook.restaurentInterface.RestaurantInterfaceControl
import com.example.wehelpyoubook.scrapingdata.db
import com.google.firebase.firestore.ktx.toObjects
import java.text.Normalizer
import java.util.*
import java.util.regex.Pattern

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
        searchingBarHandle()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun searchingBarHandle(){
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
                    NearRestaurantAdapter(context!!, results) {
                            res -> val myIntent = Intent(context, RestaurantInterfaceControl::class.java)
                        myIntent.putExtra("resKey",res.resID)
                        startActivity(myIntent)
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


