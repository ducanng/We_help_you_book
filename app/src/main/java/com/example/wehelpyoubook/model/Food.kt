package com.example.wehelpyoubook.model

import com.example.wehelpyoubook.interfacecontrol.DataCenter

data class Food (
    val resId : String,
    val name : String? = null,
    val price : Int? = null,
    val urlImage : String? = null,
)
//    : DataCenter{
//    @Override
//    override fun getData() : Food {
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