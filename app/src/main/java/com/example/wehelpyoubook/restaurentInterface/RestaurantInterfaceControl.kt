package com.example.wehelpyoubook.restaurentInterface

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.adapter.ReviewAdapter
import com.example.wehelpyoubook.model.Restaurant
import com.example.wehelpyoubook.model.Review
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

val db = Firebase.firestore
class RestaurantInterfaceControl : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var reviewArrayList: ArrayList<Review>
    private lateinit var adapter: ReviewAdapter
    private lateinit var button: ImageButton
    private lateinit var comment: EditText
    private lateinit var resName: TextView
    private var resID = ""
    private lateinit var pd: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_resto)
        button = findViewById(R.id.sendButton)
        comment = findViewById(R.id.comment)
        recyclerView = findViewById(R.id.rvReviewRestaurant)
        pd = ProgressBar(this)

        val toolbar = findViewById<View>(R.id.toolbar)
        toolbar.setOnClickListener {
            startActivity(Intent(this@RestaurantInterfaceControl,MainActivity::class.java))
        }

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
        resID = intent.getStringExtra("resKey").toString()
        eventChangeListener(resID,1)

        val resDoc = com.example.wehelpyoubook.scrapingdata.db
            .collection("Restaurants")
            .whereEqualTo("resID",resID)
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            val resData = documentSnapshot.toObjects<Restaurant>()
            if (resData.isNotEmpty()) {
                resName = findViewById(R.id.tvTitle)
                resName.text = resData[0].name
            }
        }
    }

    private fun uploadComment(review: String) {
        val auth = Firebase.auth.currentUser
        if (auth == null) {
            Toast.makeText(this@RestaurantInterfaceControl, "You must login to write review", Toast.LENGTH_SHORT).show()
            return
        }
        val review = Review(
            resID,
            auth!!.uid,
            review
        )
        com.example.wehelpyoubook.accountcontrol.user.db.collection("Reviews")
            .add(
                review
            )
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "User added with ID: ${documentReference.id}")
                Toast.makeText(this@RestaurantInterfaceControl, "Review uploaded!!!", Toast.LENGTH_SHORT).show()
                eventChangeListener(resID,2)
            }
            .addOnFailureListener { e ->
                Toast.makeText(this@RestaurantInterfaceControl, "Can't upload review", Toast.LENGTH_SHORT).show()
                Log.w(ContentValues.TAG, "Error adding review", e)
            }
    }

    private fun eventChangeListener(Id: String,type :Int) {
        reviewArrayList.clear()
        db.collection("Reviews").whereEqualTo("resId",Id).addSnapshotListener(object : EventListener<QuerySnapshot> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error!=null){
                    Log.e("FireStore error",error.message.toString())
                    return
                }
                for (dc : DocumentChange in value?.documentChanges!!){
                    if (dc.type == DocumentChange.Type.ADDED){
                        when(type){
                            1 -> reviewArrayList.add(dc.document.toObject(Review::class.java))
                            2 -> reviewArrayList.add(0,dc.document.toObject(Review::class.java))
                        }
                    }
                }
                adapter.notifyDataSetChanged()
            }
        })
    }
}


