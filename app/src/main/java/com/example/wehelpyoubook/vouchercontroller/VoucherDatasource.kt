package com.example.wehelpyoubook.vouchercontroller

import android.content.ContentValues
import android.util.Log
import com.example.wehelpyoubook.R
import com.example.wehelpyoubook.model.Voucher
import com.example.wehelpyoubook.scrapingdata.db

class VoucherDatasource {

    fun getData(userId: String,resId : String) : List<Voucher>{
        var voucherList = mutableListOf<Voucher>()
        voucherList.add(Voucher("Voucher giảm 5%", R.drawable.avatar_whybook, 5,userId,resId))
        voucherList.add(Voucher("Voucher giảm 10%", R.drawable.avatar_whybook, 10,userId,resId))
        voucherList.add(Voucher("Voucher giảm 15%", R.drawable.avatar_whybook, 15,userId,resId))
        voucherList.add(Voucher("Voucher giảm 20%", R.drawable.avatar_whybook, 20,userId,resId))
        return voucherList
    }
    fun UpVoucherData(userId : String,resId : String){
        var voucherList = getData(userId,resId)
        for (v in voucherList) {
            var voucher = v
            db.collection("Vouchers")
                .add(
                    voucher
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