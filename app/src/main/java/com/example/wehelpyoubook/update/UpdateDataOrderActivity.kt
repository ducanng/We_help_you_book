package com.example.wehelpyoubook.update

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.accountcontrol.HomeSignInActivity
import com.example.wehelpyoubook.databinding.ActivityUpdateDataBinding
import com.example.wehelpyoubook.home.HomeFragment
import com.example.wehelpyoubook.model.Orders
import com.example.wehelpyoubook.mybooking.MyBookingFragment
import com.example.wehelpyoubook.mybooking.db
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

class UpdateData : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateDataBinding
    private lateinit var documentID : String
    var customerId : String = ""
    var resId : String = ""
    var time : String = ""
    var voucherDes : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        getData()
        binding.updateBtn.setOnClickListener {

            updateData()
            startActivity(Intent(this,MyBookingFragment::class.java))
        }

        binding.cancelBtn.setOnClickListener {
            CancelOrder()
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun getData() {
        resId = intent.getStringExtra("resKey").toString()
        var customer = Firebase.auth.currentUser
        db.collection("MyOrders")
            .whereEqualTo("resID", resId)
            .whereEqualTo("userId", customer!!.uid)
            .get()
            .addOnSuccessListener { documentSnap ->
                if(documentSnap.documents.isNotEmpty()) {
                    documentID = documentSnap.documents[0].id
                    var order = documentSnap.toObjects<Orders>()[0]
                    binding.timeBooking.setText(order.timeBooking)
                    binding.voucher.setText(order.voucher)
                }
            }
    }
    private fun updateData(){
        db.collection("MyOrders").document(documentID).delete()
        db.collection("MyOrders").add{
            Orders(
                customerId,
                resId,
                binding.timeBooking.toString(),
                "",
                "",
                binding.voucher.toString()
            )
        }
    }

    private fun CancelOrder() {
        resId = intent.getStringExtra("resKey").toString()
        customerId = intent.getStringExtra("cusKey").toString()
        time = intent.getStringExtra("timeKey").toString()
        voucherDes = intent.getStringExtra("voucherKey").toString()

        var customer = Firebase.auth.currentUser
        println(resId)
        println(customer!!.uid)
        db.collection("MyOrders")
            .whereEqualTo("resID", resId)
            .whereEqualTo("userId", customerId)
            .whereEqualTo("timeBooking", time)
            .whereEqualTo("voucher", voucherDes)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.documents.isNotEmpty()) {
                    println("thay")
                    var documentRemoveId = documentSnapshot.documents[0].id
                    db.collection("MyOrders").document(documentRemoveId).delete()
                        Toast.makeText(this, "Cancel Successfully", Toast.LENGTH_SHORT).show()
                }
            }
    }
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, MyBookingFragment::class.java))
        return super.onSupportNavigateUp()
    }
}
