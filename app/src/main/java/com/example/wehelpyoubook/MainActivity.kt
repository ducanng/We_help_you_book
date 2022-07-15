package com.example.wehelpyoubook


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

import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var reviewArrayList: ArrayList<Review>
    private lateinit var adapter: Adapter
    private lateinit var db: FirebaseFirestore
    private lateinit var sbutton: ImageButton
    private lateinit var bookButton: ImageButton
    private lateinit var comment: EditText

    private lateinit var pd: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_resto)
        sbutton = findViewById(R.id.sendButton)
        comment = findViewById(R.id.comment)
        recyclerView = findViewById(R.id.rvReviewRestaurant)
        pd = ProgressBar(this)
        db = FirebaseFirestore.getInstance()
        bookButton = findViewById(R.id.bookingButton)


        //Review
        sbutton.setOnClickListener(object: View.OnClickListener {
           override fun onClick(view: View?) {
               val review = comment.text.toString().trim()
               uploadComment(review)
            }
        })

        //BOOKING


        bookButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View?) {
                Booking()
            }
        })


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        reviewArrayList = arrayListOf()
        adapter = Adapter(reviewArrayList)
        recyclerView.adapter = adapter
        eventChangeListener()

    }

    private fun uploadComment(review: String) {
        val id = UUID.randomUUID().toString()
        val res = (0..10000).random().toString()
        val use = (0..10000).random().toString()
        val data = hashMapOf("description" to review,
                                "resId" to res,
                                "useId" to use)
        val docRef = db.collection("Reviews").document(id).set(data)
        docRef.addOnCompleteListener { docRef ->
            Toast.makeText(this@MainActivity, "Review uploaded!!!", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener { e ->
                Toast.makeText(this@MainActivity, "Can't upload review", Toast.LENGTH_SHORT).show()
            }
    }
//val urlImage : String? = null,
//    var name: String? = null,
//    var timeBooking: String? = null,
//    var timeEnd: String? = null,
//    var order: String? = null
    private fun Booking(){
        val id = UUID.randomUUID().toString()
        val url = ""
        val name = ""
        val timeS = ""
        val timeE = ""
        val or = ""
        val data = hashMapOf("urlImage" to url,
            "name" to name,
            "timeBooking" to timeS,
            "timeEnd" to timeE,
            "order" to or)
        val docRef = db.collection("MyOrders").document(id).set(data)
        docRef.addOnCompleteListener { docRef ->
            Toast.makeText(this@MainActivity, "Booking...", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener { e ->
                Toast.makeText(this@MainActivity, "Can't book!!!", Toast.LENGTH_SHORT).show()
            }
    }

    private fun eventChangeListener() {
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


