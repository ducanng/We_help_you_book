package com.example.wehelpyoubook.vouchercontroller

import android.content.ContentValues
import android.util.Log
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.model.Voucher
import com.example.wehelpyoubook.scrapingdata.db

class VoucherDatasource {

    private fun getData(userId: String, resId : String) : List<Voucher>{
        val voucherList = mutableListOf<Voucher>()
        voucherList.add(Voucher("Voucher giảm 5%", R.drawable.avatar_whybook, 5,userId,resId))
        voucherList.add(Voucher("Voucher giảm 10%", R.drawable.avatar_whybook, 10,userId,resId))
        voucherList.add(Voucher("Voucher giảm 15%", R.drawable.avatar_whybook, 15,userId,resId))
        voucherList.add(Voucher("Voucher giảm 20%", R.drawable.avatar_whybook, 20,userId,resId))
        return voucherList
    }
    fun UpVoucherData(userId : String,resId : String){
        val voucherList = getData(userId,resId)
        for (v in voucherList) {
            db.collection("Vouchers")
                .add(
                    v
                )
                .addOnSuccessListener { documentReference ->
                    Log.d(ContentValues.TAG, "Voucher added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error adding Voucher", e)
                }
        }
    }
}