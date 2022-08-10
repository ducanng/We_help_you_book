package com.example.wehelpyoubook.mybooking

<<<<<<< HEAD
<<<<<<< HEAD
import android.annotation.SuppressLint
import android.content.Intent
=======
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
import android.annotation.SuppressLint
import android.content.Intent
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
<<<<<<< HEAD
<<<<<<< HEAD
import com.example.wehelpyoubook.adapter.NearRestaurantAdapter
import com.example.wehelpyoubook.databinding.FragmentMyBookingBinding
import com.example.wehelpyoubook.model.Orders
import com.example.wehelpyoubook.model.User
import com.example.wehelpyoubook.restaurentInterface.ListRestaurantActivity
import com.example.wehelpyoubook.restaurentInterface.RestaurantInterfaceControl
import com.example.wehelpyoubook.update.UpdateData
import com.google.firebase.auth.ktx.auth
=======
import androidx.lifecycle.ViewModelProvider
=======
import com.example.wehelpyoubook.adapter.NearRestaurantAdapter
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
import com.example.wehelpyoubook.databinding.FragmentMyBookingBinding
import com.example.wehelpyoubook.model.Orders
<<<<<<< HEAD
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
import com.example.wehelpyoubook.restaurentInterface.ListRestaurantActivity
import com.example.wehelpyoubook.restaurentInterface.RestaurantInterfaceControl
import com.example.wehelpyoubook.update.UpdateData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

<<<<<<< HEAD
<<<<<<< HEAD

@SuppressLint("StaticFieldLeak")
val db = Firebase.firestore

class MyBookingFragment : Fragment() {
    private var orderList = listOf<Orders>()
    private var _binding: FragmentMyBookingBinding? = null
    private var userId = ""
    private var manager : User = User()
=======
=======

@SuppressLint("StaticFieldLeak")
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
val db = Firebase.firestore

class MyBookingFragment : Fragment() {
    private var orderList = listOf<Orders>()
    private var _binding: FragmentMyBookingBinding? = null
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
<<<<<<< HEAD
<<<<<<< HEAD
        //ViewModelProvider(this).get(FeedbackViewModel::class.java)
=======
        ViewModelProvider(this).get(FeedbackViewModel::class.java)
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
        //ViewModelProvider(this).get(FeedbackViewModel::class.java)
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7

        // Initialize data.

        _binding = FragmentMyBookingBinding.inflate(inflater, container, false)
        val root: View = binding.root

<<<<<<< HEAD

<<<<<<< HEAD
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
        println("Thay")
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


=======
=======
        var customer = Firebase.auth.currentUser
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
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
        //binding.recyclerView.setHasFixedSize(true)



        return root
    }
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
