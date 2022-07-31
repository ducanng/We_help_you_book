package com.example.wehelpyoubook.vouchercontroller

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.adapter.VoucherAdapter
import com.example.wehelpyoubook.databinding.ActivityVoucherListBinding
import com.example.wehelpyoubook.model.User
import com.example.wehelpyoubook.model.Voucher
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

// This is VoucherListActivity for manager
class VoucherListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVoucherListBinding
    private var userId = ""
    private var manager : User = User()
    var voucherList = listOf<Voucher>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoucherListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //Get voucher list data from firestore
        getMangedRestaurantId()
    }
    // Awaken backing button
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@VoucherListActivity,MainActivity::class.java))
        return super.onSupportNavigateUp()
    }
    private fun getMangedRestaurantId() {
        val auth = Firebase.auth.currentUser
        userId = auth!!.uid

        val resDoc = com.example.wehelpyoubook.scrapingdata.db.collection("Users").whereEqualTo("id",userId)
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.toObjects<User>().isNotEmpty()){
                manager = documentSnapshot.toObjects<User>()[0]
                fetchVoucherList()
            }
        }
    }
    private fun fetchVoucherList(){
        val resDoc = com.example.wehelpyoubook.scrapingdata.db.collection("Vouchers").whereEqualTo("resId",manager.restaurantManager)
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            voucherList = documentSnapshot.toObjects()
            println(voucherList.size)
            binding.recyclerView.adapter =
                VoucherAdapter(this@VoucherListActivity, voucherList) {
                        voucher -> println(voucher.description)
                }
        }
        binding.recyclerView.setHasFixedSize(true)
    }
}