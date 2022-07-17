package com.example.wehelpyoubook

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wehelpyoubook.adapter.ReviewAdapter
import com.example.wehelpyoubook.model.Review
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import java.util.*

class RestaurantInterfaceControl : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var reviewArrayList: ArrayList<Review>
    private lateinit var adapter: ReviewAdapter
    private lateinit var db: FirebaseFirestore
    private lateinit var button: ImageButton
    private lateinit var comment: EditText

    private lateinit var pd: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_resto)
        button = findViewById(R.id.sendButton)
        comment = findViewById(R.id.comment)
        recyclerView = findViewById(R.id.rvReviewRestaurant)
        pd = ProgressBar(this)
        db = FirebaseFirestore.getInstance()

        button.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View?) {
                val review = comment.text.toString().trim()
                uploadComment(review)
            }
        })


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        reviewArrayList = arrayListOf()
        adapter = ReviewAdapter(this@RestaurantInterfaceControl,reviewArrayList)
        recyclerView.adapter = adapter

        // Get Intent from class RestaurantAdapter
        val resID = intent.getStringExtra("resKey").toString()
        eventChangeListener(resID)
    }

    private fun uploadComment(review: String) {
        val id = UUID.randomUUID().toString()
        val res = (0..10000).random().toString()
        val use = (0..10000).random().toString()
        val data = hashMapOf("description" to review,
            "resId" to res,
            "useId" to use)
        val docRef = db.collection("Reviews").document(id).set(data)
        docRef.addOnCompleteListener {
            Toast.makeText(this@RestaurantInterfaceControl, "Review uploaded!!!", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(this@RestaurantInterfaceControl, "Can't upload review", Toast.LENGTH_SHORT).show()
            }
    }

    private fun eventChangeListener(Id: String) {
        db.collection("Reviews").whereEqualTo("resId",Id).addSnapshotListener(object : EventListener<QuerySnapshot> {
            @SuppressLint("NotifyDataSetChanged")
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


