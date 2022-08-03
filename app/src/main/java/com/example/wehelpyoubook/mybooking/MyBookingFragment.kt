package com.example.wehelpyoubook.mybooking

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wehelpyoubook.adapter.NearRestaurantAdapter
import com.example.wehelpyoubook.databinding.FragmentMyBookingBinding
import com.example.wehelpyoubook.model.Orders
import com.example.wehelpyoubook.model.User
import com.example.wehelpyoubook.restaurentInterface.ListRestaurantActivity
import com.example.wehelpyoubook.restaurentInterface.RestaurantInterfaceControl
import com.example.wehelpyoubook.update.UpdateData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase


@SuppressLint("StaticFieldLeak")
val db = Firebase.firestore

class MyBookingFragment : Fragment() {
    private var orderList = listOf<Orders>()
    private var _binding: FragmentMyBookingBinding? = null
    private var userId = ""
    private var manager : User = User()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //ViewModelProvider(this).get(FeedbackViewModel::class.java)

        // Initialize data.

        _binding = FragmentMyBookingBinding.inflate(inflater, container, false)
        val root: View = binding.root


        //binding.recyclerView.setHasFixedSize(true)

        getManagerRestaurantId()

        return root
    }
    private fun getUserRestaurantId() {
        var customer = Firebase.auth.currentUser
        val resDoc = db.collection("MyOrders")
        resDoc.whereEqualTo("userId",customer!!.uid).get().addOnSuccessListener { documentSnapshot ->
            orderList = documentSnapshot.toObjects()
            binding.recyclerView.adapter =
                context?.let {
                    MyBookingAdapter(this, orderList) {
                            order -> val myIntent = Intent(context, UpdateData::class.java)
                        myIntent.putExtra("resKey",order.resID)
                        myIntent.putExtra("cusKey",order.userId)
                        myIntent.putExtra("timeKey",order.timeBooking)
                        myIntent.putExtra("voucherKey",order.voucher)
                        startActivity(myIntent)
                    }
                }
        }
    }

    private fun getManagerRestaurantId() {
        val auth = Firebase.auth.currentUser
        userId = auth!!.uid

        val resDoc = com.example.wehelpyoubook.scrapingdata.db.collection("Users")
            .whereEqualTo("id",userId)
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.toObjects<User>().isNotEmpty()){
                manager = documentSnapshot.toObjects<User>()[0]
                if(manager!!.role == "customer"){
                    getUserRestaurantId()

                }
                else if(manager.role == "manager"){
                    fetchOrderList()
                }

            }
        }
    }
    private fun fetchOrderList() {
        val resDoc = com.example.wehelpyoubook.scrapingdata.db.collection("MyOrders")
            .whereEqualTo("resID", manager.restaurantManager)
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            orderList = documentSnapshot.toObjects()
            binding.recyclerView.adapter =
                MyBookingAdapter(this, orderList) {
                        order ->
                }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}