package com.example.wehelpyoubook.model

import androidx.annotation.DrawableRes
import com.example.wehelpyoubook.interfacecontrol.DataCenter

data class Restaurant (
    val resID: String,
    val name: String? = null,
    val rate: String? = null,
    val address: String? = null,
    val imageUrl: String? = null,
//    val listReview : MutableSet<Review> = mutableSetOf<Review>()
)
//    : DataCenter{
//    @Override
//    override fun getData() : Restaurant {
//        return this
//    }
//    override fun insertData() : Boolean{
//        return true
//    }
//    override fun deleteData() : Boolean{
//        return true
//    }
//    override fun updateData() : Boolean{
//        return true
//    }
//}

