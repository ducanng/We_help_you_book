package com.example.wehelpyoubook.vouchercontroller

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.databinding.ActivityVoucherEditBinding
import com.example.wehelpyoubook.model.Voucher
import com.example.wehelpyoubook.scrapingdata.db
import com.google.firebase.firestore.ktx.toObjects

class VoucherEdit : AppCompatActivity() {
    private lateinit var binding : ActivityVoucherEditBinding
    private var resId : String = ""
    private var voucherDes : String = ""
    private var voucherImUrl : String = ""
    private var voucherPercentage : String = ""
    private var documentID : String = ""
    private var actionType : String = "edit"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoucherEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getVoucherData()

        binding.voucherEditDescription.addTextChangedListener{
        }
        binding.voucherEditSale.addTextChangedListener{
        }
        binding.voucherEditImage.addTextChangedListener{
        }
        binding.voucherEditChangeButton.setOnClickListener{
            voucherInputHandle()
            when(actionType) {
                "add" -> {
                    db.collection("Vouchers").add(
                        Voucher(
                            binding.voucherEditDescription.text.toString(),
                            binding.voucherEditImage.text.toString().toInt(),
                            binding.voucherEditSale.text.toString().toInt(),
                            "",
                            resId
                        )
                    )
                    Toast.makeText(
                        this@VoucherEdit,
                        "Thêm thành công !!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                //edit
                else ->{
                    db.collection("Vouchers").document(documentID).delete()
                    db.collection("Vouchers").add(
                        Voucher(
                            binding.voucherEditDescription.text.toString(),
                            binding.voucherEditImage.text.toString().toInt(),
                            binding.voucherEditSale.text.toString().toInt(),
                            "",
                            resId
                        )
                    )
                    Toast.makeText(
                        this@VoucherEdit,
                        "Cập nhật thành công !!!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            startActivity(Intent(this@VoucherEdit,VoucherListActivity::class.java))
        }
        binding.voucherEditCancelButton.setOnClickListener(){
            startActivity(Intent(this@VoucherEdit,VoucherListActivity::class.java))
        }
    }
    private fun getVoucherData(){
        resId = intent.getStringExtra("resKey").toString()
        actionType= intent.getStringExtra("actionType").toString()
        voucherDes = intent.getStringExtra("voucherDescription").toString()

        when(actionType) {
            "add" -> {
                voucherImUrl = R.drawable.avatar_whybook.toString()
            }
            else ->
            {
                val resDoc = db
                    .collection("Vouchers")
                    .whereEqualTo("resId", resId)
                    .whereEqualTo("description", voucherDes)
                resDoc.get().addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.documents.isNotEmpty()) {
                        documentID = documentSnapshot.documents[0].id
                        val voucher = documentSnapshot.toObjects<Voucher>()
                        if (voucher.isNotEmpty()) {
                            binding.voucherEditDescription.setText(voucher[0].description.toString())
                            binding.voucherEditSale.setText(voucher[0].percentage.toString())
                            voucherImUrl = voucher[0].imageUrl.toString()
                        }
                    }
                }
            }
        }
    }
    private fun voucherInputHandle(){
        voucherPercentage = binding.voucherEditSale.text.toString()
        if (voucherPercentage == ""){
            voucherPercentage = "5"
            binding.voucherEditSale.setText(voucherPercentage)
            voucherPercentage
        }
        binding.voucherEditDescription.setText("Voucher giảm $voucherPercentage%")
        if (binding.voucherEditImage.text.toString() == "") {
            binding.voucherEditImage.setText(voucherImUrl)
        }
    }
}