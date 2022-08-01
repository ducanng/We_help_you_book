package com.example.wehelpyoubook.vouchercontroller

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wehelpyoubook.MainActivity
import com.example.wehelpyoubook.adapter.VoucherAdapter
import com.example.wehelpyoubook.databinding.ActivityVoucherListBinding
import com.example.wehelpyoubook.model.Voucher
import com.google.firebase.firestore.ktx.toObjects


class VoucherListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVoucherListBinding
    var voucherList = listOf<Voucher>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoucherListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //Get voucher list data from firestore
        val resDoc = com.example.wehelpyoubook.scrapingdata.db.collection("Vouchers")
        resDoc.get().addOnSuccessListener { documentSnapshot ->
            voucherList = documentSnapshot.toObjects()
            binding.recyclerView.adapter =
                VoucherAdapter(this@VoucherListActivity, voucherList) {
                        voucher -> println(voucher.description)
                }
        }
        binding.recyclerView.setHasFixedSize(true)
    }
    // Awaken backing button
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this@VoucherListActivity,MainActivity::class.java))
        return super.onSupportNavigateUp()
    }
}