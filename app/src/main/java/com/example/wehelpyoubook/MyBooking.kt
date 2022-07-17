package com.example.wehelpyoubook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.wehelpyoubook.adapter.MyBookingAdapter
import com.example.wehelpyoubook.model.Orders
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

val db = Firebase.firestore
private const val TAG = "MyBooking"
class MyBooking : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.title = "My Booking"
        setContentView(R.layout.my_booking)

        // Initialize data.

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        var resDoc = db.collection("MyOrders")
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            val res = documentSnapshot.toObjects<Orders>()
            recyclerView.adapter = MyBookingAdapter(this, res)

        }
        recyclerView.setHasFixedSize(true)

    }

}
