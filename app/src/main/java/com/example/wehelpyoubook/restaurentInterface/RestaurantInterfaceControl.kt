package com.example.wehelpyoubook.restaurentInterface

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.adapter.FoodAdapter
import com.example.wehelpyoubook.adapter.ReviewAdapter
import com.example.wehelpyoubook.model.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
import java.util.*


val db = Firebase.firestore
class RestaurantInterfaceControl : AppCompatActivity() {
    private lateinit var reviewRecyclerView: RecyclerView
    private lateinit var reviewArrayList: ArrayList<Review>
    private lateinit var adapter: ReviewAdapter
    private lateinit var button: ImageButton
    private lateinit var comment: EditText
    private lateinit var resName: TextView

    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var foodAdapter: FoodAdapter
    private lateinit var foodArrayList: ArrayList<Food>
    private lateinit var yesBook: Button
    private lateinit var noBook: Button
    private lateinit var yesTimeDialogButton: Button

    //Declare button for booking
    private lateinit var buttonBook: ImageButton
    private var resID = ""
    private var userId = ""
    private lateinit var pd: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_resto)
        button = findViewById(R.id.sendButton)
        comment = findViewById(R.id.comment)
        reviewRecyclerView = findViewById(R.id.rvReviewRestaurant)
        foodRecyclerView = findViewById(R.id.rv_FoodList)
        pd = ProgressBar(this)

        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val review = comment.text.toString().trim()
                uploadComment(review)
            }
        })


        reviewRecyclerView.layoutManager = LinearLayoutManager(this)
        reviewRecyclerView.setHasFixedSize(true)

        reviewArrayList = arrayListOf()
        adapter = ReviewAdapter(this@RestaurantInterfaceControl, reviewArrayList)
        reviewRecyclerView.adapter = adapter

        // Get Intent from class RestaurantAdapter
        resID = intent.getStringExtra("resKey").toString()
        eventChangeListener(resID, 1)
        // Find res following resKey
        val resDoc = com.example.wehelpyoubook.scrapingdata.db
            .collection("Restaurants")
            .whereEqualTo("resID", resID)
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            val resData = documentSnapshot.toObjects<Restaurant>()
            if (resData.isNotEmpty()) {
                resName = findViewById(R.id.tvTitle)
                resName.text = resData[0].name
            }
        }

        foodRecyclerView.setHasFixedSize(true)
        foodArrayList = arrayListOf()
        foodCollectData(resID)
        foodAdapter = FoodAdapter(this@RestaurantInterfaceControl, foodArrayList)
        foodRecyclerView.adapter = foodAdapter

        bookingDialogAction()
    }


    private fun uploadComment(review: String) {
        val auth = Firebase.auth.currentUser
        if (auth == null) {
            Toast.makeText(
                this@RestaurantInterfaceControl,
                "You must login to write review",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        userId = auth!!.uid
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
                Toast.makeText(
                    this@RestaurantInterfaceControl,
                    "Review uploaded!!!",
                    Toast.LENGTH_SHORT
                ).show()
                eventChangeListener(resID, 2)
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    this@RestaurantInterfaceControl,
                    "Can't upload review",
                    Toast.LENGTH_SHORT
                ).show()
                Log.w(ContentValues.TAG, "Error adding review", e)
            }
    }

    private fun eventChangeListener(Id: String, type: Int) {
        reviewArrayList.clear()
        db.collection("Reviews").whereEqualTo("resId", Id)
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.e("FireStore error", error.message.toString())
                        return
                    }
                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            when (type) {
                                1 -> reviewArrayList.add(dc.document.toObject(Review::class.java))
                                2 -> reviewArrayList.add(
                                    0,
                                    dc.document.toObject(Review::class.java)
                                )
                            }
                        }
                    }
                    adapter.notifyDataSetChanged()
                }
            })
    }

    private fun foodCollectData(resId: String) {
        db.collection("Foods").whereEqualTo("resId", resId)
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.e("FireStore error", error.message.toString())
                        return
                    }
                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            foodArrayList.add(dc.document.toObject(Food::class.java))
                        }
                    }
                    foodAdapter.notifyDataSetChanged()
                }
            })

    }

    fun getBookingTime(time: String): String {
        return time.split(" ")[3]
    }

    fun bookingDialogAction(){
        val doc = com.example.wehelpyoubook.scrapingdata.db
            .collection("Vouchers")
            .whereEqualTo("userId", "1")
        doc.get().addOnSuccessListener { documentSnapshot ->
            val tmpData = documentSnapshot.toObjects<Voucher>()
            val listVoucherName = mutableListOf<String>()
            if (tmpData.isNotEmpty()) {
                for (item in tmpData){
                    listVoucherName.add(item.description.toString())
                }
            }
            buttonBook = findViewById(R.id.bookingButton)
            buttonBook.setOnClickListener {
                val view = View.inflate(this@RestaurantInterfaceControl, R.layout.booking_layout, null)
                val builder = AlertDialog.Builder(this@RestaurantInterfaceControl)
                builder.setView(view)
                val dialog = builder.create()
                dialog.show()
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                yesBook = view.findViewById(R.id.order)
                noBook = view.findViewById(R.id.no_order)
                yesBook.setOnClickListener {
                    dialog.dismiss()
                    chooseVoucher(listVoucherName)
                }
                noBook.setOnClickListener {
                    dialog.dismiss()
                }
            }

        }
    }
    fun showHourPicker(): String {
        val myCalender: Calendar = Calendar.getInstance()
        val hour: Int = myCalender.get(Calendar.HOUR_OF_DAY)
        val minute: Int = myCalender.get(Calendar.MINUTE)
        var res: String = ""
        val myTimeListener =
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                if (view.isShown) {
                    myCalender.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    myCalender.set(Calendar.MINUTE, minute)

                    res = getBookingTime(myCalender.time.toString())
                    UpOrder(res)
                }
            }

        val timePickerDialog = TimePickerDialog(
            this@RestaurantInterfaceControl,
            android.R.style.ThemeOverlay_Material_Dialog,
            myTimeListener,
            hour,
            minute,
            true
        )
        timePickerDialog.setTitle("Choose hour:")
        timePickerDialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        timePickerDialog.show()
        return res
    }

    fun UpOrder(time: String) {
        var order = Orders(
            userId,
            resID,
            time,
            "",
            "",
            ""
        )
        com.example.wehelpyoubook.scrapingdata.db.collection("MyOrders")
            .add(
                order
            )
            .addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "Orders added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding Orders", e)
            }
    }

    private fun chooseVoucher(voucherList : MutableList<String>) {
        // setup the alert builder
        val builder = AlertDialog.Builder(this@RestaurantInterfaceControl)
        builder.setTitle("Choose a voucher")

        builder.setSingleChoiceItems(voucherList.toTypedArray(), 0) { dialog, which ->
            // user checked an item
        }
        builder.setPositiveButton("OK") { dialog, which ->
            showHourPicker()
        }
        builder.setNegativeButton("Cancel", null)

        val dialog = builder.create()
        dialog.show()
    }

}




