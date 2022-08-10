package com.example.wehelpyoubook.restaurentInterface

import android.annotation.SuppressLint
<<<<<<< HEAD
<<<<<<< HEAD
import android.app.*
import android.content.ContentValues.TAG
import android.content.Context
=======
import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.ContentValues.TAG
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
<<<<<<< HEAD
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.adapter.FoodAdapter
import com.example.wehelpyoubook.adapter.ReviewAdapter
import com.example.wehelpyoubook.databinding.FragmentMyBookingBinding
import com.example.wehelpyoubook.model.*
import com.example.wehelpyoubook.mybooking.MyBookingAdapter
import com.example.wehelpyoubook.notification.NotificationActivity
=======
import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.adapter.FoodAdapter
import com.example.wehelpyoubook.adapter.ReviewAdapter
import com.example.wehelpyoubook.model.Food
import com.example.wehelpyoubook.model.Restaurant
import com.example.wehelpyoubook.model.Review
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.accountcontrol.HomeSignInActivity
import com.example.wehelpyoubook.adapter.FoodAdapter
import com.example.wehelpyoubook.adapter.ReviewAdapter
import com.example.wehelpyoubook.model.*
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.*

=======
import org.jsoup.select.Evaluator
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
import java.util.*
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7


@SuppressLint("StaticFieldLeak")
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
    private lateinit var yesBook: Button
    private lateinit var noBook: Button
    private lateinit var rating: TextView
    private lateinit var address: TextView
<<<<<<< HEAD
=======
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7


    //Declare button for booking
    private lateinit var buttonBook: ImageButton
    private var resID = ""
<<<<<<< HEAD
<<<<<<< HEAD
    private var userId = ""
    private lateinit var pd: ProgressBar


=======
    private lateinit var pd: ProgressBar
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
    private var userId = ""
    private lateinit var pd: ProgressBar

>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_resto)
        button = findViewById(R.id.sendButton)
        comment = findViewById(R.id.comment)
        reviewRecyclerView = findViewById(R.id.rvReviewRestaurant)
        foodRecyclerView = findViewById(R.id.rv_FoodList)
        pd = ProgressBar(this)
<<<<<<< HEAD

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        button.setOnClickListener(object : View.OnClickListener {
<<<<<<< HEAD
            override fun onClick(view: View?) {
                val review = comment.text.toString().trim()
                comment.setText("")
                if (review != "") {
                    uploadComment(review)
                }
            }
        })

        val auth = Firebase.auth.currentUser
        userId = auth!!.uid

=======
        buttonBook = findViewById(R.id.bookingButton)
        buttonBook.setOnClickListener {
            val view = View.inflate(this@RestaurantInterfaceControl,R.layout.booking_layout,null)
            val builder = AlertDialog.Builder(this@RestaurantInterfaceControl)
            builder.setView(view)
            val dialog = builder.create()
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            val yesBook = findViewById<Button>(R.id.order)
            yesBook.setOnClickListener(object:View.OnClickListener{
                override fun onClick(v: View?) {

                }
            })
        }

        button.setOnClickListener(object: View.OnClickListener {
=======
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
            override fun onClick(view: View?) {
                val review = comment.text.toString().trim()
                uploadComment(review)
            }
        })

<<<<<<< HEAD
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
        val auth = Firebase.auth.currentUser
        userId = auth!!.uid
        println(userId)
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7

        reviewRecyclerView.layoutManager = LinearLayoutManager(this)
        reviewRecyclerView.setHasFixedSize(true)

        reviewArrayList = arrayListOf()
<<<<<<< HEAD
<<<<<<< HEAD
        adapter = ReviewAdapter(this@RestaurantInterfaceControl, reviewArrayList)
=======
        adapter = ReviewAdapter(this@RestaurantInterfaceControl,reviewArrayList)
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
        adapter = ReviewAdapter(this@RestaurantInterfaceControl, reviewArrayList)
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
        reviewRecyclerView.adapter = adapter

        // Get Intent from class RestaurantAdapter
        resID = intent.getStringExtra("resKey").toString()
<<<<<<< HEAD
<<<<<<< HEAD
        eventChangeListener(resID, 1)
        // Find res following resKey
        val resDoc = com.example.wehelpyoubook.scrapingdata.db
            .collection("Restaurants")
            .whereEqualTo("resID", resID)
=======
        eventChangeListener(resID,1)
        // Find res following resKey
        val resDoc = com.example.wehelpyoubook.scrapingdata.db
            .collection("Restaurants")
            .whereEqualTo("resID",resID)
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
        eventChangeListener(resID, 1)
        // Find res following resKey
        val resDoc = com.example.wehelpyoubook.scrapingdata.db
            .collection("Restaurants")
            .whereEqualTo("resID", resID)
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            val resData = documentSnapshot.toObjects<Restaurant>()
            if (resData.isNotEmpty()) {
                resName = findViewById(R.id.tvTitle)
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
                rating = findViewById(R.id.ratingID)
                rating.text = resources?.getString(R.string.rate,resData[0].rate)
                address = findViewById(R.id.addressID)
                address.text = resData[0].address
<<<<<<< HEAD
=======
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
                resName.text = resData[0].name
            }
        }

        foodRecyclerView.setHasFixedSize(true)
        foodArrayList = arrayListOf()
        foodCollectData(resID)
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
        foodAdapter = FoodAdapter(this@RestaurantInterfaceControl, foodArrayList)
        foodRecyclerView.adapter = foodAdapter

        bookingDialogAction()
<<<<<<< HEAD
=======
        foodAdapter = FoodAdapter(this@RestaurantInterfaceControl,foodArrayList)
        foodRecyclerView.adapter = foodAdapter
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
    }


    private fun uploadComment(review: String) {
        val auth = Firebase.auth.currentUser
<<<<<<< HEAD
<<<<<<< HEAD
        userId = auth!!.uid
=======
        if (auth == null) {
            Toast.makeText(this@RestaurantInterfaceControl, "You must login to write review", Toast.LENGTH_SHORT).show()
            return
        }
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======
        userId = auth!!.uid
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
                Log.d(TAG, "User added with ID: ${documentReference.id}")
                Toast.makeText(
                    this@RestaurantInterfaceControl,
                    "Review uploaded!!!",
                    Toast.LENGTH_SHORT
                ).show()
                eventChangeListener(resID, 2)
<<<<<<< HEAD
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    this@RestaurantInterfaceControl,
                    "Can't upload review",
                    Toast.LENGTH_SHORT
                ).show()
                Log.w(TAG, "Error adding review", e)
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
            .whereEqualTo("resId", resID)
        doc.get().addOnSuccessListener { documentSnapshot ->
            val tmpData = documentSnapshot.toObjects<Voucher>()
            var listCurrentVoucher = mutableListOf<Voucher>()

            if (tmpData.isNotEmpty()) {
                for (item in tmpData){
                    val doc2 = com.example.wehelpyoubook.scrapingdata.db
                        .collection("UsedVouchers")
                        .whereEqualTo("userId", userId).whereEqualTo("resId", resID).whereEqualTo("description",item.description)
                    doc2.get().addOnSuccessListener { documentReference ->
                        val tmpList = documentReference.toObjects<UsedVoucher>()
                        if (tmpList.isEmpty()){
                            listCurrentVoucher.add((item))
                        }
                        buttonBook = findViewById(R.id.bookingButton)
                        buttonBook.setOnClickListener {
                            chooseVoucher(listCurrentVoucher)
                        }
                    }
                }
            }



        }
    }
    private fun showHourPicker(voucher: Voucher): String {
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
                    if (voucher.description == null) {
                        UpOrder(res, "")
                    }
                    else{
                        UpOrder(res, voucher.description!!)
                        uploadUsedVouchers(voucher)
                    }
                    Toast.makeText(
                        this@RestaurantInterfaceControl,
                        "Booking success",
                        Toast.LENGTH_SHORT
                    ).show()
                    bookingDialogAction()
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



    fun UpOrder(time: String,description: String) {
        var timeEnd = calcDifTime(time)
        var order = Orders(
            userId,
            resID,
            time,
            timeEnd,
            "",
            description
        )

        createNotificationChannel()
        sendNotify(userId, resID,time,timeEnd,description)
        com.example.wehelpyoubook.scrapingdata.db.collection("MyOrders")
            .add(
                order
            )
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Orders added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding Orders", e)
            }
    }

    private fun calcDifTime(tmpStr: String): String {
        if(tmpStr == ""){
            val res = tmpStr
            return res
        }
        val currentString = tmpStr
        val separated = currentString.split(":").toMutableList()

        //val content = "Time ending: "
        if (separated[0] == "23"){
            separated[0] = "-1"
        }
        val difHour = separated[0].toInt() + 1
        val difMin = separated[1]
        val difSec = separated[2]
        val res = (difHour).toString() + " giờ " + difMin + " phút " + difSec + " giây"

        return res
    }

    private fun chooseVoucher(listVoucher: List<Voucher>) {
        // setup the alert builder
        val listVoucherName = mutableListOf<String>()
        listVoucherName.add("Không")
        for (item in listVoucher) {
            listVoucherName.add(item.description.toString())
        }
        val builder = AlertDialog.Builder(this@RestaurantInterfaceControl)
        builder.setTitle("Choose a voucher")
        var order : Int = 0
        builder.setSingleChoiceItems(listVoucherName.toTypedArray(), 0) { dialog, which ->
            order = which
        }
        builder.setPositiveButton("OK") { dialog, which ->
            if (order == 0){
                showHourPicker(Voucher())
            }
            else{
                order -= 1
                showHourPicker(listVoucher[order])
            }
        }
        builder.setNegativeButton("Cancel", null)

        val dialog = builder.create()
        dialog.show()
    }
    private fun uploadUsedVouchers(voucher : Voucher) {
        var usedVoucher = UsedVoucher(
            voucher.description,
            voucher.imageUrl,
            voucher.percentage,
            userId,
            voucher.resId,
        )
        com.example.wehelpyoubook.scrapingdata.db.collection("UsedVouchers")
            .add(
                usedVoucher
            )
    }
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@RestaurantInterfaceControl, MainActivity::class.java))
        return super.onSupportNavigateUp()
    }
    private val CHANNEL_ID = "1234"
    private val notificationId = 101

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Bug012"
            val descriptionText = "Bug 0 and 1 and 2"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private var orderList = listOf<Orders>()

    private fun sendNotify(userId: String, resID: String,time: String,
                           timeEnd: String,description:String){

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

        val name: String = resName.text.toString()
        val intent = Intent(this, NotificationActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent
            .getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_notifications)
            .setContentTitle("Booking successfully")
            //.setContentText("Time booking: " + time + "\n" + timeEnd + "\n" + description)
//            .setContentText(timeEnd)
//            .setContentText(description)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(name + "\n"
                        + "Time booking: " + time + "\n"
                        + "Time ending: "+ timeEnd + "\n"
                        + description))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, builder.build())
        }
    }
}





=======
                Log.d(ContentValues.TAG, "User added with ID: ${documentReference.id}")
                Toast.makeText(this@RestaurantInterfaceControl, "Review uploaded!!!", Toast.LENGTH_SHORT).show()
                eventChangeListener(resID,2)
=======
>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
            }
            .addOnFailureListener { e ->
                Toast.makeText(
                    this@RestaurantInterfaceControl,
                    "Can't upload review",
                    Toast.LENGTH_SHORT
                ).show()
                Log.w(TAG, "Error adding review", e)
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
            .whereEqualTo("userId", userId)
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
                    removeVoucher(10)
                    Toast.makeText(
                        this@RestaurantInterfaceControl,
                        "Booking success",
                        Toast.LENGTH_SHORT
                    ).show()

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
        println(userId)
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
                Log.d(TAG, "Orders added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding Orders", e)
            }
    }

    private fun chooseVoucher(voucherList : MutableList<String>) {
        // setup the alert builder
        val builder = AlertDialog.Builder(this@RestaurantInterfaceControl)
        builder.setTitle("Choose a voucher")
        builder.setSingleChoiceItems(voucherList.toTypedArray(), 0) { dialog, which ->

        }
        builder.setPositiveButton("OK") { dialog, which ->
            showHourPicker()
        }
        builder.setNegativeButton("Cancel", null)

        val dialog = builder.create()
        dialog.show()
    }
    fun getIDColection(s : String) : String{
        if (s.isNotEmpty()) {
            var tmp = s.split(",")[0]
            val listRes : List<String> = tmp.split("/")
                if (listRes.size > 1)
                    return listRes[1]
        }
        return ""
    }
    fun removeVoucher(percent : Int) {
        val doc = com.example.wehelpyoubook.scrapingdata.db
            .collection("Vouchers")
            .whereEqualTo("userId", userId).whereEqualTo("percentage", percent)
        doc.get().addOnSuccessListener { documentReference ->
            if  (documentReference.documents.toString() != "") {
                val idColection = getIDColection(documentReference.documents.toString())
                if (idColection.isNotEmpty()) {
                    db.collection("Vouchers").document(idColection)
                        .delete()
                }
            }
        }
    }
    private fun foodCollectData(resId: String)  {
        db.collection("Foods").whereEqualTo("resId", resId).addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error!=null){
                    Log.e("FireStore error",error.message.toString())
                    return
                }
                for (dc : DocumentChange in value?.documentChanges!!){
                    if (dc.type == DocumentChange.Type.ADDED){
                        foodArrayList.add(dc.document.toObject(Food::class.java))
                    }
                }
                foodAdapter.notifyDataSetChanged()
            }
        })

    }

}


<<<<<<< HEAD
>>>>>>> d3a1e2e87bf0def9abbbaba35558de4ed77c9544
=======



>>>>>>> dbd45e2a8c65157d53125cb85dcf374f0a3e40d7
