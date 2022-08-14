package com.example.wehelpyoubook.vouchercontroller

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.adapter.VoucherAdapter
import com.example.wehelpyoubook.databinding.ActivityVoucherListBinding
import com.example.wehelpyoubook.model.User
import com.example.wehelpyoubook.model.Voucher
import com.example.wehelpyoubook.scrapingdata.db
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.ktx.Firebase

// This is VoucherListActivity for manager
class VoucherListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVoucherListBinding
    private var userId = ""
    private var manager : User = User()
    private var voucherDescription : String = ""
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

        val resDoc = db.collection("Users").whereEqualTo("id",userId)
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.toObjects<User>().isNotEmpty()){
                manager = documentSnapshot.toObjects<User>()[0]
                fetchVoucherList()
            }
        }
    }
    private fun fetchVoucherList(){
        val resDoc = db.collection("Vouchers").whereEqualTo("resId",manager.restaurantManager)
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            voucherList = documentSnapshot.toObjects()
            binding.recyclerView.adapter =
                VoucherAdapter(this@VoucherListActivity, voucherList) {
                        voucher,type ->  when (type) {
                    "Edit" -> {
                        var intent = Intent(this@VoucherListActivity, VoucherEdit::class.java)
                        intent.putExtra("resKey", manager.restaurantManager)
                        intent.putExtra("voucherDescription", voucher.description)
                        startActivity(intent)
                    }
                    "Remove" -> {
                        val resDoc = db
                            .collection("Vouchers")
                            .whereEqualTo("resId", manager.restaurantManager)
                            .whereEqualTo("description", voucher.description)
                        resDoc.get().addOnSuccessListener { documentSnapshot ->
                            if (documentSnapshot.documents.isNotEmpty()) {
                                var documentRemoveId = documentSnapshot.documents[0].id
                                db.collection("Vouchers").document(documentRemoveId).delete()
                            }
                            fetchVoucherList()
                        }
                    }

                }
                }
            addNewVoucher()
        }
        binding.recyclerView.setHasFixedSize(true)
    }
    private fun addNewVoucher(){
        binding.addVoucherBt.setOnClickListener(){
            var intent = Intent(this@VoucherListActivity, VoucherEdit::class.java)
            intent.putExtra("resKey", manager.restaurantManager)
            intent.putExtra("actionType", "add")
            startActivity(intent)
        }
    }
}