package com.example.wehelpyoubook


import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var reviewArrayList: ArrayList<Review>
    private lateinit var adapter: Adapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_resto)

        recyclerView = findViewById(R.id.rvReviewRestaurant)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        reviewArrayList = arrayListOf()
        adapter = Adapter(reviewArrayList)
        recyclerView.adapter = adapter

        eventChangeListener()


    }

    private fun eventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("Reviews").addSnapshotListener(object : EventListener<QuerySnapshot>{
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error!=null){
                    Log.e("FireStore error",error.message.toString())
                    return
                }
                for (dc : DocumentChange in value?.documentChanges!!){
                    if (dc.type == DocumentChange.Type.ADDED){

                        reviewArrayList.add(dc.document.toObject(Review::class.java))

                    }
                }
                adapter.notifyDataSetChanged()
            }
        })
    }
}