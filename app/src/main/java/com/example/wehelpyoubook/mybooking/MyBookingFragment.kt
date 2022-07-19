package com.example.wehelpyoubook.mybooking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.wehelpyoubook.databinding.FragmentMyBookingBinding
import com.example.wehelpyoubook.feedback.FeedbackViewModel
import com.example.wehelpyoubook.model.Orders
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

val db = Firebase.firestore
class MyBookingFragment : Fragment() {
    private var orderList = listOf<Orders>()
    private var _binding: FragmentMyBookingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ViewModelProvider(this).get(FeedbackViewModel::class.java)

        // Initialize data.

        _binding = FragmentMyBookingBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val resDoc = db.collection("MyOrders")
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            orderList = documentSnapshot.toObjects()
            println(orderList.size)
            binding.recyclerView.adapter = MyBookingAdapter(this, orderList){
                    res-> println("hello")
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}