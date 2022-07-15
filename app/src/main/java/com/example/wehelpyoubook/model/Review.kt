package com.example.wehelpyoubook.model

import com.example.wehelpyoubook.interfacecontrol.DataCenter

data class Review(
    val resId : String? = null,
    val useId : String? = null,
    val description : String? = null
)
//    : DataCenter {
//    @Override
//    override fun getData() : Review {
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